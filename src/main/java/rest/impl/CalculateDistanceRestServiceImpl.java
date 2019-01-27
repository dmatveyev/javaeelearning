package rest.impl;

import dto.Cities;
import rest.CalculateDistanceRestService;
import services.CalculateDistanceService;
import services.CityService;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

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


    public Response createCities(Cities cities) {
        cities.getCities().forEach(cityService::save);
        return Response.ok().build();
    }
}
