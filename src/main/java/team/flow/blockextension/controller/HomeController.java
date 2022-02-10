package team.flow.blockextension.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import team.flow.blockextension.domain.service.UserService;
import team.flow.blockextension.dto.UserExtensionsResponse;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final UserService userService;


    @GetMapping("/")
    public ModelAndView home(@CookieValue(value="identifier", required = false) Cookie cookie,
                       HttpServletResponse response, Model model){

        ModelAndView mav = new ModelAndView("home");
        UserExtensionsResponse userExtensionsResponse;

        // 최초 사용자라면
        if (cookie == null) {
            String identifier = userService.createUserAndGetHisIdentifier();
            Cookie newCookie = new Cookie("identifier", identifier);
            newCookie.setMaxAge(60*15);
            response.addCookie(newCookie);
            userExtensionsResponse = userService.getUserExtensionsByIdentifier(identifier);
        }else{
            userExtensionsResponse = userService.getUserExtensionsByIdentifier(cookie.getValue());
        }

        mav.addObject("userExtensionsResponse", userExtensionsResponse);

        return mav;
    }
}
