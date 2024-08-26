package com.backend.ingresso.application.dto.validations.cinemaMovieDTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class CinemaMovieCreate {
    @JsonProperty("id")
    private UUID Id;
    @NotEmpty(message = "cinemaId should not be empty")
    @Size(min = 36, message = "error cinemaId size Minimum is 36")
    @JsonProperty("cinemaId")
    private String CinemaId;
    @NotEmpty(message = "movieId should not be empty")
    @Size(min = 36, message = "error movieId size Minimum is 36")
    @JsonProperty("movieId")
    private String MovieId;
    @NotEmpty(message = "regionId should not be empty")
    @Size(min = 36, message = "error regionId size Minimum is 36")
    @JsonProperty("regionId")
    private String RegionId;
    @NotEmpty(message = "screeningSchedule should not be empty")
    @JsonProperty("screeningSchedule")
    private String ScreeningSchedule;
    @Min(value = 1, message = "room should be greater than 0")
    @JsonProperty("room")
    private Integer Room;

    public CinemaMovieCreate(UUID id, String cinemaId, String movieId, String regionId, String screeningSchedule) {
        Id = id;
        CinemaId = cinemaId;
        MovieId = movieId;
        RegionId = regionId;
        ScreeningSchedule = screeningSchedule;
    }

    public CinemaMovieCreate() {
    }

    public UUID getId() {
        return Id;
    }

    public String getCinemaId() {
        return CinemaId;
    }

    public String getMovieId() {
        return MovieId;
    }

    public String getRegionId() {
        return RegionId;
    }

    public String getScreeningSchedule() {
        return ScreeningSchedule;
    }

    public Integer getRoom() {
        return Room;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public void setCinemaId(String cinemaId) {
        CinemaId = cinemaId;
    }

    public void setMovieId(String movieId) {
        MovieId = movieId;
    }

    public void setRegionId(String regionId) {
        RegionId = regionId;
    }

    public void setScreeningSchedule(String screeningSchedule) {
        ScreeningSchedule = screeningSchedule;
    }

    public void setRoom(Integer room) {
        Room = room;
    }
}
