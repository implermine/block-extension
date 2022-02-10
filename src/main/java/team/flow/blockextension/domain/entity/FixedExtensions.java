package team.flow.blockextension.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class FixedExtensions {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean bat;

    private Boolean cmd;

    private Boolean com;

    private Boolean cpl;

    private Boolean exe;

    private Boolean scr;

    private Boolean js;

    public FixedExtensions() {
        this.bat = Boolean.FALSE;
        this.cmd = Boolean.FALSE;
        this.com = Boolean.FALSE;
        this.cpl = Boolean.FALSE;
        this.exe = Boolean.FALSE;
        this.scr = Boolean.FALSE;
        this.js = Boolean.FALSE;
    }

    @Builder
    public FixedExtensions(Boolean bat, Boolean cmd, Boolean com, Boolean cpl, Boolean exe, Boolean scr, Boolean js) {
        this.bat = bat;
        this.cmd = cmd;
        this.com = com;
        this.cpl = cpl;
        this.exe = exe;
        this.scr = scr;
        this.js = js;
    }
}
