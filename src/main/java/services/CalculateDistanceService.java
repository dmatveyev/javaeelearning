package services;

import dto.CalculateResultDTO;
import dto.CityDTO;

import java.util.List;

public interface CalculateDistanceService {

    String JNDI_NAMI = "CalculateDistanceService";
    CalculateResultDTO calculateDistance(List<CityDTO> from, List<CityDTO> to);
}
