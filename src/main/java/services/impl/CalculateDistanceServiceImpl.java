package services.impl;

import dto.CalculateResultDTO;
import dto.CityDTO;
import services.CalculateDistanceService;

import javax.ejb.Local;
import javax.ejb.Stateless;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.atan2;
import static java.lang.Math.sin;
import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

@Stateless(name = CalculateDistanceService.JNDI_NAMI)
@Local(CalculateDistanceService.class)
public class CalculateDistanceServiceImpl implements CalculateDistanceService {

    private static final int EARTH_RADIUS = 6367;

    public List<CalculateResultDTO> calculateDistance(List<CityDTO> from1, List<CityDTO> to1) {
        List<CalculateResultDTO> resultList = new ArrayList<>();
        for (CityDTO cityFrom : from1) {
            for (CityDTO cityTo: to1) {
                CalculateResultDTO result = getCalculateResultDTO(cityFrom, cityTo);
                resultList.add(result);
            }
        }
        return resultList;
    }

    private CalculateResultDTO getCalculateResultDTO(CityDTO from, CityDTO to) {
        CalculateResultDTO result = new CalculateResultDTO();
        result.setFrom(from);
        result.setTo(to);

        Double d2r = Math.PI / 180;
        Double dlong = (to.getLongitude() - from.getLongitude()) * d2r;
        Double dlat = (to.getLatitude() - from.getLatitude()) * d2r;
        Double a = pow(sin(dlat/2.0), 2) + cos(from.getLatitude()*d2r) * cos(to.getLatitude()*d2r) * pow(sin(dlong/2.0), 2);
        double c = 2 * atan2(sqrt(a), sqrt(1-a));
        result.setCrowflightDistance((EARTH_RADIUS * c));
        return result;
    }
}
