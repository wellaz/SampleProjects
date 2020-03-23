package com.example.wellington.samplepos;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
/**
 * Created by wellington on 19/3/2020.
 */

public class autostart extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(autostart.class.getSimpleName(), "Service Stops! Oooooooooooooppppssssss!!!!");
        context.startService(new Intent(context, service.class));
    }
}
