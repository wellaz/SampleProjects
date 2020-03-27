package com.example.wellington.samplepos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Picture;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by wellington on 27/3/2020.
 */

public class PrinterActivity extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            WebView.enableSlowWholeDocumentDraw();
        }
        setContentView(R.layout.activity_print);
        webView =(WebView)findViewById(R.id.txt_webview);
        webView.setDrawingCacheEnabled(true);
        webView.loadData(readFile(), "text/html; charset=utf-8", "UTF-8");
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Bitmap b=viewToImage(webView);
        PrintFragment printFragment =new PrintFragment();
        printFragment.initialize();
        printFragment.printPic(b);
        System.exit(0);
    }


    private String readFile() {
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File myFile = new File(path, "print.txt");
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(myFile));
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        } catch (Exception e) { }
        String result = text.toString();
        return "<body style='margin:10;padding:10;'>"+ result+"</body>";
    }


    public static Bitmap viewToImage( WebView viewToBeConverted) {
        int extraSpace = 2000; //because getContentHeight doesn't always return the full screen height.
        int height = viewToBeConverted.getContentHeight() + extraSpace;

        Bitmap viewBitmap = Bitmap.createBitmap(
                viewToBeConverted.getWidth(), height,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(viewBitmap);
        viewToBeConverted.draw(canvas);//w ww . ja  va 2 s. c  o m

        //If the view is scrolled, cut off the top part that is off the screen.
        try {
            int scrollY = viewToBeConverted.getScrollY();

            if (scrollY > 0) {
                viewBitmap = Bitmap.createBitmap(viewBitmap, 0, scrollY,
                        viewToBeConverted.getWidth(), height - scrollY);
            }
        } catch (Exception ex) {

        }
        return viewBitmap;
    }
}
