package rest;

import dto.CalculateResultDTO;
import dto.Cities;
import dto.CitiesForCalculate;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/calculate")
public interface CalculateDistanceRestService {

    String JNDI_NAME = "CalculateDistanceRestService";

    @GET
    @Path("/list")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    Cities getCities();

    // TODO: 27.01.2019 Переделать на multipart/form-data
    @POST
    @Path("/createSities")
    //@Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_XML)
    Response createCities(Cities cities);

    @POST
    @Path("/calculateDistance")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    List<CalculateResultDTO> calculateDistance(CitiesForCalculate citiesForCalculate);

}
