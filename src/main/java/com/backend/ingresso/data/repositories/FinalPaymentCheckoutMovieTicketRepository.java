package com.backend.ingresso.data.repositories;

import com.backend.ingresso.data.context.FinalPaymentCheckoutMovieTicketRepositoryJPA;
import com.backend.ingresso.domain.entities.FinalPaymentCheckoutMovieProduct;
import com.backend.ingresso.domain.entities.FinalPaymentCheckoutMovieTicket;
import com.backend.ingresso.domain.repositories.IFinalPaymentCheckoutMovieTicketRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FinalPaymentCheckoutMovieTicketRepository implements IFinalPaymentCheckoutMovieTicketRepository {
    private final FinalPaymentCheckoutMovieTicketRepositoryJPA finalPaymentCheckoutMovieTicketRepositoryJPA;

    public FinalPaymentCheckoutMovieTicketRepository(FinalPaymentCheckoutMovieTicketRepositoryJPA finalPaymentCheckoutMovieTicketRepositoryJPA) {
        this.finalPaymentCheckoutMovieTicketRepositoryJPA = finalPaymentCheckoutMovieTicketRepositoryJPA;
    }

    @Override
    public FinalPaymentCheckoutMovieTicket getFinalPaymentMovieTicketNULL() {
        return finalPaymentCheckoutMovieTicketRepositoryJPA.findById(UUID.fromString("f9d1fdc6-47ba-48a5-899b-32cce43448e0")).orElse(null);
    }

    @Override
    public FinalPaymentCheckoutMovieTicket create(FinalPaymentCheckoutMovieTicket finalPaymentCheckoutMovieTicket) {
        if(finalPaymentCheckoutMovieTicket == null)
            return null;

        return finalPaymentCheckoutMovieTicketRepositoryJPA.save(finalPaymentCheckoutMovieTicket);
    }

    @Override
    public FinalPaymentCheckoutMovieTicket delete(UUID finalPaymentCheckoutMovieTicketId) {
        if(finalPaymentCheckoutMovieTicketId == null)
            return null;

        var finalPaymentCheckoutMovieTicket = finalPaymentCheckoutMovieTicketRepositoryJPA.findById(finalPaymentCheckoutMovieTicketId).orElse(null);

        if(finalPaymentCheckoutMovieTicket == null)
            return null;

        finalPaymentCheckoutMovieTicketRepositoryJPA.delete(finalPaymentCheckoutMovieTicket);

        return finalPaymentCheckoutMovieTicket;
    }
}
