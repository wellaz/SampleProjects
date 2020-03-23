package com.example.wellington.samplepos;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;
/**
 * Created by wellington on 19/3/2020.
 */

public class service extends Service {
    public int counter=0;
    private static final int READ_TIMEOUT = 60 * 1000;
    //CardFragment cardFragment = new CardFragment();
    public service(Context applicationContext) {
        super();
        Log.i("HERE", "here I am!");

    }

    public service() {
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        startService("com.szzcs.smartpos.MainActivity");
       // cardFragment.Initialize();
        startTimer();
        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("EXIT", "ondestroy!");
        Intent broadcastIntent = new Intent(this, autostart.class);

        sendBroadcast(broadcastIntent);
        stoptimertask();
    }

    private Timer timer;
    private TimerTask timerTask;
    long oldTime=0;
    public void startTimer() {
        //set a new Timer
        timer = new Timer();

        //initialize the TimerTask's job
        initializeTimerTask();

        //schedule the timer, to wake up every 1 second
       timer.schedule(timerTask, 1000, 1000); //
        //timer.schedule(timerTask, 0, 0); //

    }

    /**
     * it sets the timer to print the counter every x seconds
     */
    public void initializeTimerTask() {
        timerTask = new TimerTask() {
            public void run() {
               // Log.i("in timer", "in timer ++++  "+ (counter++));
            //  cardFragment.readAllCardTypes();
            }
        };
    }

    /**
     * not needed
     */
    public void stoptimertask() {
        //stop the timer, if it's not already null
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void startService(String aServiceName) {

        if (aServiceName.trim().length() > 0) {
            try {
                Context ctx = getApplicationContext();
                Intent iServiceIntent = ctx.getPackageManager().getLaunchIntentForPackage(aServiceName);
                ctx.startActivity(iServiceIntent);

                Thread.sleep(800);
            } catch (Exception e) {

            }
        }
    }
}
