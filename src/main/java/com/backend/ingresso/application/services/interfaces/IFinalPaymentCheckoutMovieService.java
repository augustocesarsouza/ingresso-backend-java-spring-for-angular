package com.backend.ingresso.application.services.interfaces;

import com.backend.ingresso.application.dto.FinalPaymentCheckoutMovieDTO;
import com.backend.ingresso.application.services.ResultService;
import com.backend.ingresso.domain.entities.FinalPaymentCheckoutMovie;

public interface IFinalPaymentCheckoutMovieService {
    ResultService<FinalPaymentCheckoutMovie> create(FinalPaymentCheckoutMovieDTO finalPaymentCheckoutMovieDTO);
}
