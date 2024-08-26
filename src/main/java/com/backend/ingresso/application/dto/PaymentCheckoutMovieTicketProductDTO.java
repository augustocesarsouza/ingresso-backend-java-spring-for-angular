package com.backend.ingresso.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentCheckoutMovieTicketProductDTO {
    @JsonProperty("id")
    private UUID Id;
    @JsonProperty("finalPaymentCheckoutMovieDTO")
    private FinalPaymentCheckoutMovieDTO FinalPaymentCheckoutMovieDTO;
    @JsonProperty("finalPaymentCheckoutMovieTicketDTO")
    private FinalPaymentCheckoutMovieTicketDTO FinalPaymentCheckoutMovieTicketDTO;
    @JsonProperty("finalPaymentCheckoutMovieProductDTO")
    private FinalPaymentCheckoutMovieProductDTO FinalPaymentCheckoutMovieProductDTO;

    public PaymentCheckoutMovieTicketProductDTO(UUID id, FinalPaymentCheckoutMovieDTO finalPaymentCheckoutMovieDTO,
                                                FinalPaymentCheckoutMovieTicketDTO finalPaymentCheckoutMovieTicketDTO,
                                                FinalPaymentCheckoutMovieProductDTO finalPaymentCheckoutMovieProductDTO) {
        Id = id;
        FinalPaymentCheckoutMovieDTO = finalPaymentCheckoutMovieDTO;
        FinalPaymentCheckoutMovieTicketDTO = finalPaymentCheckoutMovieTicketDTO;
        FinalPaymentCheckoutMovieProductDTO = finalPaymentCheckoutMovieProductDTO;
    }

    public PaymentCheckoutMovieTicketProductDTO() {
    }

    public UUID getId() {
        return Id;
    }

    public FinalPaymentCheckoutMovieDTO getFinalPaymentCheckoutMovieDTO() {
        return FinalPaymentCheckoutMovieDTO;
    }

    public FinalPaymentCheckoutMovieTicketDTO getFinalPaymentCheckoutMovieTicketDTO() {
        return FinalPaymentCheckoutMovieTicketDTO;
    }

    public FinalPaymentCheckoutMovieProductDTO getFinalPaymentCheckoutMovieProductDTO() {
        return FinalPaymentCheckoutMovieProductDTO;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public void setFinalPaymentCheckoutMovieDTO(FinalPaymentCheckoutMovieDTO finalPaymentCheckoutMovieDTO) {
        FinalPaymentCheckoutMovieDTO = finalPaymentCheckoutMovieDTO;
    }

    public void setFinalPaymentCheckoutMovieTicketDTO(FinalPaymentCheckoutMovieTicketDTO finalPaymentCheckoutMovieTicketDTO) {
        FinalPaymentCheckoutMovieTicketDTO = finalPaymentCheckoutMovieTicketDTO;
    }

    public void setFinalPaymentCheckoutMovieProductDTO(FinalPaymentCheckoutMovieProductDTO finalPaymentCheckoutMovieProductDTO) {
        FinalPaymentCheckoutMovieProductDTO = finalPaymentCheckoutMovieProductDTO;
    }
}
