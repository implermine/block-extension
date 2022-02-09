package team.flow.blockextension.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import team.flow.blockextension.domain.service.UserService;
import team.flow.blockextension.dto.UserCheckedExtensionsDto;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final UserService userService;


    @GetMapping("/")
    public String home(@CookieValue(value="identifier", required = false) Cookie cookie,
                       HttpServletResponse response, Model model){

        UserCheckedExtensionsDto userCheckedExtensionsDto;

        if (cookie == null){
            String identifier = userService.createUserAndGetHisIdentifier();
            userCheckedExtensionsDto = userService.getUserCheckedExtensions(identifier);
            Cookie newCookie = new Cookie("identifier", identifier);
            newCookie.setMaxAge(60*3);
            response.addCookie(newCookie);
        }else{
            userCheckedExtensionsDto = userService.getUserCheckedExtensions(cookie.getValue());
        }

        model.addAttribute("bat",userCheckedExtensionsDto.getBat());
        model.addAttribute("cmd",userCheckedExtensionsDto.getCmd());
        model.addAttribute("com",userCheckedExtensionsDto.getCom());
        model.addAttribute("cpl",userCheckedExtensionsDto.getCpl());
        model.addAttribute("exe",userCheckedExtensionsDto.getExe());
        model.addAttribute("scr",userCheckedExtensionsDto.getScr());
        model.addAttribute("js",userCheckedExtensionsDto.getJs());
        model.addAttribute("names", userCheckedExtensionsDto.getCustomExtensions());
        return "home";
    }
}
