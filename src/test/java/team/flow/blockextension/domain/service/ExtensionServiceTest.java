package team.flow.blockextension.domain.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import team.flow.blockextension.domain.entity.FixedExtensions;
import team.flow.blockextension.domain.entity.User;
import team.flow.blockextension.domain.repository.CustomExtensionRepository;
import team.flow.blockextension.domain.repository.FixedExtensionsRepository;
import team.flow.blockextension.domain.repository.UserRepository;
import team.flow.blockextension.dto.CustomExtensionRequest;
import team.flow.blockextension.dto.FixedExtensionRequest;
import team.flow.blockextension.exception.ApiRequestException;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ExtensionServiceTest {

    @Autowired
    private ExtensionService extensionService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FixedExtensionsRepository fixedExtensionsRepository;
    @Autowired
    private CustomExtensionRepository customExtensionRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void updateFixedExtension() {
        //given
        String randomUUID = UUID.randomUUID().toString();
        FixedExtensions fixedExtensions = new FixedExtensions();
        FixedExtensions savedFixedExtensions = fixedExtensionsRepository.save(fixedExtensions);
        User user = userRepository.save(User.builder().fixedExtensions(savedFixedExtensions).identifier(randomUUID).build());


        //when
        extensionService.updateFixedExtension(user.getIdentifier(),new FixedExtensionRequest("bat",Boolean.TRUE));

        //then
        assertThat(user.getFixedExtensions().getBat()).isEqualTo(Boolean.TRUE);
    }

    @Test
    void createCustomExtensionSuccess() {
        //given
        String randomUUID = UUID.randomUUID().toString();
        FixedExtensions fixedExtensions = new FixedExtensions();
        FixedExtensions savedFixedExtensions = fixedExtensionsRepository.save(fixedExtensions);
        User user = userRepository.save(User.builder().fixedExtensions(savedFixedExtensions).identifier(randomUUID).build());
        //when
        extensionService.createCustomExtension(user.getIdentifier(),new CustomExtensionRequest("not_bat"));
        //then
        assertThat(user.getCustomExtensionList().size()).isNotSameAs(0);
    }

    @Test
    void createCustomExtensionFail() {
        //given
        String randomUUID = UUID.randomUUID().toString();
        FixedExtensions fixedExtensions = new FixedExtensions();
        FixedExtensions savedFixedExtensions = fixedExtensionsRepository.save(fixedExtensions);
        User user = userRepository.save(User.builder().fixedExtensions(savedFixedExtensions).identifier(randomUUID).build());
        //when
        //then
        assertThrows(ApiRequestException.class, ()-> extensionService.createCustomExtension(user.getIdentifier(),new CustomExtensionRequest("bat")));
    }

    @Test
    void deleteCustomExtension() {
    }
}