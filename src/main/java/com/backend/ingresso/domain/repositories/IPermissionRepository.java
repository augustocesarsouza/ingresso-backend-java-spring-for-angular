package com.backend.ingresso.domain.repositories;

import com.backend.ingresso.domain.entities.Permission;
import com.backend.ingresso.domain.entities.Region;

public interface IPermissionRepository {
    Permission create(Permission permission);
}
