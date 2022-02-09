package team.flow.blockextension.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.flow.blockextension.domain.entity.FixedExtensions;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Getter
@Entity
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String identifier;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private final List<CustomExtension> customExtensionList = new ArrayList<>();


    @OneToOne
    @JoinColumn(name="fixed_id")
    private FixedExtensions fixedExtensions;

    @Builder
    public User(String identifier, FixedExtensions fixedExtensions) {
        this.identifier = identifier;
        this.fixedExtensions = fixedExtensions;
    }
}
