package rest.impl;

import dto.CalculateResultDTO;
import dto.Cities;
import dto.CitiesForCalculate;
import rest.CalculateDistanceRestService;
import services.CalculateDistanceService;
import services.CityService;
import util.Util;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Stateless(name = CalculateDistanceRestService.JNDI_NAME)
@Local(CalculateDistanceRestService.class)
public class CalculateDistanceRestServiceImpl implements CalculateDistanceRestService {

    @EJB
    private CityService cityService;

   @EJB
    private CalculateDistanceService calculateDistanceService;

    public Cities getCities() {

        return new Cities(cityService.getCities());
    }

    @Override
    public Response createCities(Cities cities) {
        cities.getCities().forEach(cityService::save);
        return Response.ok().build();
    }

    @Override
    public List<CalculateResultDTO> calculateDistance(CitiesForCalculate citiesForCalculate) {
        List<CalculateResultDTO> resultList = new ArrayList<>();
        resultList.add(calculateDistanceService.calculateDistance(citiesForCalculate.getFrom(), citiesForCalculate.getTo()));

        return resultList;
    }
}
