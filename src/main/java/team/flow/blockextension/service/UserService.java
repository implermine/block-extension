package team.flow.blockextension.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.flow.blockextension.domain.fixedExtensions.FixedExtensions;
import team.flow.blockextension.domain.fixedExtensions.FixedExtensionsRepository;
import team.flow.blockextension.domain.user.User;
import team.flow.blockextension.domain.user.UserRepository;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final FixedExtensionsRepository fixedExtensionsRepository;

    /**
     * 최초 방문자에게 identifier 정보를 반환하여, 재 방문시, 데이터를 유지하도록 합니다.
     * @return identifier
     */
    public String createUser() {

        FixedExtensions newFixedExtensions = new FixedExtensions();
        fixedExtensionsRepository.save(newFixedExtensions);

        String randomUUID = UUID.randomUUID().toString();
        User newUser = User.builder().identifier(randomUUID).fixedExtensions(newFixedExtensions).build();

        userRepository.save(newUser);

        return randomUUID;
    }

}
