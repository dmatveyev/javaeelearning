package services;

import dto.CalculateResultDTO;
import dto.CityDTO;

import java.util.List;

public interface CalculateDistanceService {

    String JNDI_NAMI = "CalculateDistanceService";
    List<CalculateResultDTO> calculateDistanceByCrowFlight(List<CityDTO> from, List<CityDTO> to);

    List<CalculateResultDTO> calculateDistanceByMatrix(List<CityDTO> from, List<CityDTO> to);

    List<CalculateResultDTO> calculateDistanceByMixedMode(List<CityDTO> from, List<CityDTO> to);
}
