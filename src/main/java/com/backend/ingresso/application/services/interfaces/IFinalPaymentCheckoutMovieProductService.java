package com.backend.ingresso.application.services.interfaces;

import com.backend.ingresso.application.services.ResultService;
import com.backend.ingresso.domain.entities.FinalPaymentCheckoutMovieProduct;

public interface IFinalPaymentCheckoutMovieProductService {
    ResultService<FinalPaymentCheckoutMovieProduct> create(FinalPaymentCheckoutMovieProduct finalPaymentCheckoutMovieProduct);
}
