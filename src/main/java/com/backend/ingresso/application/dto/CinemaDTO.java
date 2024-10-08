package com.backend.ingresso.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CinemaDTO {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("nameCinema")
    private String nameCinema;
    @JsonProperty("district")
    private String district;

    public CinemaDTO(UUID id, String nameCinema, String district) {
        this.id = id;
        this.nameCinema = nameCinema;
        this.district = district;
    }

    public CinemaDTO() {
    }

    public UUID getId() {
        return id;
    }

    public String getNameCinema() {
        return nameCinema;
    }

    public String getDistrict() {
        return district;
    }
}
