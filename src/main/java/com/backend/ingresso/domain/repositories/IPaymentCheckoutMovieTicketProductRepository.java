package com.backend.ingresso.domain.repositories;

import com.backend.ingresso.application.dto.PaymentCheckoutMovieTicketProductDTO;
import com.backend.ingresso.domain.entities.PaymentCheckoutMovieTicketProduct;

import java.util.List;
import java.util.UUID;

public interface IPaymentCheckoutMovieTicketProductRepository {
    List<PaymentCheckoutMovieTicketProductDTO> getInfoAboutBayFinalOfTheUser(UUID finalPaymentCheckoutMovieId);
    PaymentCheckoutMovieTicketProduct create(PaymentCheckoutMovieTicketProduct paymentCheckoutMovieTicketProduct);
    PaymentCheckoutMovieTicketProduct delete(UUID paymentCheckoutMovieTicketProductId);
}
