package com.backend.ingresso.data.context;
import com.backend.ingresso.application.dto.UserPermissionDTO;

import com.backend.ingresso.domain.entities.User;
import com.backend.ingresso.domain.entities.UserPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserPermissionRepositoryJPA extends JpaRepository<UserPermission, UUID> {
    //UserPermissionDTO(UUID id, UUID userId, User user, UUID permissionId, Permission permission)
    //@Query(value = queryPass, nativeQuery = true)

//    @Query("SELECT new com.backend.ingresso.application.dto." +
//            "UserPermissionDTO(up.Id, up.UserId, null, null, new com.backend.ingresso.application.dto." +
//            "PermissionDTO(null, ps.VisualName, ps.PermissionName)) " +
//            "FROM UserPermission AS up " +
//            "INNER JOIN Permission AS ps ON up.PermissionId = ps.Id WHERE up.UserId = :idUser")
    @Query("SELECT new com.backend.ingresso.application.dto." +
        "UserPermissionDTO(up.Id, new com.backend.ingresso.application.dto.UserDTO(up.User.Id), new com.backend.ingresso.application.dto." +
        "PermissionDTO(null, up.Permission.VisualName, up.Permission.PermissionName)) " +
        "FROM UserPermission AS up " +
        "WHERE up.User.Id = :idUser")
    List<UserPermissionDTO> getAllPermissionUser(UUID idUser);
}