package com.example.wellington.samplepos;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zcs.sdk.SdkResult;
import com.zcs.sdk.Sys;
import com.zcs.sdk.card.CardReaderTypeEnum;

import java.io.Console;
import java.io.File;
import java.io.PrintWriter;
import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;


public class MainActivity extends AppCompatActivity {
    //Intent mServiceIntent;
    //private service mSensorService;
    public static int ReqCode = 11;
    Context ctx;

    public Context getCtx() {
        return ctx;
    }

    CardFragment cardFragment;
    private Timer timer = new Timer();
    public static final String SHARED_PREFS = "sharedData";
    public static final String TEXT = "text";
    public static final String SWITCH1 = "switch1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx = this;
        setContentView(R.layout.activity_main);

        cardFragment = new CardFragment(this);
        //cardFragment.readAllCardTypes();
        final TextView textView = (TextView) findViewById(R.id.topic);
        final Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(50); //You can manage the blinking time with this parameter
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);

        ((Button) findViewById(R.id.btnswipe)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Swipe card...");
                textView.startAnimation(anim);
                cardFragment.searchBankCard(CardReaderTypeEnum.MAG_CARD);
            }
        });
        ((Button) findViewById(R.id.btninsert)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Insert card...");
                textView.startAnimation(anim);
                cardFragment.searchBankCard(CardReaderTypeEnum.IC_CARD);
            }
        });

      /*  TimerTask timerTask = new TimerTask() {
            public void run() {
                if (!cardFragment.cardNumber.isEmpty()) {
                    String val = cardFragment.cardNumber;
                    Log.i("final", val);
                    saveData(val);
                    timer.cancel();
                    timer = null;
                    finish();
                }
            }
        };
        timer.schedule(timerTask, 1000, 1000);*/

        // mSensorService = new service(getCtx());
        // mServiceIntent = new Intent(getCtx(), mSensorService.getClass());
        //  if (!isMyServiceRunning(mSensorService.getClass())) {
        //    startService(mServiceIntent);
        // Toast.makeText(getBaseContext(), "Hello........", Toast.LENGTH_LONG).show();
        // }

    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                Log.i("isMyServiceRunning?", true + "");
                //cardFragment = new CardFragment();
                return true;
            }
        }
        Log.i("isMyServiceRunning?", false + "");
        return false;
    }


    @Override
    protected void onDestroy() {
        // stopService(mServiceIntent);
        Log.i("MAINACT", "onDestroy!");
        super.onDestroy();
        System.exit(0);
    }

    public void saveData(String value) {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_WORLD_READABLE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TEXT, value);
        editor.apply();
        writeToFile(value);
    }

    public void writeToFile(String value) {
        try {
            File path = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS);
            File myFile = new File(path, "mytextfile.txt");
            if (myFile.exists()) {
                PrintWriter writer = new PrintWriter(myFile);
                writer.print("");
                writer.close();
            }
            FileOutputStream fOut = new FileOutputStream(myFile, true);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append(value);
            myOutWriter.close();
            fOut.close();
        } catch (java.io.IOException e) {
            //do something if an IOException occurs.
        }
    }


}
