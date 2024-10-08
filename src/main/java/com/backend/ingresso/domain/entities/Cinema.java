package com.backend.ingresso.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_cinemas", schema = "public")
public class Cinema {
    @Id
    @Column(name = "cinema_id")
    @JsonProperty("id")
    private UUID Id;
    @Column(name = "name_cinema")
    @JsonProperty("nameCinema")
    private String NameCinema;
    @Column(name = "district")
    @JsonProperty("district")
    private String District;
    @OneToMany(mappedBy = "cinema", cascade = CascadeType.REMOVE)
    private List<MovieRegionTicketsPurchesed> movieRegionTicketsPurcheseds;
    @OneToMany(mappedBy = "cinema", cascade = CascadeType.REMOVE)
    private List<CinemaMovie> cinemaMovies;

    public Cinema(UUID id, String nameCinema, String district) {
        Id = id;
        NameCinema = nameCinema;
        District = district;
    }

    public Cinema() {
    }

    public UUID getId() {
        return Id;
    }

    public String getNameCinema() {
        return NameCinema;
    }

    public String getDistrict() {
        return District;
    }
}
