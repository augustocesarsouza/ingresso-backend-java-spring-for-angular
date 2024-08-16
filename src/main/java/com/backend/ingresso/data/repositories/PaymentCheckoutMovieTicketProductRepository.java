package com.backend.ingresso.data.repositories;

import com.backend.ingresso.application.services.ResultService;
import com.backend.ingresso.data.context.PaymentCheckoutMovieTicketProductJPA;
import com.backend.ingresso.domain.entities.FinalPaymentCheckoutMovie;
import com.backend.ingresso.domain.entities.PaymentCheckoutMovieTicketProduct;
import com.backend.ingresso.domain.repositories.IPaymentCheckoutMovieTicketProductRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class PaymentCheckoutMovieTicketProductRepository implements IPaymentCheckoutMovieTicketProductRepository {
    private final PaymentCheckoutMovieTicketProductJPA paymentCheckoutMovieTicketProductJPA;

    public PaymentCheckoutMovieTicketProductRepository(PaymentCheckoutMovieTicketProductJPA paymentCheckoutMovieTicketProductJPA) {
        this.paymentCheckoutMovieTicketProductJPA = paymentCheckoutMovieTicketProductJPA;
    }

    @Override
    public ResultService<List<PaymentCheckoutMovieTicketProduct>> getInfoAboutBayFinalOfTheUser(UUID paymentCheckoutMovieTicketId){
        var resultGet = paymentCheckoutMovieTicketProductJPA.getInfoAboutBayFinalOfTheUser(paymentCheckoutMovieTicketId);

        return ResultService.Ok(resultGet);
    }

    @Override
    public PaymentCheckoutMovieTicketProduct create(PaymentCheckoutMovieTicketProduct paymentCheckoutMovieTicketProduct) {
        if(paymentCheckoutMovieTicketProduct == null)
            return null;

        return paymentCheckoutMovieTicketProductJPA.save(paymentCheckoutMovieTicketProduct);
    }

    @Override
    public PaymentCheckoutMovieTicketProduct delete(UUID paymentCheckoutMovieTicketProductId) {
        if(paymentCheckoutMovieTicketProductId == null)
            return null;

        var paymentCheckoutMovieTicketProduct = paymentCheckoutMovieTicketProductJPA.findById(paymentCheckoutMovieTicketProductId).orElse(null);

        if(paymentCheckoutMovieTicketProduct == null)
            return null;

        paymentCheckoutMovieTicketProductJPA.delete(paymentCheckoutMovieTicketProduct);

        return paymentCheckoutMovieTicketProduct;
    }
}
