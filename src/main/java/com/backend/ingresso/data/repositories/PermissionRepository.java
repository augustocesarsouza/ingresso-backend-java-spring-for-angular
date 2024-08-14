package com.backend.ingresso.data.repositories;

import com.backend.ingresso.application.dto.PermissionDTO;
import com.backend.ingresso.data.context.PermissionRepositoryJPA;
import com.backend.ingresso.domain.entities.Permission;
import com.backend.ingresso.domain.repositories.IPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PermissionRepository implements IPermissionRepository {
    private final PermissionRepositoryJPA permissionRepositoryJPA;

    @Autowired
    public PermissionRepository(PermissionRepositoryJPA permissionRepositoryJPA) {
        this.permissionRepositoryJPA = permissionRepositoryJPA;
    }

    @Override
    public PermissionDTO getIdByVisualName(String visualName) {
        return permissionRepositoryJPA.getIdByVisualName(visualName);
    }

    @Override
    public Permission findById(UUID permissionId) {
        return permissionRepositoryJPA.findById(permissionId).orElse(null);
    }

    @Override
    public Permission create(Permission permission) {

        if(permission == null)
            return null;
        return permissionRepositoryJPA.save(permission);
    }
}
