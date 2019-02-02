package rest;

import dto.*;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

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
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    Response createCities(MultipartFormDataInput multiPart);

    @POST
    @Path("/calculateDistance")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    List<CalculateResultDTO> calculateDistance(CitiesForCalculate citiesForCalculate);

}
