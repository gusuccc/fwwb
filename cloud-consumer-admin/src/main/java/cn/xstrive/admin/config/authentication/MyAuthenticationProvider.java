package cn.xstrive.admin.config.authentication;


import cn.xstrive.admin.exception.VerificationCodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

//这包中的三个类用于实现验证码功能的！！
@Component
public class MyAuthenticationProvider extends DaoAuthenticationProvider {
    //这个类提供的就是一个认证过程
    // 构造方法注入UserDetailService和PasswordEncoder
/*    @Autowired
    private PasswordEncoder passwordEncoder;

    @Qualifier("adminServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;*/

    public MyAuthenticationProvider(@Qualifier("adminServiceImpl") UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {

        this.setUserDetailsService(userDetailsService);
        this.setPasswordEncoder(passwordEncoder);
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        Object detail = usernamePasswordAuthenticationToken.getDetails();
       // System.out.println("===>"+detail);
        WebAuthenticationDetails detailss = (WebAuthenticationDetails)detail;
        //System.out.println(detailss);
        MWebAuthenticationDetails details = (MWebAuthenticationDetails) detailss;
        String imageCode = details.getImageCode();
        String savedImageCode = details.getSavedImageCode();
        // 检验图形验证码逻辑
/*        System.out.println("========>验证的imagecode为"+imageCode);
        System.out.println("========>savedImageCode为"+savedImageCode);
        System.out.println("验证结果为imageCode.equals(savedImageCode)="+imageCode.equals(savedImageCode));*/
        if (StringUtils.isEmpty(imageCode) || StringUtils.isEmpty(savedImageCode) || !imageCode.equals(savedImageCode)) {
            System.out.println("抛出异常！");   //为什么抛出异常了还登陆？好像是又调用了一次验证密码的，然后通过了。。但是为什么又会调用，原因不详。。
            throw new VerificationCodeException();
        }
     //   System.out.println("==========================>继续执行！");
        //调用父类方法完成密码验证
        super.additionalAuthenticationChecks(userDetails, usernamePasswordAuthenticationToken);
    }

}
