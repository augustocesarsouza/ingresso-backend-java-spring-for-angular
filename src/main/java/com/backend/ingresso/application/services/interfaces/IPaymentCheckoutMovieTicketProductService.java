package com.backend.ingresso.application.services.interfaces;

import com.backend.ingresso.application.dto.PaymentCheckoutMovieTicketProductDTO;
import com.backend.ingresso.application.services.ResultService;
import com.backend.ingresso.domain.entities.PaymentCheckoutMovieTicketProduct;

import java.util.List;
import java.util.UUID;

public interface IPaymentCheckoutMovieTicketProductService {
    ResultService<List<List<PaymentCheckoutMovieTicketProductDTO>>> getInfoAboutBayFinalOfTheUser(UUID userId);
    ResultService<PaymentCheckoutMovieTicketProduct> create(PaymentCheckoutMovieTicketProduct paymentCheckoutMovieTicketProduct);
}
