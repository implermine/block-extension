package team.flow.blockextension.domain.user;

import lombok.Builder;
import lombok.NoArgsConstructor;
import team.flow.blockextension.domain.fixedExtensions.FixedExtensions;

import javax.persistence.*;


@NoArgsConstructor
@Entity
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String identifier;

    @OneToOne
    @JoinColumn(name="id")
    private FixedExtensions fixedExtensions;

    @Builder
    public User(String identifier, FixedExtensions fixedExtensions) {
        this.identifier = identifier;
        this.fixedExtensions = fixedExtensions;
    }
}
