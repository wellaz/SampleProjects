package com.example.wellington.samplepos;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.util.Log;
import android.view.View;

import com.zcs.sdk.DriverManager;
import com.zcs.sdk.Printer;
import com.zcs.sdk.SdkResult;
import com.zcs.sdk.Sys;
import com.zcs.sdk.print.PrnStrFormat;
import com.zcs.sdk.print.PrnTextFont;
import com.zcs.sdk.print.PrnTextStyle;

/**
 * Created by yyzz on 2018/5/25.
 */

public class PrintFragment  {
    private static final String TAG = "PrintFragment";
    private DriverManager mDriverManager = new MyManager().sysDriverManager;//MyApp.sDriverManager;
    private Printer mPrinter;

    public static final String QR_TEXT = "https://www.baidu.com";
    public static final String BAR_TEXT = "6922711079066";

    public void initialize() {
        mDriverManager = new MyManager().sysDriverManager;//MyApp.sDriverManager;
        mPrinter = mDriverManager.getPrinter();
        String s =getSn();
        Log.i("serial",s);

              //  printPaperOut();



              //  printMatrixText(1);



              //  printPic("print_demo.bmp");


            //    printQr();










    }


    int paperWidth = 360;
    int paperHeight = 240;




    boolean isLoop = false;

    void startPrintLoop(final int total, final int interval) {
        if (isLoop) {
            //Toast.makeText(getActivity(), "Loop printing now!!!", Toast.LENGTH_SHORT).show();
            return;
        }
        isLoop = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 0;
                do {
                    final int ret = printPic("test_print.png");
                    Log.e(TAG, "print error: " + count);
                    if (ret != SdkResult.SDK_OK) {
                        isLoop = false;
                        if (ret != SdkResult.SDK_PRN_STATUS_PAPEROUT) {
                            final int cnt = count;
                            //paper out
                        }
                    }
                    if (interval != 0) {
                        SystemClock.sleep(interval * 1000);
                    }
                } while (isLoop && (total == 0 || ++count < total));
                isLoop = false;
            }
        }).start();
    }

    void stopPrintLoop() {
        isLoop = false;
       // Toast.makeText(getActivity(), "Stop print", Toast.LENGTH_SHORT).show();
    }

    /**
     * paper out
     */
    private void printPaperOut() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int printStatus = mPrinter.getPrinterStatus();
                if (printStatus == SdkResult.SDK_PRN_STATUS_PAPEROUT) {
                    //Paper out
                } else {
                    mPrinter.setPrintLine(30);
                }
            }
        }).start();
    }

    private void printMatrixText(final int fontsStyle) {
        new Thread(new Runnable() {
            @Override
            public void run() {
              //  AssetManager asm = getActivity().getAssets();
             /*   InputStream inputStream = null;
                try {
                    inputStream = asm.open("china_unin.bmp");
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
               // Drawable d = Drawable.createFromStream(inputStream, null);
               // Bitmap bitmap = ((BitmapDrawable) d).getBitmap();

                int printStatus = mPrinter.getPrinterStatus();
                if (printStatus == SdkResult.SDK_PRN_STATUS_PAPEROUT) {
                    //Paperout
                } else {
                   // mPrinter.setPrintAppendBitmap(bitmap, Layout.Alignment.ALIGN_CENTER);
                    PrnStrFormat format = new PrnStrFormat();
                    format.setTextSize(30);
                    format.setAli(Layout.Alignment.ALIGN_CENTER);
                    format.setStyle(PrnTextStyle.BOLD);
                    if (fontsStyle == 0) {
                        format.setFont(PrnTextFont.DEFAULT);
                    } else {
                        format.setFont(PrnTextFont.CUSTOM);
                        format.setPath(Environment.getExternalStorageDirectory() + "/fonts/fangzhengyouyuan.ttf");
                    }
                    mPrinter.setPrintAppendString("Slip", format);
                    format.setTextSize(25);
                    format.setStyle(PrnTextStyle.NORMAL);
                    format.setAli(Layout.Alignment.ALIGN_NORMAL);
                    mPrinter.setPrintAppendString(" ", format);
                    mPrinter.setPrintAppendString("Merchant name" + " Test ", format);
                    mPrinter.setPrintAppendString("Merchant no: " + " 123456789012345 ", format);
                    mPrinter.setPrintAppendString("Terminal " + " 12345678 ", format);
                    mPrinter.setPrintAppendString("Operator" + " 01 ", format);
                    mPrinter.setPrintAppendString("Card no" + " ", format);
                    format.setAli(Layout.Alignment.ALIGN_CENTER);
                    format.setTextSize(30);
                    format.setStyle(PrnTextStyle.BOLD);
                    mPrinter.setPrintAppendString("6214 44** **** **** 7816", format);
                    format.setAli(Layout.Alignment.ALIGN_NORMAL);
                    format.setStyle(PrnTextStyle.NORMAL);
                    format.setTextSize(25);
                    mPrinter.setPrintAppendString("ACQ", format);
                    mPrinter.setPrintAppendString("ISS" + " ", format);
                    mPrinter.setPrintAppendString("Transaction type" + " ", format);
                    format.setTextSize(30);
                    format.setStyle(PrnTextStyle.BOLD);
                    mPrinter.setPrintAppendString("Sale" + " (C) ", format);
                    format.setAli(Layout.Alignment.ALIGN_NORMAL);
                    format.setStyle(PrnTextStyle.NORMAL);
                    format.setTextSize(25);
                    mPrinter.setPrintAppendString("EX date" + " 2030/10  ", format);
                    mPrinter.setPrintAppendString("Batch no" + " 000335 ", format);
                    mPrinter.setPrintAppendString("Voucher No" + " 000002 ", format);
                    mPrinter.setPrintAppendString("Date " + " 2018/05/28 ", format);
                    mPrinter.setPrintAppendString("Time " + " 00:00:01 ", format);
                    format.setTextSize(30);
                    format.setStyle(PrnTextStyle.BOLD);
                    mPrinter.setPrintAppendString("Amount " + "￥0.01", format);
                    format.setStyle(PrnTextStyle.NORMAL);
                    format.setTextSize(25);
                    mPrinter.setPrintAppendString("REF " + " ", format);
                    mPrinter.setPrintAppendString("Signature of card holder "+ " ", format);
                    mPrinter.setPrintAppendString(" ", format);

                    mPrinter.setPrintAppendString(" -----------------------------", format);
                    mPrinter.setPrintAppendString("remark "+ " ", format);
                    mPrinter.setPrintAppendString("Copy " + " ", format);
                    mPrinter.setPrintAppendString(" ", format);
                    mPrinter.setPrintAppendString(" ", format);
                    mPrinter.setPrintAppendString(" ", format);
                    mPrinter.setPrintAppendString(" ", format);
                    printStatus = mPrinter.setPrintStart();
                    if (printStatus == SdkResult.SDK_PRN_STATUS_PAPEROUT) {
                        //Paper out
                    }
                }
            }
        }).start();
    }
    public int printPic(Bitmap bitmap) {
        int printStatus = mPrinter.getPrinterStatus();
        if (printStatus == SdkResult.SDK_PRN_STATUS_PAPEROUT) {
            //Paper out
        } else {
            try {
                PrnStrFormat format = new PrnStrFormat();
                 mPrinter.setPrintAppendBitmap(bitmap, Layout.Alignment.ALIGN_CENTER);
                mPrinter.setPrintAppendString(" ", format);
                mPrinter.setPrintAppendString(" ", format);
                mPrinter.setPrintAppendString(" ", format);
                printStatus = mPrinter.setPrintStart();
                if (printStatus == SdkResult.SDK_PRN_STATUS_PAPEROUT) {
                    //Paper out
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return printStatus;
    }


    private int printPic(String path) {
        int printStatus = mPrinter.getPrinterStatus();
        if (printStatus == SdkResult.SDK_PRN_STATUS_PAPEROUT) {
           //Paper out
        } else {
            try {
               // InputStream inputStream = getActivity().getAssets().open(path);
               // Drawable drawable = Drawable.createFromStream(inputStream, null);
               // Bitmap mBitmapDef = ((BitmapDrawable) drawable).getBitmap();

                PrnStrFormat format = new PrnStrFormat();
               // mPrinter.setPrintAppendBitmap(mBitmapDef, Layout.Alignment.ALIGN_CENTER);
                mPrinter.setPrintAppendString(" ", format);
                mPrinter.setPrintAppendString(" ", format);
                mPrinter.setPrintAppendString(" ", format);
                printStatus = mPrinter.setPrintStart();
                if (printStatus == SdkResult.SDK_PRN_STATUS_PAPEROUT) {
                   //Paper out
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return printStatus;
    }


    private void printQr() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int printStatus = mPrinter.getPrinterStatus();
                if (printStatus == SdkResult.SDK_PRN_STATUS_PAPEROUT) {
                   //Paper is out
                } else {

                    PrnStrFormat format = new PrnStrFormat();
                    mPrinter.setPrintAppendString("String one", format);
                    mPrinter.setPrintAppendQRCode(QR_TEXT, 200, 200, Layout.Alignment.ALIGN_NORMAL);
                    mPrinter.setPrintAppendString(" ", format);
                    mPrinter.setPrintAppendString("String 2", format);
                    mPrinter.setPrintAppendQRCode(QR_TEXT, 200, 200, Layout.Alignment.ALIGN_OPPOSITE);
                    mPrinter.setPrintAppendString(" ", format);
                    mPrinter.setPrintAppendString("Status 3", format);
                    mPrinter.setPrintAppendQRCode(QR_TEXT, 200, 200, Layout.Alignment.ALIGN_CENTER);
                    mPrinter.setPrintAppendString(" ", format);
                    mPrinter.setPrintAppendString(" ", format);
                    mPrinter.setPrintAppendString(" ", format);
                    printStatus = mPrinter.setPrintStart();
                    if (printStatus == SdkResult.SDK_PRN_STATUS_PAPEROUT) {
                       //Paper is out
                    }
                }
            }
        }).start();
    }

    private String getSn() {
        // Config the SDK base info
        Sys sys = new MyManager().sysDriverManager.getBaseSysDevice();
        String[] pid = new String[1];
        int status = sys.getPid(pid);
        int count = 0;
        while (status != SdkResult.SDK_OK && count < 3) {
            count++;
            int sysPowerOn = sys.sysPowerOn();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            final int i = sys.sdkInit();
        }
        int statue = sys.getFirmwareVer(new String[1]);
        if (statue != SdkResult.SDK_OK) {
            int sysPowerOn = sys.sysPowerOn();
            Log.i(TAG, "sysPowerOn: " + sysPowerOn);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int i = sys.sdkInit();
        if (i == SdkResult.SDK_OK) {
        }



        return pid[0];
    }
}
