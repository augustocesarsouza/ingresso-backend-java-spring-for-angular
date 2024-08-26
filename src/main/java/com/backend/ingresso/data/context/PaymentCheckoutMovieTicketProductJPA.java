package com.backend.ingresso.data.context;

import com.backend.ingresso.application.dto.*;
import com.backend.ingresso.domain.entities.FinalPaymentCheckoutMovie;
import com.backend.ingresso.domain.entities.PaymentCheckoutMovieTicketProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PaymentCheckoutMovieTicketProductJPA extends JpaRepository<PaymentCheckoutMovieTicketProduct, UUID> {
    @Query("SELECT new com.backend.ingresso.application.dto." +
            "PaymentCheckoutMovieTicketProductDTO(null, new com.backend.ingresso.application.dto.FinalPaymentCheckoutMovieDTO(pcmtp.FinalPaymentCheckoutMovie.Id), new com.backend.ingresso.application.dto." +
            "FinalPaymentCheckoutMovieTicketDTO(null, new com.backend.ingresso.application.dto.FormOfPaymentDTO(null, pcmtp.FinalPaymentCheckoutMovieTicket.FormOfPayment.FormName, null, null, null), " +
            "pcmtp.FinalPaymentCheckoutMovieTicket.QuantityTicket), new com.backend.ingresso.application.dto.FinalPaymentCheckoutMovieProductDTO(null, " +
            "new com.backend.ingresso.application.dto.AdditionalFoodMovieDTO(null, finalProduct.AdditionalFoodMovie.Title, null, null, null, null, null, null), pcmtp.FinalPaymentCheckoutMovieProduct.QuantityProduct)) " +
            "FROM PaymentCheckoutMovieTicketProduct AS pcmtp " +
            "LEFT JOIN pcmtp.FinalPaymentCheckoutMovieProduct finalProduct " +
            "WHERE pcmtp.FinalPaymentCheckoutMovie.Id = :paymentCheckoutMovieTicketId")
    List<PaymentCheckoutMovieTicketProductDTO> getInfoAboutBayFinalOfTheUser(UUID paymentCheckoutMovieTicketId);
    // Ticket e Product que estiver "null" e algum deles vai ter uma string "só retirnar porque nao deu para nao colocar sem colocar essa string null"

// PaymentCheckoutMovieTicketProduct(UUID id, FinalPaymentCheckoutMovie finalPaymentCheckoutMovie,
//                                  FinalPaymentCheckoutMovieTicket finalPaymentCheckoutMovieTicket,
//                                  FinalPaymentCheckoutMovieProduct finalPaymentCheckoutMovieProduct)

// FinalPaymentCheckoutMovieTicket(UUID id, FormOfPayment formOfPayment, Integer quantityTicket)
// FinalPaymentCheckoutMovieProduct(UUID id, AdditionalFoodMovie additionalFoodMovie, Integer quantityProduct)

// FormOfPayment(UUID id, String formName, String price, UUID movieId, Movie movie)
// AdditionalFoodMovie(UUID id, String title, String price, String fee, String imgUrl, String publicId, UUID movieId, Movie movie)
}
