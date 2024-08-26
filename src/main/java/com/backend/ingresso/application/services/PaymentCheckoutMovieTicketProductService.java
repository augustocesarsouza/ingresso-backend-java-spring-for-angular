package com.backend.ingresso.application.services;

import com.backend.ingresso.application.dto.FinalPaymentCheckoutMovieDTO;
import com.backend.ingresso.application.dto.PaymentCheckoutMovieTicketProductDTO;
import com.backend.ingresso.application.services.interfaces.IFinalPaymentCheckoutMovieService;
import com.backend.ingresso.application.services.interfaces.IPaymentCheckoutMovieTicketProductService;
import com.backend.ingresso.domain.entities.PaymentCheckoutMovieTicketProduct;
import com.backend.ingresso.domain.repositories.IPaymentCheckoutMovieTicketProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PaymentCheckoutMovieTicketProductService implements IPaymentCheckoutMovieTicketProductService {
    private final IPaymentCheckoutMovieTicketProductRepository paymentCheckoutMovieTicketProductRepository;
    private final IFinalPaymentCheckoutMovieService finalPaymentCheckoutMovieService;

    @Autowired
    public PaymentCheckoutMovieTicketProductService(IPaymentCheckoutMovieTicketProductRepository paymentCheckoutMovieTicketProductRepository,
                                                    IFinalPaymentCheckoutMovieService finalPaymentCheckoutMovieService) {
        this.paymentCheckoutMovieTicketProductRepository = paymentCheckoutMovieTicketProductRepository;
        this.finalPaymentCheckoutMovieService = finalPaymentCheckoutMovieService;
    }

    @Override
    public ResultService<List<List<PaymentCheckoutMovieTicketProductDTO>>> getInfoAboutBayFinalOfTheUser(UUID userId) {
        try {
            var resultFinalPaymentCheckoutMovieList = finalPaymentCheckoutMovieService.getByUserIdFinalPaymentCheckoutMovieIds(userId);

            if(!resultFinalPaymentCheckoutMovieList.IsSuccess)
                return ResultService.Fail(resultFinalPaymentCheckoutMovieList.Message);

            List<FinalPaymentCheckoutMovieDTO> finalPaymentCheckoutMovieList = resultFinalPaymentCheckoutMovieList.Data;

            List<List<PaymentCheckoutMovieTicketProductDTO>> value = new java.util.ArrayList<>(List.of());
            // abtes de fazer o forEach, ali de baixo faz um get e pega o primeiro objeto que tenha esse id "tb_final_payment_checkout_movie"
            // que esteja qui "tb_payment_checkout_movie_ticket_product" ai verifica o "payment_checkout_movie_product_id" se for null
            // você faz o get ali do "QUERY" sem "AdditionalFoodMovieDTO" coloca null

            if(finalPaymentCheckoutMovieList.isEmpty())
                return ResultService.Fail("is empty finalPaymentCheckoutMovieList");

            finalPaymentCheckoutMovieList.forEach((el) -> {
                List<PaymentCheckoutMovieTicketProductDTO> resultGet = paymentCheckoutMovieTicketProductRepository.getInfoAboutBayFinalOfTheUser(el.getId());
                // se não tiver

                if(resultGet != null && !resultGet.isEmpty()){
                    value.add(resultGet);
                }
            });

            return ResultService.Ok(value);

        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<PaymentCheckoutMovieTicketProduct> create(PaymentCheckoutMovieTicketProduct paymentCheckoutMovieTicketProduct) {
        try {
            if(paymentCheckoutMovieTicketProduct.getFinalPaymentCheckoutMovie() == null)
                return ResultService.Fail("final_payment-checkout_movie is null");

            var createPaymentCheckoutMovieTicketProduct = paymentCheckoutMovieTicketProductRepository.create(paymentCheckoutMovieTicketProduct);

            return ResultService.Ok(createPaymentCheckoutMovieTicketProduct);

        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }
}
