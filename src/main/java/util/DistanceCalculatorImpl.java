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
            CalculateResultDTO calculateResultDTO;
        switch (mode) {
            case CROWFLIGHT: return calculateDistanceService.calculateDistanceByCrowFlight(from, to);
            case MATRIX: return calculateDistanceService.calculateDistanceByMatrix(from, to);
            case ALL: return  calculateDistanceService.calculateDistanceByMixedMode(from, to);
            default: {
                CalculateResultDTO error = new CalculateResultDTO();
                error.setError("Calculation mode isn't set");
                ArrayList<CalculateResultDTO> result = new ArrayList<>();
                result.add(error);
                return result;
            }
        }
    }
}
