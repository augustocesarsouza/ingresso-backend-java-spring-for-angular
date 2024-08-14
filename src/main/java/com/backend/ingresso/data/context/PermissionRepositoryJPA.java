package com.backend.ingresso.data.context;

import com.backend.ingresso.application.dto.PermissionDTO;
import com.backend.ingresso.domain.entities.Permission;
import com.backend.ingresso.domain.entities.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface PermissionRepositoryJPA extends JpaRepository<Permission, UUID> {
    @Query("SELECT new com.backend.ingresso.application.dto." +
            "PermissionDTO(p.Id, null, null) " +
            "FROM Permission AS p WHERE p.VisualName = :visualName")
    PermissionDTO getIdByVisualName(String visualName);

//    Permission(UUID id, String visualName, String permissionName
}
