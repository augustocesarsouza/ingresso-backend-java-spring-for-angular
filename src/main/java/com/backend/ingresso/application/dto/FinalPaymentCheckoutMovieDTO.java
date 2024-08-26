package com.backend.ingresso.application.dto;

import com.backend.ingresso.domain.entities.Cinema;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FinalPaymentCheckoutMovieDTO {
    @JsonIgnore
    private UUID id;
    @JsonProperty("finalPaymentCheckoutMovieId")
    private UUID Id;
    @JsonProperty("userDTO")
    private UserDTO UserDTO;
    @JsonProperty("userId")
    private String UserId;
    @JsonProperty("movieDTO")
    private MovieDTO MovieDTO;
    @JsonProperty("movieId")
    private String MovieId;
    @JsonProperty("cinemaDTO")
    private CinemaDTO CinemaDTO;
    @JsonProperty("cinemaId")
    private String CinemaId;
    @JsonProperty("seats")
    private String Seats;
    @JsonProperty("objTicketDTO")
    private List<ObjTicketDTO> ObjTicketDTO;
    @JsonProperty("objProductDTO")
    private List<ObjProductDTO> ObjProductDTO;

    public FinalPaymentCheckoutMovieDTO(UUID id, UserDTO userDTO, MovieDTO movieDTO, CinemaDTO cinemaDTO, String seats,
                                        List<ObjTicketDTO> objTicketDTO, List<ObjProductDTO> objProductDTO) {
        Id = id;
        UserDTO = userDTO;
        MovieDTO = movieDTO;
        CinemaDTO = cinemaDTO;
        Seats = seats;
        ObjTicketDTO = objTicketDTO;
        ObjProductDTO = objProductDTO;
    }

    public FinalPaymentCheckoutMovieDTO(UUID id, UserDTO userDTO, String userId, MovieDTO movieDTO, String movieId, CinemaDTO cinemaDTO, String cinemaId, String seats) {
        Id = id;
        UserDTO = userDTO;
        UserId = userId;
        MovieDTO = movieDTO;
        MovieId = movieId;
        CinemaDTO = cinemaDTO;
        CinemaId = cinemaId;
        Seats = seats;
    }

    public FinalPaymentCheckoutMovieDTO(UUID id, UserDTO userDTO, MovieDTO movieDTO, CinemaDTO cinemaDTO, String seats) {
        Id = id;
        UserDTO = userDTO;
        MovieDTO = movieDTO;
        CinemaDTO = cinemaDTO;
        Seats = seats;
    }

    public FinalPaymentCheckoutMovieDTO(UUID id) {
        Id = id;
    }

    public FinalPaymentCheckoutMovieDTO() {
    }

    public UUID getId() {
        return Id;
    }

    public UserDTO getUserDTO() {
        return UserDTO;
    }

    public MovieDTO getMovieDTO() {
        return MovieDTO;
    }

    public CinemaDTO getCinemaDTO() {
        return CinemaDTO;
    }

    public String getSeats() {
        return Seats;
    }

    public List<ObjTicketDTO> getObjTicketDTO() {
        return ObjTicketDTO;
    }

    public List<ObjProductDTO> getObjProductDTO() {
        return ObjProductDTO;
    }

    public String getUserId() {
        return UserId;
    }
    public String getMovieId() {
        return MovieId;
    }
    public String getCinemaId() {
        return CinemaId;
    }

    public void setId(UUID id) {
        Id = id;
    }
    public void setUserDTO(UserDTO userDTO) {
        UserDTO = userDTO;
    }

    public void setMovieDTO(MovieDTO movieDTO) {
        MovieDTO = movieDTO;
    }

    public void setCinemaDTO(CinemaDTO cinemaDTO) {
        CinemaDTO = cinemaDTO;
    }

    public void setSeats(String seats) {
        Seats = seats;
    }

    public void setObjTicketDTO(List<ObjTicketDTO> objTicketDTO) {
        ObjTicketDTO = objTicketDTO;
    }

    public void setObjProductDTO(List<ObjProductDTO> objProductDTO) {
        ObjProductDTO = objProductDTO;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public void setMovieId(String movieId) {
        MovieId = movieId;
    }

    public void setCinemaId(String cinemaId) {
        CinemaId = cinemaId;
    }
}
