package m.co.rh.id.alogger;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class CompositeLoggerTest {

    @Test
    public void getLogger_gettingExactLoggerType() {
        AndroidLogger androidLogger = Mockito.mock(AndroidLogger.class);
        FileLogger fileLogger = Mockito.mock(FileLogger.class);
        ToastLogger toastLogger = Mockito.mock(ToastLogger.class);
        List<ILogger> iLoggerList = new ArrayList<>();
        iLoggerList.add(androidLogger);
        iLoggerList.add(fileLogger);
        iLoggerList.add(toastLogger);

        CompositeLogger compositeLogger = new CompositeLogger(iLoggerList);
        Assert.assertSame(compositeLogger.getLogger(AndroidLogger.class), androidLogger);
        Assert.assertSame(compositeLogger.getLogger(FileLogger.class), fileLogger);
        Assert.assertSame(compositeLogger.getLogger(ToastLogger.class), toastLogger);
    }

    @Test
    public void setLogLevel_settingBaseOnLoggerType() {
        AndroidLogger androidLogger = Mockito.mock(AndroidLogger.class);
        FileLogger fileLogger = Mockito.mock(FileLogger.class);
        ToastLogger toastLogger = Mockito.mock(ToastLogger.class);
        List<ILogger> iLoggerList = new ArrayList<>();
        iLoggerList.add(androidLogger);
        iLoggerList.add(fileLogger);
        iLoggerList.add(toastLogger);

        CompositeLogger compositeLogger = new CompositeLogger(iLoggerList);
        compositeLogger.setLogLevel(AndroidLogger.class, ILogger.ERROR);
        Mockito.verify(androidLogger, Mockito.times(1)).setLogLevel(ILogger.ERROR);
        compositeLogger.setLogLevel(FileLogger.class, ILogger.DEBUG);
        Mockito.verify(fileLogger, Mockito.times(1)).setLogLevel(ILogger.DEBUG);
        compositeLogger.setLogLevel(ToastLogger.class, ILogger.INFO);
        Mockito.verify(toastLogger, Mockito.times(1)).setLogLevel(ILogger.INFO);

        Mockito.clearInvocations(androidLogger, fileLogger, toastLogger);

        compositeLogger.setLogLevel(ILogger.class, ILogger.VERBOSE);
        Mockito.verify(androidLogger, Mockito.times(1)).setLogLevel(ILogger.VERBOSE);
        Mockito.verify(fileLogger, Mockito.times(1)).setLogLevel(ILogger.VERBOSE);
        Mockito.verify(toastLogger, Mockito.times(1)).setLogLevel(ILogger.VERBOSE);

    }
}
