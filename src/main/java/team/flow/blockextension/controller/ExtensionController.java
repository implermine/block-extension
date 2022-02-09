package team.flow.blockextension.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import team.flow.blockextension.domain.service.ExtensionService;
import team.flow.blockextension.dto.CheckCustomRequestDto;
import team.flow.blockextension.dto.CheckFixRequestDto;

import javax.servlet.http.Cookie;

@RequiredArgsConstructor
@RestController
public class ExtensionController {

    private final ExtensionService extensionService;

    @PostMapping("/check/fix")
    public void check_fix(@CookieValue(value="identifier", required = false) Cookie cookie, @RequestBody CheckFixRequestDto requestDto){

        extensionService.updateFixedExtension(cookie.getValue(), requestDto.getCheckedValue(), requestDto.getIsChecked());
    }

    @PostMapping("/check/custom")
    public void check_custom(@CookieValue(value="identifier", required=false ) Cookie cookie, @RequestBody CheckCustomRequestDto requestDto){
        extensionService.createCustomExtension(cookie.getValue(), requestDto.getName());
    }

    @DeleteMapping("/check/custom")
    public void delete_custom(@CookieValue(value="identifier", required=false ) Cookie cookie, @RequestBody CheckCustomRequestDto requestDto){
        extensionService.deleteCustomExtension(cookie.getValue(), requestDto.getName());
    }

}
