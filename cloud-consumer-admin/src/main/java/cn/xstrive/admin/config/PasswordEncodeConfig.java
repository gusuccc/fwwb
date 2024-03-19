package cn.xstrive.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncodeConfig {
    @Bean     //将密码编码器加入容器中
    public PasswordEncoder password() {
        return new BCryptPasswordEncoder();
    }
}
