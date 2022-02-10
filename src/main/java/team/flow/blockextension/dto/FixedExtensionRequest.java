package team.flow.blockextension.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FixedExtensionRequest {

    private String name;
    private Boolean isChecked;
}
