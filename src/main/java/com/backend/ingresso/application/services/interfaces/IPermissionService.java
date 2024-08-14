package com.backend.ingresso.application.services.interfaces;

import com.backend.ingresso.application.dto.PermissionDTO;
import com.backend.ingresso.application.services.ResultService;
import com.backend.ingresso.domain.entities.Permission;

import java.util.UUID;

public interface IPermissionService {
    ResultService<PermissionDTO> getIdByVisualName(String visualName);
    ResultService<Permission> findById(UUID permissionId);
}
