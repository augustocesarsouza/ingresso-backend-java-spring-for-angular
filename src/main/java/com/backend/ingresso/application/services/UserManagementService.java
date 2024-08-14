package com.backend.ingresso.application.services;

import com.backend.ingresso.application.ErrorValidation;
import com.backend.ingresso.application.dto.AdditionalInfoUserDTO;
import com.backend.ingresso.application.dto.PermissionDTO;
import com.backend.ingresso.application.dto.TokenSentToEmailDTO;
import com.backend.ingresso.application.dto.UserDTO;
import com.backend.ingresso.application.dto.validations.userValidationDTOs.UserPasswordChangeDTO;
import com.backend.ingresso.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.backend.ingresso.application.dto.validations.userValidationDTOs.UserCreateValidatorDTO;
import com.backend.ingresso.application.dto.validations.userValidationDTOs.UserUpdateValidatorDTO;
import com.backend.ingresso.application.mappings.MappingClassInterface.IAdditionalInfoUserMapper;
import com.backend.ingresso.application.mappings.MappingClassInterface.IUserMapper;
import com.backend.ingresso.application.services.interfaces.IAdditionalInfoUserService;
import com.backend.ingresso.application.services.interfaces.IPermissionService;
import com.backend.ingresso.application.services.interfaces.IUserManagementService;
import com.backend.ingresso.application.services.interfaces.IUserPermissionService;
import com.backend.ingresso.application.util.ValidateUUID;
import com.backend.ingresso.application.util.interfaces.IBCryptPasswordEncoderUtil;
import com.backend.ingresso.application.util.interfaces.IDictionaryCode;
import com.backend.ingresso.data.utilityExternal.Interface.ISendEmailUser;
import com.backend.ingresso.domain.InfoErrors.InfoErrors;
import com.backend.ingresso.domain.entities.Permission;
import com.backend.ingresso.domain.entities.User;
import com.backend.ingresso.domain.entities.UserPermission;
import com.backend.ingresso.domain.repositories.IPermissionRepository;
import com.backend.ingresso.domain.repositories.IUserPermissionRepository;
import com.backend.ingresso.domain.repositories.IUserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.sql.Timestamp;
import java.util.*;
import java.util.regex.Pattern;

@Service
public class UserManagementService implements IUserManagementService {
    private final IUserRepository userRepository;
    private final IUserMapper userMapper;
    private final IAdditionalInfoUserMapper additionalInfoUserMapper;
    private final IValidateErrorsDTO validateErrorsDTO;
    private final IAdditionalInfoUserService additionalInfoUserService;
    private final IBCryptPasswordEncoderUtil bCryptPasswordEncoder;
    private final ISendEmailUser sendEmailUser;
    private final AuthenticationManager authenticationManager;
    private final IDictionaryCode dictionaryCode;
    private final IUserPermissionRepository userPermissionRepository;
    private final IPermissionService permissionService;
    private final IUserPermissionService userPermissionService;
    private IPermissionService permissionService1;

    @Autowired
    public UserManagementService(IUserRepository userRepository, IValidateErrorsDTO validateErrorsDTO, IUserMapper userMapper, IAdditionalInfoUserService additionalInfoUserService,
                                 IBCryptPasswordEncoderUtil bCryptPasswordEncoder, IAdditionalInfoUserMapper additionalInfoUserMapper,
                                 ISendEmailUser sendEmailUser, AuthenticationManager authenticationManager, IDictionaryCode dictionaryCode, IUserPermissionRepository userPermissionRepository,
                                 IPermissionService permissionService, IUserPermissionService userPermissionService) {
        this.userRepository = userRepository;
        this.validateErrorsDTO = validateErrorsDTO;
        this.userMapper = userMapper;
        this.additionalInfoUserService = additionalInfoUserService;
        this.additionalInfoUserMapper = additionalInfoUserMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.sendEmailUser = sendEmailUser;
        this.authenticationManager = authenticationManager;
        this.dictionaryCode = dictionaryCode;
        this.userPermissionRepository = userPermissionRepository;
        this.permissionService = permissionService;
        this.userPermissionService = userPermissionService;
    }

