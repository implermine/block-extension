package team.flow.blockextension.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CookieTestController {

    @GetMapping("/cookie")
    @ResponseBody
    public String cookie(HttpServletResponse response){

        Cookie cookie = new Cookie("hello", "world");

        cookie.setMaxAge(60*5);

        response.addCookie(cookie);

        return "successfully added to cookie";
    }

    @GetMapping("/cookie/show")
    @ResponseBody
    public String cookieShow(
            @CookieValue(value="hello", required = false) Cookie cookie,
            HttpServletRequest request){

        return cookie.getValue();
    }
}
