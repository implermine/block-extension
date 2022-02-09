package team.flow.blockextension.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.flow.blockextension.domain.entity.CustomExtension;

public interface CustomExtensionRepository extends JpaRepository<CustomExtension, Long> {
}