    @Override
    @Transactional
    public ResultService<UserCreateValidatorDTO> create(UserCreateValidatorDTO userCreateValidatorDTO, BindingResult result) {
        // ideia futura colocar aqui quando criar um usuario salvar na tabela 'tb_user_permissions' uma permissao padrão já para os usuarios
        if(result.hasErrors()){
            var errorsDTO = result.getAllErrors();
            var errors = validateErrorsDTO.ValidateDTO(errorsDTO);

            return ResultService.RequestError("error validate DTO", errors);
        }

        User userExist = userRepository.checkUserExits(userCreateValidatorDTO.getEmail(), userCreateValidatorDTO.getCpf());

        if(userExist != null)
            return ResultService.Fail("email or cpf already exist");

        try {
            String passwordEncoder = bCryptPasswordEncoder.encodePassword(userCreateValidatorDTO.getPassword());

            UUID uuid_user_id = UUID.randomUUID();
            UUID userPermissionId = UUID.randomUUID();
            var permission = permissionService.getIdByVisualName("guest");

            if(!permission.IsSuccess)
                return ResultService.Fail(permission.Message);

            PermissionDTO permissionDTO = permission.Data;

            var userToEmail = new User();
            userToEmail.setId(uuid_user_id);
            userToEmail.setName(userCreateValidatorDTO.getName());
            userToEmail.setEmail(userCreateValidatorDTO.getEmail());

            InfoErrors<String> resultSend = sendMessageEmail(userToEmail); // tem que tratar isso se nao mandar eu tenho que mandar algo para o frontend informando

            if(resultSend == null || !resultSend.IsSuccess){
                //userCreateDTO.setEmailSendSuccessfully(false);
                throw new RuntimeException("Failed to send email.");
            }

            userCreateValidatorDTO.setIdPasswordHashConfirmEmail(uuid_user_id, passwordEncoder, false);

            UUID uuid_additionalInfoUserId = UUID.randomUUID();

            String birthDate = userCreateValidatorDTO.getBirthDateString();
            String regex = "\\d{2}/\\d{2}/\\d{4}";
            Pattern pattern = Pattern.compile(regex);
            AdditionalInfoUserDTO additionalInfoUserDTO;

            if(pattern.matcher(birthDate).matches()){
                DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
                DateTime dateBirthDate = DateTime.parse(birthDate, formatter);

                // Convertendo DateTime para Date
                Date utilDate = dateBirthDate.toDate();

                // Criando um Timestamp com o java.util.Date
                Timestamp timestamp = new Timestamp(utilDate.getTime());

                additionalInfoUserDTO = new AdditionalInfoUserDTO(uuid_additionalInfoUserId, timestamp, userCreateValidatorDTO.getGender(),
                        userCreateValidatorDTO.getPhone(), userCreateValidatorDTO.getCep(), userCreateValidatorDTO.getLogradouro(), userCreateValidatorDTO.getNumero(),
                        userCreateValidatorDTO.getComplemento(), userCreateValidatorDTO.getReferencia(), userCreateValidatorDTO.getBairro(),
                        userCreateValidatorDTO.getEstado(), userCreateValidatorDTO.getCidade(), uuid_user_id);
            }else{
                additionalInfoUserDTO = new AdditionalInfoUserDTO(uuid_additionalInfoUserId, null, userCreateValidatorDTO.getGender(),
                        userCreateValidatorDTO.getPhone(), userCreateValidatorDTO.getCep(), userCreateValidatorDTO.getLogradouro(), userCreateValidatorDTO.getNumero(),
                        userCreateValidatorDTO.getComplemento(), userCreateValidatorDTO.getReferencia(), userCreateValidatorDTO.getBairro(),
                        userCreateValidatorDTO.getEstado(), userCreateValidatorDTO.getCidade(), uuid_user_id);
            }

            User userCreate = userRepository.create(userMapper.userCreateValidatorDtoToUser(userCreateValidatorDTO));

            UserCreateValidatorDTO userCreateDTO = userMapper.userToUserCreateValidatorDto(userCreate);
            userCreateDTO.setEmailSendSuccessfully(resultSend.IsSuccess);

            if(userCreate == null)
                return ResultService.Fail("error when create user");

            User user = userRepository.findById(uuid_user_id);

            if(user == null)
                return ResultService.Fail("User not been found");

            var resultFind = permissionService.findById(permissionDTO.getId());

            if(!resultFind.IsSuccess)
                return ResultService.Fail("Permission not been found");

            UserPermission userPermission = new UserPermission(userPermissionId, user, resultFind.Data);

            var createUserPermission = userPermissionService.create(userPermission);

            if(!createUserPermission.IsSuccess)
                return ResultService.Fail(createUserPermission.Message);

            BindingResult resultValid = new BeanPropertyBindingResult(additionalInfoUserDTO, "additionalInfoUserDTO");
            CreateInfoUser(additionalInfoUserDTO, resultValid);

            return ResultService.Ok(userCreateDTO);
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    @Transactional
    public ResultService<UserCreateValidatorDTO> createUserToCheckout(UserCreateValidatorDTO userCreateValidatorDTO, BindingResult result) {
        // ideia futura colocar aqui quando criar um usuario salvar na tabela 'tb_user_permissions' uma permissao padrão já para os usuarios
        if(result.hasErrors()){
            var errorsDTO = result.getAllErrors();
            var errors = validateErrorsDTO.ValidateDTO(errorsDTO);

            return ResultService.RequestError("error validate DTO", errors);
        }

        User userExist = userRepository.checkUserExits(userCreateValidatorDTO.getEmail(), userCreateValidatorDTO.getCpf());

        if(userExist != null)
            return ResultService.Fail("email or cpf already exist");

        try {
            String passwordEncoder = bCryptPasswordEncoder.encodePassword(userCreateValidatorDTO.getPassword());

            UUID uuid_user_id = UUID.randomUUID();

            var userToEmail = new User();
            userToEmail.setId(uuid_user_id);
            userToEmail.setName(userCreateValidatorDTO.getName());
            userToEmail.setEmail(userCreateValidatorDTO.getEmail());

            int randomCode = generateRandomNumber();
            dictionaryCode.putKeyValueDictionary(uuid_user_id.toString(), randomCode);
            InfoErrors<String> resultSendCodeEmail = sendEmailUser.sendCodeRandom(userToEmail, randomCode);

            userCreateValidatorDTO.setIdPasswordHashConfirmEmail(uuid_user_id, passwordEncoder, false);

            UUID uuid_additionalInfoUserId = UUID.randomUUID();

            String birthDate = userCreateValidatorDTO.getBirthDateString();
            String regex = "\\d{2}/\\d{2}/\\d{4}";
            Pattern pattern = Pattern.compile(regex);
            AdditionalInfoUserDTO additionalInfoUserDTO;

            if(pattern.matcher(birthDate).matches()){
                DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
                DateTime dateBirthDate = DateTime.parse(birthDate, formatter);

                // Convertendo DateTime para Date
                Date utilDate = dateBirthDate.toDate();

                // Criando um Timestamp com o java.util.Date
                Timestamp timestamp = new Timestamp(utilDate.getTime());

                additionalInfoUserDTO = new AdditionalInfoUserDTO(uuid_additionalInfoUserId, timestamp, userCreateValidatorDTO.getGender(),
                        userCreateValidatorDTO.getPhone(), userCreateValidatorDTO.getCep(), userCreateValidatorDTO.getLogradouro(), userCreateValidatorDTO.getNumero(),
                        userCreateValidatorDTO.getComplemento(), userCreateValidatorDTO.getReferencia(), userCreateValidatorDTO.getBairro(),
                        userCreateValidatorDTO.getEstado(), userCreateValidatorDTO.getCidade(), uuid_user_id);
            }else{
                additionalInfoUserDTO = new AdditionalInfoUserDTO(uuid_additionalInfoUserId, null, userCreateValidatorDTO.getGender(),
                        userCreateValidatorDTO.getPhone(), userCreateValidatorDTO.getCep(), userCreateValidatorDTO.getLogradouro(), userCreateValidatorDTO.getNumero(),
                        userCreateValidatorDTO.getComplemento(), userCreateValidatorDTO.getReferencia(), userCreateValidatorDTO.getBairro(),
                        userCreateValidatorDTO.getEstado(), userCreateValidatorDTO.getCidade(), uuid_user_id);
            }

            User userCreate = userRepository.create(userMapper.userCreateValidatorDtoToUser(userCreateValidatorDTO));

            UserCreateValidatorDTO userCreateDTO = userMapper.userToUserCreateValidatorDto(userCreate);
            userCreateDTO.setEmailSendSuccessfully(resultSendCodeEmail.IsSuccess);
            userCreateDTO.setId(uuid_user_id);

            if(userCreate == null)
                return ResultService.Fail("error when create user");

            BindingResult resultValid = new BeanPropertyBindingResult(additionalInfoUserDTO, "additionalInfoUserDTO");
            CreateInfoUser(additionalInfoUserDTO, resultValid);

            return ResultService.Ok(userCreateDTO);
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    @Transactional
    public ResultService<TokenSentToEmailDTO> resendCodeOfTheVerifyEmail(String uuid_user_id){
       try {
           User userToEmail = userRepository.getByIdInfoToSentCodeToEmail(UUID.fromString(uuid_user_id));

           int randomCode = generateRandomNumber();

           if(dictionaryCode.getKeyDictionary(uuid_user_id) != null)
               dictionaryCode.removeKeyDictionary(uuid_user_id);

           dictionaryCode.putKeyValueDictionary(uuid_user_id, randomCode);
           InfoErrors<String> resultSendCodeEmail = sendEmailUser.sendCodeRandom(userToEmail, randomCode);

           if(!resultSendCodeEmail.IsSuccess)
               return ResultService.Fail(new TokenSentToEmailDTO(false, resultSendCodeEmail.Message));

           return ResultService.Ok(new TokenSentToEmailDTO(true, resultSendCodeEmail.Message));
       }catch (Exception ex){
           return ResultService.Fail(ex.getMessage());
       }
    }

    private static int generateRandomNumber(){
        Random random = new Random();
        return random.nextInt(900000) + 100000;
    }

    @Override
    @Transactional
    public ResultService<Map<String, Object>> checkEmailAlreadyExists(String cpfOrEmail){
        String regex = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}";
        Pattern pattern = Pattern.compile(regex);

        try {
            Map<String, Object> result = new HashMap<>();

            if(cpfOrEmail.contains("@")){
                User userCheck = userRepository.checkEmailAlreadyExists(cpfOrEmail);

                if(userCheck != null){
                    result.put("userExists", true);
                }else {
                    result.put("userExists", false);
                }

                return ResultService.Ok(result);

            }else if(pattern.matcher(cpfOrEmail).matches()){
                return null;
            }
        }catch (Exception ex){
            String message = ex.getMessage();
            return ResultService.Fail(message);
        }

        return ResultService.Fail("error email or cpf not informed");
    }

    @Override
    @Transactional
    public ResultService<Map<String, Object>> checkIfAlreadyExistCpf(String cpf){
        String regex = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}";
        Pattern pattern = Pattern.compile(regex);

        try {
            Map<String, Object> result = new HashMap<>();

            if(pattern.matcher(cpf).matches()){
                User userCheckExist = userRepository.checkIfAlreadyExistCpf(cpf);

                if(userCheckExist != null){
                    result.put("cpfExists", true);
                }else {
                    result.put("cpfExists", false);
                }

                return ResultService.Ok(result);
            }else {
                return ResultService.Fail("Error Cpf Not Valid");
            }
        }catch (Exception ex){
            String message = ex.getMessage();
            return ResultService.Fail(message);
        }
    }

    @Override
    @Transactional
    public ResultService<UserDTO> update(UserUpdateValidatorDTO userUpdateValidatorDTO, BindingResult resultValid, String password) {
        if(userUpdateValidatorDTO == null)
            return ResultService.Fail("obj null");

        var resultValidateDTO = validateDTOUser(userUpdateValidatorDTO.getId(), resultValid);
        if(!resultValidateDTO.IsSuccess)
            return resultValidateDTO;

        if(password == null)
            return ResultService.Fail("password is null");

        try {
            var user = userRepository.getByIdOnlyEmailOrCpfId(UUID.fromString(userUpdateValidatorDTO.getId()));
            if(user == null)
                return ResultService.Fail("user not found");

            var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getEmail(), password);
            Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            if(!authenticate.isAuthenticated())
                return ResultService.Fail("password is not valid");

            user.changeNameUser(userUpdateValidatorDTO.getName());
            var userChange = userRepository.update(user);

            if(userChange == null)
                return ResultService.Fail("error when updating user");

            return ResultService.Ok(userMapper.userToUserDto(userChange));
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    @Transactional
    public ResultService<UserDTO> updateUserPassword(UserPasswordChangeDTO userPasswordChangeDTO, BindingResult resultValid) {
        if(userPasswordChangeDTO == null)
            return ResultService.Fail("obj null");

        var resultValidateDTO = validateDTOUser(userPasswordChangeDTO.getIdGuid(), resultValid);

        if(!resultValidateDTO.IsSuccess)
            return resultValidateDTO;

        try {
            var user = userRepository.getByIdOnlyEmailOrCpfId(UUID.fromString(userPasswordChangeDTO.getIdGuid()));
            if(user == null)
                return ResultService.Fail("user not found");

//            var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getEmail(), userPasswordChangeDTO.getPasswordCurrent());
//            Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
//            if(!authenticate.isAuthenticated())
//                return ResultService.Fail("password is not valid");

            String passwordEncoder = bCryptPasswordEncoder.encodePassword(userPasswordChangeDTO.getNewPassword());

            user.changePasswordHash(passwordEncoder);

            var userChange = userRepository.update(user);

            if(userChange == null)
                return ResultService.Fail("error when updating user");

            return ResultService.Ok(userMapper.userToUserDto(userChange));
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    private ResultService<UserDTO> validateDTOUser(String idGuid, BindingResult resultValid){
        if(resultValid.hasErrors()){
            var errorsDTO = resultValid.getAllErrors();
            List<ErrorValidation> errors = validateErrorsDTO.ValidateDTO(errorsDTO);

            return ResultService.RequestError("error validate DTO", errors);
        }else {
//            String regex = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
//            Pattern pattern = Pattern.compile(regex);
            boolean validateUUID = ValidateUUID.Validate(idGuid);

            if(!validateUUID){
                List<ErrorValidation> errors = new ArrayList<>();
                var errorValidation = new ErrorValidation("id", "invalid format id must be UUID");
                errors.add(errorValidation);
                return ResultService.RequestError("error validate DTO", errors);
            }
        }

        return ResultService.Ok("everything ok correct DTO and UUID");
    }

    private void CreateInfoUser(@Valid AdditionalInfoUserDTO additionalInfoUserDTO, BindingResult resultValid){
        additionalInfoUserService.create(additionalInfoUserMapper.additionalInfoUserDtoToAdditionalInfoUserCreateDto(additionalInfoUserDTO), resultValid);
    }

    private InfoErrors<String> sendMessageEmail(User user){
        return sendEmailUser.sendEmailConfirmRegisterUser(user);
    }

    @Override
    @Transactional
    public ResultService<String> sendTokenEmailChangePassword(String email) {
        if(email == null)
            return ResultService.Fail("Email Null");

        if(email.isEmpty())
            return ResultService.Fail("Email empty 0");

        try {
            var user = userRepository.getByEmailInfoForSendTokenChangePassword(email);

            if(user == null)
                return ResultService.Fail("user not found");

            var resultSend = sendEmailUser.sendTokenForEmailChangePassword(user);

            if(!resultSend.IsSuccess)
                return ResultService.Fail(resultSend.Message);

            return ResultService.Ok(resultSend.Message);
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }
}
