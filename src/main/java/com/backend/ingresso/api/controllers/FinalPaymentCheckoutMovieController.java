package com.backend.ingresso.api.controllers;

import com.backend.ingresso.application.dto.CinemaMovieDTO;
import com.backend.ingresso.application.dto.FinalPaymentCheckoutMovieDTO;
import com.backend.ingresso.application.dto.FormOfPaymentDTO;
import com.backend.ingresso.application.dto.validations.formOfPaymentDTOs.FormOfPaymentCreate;
import com.backend.ingresso.application.services.ResultService;
import com.backend.ingresso.application.services.interfaces.IFinalPaymentCheckoutMovieService;
import com.backend.ingresso.domain.entities.FinalPaymentCheckoutMovie;
import com.backend.ingresso.domain.entities.PaymentCheckoutMovieTicketProduct;
import com.backend.ingresso.domain.repositories.IFinalPaymentCheckoutMovieRepository;
import com.backend.ingresso.domain.repositories.IPaymentCheckoutMovieTicketProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Component
@RestController
@CrossOrigin
@RequestMapping("/v1")
public class FinalPaymentCheckoutMovieController {
    private final IFinalPaymentCheckoutMovieService finalPaymentCheckoutMovieService;
    private final IPaymentCheckoutMovieTicketProductRepository paymentCheckoutMovieTicketProductRepository;
    // Criar amanha o 'paymentCheckoutMovieTicketProductRepository' Service dele e retornar um DTO e no retorno tirar esses NULL ""id": null,"

    @Autowired
    public FinalPaymentCheckoutMovieController(IFinalPaymentCheckoutMovieService finalPaymentCheckoutMovieService, IPaymentCheckoutMovieTicketProductRepository paymentCheckoutMovieTicketProductRepository) {
        this.finalPaymentCheckoutMovieService = finalPaymentCheckoutMovieService;
        this.paymentCheckoutMovieTicketProductRepository = paymentCheckoutMovieTicketProductRepository;
    }

    @GetMapping("/public/final-payment-checkout/get-info-about-bay-final/{paymentCheckoutMovieTicketId}")
    public ResponseEntity<ResultService<List<PaymentCheckoutMovieTicketProduct>>> getInfoAboutBayFinalOfTheUser(@PathVariable String paymentCheckoutMovieTicketId){
        var result = paymentCheckoutMovieTicketProductRepository.getInfoAboutBayFinalOfTheUser(UUID.fromString(paymentCheckoutMovieTicketId));

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/public/final-payment-checkout-movie/create")
    public ResponseEntity<ResultService<FinalPaymentCheckoutMovie>> create(@RequestBody FinalPaymentCheckoutMovieDTO finalPaymentCheckoutMovieDTO){
        var result = finalPaymentCheckoutMovieService.create(finalPaymentCheckoutMovieDTO);

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }
}
