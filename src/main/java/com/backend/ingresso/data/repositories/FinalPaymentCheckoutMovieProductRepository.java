package com.backend.ingresso.data.repositories;

import com.backend.ingresso.data.context.FinalPaymentCheckoutMovieProductRepositoryJPA;
import com.backend.ingresso.domain.entities.FinalPaymentCheckoutMovieProduct;
import com.backend.ingresso.domain.repositories.IFinalPaymentCheckoutMovieProductRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FinalPaymentCheckoutMovieProductRepository implements IFinalPaymentCheckoutMovieProductRepository {
    private final FinalPaymentCheckoutMovieProductRepositoryJPA finalPaymentCheckoutMovieProductRepositoryJPA;

    public FinalPaymentCheckoutMovieProductRepository(FinalPaymentCheckoutMovieProductRepositoryJPA finalPaymentCheckoutMovieProductRepositoryJPA) {
        this.finalPaymentCheckoutMovieProductRepositoryJPA = finalPaymentCheckoutMovieProductRepositoryJPA;
    }

    @Override
    public FinalPaymentCheckoutMovieProduct getFinalPaymentMovieProductNULL() {
        return finalPaymentCheckoutMovieProductRepositoryJPA.findById(UUID.fromString("bf0923d8-cc68-4520-84ff-18ceb48aa1d9")).orElse(null);
    }

    @Override
    public FinalPaymentCheckoutMovieProduct create(FinalPaymentCheckoutMovieProduct finalPaymentCheckoutMovieProduct) {
        if(finalPaymentCheckoutMovieProduct == null)
            return null;

        return finalPaymentCheckoutMovieProductRepositoryJPA.save(finalPaymentCheckoutMovieProduct);
    }

    @Override
    public FinalPaymentCheckoutMovieProduct delete(UUID finalPaymentCheckoutMovieProductId) {
        if(finalPaymentCheckoutMovieProductId == null)
            return null;

        var finalPaymentCheckoutMovieProduct = finalPaymentCheckoutMovieProductRepositoryJPA.findById(finalPaymentCheckoutMovieProductId).orElse(null);

        if(finalPaymentCheckoutMovieProduct == null)
            return null;

        finalPaymentCheckoutMovieProductRepositoryJPA.delete(finalPaymentCheckoutMovieProduct);

        return finalPaymentCheckoutMovieProduct;
    }
}
