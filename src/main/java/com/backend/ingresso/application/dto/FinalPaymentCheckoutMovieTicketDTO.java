package com.backend.ingresso.application.dto;

import com.backend.ingresso.domain.entities.FormOfPayment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.JoinColumn;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FinalPaymentCheckoutMovieTicketDTO {
    @JsonProperty("id")
    private UUID Id;
    @JsonIgnore
    private FormOfPayment formOfPayment;
    @JsonProperty("formOfPaymentDTO")
    private FormOfPaymentDTO FormOfPaymentDTO;
    @JsonProperty("quantityTicket")
    private Integer QuantityTicket;

    public FinalPaymentCheckoutMovieTicketDTO(UUID id, FormOfPaymentDTO formOfPaymentDTO, Integer quantityTicket) {
        Id = id;
        FormOfPaymentDTO = formOfPaymentDTO;
        QuantityTicket = quantityTicket;
    }

    public FinalPaymentCheckoutMovieTicketDTO() {
    }

    public UUID getId() {
        return Id;
    }

    public FormOfPaymentDTO getFormOfPayment() {
        return FormOfPaymentDTO;
    }

    public Integer getQuantityTicket() {
        return QuantityTicket;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public void setFormOfPayment(FormOfPaymentDTO formOfPayment) {
        FormOfPaymentDTO = formOfPayment;
    }

    public void setQuantityTicket(Integer quantityTicket) {
        QuantityTicket = quantityTicket;
    }
}
