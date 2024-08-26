package com.backend.ingresso.domain.repositories;

import com.backend.ingresso.application.dto.FinalPaymentCheckoutMovieDTO;
import com.backend.ingresso.application.services.ResultService;
import com.backend.ingresso.domain.entities.FinalPaymentCheckoutMovie;

import java.util.List;
import java.util.UUID;

public interface IFinalPaymentCheckoutMovieRepository {
    List<FinalPaymentCheckoutMovieDTO> getByUserIdFinalPaymentCheckoutMovieIds(UUID userId);
    List<FinalPaymentCheckoutMovieDTO> getByUserIdFinalPaymentCheckoutMovieInfo(UUID userId);
    FinalPaymentCheckoutMovie create(FinalPaymentCheckoutMovie finalPaymentCheckoutMovie);
    FinalPaymentCheckoutMovie delete(UUID finalPaymentCheckoutMovieId);
}
