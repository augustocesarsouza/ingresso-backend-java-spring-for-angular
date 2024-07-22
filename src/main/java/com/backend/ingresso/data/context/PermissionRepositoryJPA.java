package com.backend.ingresso.data.context;

import com.backend.ingresso.domain.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PermissionRepositoryJPA extends JpaRepository<Permission, UUID> {
}
