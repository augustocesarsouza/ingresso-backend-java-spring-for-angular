package com.backend.ingresso.application.services;

import com.backend.ingresso.application.services.interfaces.IFinalPaymentCheckoutMovieTicketService;
import com.backend.ingresso.domain.entities.FinalPaymentCheckoutMovieTicket;
import com.backend.ingresso.domain.repositories.IFinalPaymentCheckoutMovieTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinalPaymentCheckoutMovieTicketService implements IFinalPaymentCheckoutMovieTicketService {
    private final IFinalPaymentCheckoutMovieTicketRepository finalPaymentCheckoutMovieTicketRepository;

    @Autowired
    public FinalPaymentCheckoutMovieTicketService(IFinalPaymentCheckoutMovieTicketRepository finalPaymentCheckoutMovieTicketRepository) {
        this.finalPaymentCheckoutMovieTicketRepository = finalPaymentCheckoutMovieTicketRepository;
    }

    @Override
    public ResultService<FinalPaymentCheckoutMovieTicket> create(FinalPaymentCheckoutMovieTicket finalPaymentCheckoutMovieTicket) {
        try {
            var create = finalPaymentCheckoutMovieTicketRepository.create(finalPaymentCheckoutMovieTicket);

            if(create == null)
                return ResultService.Fail("Error created null");

            return ResultService.Ok(create);

        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }
}
