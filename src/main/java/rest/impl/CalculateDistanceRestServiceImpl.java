package rest.impl;

import dto.*;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import rest.CalculateDistanceRestService;
import services.CityService;
import util.DistanceCalculator;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXB;
import java.io.*;
import java.util.List;
import java.util.Map;


@Stateless(name = CalculateDistanceRestService.JNDI_NAME)
@Local(CalculateDistanceRestService.class)
public class CalculateDistanceRestServiceImpl implements CalculateDistanceRestService {

    @EJB
    private CityService cityService;

    @EJB
    private DistanceCalculator distanceCalculator;

    public Cities getCities() {
        Cities cities = new Cities();
        cities.setCities(cityService.getCities());
        return cities;
    }

    @Override
    public Response createCities(MultipartFormDataInput multiPart) {
        Map<String, List<InputPart>> uploadForm = multiPart.getFormDataMap();
        List<InputPart> inputParts = uploadForm.get("cities");
        if (inputParts == null) {
            return Response.noContent().build();
        }
        for (InputPart inputPart : inputParts) {
            try (InputStream inputStream = inputPart.getBody(InputStream.class, null)) {
                Cities cities = JAXB.unmarshal(inputStream, Cities.class);
                cities.getCities().forEach(cityService::save);
            } catch (IOException e) {
                return Response.serverError().build();
            }
        }
        return Response.ok().build();
    }

    @Override
    public CalculateResults calculateDistance(CitiesForCalculate citiesForCalculate) {
        CalculateResults calculateResults = new CalculateResults();
        calculateResults.setCalculateResultDTOS(distanceCalculator
                .calculateDistance(citiesForCalculate.getFrom(), citiesForCalculate.getTo(), citiesForCalculate.mode));
        return calculateResults;
    }
}
