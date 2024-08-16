package com.backend.ingresso.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_final_payment_checkout_movie", schema = "public")
public class FinalPaymentCheckoutMovie {
    @Id
    @Column(name = "final_payment_checkout_movie_id")
    @JsonProperty("id")
    private UUID Id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonProperty("user")
    private User User;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    @JsonProperty("movie")
    private Movie Movie;
    @ManyToOne
    @JoinColumn(name = "cinema_id")
    @JsonProperty("cinema")
    private Cinema Cinema;
    @JoinColumn(name = "seats")
    @JsonProperty("seats")
    private String Seats;

    public FinalPaymentCheckoutMovie(UUID id, User user, Movie movie, Cinema cinema, String seats) {
        Id = id;
        User = user;
        Movie = movie;
        Cinema = cinema;
        Seats = seats;
    }

    public FinalPaymentCheckoutMovie() {
    }

    public UUID getId() {
        return Id;
    }

    public User getUser() {
        return User;
    }

    public Movie getMovie() {
        return Movie;
    }

    public Cinema getCinema() {
        return Cinema;
    }

    public String getSeats() {
        return Seats;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public void setUser(User user) {
        User = user;
    }

    public void setMovie(Movie movie) {
        Movie = movie;
    }

    public void setCinema(Cinema cinema) {
        Cinema = cinema;
    }

    public void setSeats(String seats) {
        Seats = seats;
    }
}
