package m.co.rh.id.example;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import m.co.rh.id.alogger.ILogger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ILogger logger = ((MainApplication) getApplication()).getLogger();
        String TAG = this.getClass().getName();
        Throwable throwable = new RuntimeException("example stack");
        logger.v(TAG, "this is verbose example");
        logger.v(TAG, "this is verbose example", throwable);
        logger.d(TAG, "this is debug example");
        logger.d(TAG, "this is debug example", throwable);
        logger.i(TAG, "this is info example");
        logger.i(TAG, "this is info example", throwable);
        logger.w(TAG, "this is warning example");
        logger.w(TAG, "this is warning example", throwable);
        logger.e(TAG, "this is error example");
        logger.e(TAG, "this is error example", throwable);
        new Handler(Looper.getMainLooper())
                .postDelayed(() -> {
                    logger.e(TAG, "this is delayed error", throwable);
                }, 9000);
    }
}