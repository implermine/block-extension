package team.flow.blockextension.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.flow.blockextension.domain.entity.CustomExtension;
import team.flow.blockextension.domain.entity.FixedExtensions;
import team.flow.blockextension.domain.entity.User;
import team.flow.blockextension.domain.repository.CustomExtensionRepository;
import team.flow.blockextension.domain.repository.FixedExtensionsRepository;
import team.flow.blockextension.domain.repository.UserRepository;
import team.flow.blockextension.dto.CustomExtensionRequest;
import team.flow.blockextension.dto.FixedExtensionRequest;
import team.flow.blockextension.exception.ApiRequestException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ExtensionService {

    private final UserRepository userRepository;
    private final CustomExtensionRepository customExtensionRepository;

    @Transactional
    public void updateFixedExtension(String identifier, FixedExtensionRequest fixedExtensionRequest){

        Optional<User> userOptional = userRepository.findByIdentifier(identifier);
        userOptional.ifPresent(
                user -> {
                    FixedExtensions fixedExtensions = user.getFixedExtensions();

                    switch(fixedExtensionRequest.getName()){
                        case "bat":
                            fixedExtensions.setBat(fixedExtensionRequest.getIsChecked());
                            break;
                        case "cmd":
                            fixedExtensions.setCmd(fixedExtensionRequest.getIsChecked());
                            break;
                        case "com":
                            fixedExtensions.setCom(fixedExtensionRequest.getIsChecked());
                            break;
                        case "cpl":
                            fixedExtensions.setCpl(fixedExtensionRequest.getIsChecked());
                            break;
                        case "exe":
                            fixedExtensions.setExe(fixedExtensionRequest.getIsChecked());
                            break;
                        case "scr":
                            fixedExtensions.setScr(fixedExtensionRequest.getIsChecked());
                            break;
                        case "js":
                            fixedExtensions.setJs(fixedExtensionRequest.getIsChecked());
                            break;
                    }
                }
        );
    }

    @Transactional
    public void createCustomExtension(String identifier, CustomExtensionRequest customExtensionRequest){

        handleDuplicationOfCustomExtensionWithFixedExtension(customExtensionRequest);


        User user = userRepository.findByIdentifier(identifier).orElseThrow(
                () -> new ApiRequestException("확인 된 유저가 없습니다.", HttpStatus.BAD_REQUEST)
        );

        List<CustomExtension> customExtensionList = user.getCustomExtensionList();
        handleUpperBoundaryOfCreatableCustomExtensions(customExtensionList);
        handleDuplicationOfCustomExtensions(customExtensionRequest, customExtensionList);


        CustomExtension customExtension = CustomExtension.builder().user(user).name(customExtensionRequest.getName()).build();
        customExtensionRepository.save(customExtension);

    }

    private void handleDuplicationOfCustomExtensions(CustomExtensionRequest customExtensionRequest, List<CustomExtension> customExtensionList) throws ApiRequestException {
        for (CustomExtension customExtension : customExtensionList) {
            if (customExtension.getName().equals(customExtensionRequest.getName())){
                throw new ApiRequestException("중복된 커스텀 확장자를 등록할 수 없습니다.", HttpStatus.BAD_REQUEST);
            }
        }
    }

    private void handleUpperBoundaryOfCreatableCustomExtensions(List<CustomExtension> customExtensionList) throws ApiRequestException {
        if (customExtensionList.size() >= 200){
            throw new ApiRequestException("200개 초과 커스텀 확장자를 등록할 수 없습니다.", HttpStatus.BAD_REQUEST);
        }
    }

    private void handleDuplicationOfCustomExtensionWithFixedExtension(CustomExtensionRequest customExtensionRequest) throws ApiRequestException {
        List<String> existingFixedExtensions = Arrays.asList("bat","cmd","com","cpl","exe","scr","js");
        if (existingFixedExtensions.contains(customExtensionRequest.getName())){
            throw new ApiRequestException("고정 확장자로 등록된 확장자를 커스텀 확장자로 등록할 수 없습니다.", HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public void deleteCustomExtension(String identifier, CustomExtensionRequest customExtensionRequest) {
        Optional<User> userOptional = userRepository.findByIdentifier(identifier);
        userOptional.ifPresent(
                user -> {
                    List<CustomExtension> customExtensionList = user.getCustomExtensionList();
                    customExtensionList.removeIf(customExtension -> customExtension.getName().equals(customExtensionRequest.getName()));
                }

        );
    }
}
