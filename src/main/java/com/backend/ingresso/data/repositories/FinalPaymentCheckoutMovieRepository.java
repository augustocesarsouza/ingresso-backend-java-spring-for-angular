package com.backend.ingresso.data.repositories;

import com.backend.ingresso.application.dto.FinalPaymentCheckoutMovieDTO;
import com.backend.ingresso.data.context.FinalPaymentCheckoutMovieJPA;
import com.backend.ingresso.domain.entities.*;
import com.backend.ingresso.domain.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class FinalPaymentCheckoutMovieRepository implements IFinalPaymentCheckoutMovieRepository {
    private final FinalPaymentCheckoutMovieJPA finalPaymentCheckoutMovieJPA;

    public FinalPaymentCheckoutMovieRepository(FinalPaymentCheckoutMovieJPA finalPaymentCheckoutMovieJPA) {
        this.finalPaymentCheckoutMovieJPA = finalPaymentCheckoutMovieJPA;
    }

    @Override
    public List<FinalPaymentCheckoutMovieDTO> getByUserIdFinalPaymentCheckoutMovieIds(UUID userId) {
        var finalPaymentCheckoutMovieCreated = finalPaymentCheckoutMovieJPA.getByUserIdFinalPaymentCheckoutMovieIds(userId);

        return finalPaymentCheckoutMovieCreated;
    }

    @Override
    public List<FinalPaymentCheckoutMovieDTO> getByUserIdFinalPaymentCheckoutMovieInfo(UUID userId) {
        var finalPaymentCheckoutMovieCreated = finalPaymentCheckoutMovieJPA.getByUserIdFinalPaymentCheckoutMovieInfo(userId);

        return finalPaymentCheckoutMovieCreated;
    }

    @Override
    public FinalPaymentCheckoutMovie create(FinalPaymentCheckoutMovie finalPaymentCheckoutMovie) {
        var finalPaymentCheckoutMovieCreated = finalPaymentCheckoutMovieJPA.save(finalPaymentCheckoutMovie);

        return finalPaymentCheckoutMovieCreated;
    }

    @Override
    public FinalPaymentCheckoutMovie delete(UUID finalPaymentCheckoutMovieId) {
        if(finalPaymentCheckoutMovieId == null)
            return null;

        var finalPaymentCheckoutMovie = finalPaymentCheckoutMovieJPA.findById(finalPaymentCheckoutMovieId).orElse(null);

        if(finalPaymentCheckoutMovie == null)
            return null;

        finalPaymentCheckoutMovieJPA.delete(finalPaymentCheckoutMovie);

        return finalPaymentCheckoutMovie;
    }
}
