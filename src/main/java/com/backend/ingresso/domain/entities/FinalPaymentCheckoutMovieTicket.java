package com.backend.ingresso.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_final_payment_checkout_movie_ticket", schema = "public")
public class FinalPaymentCheckoutMovieTicket {
    @Id
    @Column(name = "final_payment_checkout_movie_ticket_id")
    @JsonProperty("id")
    private UUID Id;
    @OneToOne// QUANDO EU MANDAR PARA BANCO SE TIVER DOIS OU MAIS "TICKET" EU SÓ VOU REGISTRAR UMA VEZ NO BANCO E COLOCAR NO "QuantityTicket = 2 OU MAIS"
    // IGUALMENTE PARA Product
    @JoinColumn(name = "ticket_id")
    @JsonProperty("formOfPayment")
    private FormOfPayment FormOfPayment;
    @JoinColumn(name = "quantity_ticket")
    @JsonProperty("quantityTicket")
    private Integer QuantityTicket;

    public FinalPaymentCheckoutMovieTicket(UUID id, FormOfPayment formOfPayment, Integer quantityTicket) {
        Id = id;
        FormOfPayment = formOfPayment;
        QuantityTicket = quantityTicket;
    }

    public FinalPaymentCheckoutMovieTicket() {
    }

    public UUID getId() {
        return Id;
    }

    public FormOfPayment getFormOfPayment() {
        return FormOfPayment;
    }

    public Integer getQuantityTicket() {
        return QuantityTicket;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public void setFormOfPayment(FormOfPayment formOfPayment) {
        FormOfPayment = formOfPayment;
    }

    public void setQuantityTicket(Integer quantityTicket) {
        QuantityTicket = quantityTicket;
    }
}
