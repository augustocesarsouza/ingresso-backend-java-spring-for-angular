package com.backend.ingresso.application.services.interfaces;

import com.backend.ingresso.application.dto.AdditionalFoodMovieDTO;
import com.backend.ingresso.application.dto.validations.additionalFoodMovieDTOs.AdditionalFoodMovieCreate;
import com.backend.ingresso.application.services.ResultService;
import com.backend.ingresso.domain.entities.AdditionalFoodMovie;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.UUID;

public interface IAdditionalFoodMovieService {
    ResultService<List<AdditionalFoodMovieDTO>> getAllFoodMovie(UUID movieId);
    ResultService<AdditionalFoodMovie> findById(UUID formOfPaymentId);
    ResultService<AdditionalFoodMovieDTO> create(AdditionalFoodMovieCreate additionalFoodMovieCreate, BindingResult result);
}
