package com.backend.ingresso.application.services.interfaces;

import com.backend.ingresso.application.dto.CinemaDTO;
import com.backend.ingresso.application.dto.validations.cinemaDTOs.CinemaCreateDTO;
import com.backend.ingresso.application.services.ResultService;
import com.backend.ingresso.domain.entities.Cinema;
import org.springframework.validation.BindingResult;

import java.util.UUID;

public interface ICinemaService {
    ResultService<Cinema> findById(UUID cinemaId);
    ResultService<CinemaDTO> getCheckIfCinemaExistsById(UUID cinemaId);
    ResultService<CinemaDTO> create(CinemaCreateDTO cinemaCreateDTO, BindingResult result);
    ResultService<CinemaDTO> delete(UUID cinemaId);
}
