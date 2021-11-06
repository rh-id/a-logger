package m.co.rh.id.alogger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Composite logger executing multiple loggers
 */
public class CompositeLogger implements ILogger {

    private List<ILogger> mILoggerList;

    public CompositeLogger(Collection<ILogger> iLoggers) {
        if (iLoggers == null) throw new IllegalStateException("iLoggers must not null");
        if (iLoggers.isEmpty()) throw new IllegalStateException("iLoggers must not empty");
        mILoggerList = new ArrayList<>();
        mILoggerList.addAll(iLoggers);
    }

    @Override
    public void setLogLevel(int logLevel) {
        for (ILogger iLogger : mILoggerList) {
            iLogger.setLogLevel(logLevel);
        }
    }

    /**
     * Set log level to all clazz type
     *
     * @param clazz    logger type
     * @param logLevel log level to be set
     */
    public <L extends ILogger> void setLogLevel(Class<L> clazz, int logLevel) {
        if (clazz == null) {
            return;
        }
        for (ILogger iLogger : mILoggerList) {
            if (clazz.isInstance(iLogger)) {
                iLogger.setLogLevel(logLevel);
            }
        }
    }

    public <L extends ILogger> L getLogger(Class<L> clazz) {
        if (clazz == null) {
            return null;
        }
        for (ILogger iLogger : mILoggerList) {
            if (clazz.isInstance(iLogger)) {
                return (L) iLogger;
            }
        }
        return null;
    }

    @Override
    public void v(String tag, String message) {
        for (ILogger iLogger : mILoggerList) {
            iLogger.v(tag, message);
        }
    }

    @Override
    public void v(String tag, String message, Throwable throwable) {
        for (ILogger iLogger : mILoggerList) {
            iLogger.v(tag, message, throwable);
        }
    }

    @Override
    public void d(String tag, String message) {
        for (ILogger iLogger : mILoggerList) {
            iLogger.d(tag, message);
        }
    }

    @Override
    public void d(String tag, String message, Throwable throwable) {
        for (ILogger iLogger : mILoggerList) {
            iLogger.d(tag, message, throwable);
        }
    }

    @Override
    public void i(String tag, String message) {
        for (ILogger iLogger : mILoggerList) {
            iLogger.i(tag, message);
        }
    }

    @Override
    public void i(String tag, String message, Throwable throwable) {
        for (ILogger iLogger : mILoggerList) {
            iLogger.i(tag, message, throwable);
        }
    }

    @Override
    public void w(String tag, String message) {
        for (ILogger iLogger : mILoggerList) {
            iLogger.w(tag, message);
        }
    }

    @Override
    public void w(String tag, String message, Throwable throwable) {
        for (ILogger iLogger : mILoggerList) {
            iLogger.w(tag, message, throwable);
        }
    }

    @Override
    public void e(String tag, String message) {
        for (ILogger iLogger : mILoggerList) {
            iLogger.e(tag, message);
        }
    }

    @Override
    public void e(String tag, String message, Throwable throwable) {
        for (ILogger iLogger : mILoggerList) {
            iLogger.e(tag, message, throwable);
        }
    }
}
