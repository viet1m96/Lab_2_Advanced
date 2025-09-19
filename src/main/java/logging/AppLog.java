package logging;
import org.jboss.logging.Logger;

public final class AppLog {
    private AppLog() {}

    private static Logger log(Class<?> cls) {
        return Logger.getLogger(cls);
    }

    public static void info(Class<?> cls, String message, Object... args) {
        if (args != null && args.length > 0) {
            log(cls).infof(message, args);
        } else {
            log(cls).info(message);
        }
    }

    public static void error(Class<?> cls, String message, Throwable t) {
        log(cls).error(message, t);
    }
}