package com.backend.ingresso.domain.repositories;

import com.backend.ingresso.domain.entities.FinalPaymentCheckoutMovieProduct;

import java.util.UUID;

public interface IFinalPaymentCheckoutMovieProductRepository {
    FinalPaymentCheckoutMovieProduct create(FinalPaymentCheckoutMovieProduct finalPaymentCheckoutMovieProduct);
    FinalPaymentCheckoutMovieProduct delete(UUID finalPaymentCheckoutMovieProductId);
}
