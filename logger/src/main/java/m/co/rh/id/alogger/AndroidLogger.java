package m.co.rh.id.alogger;

import android.util.Log;

/**
 * Logger that use default android Log class
 */
public class AndroidLogger implements ILogger {
    private int mLogLevel;

    /**
     * Prepare android logger
     *
     * @param logLevel log level, (Example ILogger.VERBOSE,ILogger.DEBUG)
     */
    public AndroidLogger(int logLevel) {
        mLogLevel = logLevel;
    }

    private boolean shouldPrint(int level) {
        return mLogLevel <= level;
    }

    @Override
    public void setLogLevel(int logLevel) {
        mLogLevel = logLevel;
    }

    @Override
    public void v(String tag, String message) {
        if (shouldPrint(ILogger.VERBOSE)) {
            Log.v(tag, message);
        }
    }

    @Override
    public void v(String tag, String message, Throwable throwable) {
        if (shouldPrint(ILogger.VERBOSE)) {
            Log.v(tag, message, throwable);
        }
    }

    @Override
    public void d(String tag, String message) {
        if (shouldPrint(ILogger.DEBUG)) {
            Log.d(tag, message);
        }
    }

    @Override
    public void d(String tag, String message, Throwable throwable) {
        if (shouldPrint(ILogger.DEBUG)) {
            Log.d(tag, message, throwable);
        }
    }

    @Override
    public void i(String tag, String message) {
        if (shouldPrint(ILogger.INFO)) {
            Log.i(tag, message);
        }
    }

    @Override
    public void i(String tag, String message, Throwable throwable) {
        if (shouldPrint(ILogger.INFO)) {
            Log.i(tag, message, throwable);
        }
    }

    @Override
    public void w(String tag, String message) {
        if (shouldPrint(ILogger.WARN)) {
            Log.w(tag, message);
        }
    }

    @Override
    public void w(String tag, String message, Throwable throwable) {
        if (shouldPrint(ILogger.WARN)) {
            Log.w(tag, message, throwable);
        }
    }

    @Override
    public void e(String tag, String message) {
        if (shouldPrint(ILogger.ERROR)) {
            Log.e(tag, message);
        }
    }

    @Override
    public void e(String tag, String message, Throwable throwable) {
        if (shouldPrint(ILogger.ERROR)) {
            Log.e(tag, message, throwable);
        }
    }
}
