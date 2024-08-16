package com.backend.ingresso.application.services;

import com.backend.ingresso.application.services.interfaces.IFinalPaymentCheckoutMovieProductService;
import com.backend.ingresso.domain.entities.FinalPaymentCheckoutMovieProduct;
import com.backend.ingresso.domain.repositories.IFinalPaymentCheckoutMovieProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinalPaymentCheckoutMovieProductService implements IFinalPaymentCheckoutMovieProductService {
    private final IFinalPaymentCheckoutMovieProductRepository finalPaymentCheckoutMovieProductRepository;

    @Autowired
    public FinalPaymentCheckoutMovieProductService(IFinalPaymentCheckoutMovieProductRepository finalPaymentCheckoutMovieProductRepository) {
        this.finalPaymentCheckoutMovieProductRepository = finalPaymentCheckoutMovieProductRepository;
    }

    @Override
    public ResultService<FinalPaymentCheckoutMovieProduct> create(FinalPaymentCheckoutMovieProduct finalPaymentCheckoutMovieProduct) {
        try {
            var create = finalPaymentCheckoutMovieProductRepository.create(finalPaymentCheckoutMovieProduct);

            if(create == null)
                return ResultService.Fail("Error created null");

            return ResultService.Ok(create);

        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }
}
