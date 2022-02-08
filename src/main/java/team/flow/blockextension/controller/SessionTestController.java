package team.flow.blockextension.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Controller
public class SessionTestController {

    @GetMapping("/session")
    @ResponseBody
    public String session(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        System.out.println("cookies = " + Arrays.toString(cookies));
        HttpSession session = request.getSession();
        session.setAttribute("hollys","coffee");
        session.setMaxInactiveInterval(60*10);

        return "successfully added to session";
    }

    @GetMapping("/session/show")
    @ResponseBody
    public String sessionShow(HttpServletRequest request){
        HttpSession session = request.getSession();
        Object hollys = session.getAttribute("hollys");
        return (String)hollys;
    }
}
