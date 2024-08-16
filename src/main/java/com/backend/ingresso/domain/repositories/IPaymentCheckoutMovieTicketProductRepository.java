package com.backend.ingresso.domain.repositories;

import com.backend.ingresso.application.services.ResultService;
import com.backend.ingresso.domain.entities.FinalPaymentCheckoutMovie;
import com.backend.ingresso.domain.entities.PaymentCheckoutMovieTicketProduct;

import java.util.List;
import java.util.UUID;

public interface IPaymentCheckoutMovieTicketProductRepository {
    ResultService<List<PaymentCheckoutMovieTicketProduct>> getInfoAboutBayFinalOfTheUser(UUID finalPaymentCheckoutMovieId);
    PaymentCheckoutMovieTicketProduct create(PaymentCheckoutMovieTicketProduct paymentCheckoutMovieTicketProduct);
    PaymentCheckoutMovieTicketProduct delete(UUID paymentCheckoutMovieTicketProductId);
}
