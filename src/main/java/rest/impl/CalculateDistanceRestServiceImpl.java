package rest.impl;

import dto.*;
import rest.CalculateDistanceRestService;
import services.CalculateDistanceService;
import services.CityService;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        Cities cities = new Cities();
        cities.setCities(cityService.getCities());
        return cities;
    }

    @Override
    public Response createCities(InputStream stream) {
        //cities.getCities().forEach(cityService::save);
        InputStreamReader inputStreamReader = new InputStreamReader(stream);

        try {
            JAXBContext context = JAXBContext.newInstance(Cities.class);
            Unmarshaller unMarshaller = context.createUnmarshaller();
            Cities cities =  (Cities) unMarshaller.unmarshal(inputStreamReader);
            cities.getCities().forEach(cityService::save);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return Response.ok().build();
    }

    @Override
    public List<CalculateResultDTO> calculateDistance(CitiesForCalculate citiesForCalculate) {
        List<CalculateResultDTO> resultList = new ArrayList<>();
        resultList.add(calculateDistanceService.calculateDistance(citiesForCalculate.getFrom(), citiesForCalculate.getTo()));

        return resultList;
    }
}
