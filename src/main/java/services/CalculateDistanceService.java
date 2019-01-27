package services;

import entities.City;

public interface CalculateDistanceService {

    String JNDI_NAMI = "CalculateDistanceService";
    Double calculateDistance(City from, City to);
}
