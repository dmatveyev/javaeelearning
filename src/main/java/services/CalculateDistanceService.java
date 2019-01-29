package services;

import dto.CalculateResultDTO;
import dto.Cities;

public interface CalculateDistanceService {

    String JNDI_NAMI = "CalculateDistanceService";
    CalculateResultDTO calculateDistance(Cities from, Cities to);
}
