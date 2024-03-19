package cn.xstrive.admin.exception;

import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class VerificationCodeException extends AuthenticationException {

    public VerificationCodeException() {
        super("图形验证码校验失败");
    }

}

