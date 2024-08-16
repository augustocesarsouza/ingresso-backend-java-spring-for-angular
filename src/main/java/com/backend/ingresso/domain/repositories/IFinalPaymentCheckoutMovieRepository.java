package com.backend.ingresso.domain.repositories;

import com.backend.ingresso.application.dto.FinalPaymentCheckoutMovieDTO;
import com.backend.ingresso.application.services.ResultService;
import com.backend.ingresso.domain.entities.FinalPaymentCheckoutMovie;

import java.util.UUID;

public interface IFinalPaymentCheckoutMovieRepository {
    FinalPaymentCheckoutMovie create(FinalPaymentCheckoutMovie finalPaymentCheckoutMovie);
    FinalPaymentCheckoutMovie delete(UUID finalPaymentCheckoutMovieId);
}
