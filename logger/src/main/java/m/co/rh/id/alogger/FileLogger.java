package m.co.rh.id.alogger;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Logger that writes log to file
 */
public class FileLogger implements ILogger {

    private int mLogLevel;
    private File mFile;
    private PrintWriter mPrintWriter;
    private ExecutorService mExecutorService;
    private DateFormat mDateFormat;

    /**
     * Prepare logger to be written to file
     *
     * @param logLevel        log level, (Example ILogger.VERBOSE,ILogger.DEBUG)
     * @param file            the log file
     * @param executorService executor that this log run on
     * @throws FileNotFoundException if file not found
     * @throws IOException           if fail to create file
     */
    public FileLogger(int logLevel, File file, ExecutorService executorService) throws IOException {
        if (file == null) throw new IllegalStateException("File must not null");
        mLogLevel = logLevel;
        mFile = file;
        checkFileExist();
        if (executorService == null) {
            mExecutorService = Executors.newSingleThreadExecutor();
        } else {
            mExecutorService = executorService;
        }
        mDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    }

    private synchronized void checkFileExist() throws IOException {
        if (!mFile.exists()) {
            mFile.getParentFile().mkdirs();
            mFile.createNewFile();
        } else {
            // re-create file if log exceeds 20 MB
            long size = mFile.length();
            if (size > 20000000) {
                mFile.delete();
                mFile.createNewFile();
            }
        }
        FileWriter fileWriter = new FileWriter(mFile, true);
        BufferedWriter bw = new BufferedWriter(fileWriter);
        mPrintWriter = new PrintWriter(bw);
    }

    public FileLogger(int logLevel, File file) throws IOException {
        this(logLevel, file, null);
    }

    private void println(int level, String tag, String msg, Throwable throwable) {
        if (mLogLevel > level) {
            return;
        }
        String currentThreadName = Thread.currentThread().getName();
        mExecutorService.execute(() -> {
            try {
                checkFileExist();
            } catch (IOException e) {
                Log.e("FileLogger", "Failed to create log file, file not exist", e);
                return;
            }
            Date currentDate = new Date();
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
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(mDateFormat.format(currentDate));
            stringBuilder.append(" ");
            stringBuilder.append(currentThreadName);
            stringBuilder.append(" ");
            stringBuilder.append(logLevel).append("/").append(tag).append(": ");
            stringBuilder.append(msg);
            mPrintWriter.println(stringBuilder.toString());
            if (throwable != null) {
                throwable.printStackTrace(mPrintWriter);
            }
            mPrintWriter.flush();
        });
    }

    @Override
    public void setLogLevel(int logLevel) {
        mLogLevel = logLevel;
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
}
