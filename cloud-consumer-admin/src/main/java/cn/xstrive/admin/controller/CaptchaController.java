package cn.xstrive.admin.controller;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
/*
返回一张图片的字节流，用于验证码验证
 */

@Controller
public class CaptchaController {
    @Autowired
    private Producer producer;

    @GetMapping("/cap.jpg/{id}")          //访问它即可获取到一张验证码图片
    public void getcaptcha(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") String id) throws IOException {
        response.setContentType("image/jpeg");
        String text = producer.createText();
        //讲验证码文本设置到session
        request.getSession().setAttribute("captcha", text);

        BufferedImage image = producer.createImage(text);
        //获取响应输出流
        ServletOutputStream outputStream = response.getOutputStream();
        //将图片验证码放到响应输出流
        ImageIO.write(image, "jpg", outputStream);
        try {
            outputStream.flush();
        } finally {
            outputStream.close();
        }
    }
}
