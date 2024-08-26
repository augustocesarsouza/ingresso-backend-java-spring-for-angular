package com.backend.ingresso.data.context;

import com.backend.ingresso.application.dto.*;
import com.backend.ingresso.domain.entities.FinalPaymentCheckoutMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FinalPaymentCheckoutMovieJPA extends JpaRepository<FinalPaymentCheckoutMovie, UUID> {
    @Query("SELECT new com.backend.ingresso.application.dto." +
            "FinalPaymentCheckoutMovieDTO(fpcm.Id, null, null, " +
            "null, null) " +
            "FROM FinalPaymentCheckoutMovie AS fpcm " +
            "WHERE fpcm.User.Id = :idUser")
    List<FinalPaymentCheckoutMovieDTO> getByUserIdFinalPaymentCheckoutMovieIds(UUID idUser);
    //MovieDTO(UUID id, String title, String description, String gender, String duration, Integer movieRating, String imgUrl, String imgUrlBackground)

    @Query("SELECT new com.backend.ingresso.application.dto." +
            "FinalPaymentCheckoutMovieDTO(fpcm.Id, null, new com.backend.ingresso.application.dto.MovieDTO(null, fpcm.Movie.Title, null, null, fpcm.Movie.Duration, null, fpcm.Movie.ImgUrl, null), " +
            "new com.backend.ingresso.application.dto.CinemaDTO(null, fpcm.Cinema.NameCinema, fpcm.Cinema.District), fpcm.Seats) " +
            "FROM FinalPaymentCheckoutMovie AS fpcm " +
            "WHERE fpcm.User.Id = :idUser")
    List<FinalPaymentCheckoutMovieDTO> getByUserIdFinalPaymentCheckoutMovieInfo(UUID idUser);
    // ESSE AQUI VAI SER Quando, Usuario pedir os pedidos dele "Ele vai chamar tanto todos os produtos e tickets que ele pagous"

    // ESSE AQUI De cima vai trazer o nome do filme e o Local que é "Cinema", Então vai ter 2 GETS LÁ
    // "PaymentCheckoutMovieTicketProductJPA" talvez aqui nesse JPÁ você tenha que trazer o id "DO MovieId"
    // Para comparar depois porque vão ser dois GETS SEPARADOS
}

// FinalPaymentCheckoutMovieDTO(UUID id, UserDTO userDTO, MovieDTO movieDTO, CinemaDTO cinemaDTO, String seats)
// MovieDTO(UUID id, String title, String description, String gender, String duration, Integer movieRating, String imgUrl, String imgUrlBackground)
// CinemaDTO(UUID id, String nameCinema, String district, String ranking)