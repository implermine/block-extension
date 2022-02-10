package team.flow.blockextension.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import team.flow.blockextension.domain.service.ExtensionService;
import team.flow.blockextension.dto.CustomExtensionRequest;
import team.flow.blockextension.dto.FixedExtensionRequest;

import javax.servlet.http.Cookie;

@RequiredArgsConstructor
@RestController
public class ExtensionController {

    private final ExtensionService extensionService;

    @PostMapping("/extension/fix")
    public void check_fix(@CookieValue(value = "identifier", required = false) Cookie cookie,
                          @RequestBody FixedExtensionRequest fixedExtensionRequest){

        extensionService.updateFixedExtension(cookie.getValue(), fixedExtensionRequest);
    }

    @PostMapping("/extension/custom")
    public void check_custom(@CookieValue(value = "identifier", required = false) Cookie cookie,
                             @RequestBody CustomExtensionRequest customExtensionRequest) {
        extensionService.createCustomExtension(cookie.getValue(), customExtensionRequest);
    }

    @DeleteMapping("/extension/custom")
    public void delete_custom(@CookieValue(value = "identifier", required = false) Cookie cookie,
                              @RequestBody CustomExtensionRequest customExtensionRequest) {
        extensionService.deleteCustomExtension(cookie.getValue(), customExtensionRequest);
    }

}
