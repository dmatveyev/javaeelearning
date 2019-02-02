package util;

import dto.CalculateMode;
import dto.CalculateResultDTO;
import dto.CityDTO;

import java.util.List;

public interface DistanceCalculator {

    String JNDI_NAME = "DistanceCalculator";

    List<CalculateResultDTO> calculateDistance(List<CityDTO> from, List<CityDTO> to, CalculateMode mode);

}
