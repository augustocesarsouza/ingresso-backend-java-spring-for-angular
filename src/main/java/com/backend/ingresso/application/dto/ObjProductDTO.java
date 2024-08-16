package com.backend.ingresso.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ObjProductDTO {
    @JsonProperty("id")
    private String Id;
    @JsonProperty("quantityProduct")
    private Number QuantityProduct;

    public ObjProductDTO(String id, Number quantityProduct) {
        Id = id;
        QuantityProduct = quantityProduct;
    }

    public ObjProductDTO() {
    }

    public String getId() {
        return Id;
    }

    public Number getQuantityProduct() {
        return QuantityProduct;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setQuantityProduct(Number quantityProduct) {
        QuantityProduct = quantityProduct;
    }
}
