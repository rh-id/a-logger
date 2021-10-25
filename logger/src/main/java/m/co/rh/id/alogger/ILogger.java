package m.co.rh.id.alogger;

import android.util.Log;

import java.util.concurrent.ExecutorService;

public interface ILogger {
    int VERBOSE = Log.VERBOSE;
    int DEBUG = Log.DEBUG;
    int INFO = Log.INFO;
    int WARN = Log.WARN;
    int ERROR = Log.ERROR;

    /**
     * Same as {@link #v(String, String, Throwable)}
     * with throwable null
     */
    void v(String tag, String message);

    /**
     * Print VERBOSE log
     *
     * @param tag
     * @param message
     * @param throwable
     */
    void v(String tag, String message, Throwable throwable);

    /**
     * Same as {@link #d(String, String, Throwable)}
     * with throwable null
     */
    void d(String tag, String message);

    /**
     * Print DEBUG log
     *
     * @param tag
     * @param message
     * @param throwable
     */
    void d(String tag, String message, Throwable throwable);

    /**
     * Same as {@link #i(String, String, Throwable)}
     * with throwable null
     *
     * @param tag
     * @param message
     */
    void i(String tag, String message);

    /**
     * Print INFO log
     *
     * @param tag
     * @param message
     * @param throwable
     */
    void i(String tag, String message, Throwable throwable);

    /**
     * Same as {@link #w(String, String, Throwable)}
     * with throwable null
     *
     * @param tag
     * @param message
     */
    void w(String tag, String message);

    /**
     * Print WARN log
     *
     * @param tag
     * @param message
     * @param throwable
     */
    void w(String tag, String message, Throwable throwable);

    /**
     * Same as {@link #e(String, String, Throwable)}
     * with throwable null
     *
     * @param tag
     * @param message
     */
    void e(String tag, String message);

    /**
     * Print ERROR log
     *
     * @param tag
     * @param message
     * @param throwable
     */
    void e(String tag, String message, Throwable throwable);
}
