package team.flow.blockextension.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team.flow.blockextension.domain.entity.CustomExtension;
import team.flow.blockextension.domain.entity.FixedExtensions;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Setter
@Getter
public class UserExtensionsResponse {

    // bat를 check 하였는지
    private Boolean bat;

    private Boolean cmd;

    private Boolean com;

    private Boolean cpl;

    private Boolean exe;

    private Boolean scr;

    private Boolean js;

    private List<String> customExtensions;

    public void fromFixedExtensions(FixedExtensions fixedExtensions){
        this.bat = fixedExtensions.getBat();
        this.cmd = fixedExtensions.getCmd();
        this.com = fixedExtensions.getCom();
        this.cpl = fixedExtensions.getCpl();
        this.exe = fixedExtensions.getExe();
        this.scr = fixedExtensions.getScr();
        this.js = fixedExtensions.getJs();
    }

    public void fromCustomExtensions(List<CustomExtension> customExtensionList) {
        customExtensions = new ArrayList<>();
        for (CustomExtension customExtension : customExtensionList) {
            customExtensions.add(customExtension.getName());
        }
    }

}
