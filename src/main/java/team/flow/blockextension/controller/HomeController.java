package team.flow.blockextension.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(@CookieValue(value="identifier", required = false) Cookie cookie){

        if (cookie == null){
        }else{
        }

        return "home";
    }
}
