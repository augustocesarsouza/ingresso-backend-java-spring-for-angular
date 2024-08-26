package com.backend.ingresso.application.services.interfaces;

import com.backend.ingresso.application.dto.FinalPaymentCheckoutMovieDTO;
import com.backend.ingresso.application.services.ResultService;
import com.backend.ingresso.domain.entities.FinalPaymentCheckoutMovie;

import java.util.List;
import java.util.UUID;

public interface IFinalPaymentCheckoutMovieService {
    ResultService<List<FinalPaymentCheckoutMovieDTO>> getByUserIdFinalPaymentCheckoutMovieIds(UUID userId);
    ResultService<List<FinalPaymentCheckoutMovieDTO>> getByUserIdFinalPaymentCheckoutMovieInfo(UUID userId);
    ResultService<FinalPaymentCheckoutMovie> create(FinalPaymentCheckoutMovieDTO finalPaymentCheckoutMovieDTO);
}
