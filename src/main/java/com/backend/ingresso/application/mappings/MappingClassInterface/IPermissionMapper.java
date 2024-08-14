package com.backend.ingresso.application.mappings.MappingClassInterface;

import com.backend.ingresso.application.dto.PermissionDTO;
import com.backend.ingresso.application.dto.UserDTO;
import com.backend.ingresso.domain.entities.Permission;
import com.backend.ingresso.domain.entities.User;

public interface IPermissionMapper {
    PermissionDTO permissionToPermissionDto(Permission permission);
    Permission permissionDtoToPermission(PermissionDTO permissionDTO);
}
