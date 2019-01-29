package util;

import dto.CalculateMode;
import dto.CalculateResultDTO;
import dto.Cities;

public interface DistanceCalculator {

    CalculateResultDTO calculateDistance(Cities from, Cities to, CalculateMode mode);

    String getStringEvent();
}
