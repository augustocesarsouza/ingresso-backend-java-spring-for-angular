package com.backend.ingresso.data.repositories;

import com.backend.ingresso.data.context.PermissionRepositoryJPA;
import com.backend.ingresso.domain.entities.Permission;
import com.backend.ingresso.domain.repositories.IPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PermissionRepository implements IPermissionRepository {
    private final PermissionRepositoryJPA permissionRepositoryJPA;

    @Autowired
    public PermissionRepository(PermissionRepositoryJPA permissionRepositoryJPA) {
        this.permissionRepositoryJPA = permissionRepositoryJPA;
    }

    @Override
    public Permission create(Permission permission) {

        if(permission == null)
            return null;
        return permissionRepositoryJPA.save(permission);
    }
}
