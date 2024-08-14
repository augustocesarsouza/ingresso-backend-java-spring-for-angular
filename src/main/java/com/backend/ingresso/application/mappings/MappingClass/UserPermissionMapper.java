package com.backend.ingresso.application.mappings.MappingClass;

import com.backend.ingresso.application.dto.PermissionDTO;
import com.backend.ingresso.application.dto.UserPermissionDTO;
import com.backend.ingresso.application.mappings.MappingClassInterface.IPermissionMapper;
import com.backend.ingresso.application.mappings.MappingClassInterface.IUserMapper;
import com.backend.ingresso.application.mappings.MappingClassInterface.IUserPermissionMapper;
import com.backend.ingresso.domain.entities.Permission;
import com.backend.ingresso.domain.entities.User;
import com.backend.ingresso.domain.entities.UserPermission;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class UserPermissionMapper implements IUserPermissionMapper {
    private final IUserMapper userMapper;
    private final IPermissionMapper permissionMapper;

    public UserPermissionMapper(IUserMapper userMapper, IPermissionMapper permissionMapper) {
        this.userMapper = userMapper;
        this.permissionMapper = permissionMapper;
    }

    @Override
    public UserPermissionDTO userPermissionToUserPermissionDto(UserPermission userPermission) {
        if(userPermission == null)
            return null;

        return new UserPermissionDTO(userPermission.getId(), userMapper.userToUserDto(userPermission.getUser()),
                permissionMapper.permissionToPermissionDto(userPermission.getPermission()));
    }

    @Override
    public UserPermission userPermissionDTOToUserPermission(UserPermissionDTO userPermissionDTO) {
        return new UserPermission(userPermissionDTO.getId(), userMapper.userDtoToUser(userPermissionDTO.getUserDTO()),
                permissionMapper.permissionDtoToPermission(userPermissionDTO.getPermissionDTO()));
    }

    public List<UserPermission> UserPermissionDtoToUserPermission(List<UserPermissionDTO> userPermissionDTO) {
        if(userPermissionDTO == null)
            return null;

        List<UserPermission> listUserPermission = new ArrayList<>();

        userPermissionDTO.forEach((el) -> {
            Permission permission = new Permission(null, el.getPermissionDTO().getVisualName(), el.getPermissionDTO().getPermissionName());
            UserPermission userPermissionNew;
            if(el.getPermissionDTO() != null){
                userPermissionNew = new UserPermission(el.getId(), userMapper.userDtoToUser(el.getUserDTO()),
                        permissionMapper.permissionDtoToPermission(el.getPermissionDTO()));
            }else {
                userPermissionNew = new UserPermission(el.getId(), userMapper.userDtoToUser(el.getUserDTO()),
                        permissionMapper.permissionDtoToPermission(el.getPermissionDTO()));
            }
            listUserPermission.add(userPermissionNew);
        });

        return listUserPermission;
    }

    @Override
    public List<UserPermissionDTO> userPermissionToUserPermissionDto(List<UserPermission> userPermission) {
        if(userPermission == null)
            return null;

        List<UserPermissionDTO> listUserPermissionDto = new ArrayList<>();

        userPermission.forEach((el) -> {
            PermissionDTO permissionDTO = new PermissionDTO(null, el.getPermission().getVisualName(), el.getPermission().getPermissionName());
            UserPermissionDTO userPermissionNew;
            if(el.getPermission() != null){
                userPermissionNew = new UserPermissionDTO(el.getId(), userMapper.userToUserDto(el.getUser()),
                        permissionMapper.permissionToPermissionDto(el.getPermission()));
            }else {
                userPermissionNew = new UserPermissionDTO(el.getId(), userMapper.userToUserDto(el.getUser()),
                        permissionMapper.permissionToPermissionDto(el.getPermission()));
            }
            listUserPermissionDto.add(userPermissionNew);
        });

        return listUserPermissionDto;
    }
}
