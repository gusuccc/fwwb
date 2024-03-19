package cn.xstrive.admin.config;

import cn.xstrive.admin.handler.MyForwardAuthenticationFailureHandler;
import cn.xstrive.admin.handler.MyForwardAuthenticationSuccessHandler;
import cn.xstrive.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;        //因为配置文件中引入了所以可以自动注入

    @Autowired
    AdminService adminService;

    @Autowired
    AuthenticationProvider authenticationProvider;
    @Autowired
    AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> myWebAuthenticationDetailsSource;
    //以上两个局部变量实现的是验证码功能

    @Bean       //别忘记加入到容器中去
    public PersistentTokenRepository persistentTokenRepository() {        //记住我功能
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        //jdbcTokenRepository.setCreateTableOnStartup(true); //在初始化时创建表,这个不应该每次都开着，否则每次都存在，就会报错
        return jdbcTokenRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(adminService).passwordEncoder(password());       //调用服务以及加密功能，写了下面那行 这里就不应该加了
        auth.authenticationProvider(authenticationProvider);        //应用authenticationProvider
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**","/fonts/**","/images/**","/js/**");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();         //解决iframe问题
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/login").permitAll();

        http.authorizeRequests().antMatchers("/admin/**").hasAnyRole("ADMIN","SYSADMIN")
                                .antMatchers("/login/**").permitAll()
                                .antMatchers("/actuator/**").permitAll()
                                .antMatchers("/cap.jpg/**").permitAll()
                                .anyRequest().authenticated()          //任何请求都必须经过认证（登陆）才可以访问
                .and()
                .formLogin()
                .authenticationDetailsSource(myWebAuthenticationDetailsSource) //验证码功能
                .loginPage("/login").permitAll()
                .loginProcessingUrl("/login")   //loginProcessingUrl必须要配置成myLogin.html中的action(post)提交界面并放行,这个controller由框架实现
                .usernameParameter("username")
                .passwordParameter("password")
                //.successForwardUrl("/success")
                //.failureForwardUrl("/fail");
                .successHandler(new MyForwardAuthenticationSuccessHandler())
                .failureHandler(new MyForwardAuthenticationFailureHandler("/fail"))
                .and().rememberMe().userDetailsService(adminService).tokenRepository(persistentTokenRepository()).tokenValiditySeconds(60*60*24);    //记住我功能
        http.csrf().disable();
    }
}
