package com.backend.ingresso.api.controllers;

import com.backend.ingresso.application.dto.FinalPaymentCheckoutMovieDTO;
import com.backend.ingresso.application.dto.PaymentCheckoutMovieTicketProductDTO;
import com.backend.ingresso.application.services.ResultService;
import com.backend.ingresso.application.services.interfaces.IFinalPaymentCheckoutMovieService;
import com.backend.ingresso.domain.entities.FinalPaymentCheckoutMovie;
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
public class FinalPaymentCheckoutMovieController {
    private final IFinalPaymentCheckoutMovieService finalPaymentCheckoutMovieService;

    @Autowired
    public FinalPaymentCheckoutMovieController(IFinalPaymentCheckoutMovieService finalPaymentCheckoutMovieService) {
        this.finalPaymentCheckoutMovieService = finalPaymentCheckoutMovieService;
    }

    @GetMapping("/public/final-payment-checkout/get-info-about-bay-final-info-user-id/{userId}")
    public ResponseEntity<ResultService<List<FinalPaymentCheckoutMovieDTO>>> getByUserIdFinalPaymentCheckoutMovieInfo(@PathVariable String userId){
        var result = finalPaymentCheckoutMovieService.getByUserIdFinalPaymentCheckoutMovieInfo(UUID.fromString(userId));

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
