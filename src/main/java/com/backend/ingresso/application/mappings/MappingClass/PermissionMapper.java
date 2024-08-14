package com.backend.ingresso.application.mappings.MappingClass;

import com.backend.ingresso.application.dto.PermissionDTO;
import com.backend.ingresso.application.dto.UserDTO;
import com.backend.ingresso.application.mappings.MappingClassInterface.IPermissionMapper;
import com.backend.ingresso.domain.entities.Permission;
import com.backend.ingresso.domain.entities.User;
import org.springframework.stereotype.Component;

@Component
public class PermissionMapper implements IPermissionMapper {
    @Override
    public PermissionDTO permissionToPermissionDto(Permission permission) {
        if(permission == null)
            return null;

        return new PermissionDTO(permission.getId(), permission.getVisualName(), permission.getPermissionName());
    }

    @Override
    public Permission permissionDtoToPermission(PermissionDTO permissionDTO) {
        if(permissionDTO == null)
            return null;

        return new Permission(permissionDTO.getId(), permissionDTO.getVisualName(), permissionDTO.getPermissionName());
    }
}
