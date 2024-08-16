package com.backend.ingresso.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_final_payment_checkout_movie_product", schema = "public")
public class FinalPaymentCheckoutMovieProduct {
    @Id
    @Column(name = "final_payment_checkout_movie_product_id")
    @JsonProperty("id")
    private UUID Id;
    @OneToOne
    @JoinColumn(name = "product_id")
    @JsonProperty("additionalFoodMovie")
    private AdditionalFoodMovie AdditionalFoodMovie;
    @JoinColumn(name = "quantity_product")
    @JsonProperty("quantityProduct")
    private Integer QuantityProduct;

    public FinalPaymentCheckoutMovieProduct(UUID id, AdditionalFoodMovie additionalFoodMovie, Integer quantityProduct) {
        Id = id;
        AdditionalFoodMovie = additionalFoodMovie;
        QuantityProduct = quantityProduct;
    }

    public FinalPaymentCheckoutMovieProduct() {
    }

    public UUID getId() {
        return Id;
    }

    public AdditionalFoodMovie getAdditionalFoodMovie() {
        return AdditionalFoodMovie;
    }

    public Integer getQuantityProduct() {
        return QuantityProduct;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public void setAdditionalFoodMovie(AdditionalFoodMovie additionalFoodMovie) {
        AdditionalFoodMovie = additionalFoodMovie;
    }

    public void setQuantityProduct(Integer quantityProduct) {
        QuantityProduct = quantityProduct;
    }
}
