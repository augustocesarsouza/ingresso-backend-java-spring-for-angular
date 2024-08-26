package com.backend.ingresso.api.controllers;

import com.backend.ingresso.application.dto.PaymentCheckoutMovieTicketProductDTO;
import com.backend.ingresso.application.services.ResultService;
import com.backend.ingresso.application.services.interfaces.IPaymentCheckoutMovieTicketProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Component
@RestController
@CrossOrigin
@RequestMapping("/v1")
public class PaymentCheckoutMovieTicketProductController {
    private final IPaymentCheckoutMovieTicketProductService paymentCheckoutMovieTicketProductService;

    @Autowired
    public PaymentCheckoutMovieTicketProductController(IPaymentCheckoutMovieTicketProductService paymentCheckoutMovieTicketProductService) {
        this.paymentCheckoutMovieTicketProductService = paymentCheckoutMovieTicketProductService;
    }

    @GetMapping("/public/final-payment-checkout/get-info-about-bay-final/{userId}")
    public ResponseEntity<ResultService<List<List<PaymentCheckoutMovieTicketProductDTO>>>> getInfoAboutBayFinalOfTheUser(@PathVariable String userId){
        var result = paymentCheckoutMovieTicketProductService.getInfoAboutBayFinalOfTheUser(UUID.fromString(userId));

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }
}
