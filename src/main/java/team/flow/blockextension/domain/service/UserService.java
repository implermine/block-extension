package team.flow.blockextension.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.flow.blockextension.domain.entity.FixedExtensions;
import team.flow.blockextension.domain.repository.FixedExtensionsRepository;
import team.flow.blockextension.domain.entity.User;
import team.flow.blockextension.domain.repository.UserRepository;
import team.flow.blockextension.dto.UserExtensionsResponse;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final FixedExtensionsRepository fixedExtensionsRepository;

    /**
     * 최초 방문자에게 identifier 정보를 반환하여, 재 방문시, 데이터를 유지하도록 합니다.
     *
     * @return identifier
     */
    @Transactional
    public String createUserAndGetHisIdentifier() {

        FixedExtensions newFixedExtensions = new FixedExtensions();
        fixedExtensionsRepository.save(newFixedExtensions);

        String randomUUID = UUID.randomUUID().toString();
        User newUser = User.builder().identifier(randomUUID).fixedExtensions(newFixedExtensions).build();

        userRepository.save(newUser);

        return randomUUID;
    }

    /**
     * 최초 방문자가 아닐 시, 가져온 identifier정보로 User를 조회해서, 해당 User의 데이터들을 호출하여 앞단에 뿌려줍니다.
     */
    @Transactional(readOnly = true)
    public UserExtensionsResponse getUserExtensionsByIdentifier(
            String identifier
    ) {
        Optional<User> userOptional = userRepository.findByIdentifier(identifier);
        UserExtensionsResponse userExtensionsResponse = new UserExtensionsResponse();
        userOptional.ifPresent(
                user -> {
                    userExtensionsResponse.fromFixedExtensions(user.getFixedExtensions());
                    userExtensionsResponse.fromCustomExtensions(user.getCustomExtensionList());
                }
        );
        return userExtensionsResponse;
    }


}
