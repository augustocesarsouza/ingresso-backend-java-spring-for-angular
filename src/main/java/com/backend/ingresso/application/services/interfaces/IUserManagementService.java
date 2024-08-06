package com.backend.ingresso.application.services.interfaces;

import com.backend.ingresso.application.dto.UserDTO;
import com.backend.ingresso.application.dto.validations.userValidationDTOs.UserCreateValidatorDTO;
import com.backend.ingresso.application.dto.validations.userValidationDTOs.UserPasswordChangeDTO;
import com.backend.ingresso.application.dto.validations.userValidationDTOs.UserUpdateValidatorDTO;
import com.backend.ingresso.application.services.ResultService;
import org.springframework.validation.BindingResult;

import java.util.Map;

public interface IUserManagementService {
    ResultService<UserCreateValidatorDTO> create(UserCreateValidatorDTO userCreateValidatorDTO, BindingResult result);
    ResultService<UserCreateValidatorDTO> createUserToCheckout(UserCreateValidatorDTO userCreateValidatorDTO, BindingResult result);
    ResultService<Map<String, Object>> checkEmailAlreadyExists(String cpfOrEmail);
    ResultService<Map<String, Object>> checkIfAlreadyExistCpf(String cpf);
    ResultService<UserDTO> update(UserUpdateValidatorDTO userUpdateValidatorDTO, BindingResult resultValid, String password);
    ResultService<UserDTO> updateUserPassword(UserPasswordChangeDTO userPasswordChangeDTO, BindingResult resultValid);
    ResultService<String> sendTokenEmailChangePassword(String email);
}
