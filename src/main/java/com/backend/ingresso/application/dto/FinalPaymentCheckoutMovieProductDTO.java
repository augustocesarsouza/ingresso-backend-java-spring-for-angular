package com.backend.ingresso.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.JoinColumn;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FinalPaymentCheckoutMovieProductDTO {
    @JsonProperty("id")
    private UUID Id;
    @JsonProperty("additionalFoodMovieDTO")
    private AdditionalFoodMovieDTO AdditionalFoodMovieDTO;
    @JoinColumn(name = "quantity_product")
    @JsonProperty("quantityProduct")
    private Integer QuantityProduct;

    public FinalPaymentCheckoutMovieProductDTO(UUID id, com.backend.ingresso.application.dto.AdditionalFoodMovieDTO additionalFoodMovieDTO, Integer quantityProduct) {
        Id = id;
        AdditionalFoodMovieDTO = additionalFoodMovieDTO;
        QuantityProduct = quantityProduct;
    }

    public FinalPaymentCheckoutMovieProductDTO() {
    }

    public UUID getId() {
        return Id;
    }

    public com.backend.ingresso.application.dto.AdditionalFoodMovieDTO getAdditionalFoodMovieDTO() {
        return AdditionalFoodMovieDTO;
    }

    public Integer getQuantityProduct() {
        return QuantityProduct;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public void setAdditionalFoodMovieDTO(com.backend.ingresso.application.dto.AdditionalFoodMovieDTO additionalFoodMovieDTO) {
        AdditionalFoodMovieDTO = additionalFoodMovieDTO;
    }

    public void setQuantityProduct(Integer quantityProduct) {
        QuantityProduct = quantityProduct;
    }
}
