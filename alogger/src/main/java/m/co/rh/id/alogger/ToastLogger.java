package m.co.rh.id.alogger;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

/**
 * Logger that shows toast message
 */
public class ToastLogger implements ILogger {
    private final int mLogLevel;
    private final Context mAppContext;
    private final Handler mMainHandler;
    private final ToastOptions mToastOptions;

    /**
     * Prepare android logger
     *
     * @param logLevel    log level, (Example ILogger.VERBOSE,ILogger.DEBUG)
     * @param context     app context for this toast
     * @param mainHandler handler with main looper to post the toast message
     */
    public ToastLogger(int logLevel, Context context, Handler mainHandler, ToastOptions toastOptions) {
        mLogLevel = logLevel;
        mAppContext = context.getApplicationContext();
        if (mainHandler.getLooper() != Looper.getMainLooper())
            throw new IllegalStateException("Handler's looper must be set to main looper");
        mMainHandler = mainHandler;
        mToastOptions = toastOptions;
    }

    /**
     * Same as {@link #ToastLogger(int, Context, Handler, ToastOptions)}
     * Prepare ToastLogger with default handler with main looper
     */
    public ToastLogger(int logLevel, Context context, ToastOptions toastOptions) {
        this(logLevel, context, new Handler(Looper.getMainLooper()), toastOptions);
    }

    /**
     * Same as {@link #ToastLogger(int, Context, Handler, ToastOptions)}
     * Prepare ToastLogger with default handler with main looper and
     * ToastOptions with toastLength = Toast.LENGTH_LONG, showLogLevel = false, showTag = false
     */
    public ToastLogger(int logLevel, Context context) {
        this(logLevel, context, new Handler(Looper.getMainLooper()), new ToastOptions(
                false,
                false,
                Toast.LENGTH_LONG));
    }

    @SuppressLint("WrongConstant")
    private void println(int level, String tag, String msg, Throwable throwable) {
        if (mLogLevel > level) {
            return;
        }
        mMainHandler.post(() -> {
            StringBuilder stringBuilder = new StringBuilder();
            if (mToastOptions.isShowLogLevel()) {
                String logLevel = "";
                switch (level) {
                    case VERBOSE:
                        logLevel = "V";
                        break;
                    case DEBUG:
                        logLevel = "D";
                        break;
                    case INFO:
                        logLevel = "I";
                        break;
                    case WARN:
                        logLevel = "W";
                        break;
                    case ERROR:
                        logLevel = "E";
                        break;
                }
                stringBuilder.append(logLevel);
            }
            if (mToastOptions.isShowTag()) {
                stringBuilder.append("/")
                        .append(tag);
            }
            if (mToastOptions.isShowLogLevel() && mToastOptions.isShowTag()) {
                stringBuilder.append(" ");
            }
            if (mToastOptions.isShowLogLevel() || mToastOptions.isShowTag()) {
                stringBuilder.append(": ");
            }
            stringBuilder.append(msg);
            if (throwable != null) {
                stringBuilder.append(": " + throwable.getMessage());
            }
            Toast.makeText(mAppContext, stringBuilder.toString(), mToastOptions.getToastLength())
                    .show();
        });
    }

    @Override
    public void v(String tag, String message) {
        println(VERBOSE, tag, message, null);
    }

    @Override
    public void v(String tag, String message, Throwable throwable) {
        println(VERBOSE, tag, message, throwable);
    }

    @Override
    public void d(String tag, String message) {
        println(DEBUG, tag, message, null);
    }

    @Override
    public void d(String tag, String message, Throwable throwable) {
        println(DEBUG, tag, message, throwable);
    }

    @Override
    public void i(String tag, String message) {
        println(INFO, tag, message, null);
    }

    @Override
    public void i(String tag, String message, Throwable throwable) {
        println(INFO, tag, message, throwable);
    }

    @Override
    public void w(String tag, String message) {
        println(WARN, tag, message, null);
    }

    @Override
    public void w(String tag, String message, Throwable throwable) {
        println(WARN, tag, message, throwable);
    }

    @Override
    public void e(String tag, String message) {
        println(ERROR, tag, message, null);
    }

    @Override
    public void e(String tag, String message, Throwable throwable) {
        println(ERROR, tag, message, throwable);
    }

    public static class ToastOptions {
        private final boolean mShowLogLevel;
        private final boolean mShowTag;
        private final int mToastLength;

        /**
         * @param showLogLevel Show log level? ("V","D","I","W", "E")
         * @param showTag      Show tag?
         * @param toastLength  how long to show toast? (example Toast.LENGTH_LONG, Toast.LENGTH_SHORT)
         */
        public ToastOptions(boolean showLogLevel, boolean showTag, int toastLength) {
            mShowLogLevel = showLogLevel;
            mShowTag = showTag;
            if (toastLength != Toast.LENGTH_SHORT && toastLength != Toast.LENGTH_LONG)
                throw new IllegalStateException("Toast length must be either LENGTH_SHORT or LENGTH_LONG");
            mToastLength = toastLength;
        }

        public boolean isShowLogLevel() {
            return mShowLogLevel;
        }

        public boolean isShowTag() {
            return mShowTag;
        }

        public int getToastLength() {
            return mToastLength;
        }
    }
}