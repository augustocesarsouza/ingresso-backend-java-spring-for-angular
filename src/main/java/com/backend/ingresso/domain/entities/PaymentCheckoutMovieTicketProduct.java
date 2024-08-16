package com.backend.ingresso.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_payment_checkout_movie_ticket_product", schema = "public")
public class PaymentCheckoutMovieTicketProduct {
    @Id
    @Column(name = "payment_checkout_movie_ticket_product_id")
    @JsonProperty("id")
    private UUID Id;
    @OneToOne
    @JoinColumn(name = "payment_checkout_movie_id")
    @JsonProperty("finalPaymentCheckoutMovie")
    private FinalPaymentCheckoutMovie FinalPaymentCheckoutMovie;
    @OneToOne//TESTAR ISSO AQUI AMANHA PODE SER QUE TENHA QUE SER 'ManyToOne'
    @JoinColumn(name = "payment_checkout_movie_ticket_id")
    @JsonProperty("finalPaymentCheckoutMovieTicket")
    private FinalPaymentCheckoutMovieTicket FinalPaymentCheckoutMovieTicket;
    @OneToOne
    @JoinColumn(name = "payment_checkout_movie_product_id")
    @JsonProperty("finalPaymentCheckoutMovieProduct")
    private FinalPaymentCheckoutMovieProduct FinalPaymentCheckoutMovieProduct;

    public PaymentCheckoutMovieTicketProduct(UUID id, FinalPaymentCheckoutMovie finalPaymentCheckoutMovie,
                                             FinalPaymentCheckoutMovieTicket finalPaymentCheckoutMovieTicket,
                                             FinalPaymentCheckoutMovieProduct finalPaymentCheckoutMovieProduct) {
        Id = id;
        FinalPaymentCheckoutMovie = finalPaymentCheckoutMovie;
        FinalPaymentCheckoutMovieTicket = finalPaymentCheckoutMovieTicket;
        FinalPaymentCheckoutMovieProduct = finalPaymentCheckoutMovieProduct;
    }

    public PaymentCheckoutMovieTicketProduct() {
    }

    public UUID getId() {
        return Id;
    }

    public FinalPaymentCheckoutMovie getFinalPaymentCheckoutMovie() {
        return FinalPaymentCheckoutMovie;
    }

    public FinalPaymentCheckoutMovieTicket getFinalPaymentCheckoutMovieTicket() {
        return FinalPaymentCheckoutMovieTicket;
    }

    public FinalPaymentCheckoutMovieProduct getFinalPaymentCheckoutMovieProduct() {
        return FinalPaymentCheckoutMovieProduct;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public void setFinalPaymentCheckoutMovie(FinalPaymentCheckoutMovie finalPaymentCheckoutMovie) {
        FinalPaymentCheckoutMovie = finalPaymentCheckoutMovie;
    }

    public void setFinalPaymentCheckoutMovieTicket(FinalPaymentCheckoutMovieTicket finalPaymentCheckoutMovieTicket) {
        FinalPaymentCheckoutMovieTicket = finalPaymentCheckoutMovieTicket;
    }

    public void setFinalPaymentCheckoutMovieProduct(FinalPaymentCheckoutMovieProduct finalPaymentCheckoutMovieProduct) {
        FinalPaymentCheckoutMovieProduct = finalPaymentCheckoutMovieProduct;
    }
}
