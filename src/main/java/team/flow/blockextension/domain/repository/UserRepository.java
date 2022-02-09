package team.flow.blockextension.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.flow.blockextension.domain.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByIdentifier(String identifier);
    //TODO: 이거 identifier를 unique로 두던가, 아님 pk로 잡아야될듯
}
