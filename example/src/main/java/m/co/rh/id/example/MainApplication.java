package m.co.rh.id.example;

import android.app.Application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import m.co.rh.id.alogger.AndroidLogger;
import m.co.rh.id.alogger.CompositeLogger;
import m.co.rh.id.alogger.FileLogger;
import m.co.rh.id.alogger.ILogger;
import m.co.rh.id.alogger.ToastLogger;

public class MainApplication extends Application {

    private ILogger mLogger;

    @Override
    public void onCreate() {
        super.onCreate();
        List<ILogger> loggerList = new ArrayList<>();
        ILogger defaultLogger = new AndroidLogger(ILogger.ERROR);
        loggerList.add(defaultLogger);
        try {
            File file = new File(getCacheDir(), "alogger/app.log");
            ILogger fileLogger = new FileLogger(ILogger.VERBOSE, file);
            loggerList.add(fileLogger);
        } catch (IOException e) {
            defaultLogger.e("Application", "Error instantiating file logger", e);
        }
        // might want to show ERROR only on toast
        ILogger toastLogger = new ToastLogger(ILogger.ERROR, this);
        loggerList.add(toastLogger);
        mLogger = new CompositeLogger(loggerList);
    }

    public ILogger getLogger() {
        return mLogger;
    }
}
