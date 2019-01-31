package util;

import dto.CalculateMode;
import dto.CalculateResultDTO;
import dto.Cities;
import interceptor.Loggable;
import services.CalculateDistanceService;

import javax.ejb.EJB;
import java.util.logging.Logger;

@Util
@Loggable
public class DistanceCalculatorImpl implements DistanceCalculator {

    private Logger logger = Logger.getLogger("L");

    @EJB
    private CalculateDistanceService calculateDistanceService;

    @Override
    public CalculateResultDTO calculateDistance(Cities from, Cities to, CalculateMode mode) {
            CalculateResultDTO calculateResultDTO;
        switch (mode) {
            case CROWFLIGHT: return calculateDistanceService.calculateDistance(from.getCities(), to.getCities());
            case MATRIX: break;
            case ALL: break;
        }

        return null;
    }
}
