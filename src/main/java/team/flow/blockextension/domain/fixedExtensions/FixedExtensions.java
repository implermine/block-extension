package team.flow.blockextension.domain.fixedExtensions;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
public class FixedExtensions {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "boolean default false")
    private Boolean bat;

    @Column(columnDefinition = "boolean default false")
    private Boolean cmd;

    @Column(columnDefinition = "boolean default false")
    private Boolean com;

    @Column(columnDefinition = "boolean default false")
    private Boolean cpl;

    @Column(columnDefinition = "boolean default false")
    private Boolean exe;

    @Column(columnDefinition = "boolean default false")
    private Boolean scr;

    @Column(columnDefinition = "boolean default false")
    private Boolean js;

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
