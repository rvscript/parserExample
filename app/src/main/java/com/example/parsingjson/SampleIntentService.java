package com.example.parsingjson;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class SampleIntentService extends IntentService {
    private final String TAG = this.getClass().getSimpleName();
    private Handler handler;
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public SampleIntentService() {
        super("SampleIntentService");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        handler = new Handler(getApplicationContext().getMainLooper());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "onHandleIntent reached...", Toast.LENGTH_SHORT).show();
            }
        });

        Log.i(TAG, "onHandleIntent: log works");
    }
}
