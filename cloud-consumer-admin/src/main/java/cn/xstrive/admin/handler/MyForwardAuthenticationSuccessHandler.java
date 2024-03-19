package cn.xstrive.admin.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

//这个模仿ForwardAuthenticationSuccessHandler来写，这里先和他一模一样，因为实际开发中用自己的更多
@Slf4j
public class MyForwardAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private String forwardUrl;

    /*    public MyForwardAuthenticationSuccessHandler(String forwardUrl) {
            Assert.isTrue(UrlUtils.isValidRedirectUrl(forwardUrl), () -> "'" + forwardUrl + "' is not a valid forward URL");     //检查url是否合法
            this.forwardUrl = forwardUrl;
        }*/
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        User loginUser = (User) authentication.getPrincipal();
        Collection<GrantedAuthority> authorities = loginUser.getAuthorities();
        forwardUrl = "/success";
        log.info("登陆成功！");
        response.sendRedirect(forwardUrl);
    }
}
