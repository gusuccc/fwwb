package cn.xstrive.admin.config;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class OtherConfig {

    @Bean
    public Producer captcha() {
        Properties properties = new Properties();
        properties.setProperty("kaptcha.image.width", "150"); //图片宽度
        properties.setProperty("kaptcha.image.heigth", "40");
        properties.setProperty("kaptcha.textproducer.char.String", "ABCDEFGHJKLMNZXV0123456789");  //设置字符集
        properties.setProperty("kaptcha.textproducer.char.length", "4"); //字符长度
        properties.setProperty("kaptcha.background.clear.from","128,170,238");        //验证码背景开始时的颜色
        properties.setProperty("kaptcha.background.clear.to","white");
        properties.setProperty("kaptcha.obscurificator.impl","com.google.code.kaptcha.impl.WaterRipple");
        properties.setProperty("kaptcha.textproducer.font.color","107,107,244");
        properties.setProperty("kaptcha.border.color","128,170,238");
        properties.setProperty("kaptcha.noise.color","236,54,54");
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

}
