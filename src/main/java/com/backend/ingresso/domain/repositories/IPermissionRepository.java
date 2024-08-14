package com.backend.ingresso.domain.repositories;

import com.backend.ingresso.application.dto.PermissionDTO;
import com.backend.ingresso.domain.entities.Permission;
import com.backend.ingresso.domain.entities.Region;

import java.util.UUID;

public interface IPermissionRepository {
    PermissionDTO getIdByVisualName(String visualName);
    Permission findById(UUID permissionId);
    Permission create(Permission permission);
}
