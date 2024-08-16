package com.backend.ingresso.application.services.interfaces;

import com.backend.ingresso.application.services.ResultService;
import com.backend.ingresso.domain.entities.FinalPaymentCheckoutMovieTicket;

public interface IFinalPaymentCheckoutMovieTicketService {
    ResultService<FinalPaymentCheckoutMovieTicket> create(FinalPaymentCheckoutMovieTicket finalPaymentCheckoutMovieTicket);
}
