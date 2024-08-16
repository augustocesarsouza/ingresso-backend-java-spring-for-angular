package com.backend.ingresso.data.context;

import com.backend.ingresso.application.dto.UserPermissionDTO;
import com.backend.ingresso.domain.entities.FinalPaymentCheckoutMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FinalPaymentCheckoutMovieJPA extends JpaRepository<FinalPaymentCheckoutMovie, UUID> {
    @Query("SELECT new com.backend.ingresso.application.dto." +
            "UserPermissionDTO(up.Id, new com.backend.ingresso.application.dto.UserDTO(up.User.Id), new com.backend.ingresso.application.dto." +
            "PermissionDTO(null, up.Permission.VisualName, up.Permission.PermissionName)) " +
            "FROM UserPermission AS up " +
            "WHERE up.User.Id = :idUser")
    List<UserPermissionDTO> getAllPermissionUser(UUID idUser); // MUDAR ISSO
}
