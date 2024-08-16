package com.backend.ingresso.application.services.interfaces;

import com.backend.ingresso.application.dto.TokenSentToEmailDTO;
import com.backend.ingresso.application.dto.UserDTO;
import com.backend.ingresso.application.dto.validations.userValidationDTOs.UserCreateValidatorDTO;
import com.backend.ingresso.application.dto.validations.userValidationDTOs.UserPasswordChangeDTO;
import com.backend.ingresso.application.dto.validations.userValidationDTOs.UserUpdateValidatorDTO;
import com.backend.ingresso.application.services.ResultService;
import com.backend.ingresso.domain.entities.User;
import org.springframework.validation.BindingResult;

import java.util.Map;
import java.util.UUID;

public interface IUserManagementService {
    ResultService<User> findById(UUID idUser);
    ResultService<UserCreateValidatorDTO> create(UserCreateValidatorDTO userCreateValidatorDTO, BindingResult result);
    ResultService<UserCreateValidatorDTO> createUserToCheckout(UserCreateValidatorDTO userCreateValidatorDTO, BindingResult result);
    ResultService<TokenSentToEmailDTO> resendCodeOfTheVerifyEmail(String uuid_user_id);
    ResultService<Map<String, Object>> checkEmailAlreadyExists(String cpfOrEmail);
    ResultService<Map<String, Object>> checkIfAlreadyExistCpf(String cpf);
    ResultService<UserDTO> update(UserUpdateValidatorDTO userUpdateValidatorDTO, BindingResult resultValid, String password);
    ResultService<UserDTO> updateUserPassword(UserPasswordChangeDTO userPasswordChangeDTO, BindingResult resultValid);
    ResultService<String> sendTokenEmailChangePassword(String email);
}
