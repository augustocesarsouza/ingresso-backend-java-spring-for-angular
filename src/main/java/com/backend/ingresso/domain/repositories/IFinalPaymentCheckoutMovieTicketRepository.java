package com.backend.ingresso.domain.repositories;

import com.backend.ingresso.domain.entities.FinalPaymentCheckoutMovieProduct;
import com.backend.ingresso.domain.entities.FinalPaymentCheckoutMovieTicket;

import java.util.UUID;

public interface IFinalPaymentCheckoutMovieTicketRepository {
    FinalPaymentCheckoutMovieTicket getFinalPaymentMovieTicketNULL();
    FinalPaymentCheckoutMovieTicket create(FinalPaymentCheckoutMovieTicket finalPaymentCheckoutMovieTicket);
    FinalPaymentCheckoutMovieTicket delete(UUID finalPaymentCheckoutMovieTicketId);
}
