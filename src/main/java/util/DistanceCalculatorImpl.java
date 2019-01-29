package util;

import dto.CalculateMode;
import dto.CalculateResultDTO;
import dto.Cities;
import interceptor.Loggable;
import services.CalculateDistanceService;

import javax.ejb.EJB;
import javax.enterprise.event.Observes;
import java.util.logging.Logger;

@Util
@Loggable
public class DistanceCalculatorImpl implements DistanceCalculator {

    private Logger logger = Logger.getLogger("L");

    static private String stringEvent;

    @EJB
    private CalculateDistanceService calculateDistanceService;

    public void setStringEvent(@Observes String stringEvent) {
        logger.info(stringEvent);
        this.stringEvent = stringEvent;
    }

    @Override
    public CalculateResultDTO calculateDistance(Cities from, Cities to, CalculateMode mode) {
            CalculateResultDTO calculateResultDTO;
        switch (mode) {
            case CROWFLIGHT: return calculateDistanceService.calculateDistance(from, to);
            case MATRIX: break;
            case ALL: break;
        }

        return null;
    }

    public String getStringEvent() {
        return this.stringEvent;
    }


}
