package com.backend.ingresso.application.services.interfaces;

import com.backend.ingresso.application.dto.FormOfPaymentDTO;
import com.backend.ingresso.application.dto.validations.formOfPaymentDTOs.FormOfPaymentCreate;
import com.backend.ingresso.application.services.ResultService;
import com.backend.ingresso.domain.entities.FormOfPayment;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.UUID;

public interface IFormOfPaymentService {
    ResultService<List<FormOfPaymentDTO>> getMovieIdInfo(UUID movieId);
    ResultService<FormOfPayment> findById(UUID formOfPaymentId);
    ResultService<FormOfPaymentDTO> create(FormOfPaymentCreate formOfPaymentCreate, BindingResult result);
}
