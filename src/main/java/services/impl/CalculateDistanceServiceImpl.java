package services.impl;

import entities.City;
import services.CalculateDistanceService;

import javax.ejb.Local;
import javax.ejb.Stateless;

import static java.lang.Math.atan2;
import static java.lang.Math.sin;
import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

@Stateless(name = CalculateDistanceService.JNDI_NAMI)
@Local(CalculateDistanceService.class)
public class CalculateDistanceServiceImpl implements CalculateDistanceService {

    private static final int EARTH_RADIUS = 6367;

    public Double calculateDistance(City from, City to) {
        Double d2r = Math.PI / 180;
        Double dlong = (to.getLongitude() - from.getLongitude()) * d2r;
        Double dlat = (to.getLatitude() - from.getLatitude()) * d2r;
        Double a = pow(sin(dlat/2.0), 2) + cos(from.getLatitude()*d2r) * cos(to.getLatitude()*d2r) * pow(sin(dlong/2.0), 2);
        double c = 2 * atan2(sqrt(a), sqrt(1-a));
        return (EARTH_RADIUS * c);
    }
}
