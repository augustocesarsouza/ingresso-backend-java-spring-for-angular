package com.backend.ingresso.data.context;

import com.backend.ingresso.application.dto.UserPermissionDTO;
import com.backend.ingresso.domain.entities.FinalPaymentCheckoutMovieProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FinalPaymentCheckoutMovieProductRepositoryJPA extends JpaRepository<FinalPaymentCheckoutMovieProduct, UUID> {
//    @Query("SELECT new com.backend.ingresso.application.dto." +
//            "UserPermissionDTO(up.Id, new com.backend.ingresso.application.dto.UserDTO(up.User.Id), new com.backend.ingresso.application.dto." +
//            "PermissionDTO(null, up.Permission.VisualName, up.Permission.PermissionName)) " +
//            "FROM FinalPaymentCheckoutMovieProduct AS fpmp " +
//            "WHERE fpmp.Id = :id")
//    FinalPaymentCheckoutMovieProduct getFinalPaymentMovieProductNULL(UUID id); // MUDAR ISSO
}
