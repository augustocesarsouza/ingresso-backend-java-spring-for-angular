package com.backend.ingresso.application.services.interfaces;

import com.backend.ingresso.application.dto.MovieDTO;
import com.backend.ingresso.application.dto.validations.movieValidationDTOs.MovieCreateValidatorDTO;
import com.backend.ingresso.application.dto.validations.movieValidationDTOs.MovieUpdateValidatorDTO;
import com.backend.ingresso.application.services.ResultService;
import com.backend.ingresso.domain.entities.Movie;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.UUID;

public interface IMovieService {
    ResultService<Movie> findById(UUID movieId);
    ResultService<MovieDTO> getCheckIfMovieExistsById(UUID movieId);
    public ResultService<MovieDTO> getInfoForChooseMovie(String movieId);
    public ResultService<List<MovieDTO>> getAllStatusTrending();
    public ResultService<MovieDTO> getStatusHighlight();
    ResultService<List<MovieDTO>> getAllMovieByRegionId(String region);
    ResultService<MovieDTO> getInfoMoviesById(UUID movieId);
    ResultService<MovieDTO> getIdMovieByTitle(String title);
    ResultService<MovieDTO> getStatusMovie(String statusMovie);
    ResultService<MovieDTO> create(MovieCreateValidatorDTO movieCreateValidatorDTO, BindingResult result);
    ResultService<MovieDTO> deleteMovie(UUID movieId);
    ResultService<MovieDTO> updateMovieImg(MovieUpdateValidatorDTO movieUpdateValidatorDTO, BindingResult result);
}
