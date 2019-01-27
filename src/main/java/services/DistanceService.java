package services;

import entities.City;
import entities.Distance;

public interface DistanceService {

    Distance getDistanceByCities(City from,City to);

    void saveDistance(Distance distance);
}
