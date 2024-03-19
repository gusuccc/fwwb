package cn.xstrive.admin.controller;

import org.bouncycastle.math.raw.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @GetMapping(value = {"/login"})
    public String login(Model model, @ModelAttribute("failinfo") String failinfo) {
        model.addAttribute("failinfo",failinfo);
        return "login/login_admin";
    }

    @GetMapping("/success")
    public String success(){
        return "index";
    }

    @PostMapping("/fail")
    public String fail(RedirectAttributes model) {

        model.addFlashAttribute("failinfo","*验证码或输入信息错误！");      //这样的话url中是看不到的,且接受的参数要添加@ModelAttribute才能接收到
        return "redirect:/login";                                               //这里不能转发，转发的话是post方式
    }

    @GetMapping("/main")
    public String main(){
        return "main";
    }


}
