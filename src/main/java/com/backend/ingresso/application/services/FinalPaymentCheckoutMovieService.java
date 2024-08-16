package com.backend.ingresso.application.services;

import com.backend.ingresso.application.dto.FinalPaymentCheckoutMovieDTO;
import com.backend.ingresso.application.services.interfaces.*;
import com.backend.ingresso.domain.entities.*;
import com.backend.ingresso.domain.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FinalPaymentCheckoutMovieService implements IFinalPaymentCheckoutMovieService {
    private final IFinalPaymentCheckoutMovieRepository finalPaymentCheckoutMovieRepository;
    private final IPaymentCheckoutMovieTicketProductRepository paymentCheckoutMovieTicketProductRepository;
    private final IFinalPaymentCheckoutMovieTicketService finalPaymentCheckoutMovieTicketService;
    private final IFinalPaymentCheckoutMovieProductService finalPaymentCheckoutMovieProductService;
    private final IUserManagementService userManagementService;
    private final IMovieService movieService;
    private final ICinemaService cinemaService;
    private final IFormOfPaymentService formOfPaymentService;
    private final IAdditionalFoodMovieService additionalFoodMovieService;

    @Autowired
    public FinalPaymentCheckoutMovieService(IFinalPaymentCheckoutMovieRepository finalPaymentCheckoutMovieRepository,
                                            IPaymentCheckoutMovieTicketProductRepository paymentCheckoutMovieTicketProductRepository,
                                            IFinalPaymentCheckoutMovieTicketService finalPaymentCheckoutMovieTicketService,
                                            IFinalPaymentCheckoutMovieProductService finalPaymentCheckoutMovieProductService,
                                            IUserManagementService userManagementService, IMovieService movieService,
                                            ICinemaService cinemaService, IFormOfPaymentService formOfPaymentService,
                                            IAdditionalFoodMovieService additionalFoodMovieService) {
        this.finalPaymentCheckoutMovieRepository = finalPaymentCheckoutMovieRepository;
        this.paymentCheckoutMovieTicketProductRepository = paymentCheckoutMovieTicketProductRepository;
        this.finalPaymentCheckoutMovieTicketService = finalPaymentCheckoutMovieTicketService;
        this.finalPaymentCheckoutMovieProductService = finalPaymentCheckoutMovieProductService;
        this.userManagementService = userManagementService;
        this.movieService = movieService;
        this.cinemaService = cinemaService;
        this.formOfPaymentService = formOfPaymentService;
        this.additionalFoodMovieService = additionalFoodMovieService;
    }

    @Transactional
    @Override
    public ResultService<FinalPaymentCheckoutMovie> create(FinalPaymentCheckoutMovieDTO finalPaymentCheckoutMovieDTO){
        try {
            if(finalPaymentCheckoutMovieDTO == null)
                return null;

            var uuidRandom = UUID.randomUUID();
//            var idPaymentCheckoutMovieTicketProduct = UUID.randomUUID();

            ResultService<User> resultGetUser = userManagementService.findById(UUID.fromString(finalPaymentCheckoutMovieDTO.getUserId()));

            if(!resultGetUser.IsSuccess)
                return ResultService.Fail(resultGetUser.Message);

            var user = resultGetUser.Data;

            ResultService<Movie> resultGetMovie = movieService.findById(UUID.fromString(finalPaymentCheckoutMovieDTO.getMovieId()));

            if(!resultGetMovie.IsSuccess)
                return ResultService.Fail(resultGetMovie.Message);

            Movie movie = resultGetMovie.Data;

            ResultService<Cinema> resultGetCinema = cinemaService.findById(UUID.fromString(finalPaymentCheckoutMovieDTO.getCinemaId()));

            if(!resultGetCinema.IsSuccess)
                return ResultService.Fail(resultGetCinema.Message);

            Cinema cinema = resultGetCinema.Data;

            var finalPaymentCheckoutMovie = new FinalPaymentCheckoutMovie(uuidRandom, user, movie, cinema, finalPaymentCheckoutMovieDTO.getSeats());

            var finalPaymentCheckoutMovieCreated = finalPaymentCheckoutMovieRepository.create(finalPaymentCheckoutMovie);

            var objTicketDTOs = finalPaymentCheckoutMovieDTO.getObjTicketDTO();
            var objProductDTOs = finalPaymentCheckoutMovieDTO.getObjProductDTO();

            for (int i = 0; i <= 8; i++){
                var objTicket = (i < objTicketDTOs.size()) ? objTicketDTOs.get(i) : null;
                var idPaymentCheckoutMovieTicketProduct = UUID.randomUUID();
                var idProduct = UUID.randomUUID();

                if(objTicket != null){
                    var idTicket = UUID.randomUUID();

                    ResultService<FormOfPayment> resultCreatedFormOfPayment = formOfPaymentService.findById(UUID.fromString(objTicket.getId()));

                    if(!resultCreatedFormOfPayment.IsSuccess)
                        return ResultService.Fail(resultCreatedFormOfPayment.Message);

                    FormOfPayment formOfPayment = resultCreatedFormOfPayment.Data;

                    Integer quantityTicket = (Integer) objTicket.getQuantityTicket();
                    var ticket = new FinalPaymentCheckoutMovieTicket(idTicket, formOfPayment, quantityTicket);
                    ResultService<FinalPaymentCheckoutMovieTicket> resultCreateMovieTicket = finalPaymentCheckoutMovieTicketService.create(ticket);

                    if(!resultCreateMovieTicket.IsSuccess)
                        return ResultService.Fail(resultCreateMovieTicket.Message);

                    var createdFinalTicket = resultCreateMovieTicket.Data;

                    if(!objProductDTOs.isEmpty()){
                        var objProduct = objProductDTOs.get(i);

                        ResultService<AdditionalFoodMovie> resultCreatedAdditionalFoodMovie = additionalFoodMovieService.findById(UUID.fromString(objProduct.getId()));

                        if(!resultCreatedAdditionalFoodMovie.IsSuccess)
                            return ResultService.Fail(resultCreatedAdditionalFoodMovie.Message);

                        AdditionalFoodMovie additionalFoodMovie = resultCreatedAdditionalFoodMovie.Data;

                        Integer quantityProduct = (Integer) objProduct.getQuantityProduct();
                        var product = new FinalPaymentCheckoutMovieProduct(idProduct, additionalFoodMovie, quantityProduct);
                        ResultService<FinalPaymentCheckoutMovieProduct> resultCreatedMovieProduct = finalPaymentCheckoutMovieProductService.create(product);

                        if(!resultCreatedMovieProduct.IsSuccess)
                            return ResultService.Fail(resultCreatedMovieProduct.Message);

                        var createdFinalProduct = resultCreatedMovieProduct.Data;

                        var paymentCheckoutMovieTicketProduct = new PaymentCheckoutMovieTicketProduct(idPaymentCheckoutMovieTicketProduct, finalPaymentCheckoutMovieCreated,
                                createdFinalTicket, createdFinalProduct);

                        var resultCreated = paymentCheckoutMovieTicketProductRepository.create(paymentCheckoutMovieTicketProduct);
                    }else {
                        var paymentCheckoutMovieTicketProduct = new PaymentCheckoutMovieTicketProduct(idPaymentCheckoutMovieTicketProduct, finalPaymentCheckoutMovieCreated,
                                createdFinalTicket, null);

                        var resultCreated = paymentCheckoutMovieTicketProductRepository.create(paymentCheckoutMovieTicketProduct);
                    }
                }else {
                    var objProduct = (i < objProductDTOs.size()) ? objProductDTOs.get(i) : null;

                    if(objProduct != null){
                        ResultService<AdditionalFoodMovie> resultCreatedAdditionalFoodMovie = additionalFoodMovieService.findById(UUID.fromString(objProduct.getId()));

                        if(!resultCreatedAdditionalFoodMovie.IsSuccess)
                            return ResultService.Fail(resultCreatedAdditionalFoodMovie.Message);

                        AdditionalFoodMovie additionalFoodMovie = resultCreatedAdditionalFoodMovie.Data;

                        Integer quantityProduct = (Integer) objProduct.getQuantityProduct();
                        var product = new FinalPaymentCheckoutMovieProduct(idProduct, additionalFoodMovie, quantityProduct);

                        ResultService<FinalPaymentCheckoutMovieProduct> resultCreatedMovieProduct = finalPaymentCheckoutMovieProductService.create(product);

                        if(!resultCreatedMovieProduct.IsSuccess)
                            return ResultService.Fail(resultCreatedMovieProduct.Message);

                        var createdFinalProduct = resultCreatedMovieProduct.Data;

                        var paymentCheckoutMovieTicketProduct = new PaymentCheckoutMovieTicketProduct(idPaymentCheckoutMovieTicketProduct, finalPaymentCheckoutMovieCreated,
                                null, createdFinalProduct);

                        var resultCreated = paymentCheckoutMovieTicketProductRepository.create(paymentCheckoutMovieTicketProduct);
                    }
                }
            }

            return ResultService.Ok(finalPaymentCheckoutMovieCreated);
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }
}
