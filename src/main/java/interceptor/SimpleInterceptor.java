package interceptor;

import java.util.logging.Logger;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Loggable
@Interceptor
public class SimpleInterceptor {

    private Logger logger = Logger.getLogger("ррр");

    @AroundInvoke
    public Object logMethod(InvocationContext ic) throws Exception {
        try {
            return ic.proceed();
        } finally {
            logger.info("Handled class: " + ic.getTarget().getClass().getAnnotations() + ". Handled method: " + ic.getMethod().getName());
        }
    }
}
