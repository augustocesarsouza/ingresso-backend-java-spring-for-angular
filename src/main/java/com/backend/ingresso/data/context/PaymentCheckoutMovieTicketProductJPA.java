package com.backend.ingresso.data.context;

import com.backend.ingresso.application.dto.UserPermissionDTO;
import com.backend.ingresso.domain.entities.FinalPaymentCheckoutMovie;
import com.backend.ingresso.domain.entities.PaymentCheckoutMovieTicketProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PaymentCheckoutMovieTicketProductJPA extends JpaRepository<PaymentCheckoutMovieTicketProduct, UUID> {
    @Query("SELECT new com.backend.ingresso.domain.entities." +
            "PaymentCheckoutMovieTicketProduct(null, null, new com.backend.ingresso.domain.entities." +
            "FinalPaymentCheckoutMovieTicket(null, new com.backend.ingresso.domain.entities.FormOfPayment(null, pcmtp.FinalPaymentCheckoutMovieTicket.FormOfPayment.FormName, null, null, null), " +
            "pcmtp.FinalPaymentCheckoutMovieTicket.QuantityTicket), new com.backend.ingresso.domain.entities.FinalPaymentCheckoutMovieProduct(null, " +
            "new com.backend.ingresso.domain.entities.AdditionalFoodMovie(null, pcmtp.FinalPaymentCheckoutMovieProduct.AdditionalFoodMovie.Title, null, null, null, null, null, null), pcmtp.FinalPaymentCheckoutMovieProduct.QuantityProduct)) " +
            "FROM PaymentCheckoutMovieTicketProduct AS pcmtp " +
            "WHERE pcmtp.FinalPaymentCheckoutMovie.Id = :paymentCheckoutMovieTicketId")
    List<PaymentCheckoutMovieTicketProduct> getInfoAboutBayFinalOfTheUser(UUID paymentCheckoutMovieTicketId);

// PaymentCheckoutMovieTicketProduct(UUID id, FinalPaymentCheckoutMovie finalPaymentCheckoutMovie,
//                                  FinalPaymentCheckoutMovieTicket finalPaymentCheckoutMovieTicket,
//                                  FinalPaymentCheckoutMovieProduct finalPaymentCheckoutMovieProduct)

// FinalPaymentCheckoutMovieTicket(UUID id, FormOfPayment formOfPayment, Integer quantityTicket)
// FinalPaymentCheckoutMovieProduct(UUID id, AdditionalFoodMovie additionalFoodMovie, Integer quantityProduct)

// FormOfPayment(UUID id, String formName, String price, UUID movieId, Movie movie)
// AdditionalFoodMovie(UUID id, String title, String price, String fee, String imgUrl, String publicId, UUID movieId, Movie movie)
}
