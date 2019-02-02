package util;

import dto.CalculateMode;
import dto.CalculateResultDTO;
import dto.CityDTO;
import services.CalculateDistanceService;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless(name = DistanceCalculator.JNDI_NAME)
@Local(DistanceCalculator.class)
public class DistanceCalculatorImpl implements DistanceCalculator {

    private Logger logger = Logger.getLogger("L");

    @EJB
    private CalculateDistanceService calculateDistanceService;

    @Override
    public List<CalculateResultDTO> calculateDistance(List<CityDTO> from, List<CityDTO> to, CalculateMode mode) {
        CalculateResultDTO error = new CalculateResultDTO();
        ArrayList<CalculateResultDTO> errorResult = new ArrayList<>();
        if (mode == null) {
            error.setError("Wrong calculation mode");
            errorResult.add(error);
            return errorResult;
        }
        switch (mode) {
            case CROWFLIGHT:
                return calculateDistanceService.calculateDistanceByCrowFlight(from, to);
            case MATRIX:
                return calculateDistanceService.calculateDistanceByMatrix(from, to);
            case ALL:
                return calculateDistanceService.calculateDistanceByMixedMode(from, to);
        }
        error.setError("Something wrong");
        errorResult.add(error);
        return errorResult;
    }
}
