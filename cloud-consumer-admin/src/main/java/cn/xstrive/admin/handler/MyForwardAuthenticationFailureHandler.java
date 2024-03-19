package cn.xstrive.admin.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyForwardAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private final String forwardUrl;

    /**
     * @param forwardUrl
     */
    public MyForwardAuthenticationFailureHandler(String forwardUrl) {
        Assert.isTrue(UrlUtils.isValidRedirectUrl(forwardUrl), () -> "'" + forwardUrl + "' is not a valid forward URL");
        this.forwardUrl = forwardUrl;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        request.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, exception);
        request.getRequestDispatcher(this.forwardUrl).forward(request, response);               //转发到失败的界面中去
    }
}
