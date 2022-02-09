package team.flow.blockextension.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.flow.blockextension.domain.entity.CustomExtension;
import team.flow.blockextension.domain.entity.FixedExtensions;
import team.flow.blockextension.domain.entity.User;
import team.flow.blockextension.domain.repository.CustomExtensionRepository;
import team.flow.blockextension.domain.repository.FixedExtensionsRepository;
import team.flow.blockextension.domain.repository.UserRepository;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class ExtensionService {

    private final FixedExtensionsRepository fixedExtensionsRepository;
    private final UserRepository userRepository;
    private final CustomExtensionRepository customExtensionRepository;

    @Transactional
    public void updateFixedExtension(String identifier, String fixedExtensionRequest, Boolean nowState) {

        User user = userRepository.findByIdentifier(identifier).get();

        FixedExtensions fixedExtensions = user.getFixedExtensions();

        if (Objects.equals(fixedExtensionRequest, "bat")) {
            fixedExtensions.setBat(nowState);
        } else if (Objects.equals(fixedExtensionRequest, "cmd")) {
            fixedExtensions.setCmd(nowState);
        } else if (Objects.equals(fixedExtensionRequest, "com")) {
            fixedExtensions.setCom(nowState);
        } else if (Objects.equals(fixedExtensionRequest, "cpl")) {
            fixedExtensions.setCpl(nowState);
        } else if (Objects.equals(fixedExtensionRequest, "exe")) {
            fixedExtensions.setExe(nowState);
        } else if (Objects.equals(fixedExtensionRequest, "scr")) {
            fixedExtensions.setExe(nowState);
        } else if (Objects.equals(fixedExtensionRequest, "js")) {
            fixedExtensions.setJs(nowState);
        }
    }

    @Transactional
    public void createCustomExtension(String identifier, String name) {
        User user = userRepository.findByIdentifier(identifier).get();
        customExtensionRepository.save(CustomExtension.builder().user(user).name(name).build());
    }

    @Transactional
    public void deleteCustomExtension(String identifier, String name) {
        User user = userRepository.findByIdentifier(identifier).get();
        List<CustomExtension> customExtensionList = user.getCustomExtensionList();
        customExtensionList.removeIf(customExtension -> customExtension.getName().equals(name));
    }
}
