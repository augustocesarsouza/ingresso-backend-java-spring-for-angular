package com.backend.ingresso.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ObjTicketDTO {
    @JsonProperty("id")
    private String Id;
    @JsonProperty("quantityTicket")
    private Number QuantityTicket;

    public ObjTicketDTO(String id, Number quantityTicket) {
        Id = id;
        QuantityTicket = quantityTicket;
    }

    public ObjTicketDTO() {
    }

    public String getId() {
        return Id;
    }

    public Number getQuantityTicket() {
        return QuantityTicket;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setQuantityTicket(Number quantityTicket) {
        QuantityTicket = quantityTicket;
    }
}
