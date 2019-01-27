package util;

import interceptor.Loggable;
import org.hibernate.internal.CacheImpl;
import services.CityService;

import javax.ejb.EJB;
import javax.enterprise.event.Observes;
import java.util.logging.Logger;

@Util
@Loggable
public class UtilImpl implements IUtil {

    private Logger logger = Logger.getLogger("L");

    static private String stringEvent;

    public void setStringEvent(@Observes String stringEvent) {
        logger.info(stringEvent);
        this.stringEvent = stringEvent;
    }

    public String getStringEvent() {
        return this.stringEvent;
    }
}
