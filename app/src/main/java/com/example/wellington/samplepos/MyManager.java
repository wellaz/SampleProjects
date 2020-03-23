package com.example.wellington.samplepos;

import com.zcs.sdk.Beeper;
import com.zcs.sdk.DriverManager;
import com.zcs.sdk.Led;
import com.zcs.sdk.Printer;
import com.zcs.sdk.Sys;
import com.zcs.sdk.bluetooth.emv.BluetoothHandler;
import com.zcs.sdk.card.CardReaderManager;
import com.zcs.sdk.card.ICCard;
import com.zcs.sdk.card.IDCard;
import com.zcs.sdk.card.MagCard;
import com.zcs.sdk.card.RfCard;
import com.zcs.sdk.card.SLE4428Card;
import com.zcs.sdk.card.SLE4442Card;
import com.zcs.sdk.emv.EmvHandler;
import com.zcs.sdk.exteranl.ExternalCardManager;
import com.zcs.sdk.fingerprint.FingerprintManager;
import com.zcs.sdk.pin.pinpad.PinPadManager;

/**
 * Created by wellington on 17/3/2020.
 */

public class MyManager {

    public DriverManager sysDriverManager = DriverManager.getInstance();
    public Sys sysManager = sysDriverManager.getBaseSysDevice();

    public CardReaderManager sysCardReaderManager = sysDriverManager.getCardReadManager();
    public ICCard sysICCard = sysCardReaderManager.getICCard();
    public MagCard sysMagCard = sysCardReaderManager.getMAGCard();

    public RfCard sysRFCard = sysCardReaderManager.getRFCard();
    public SLE4428Card sysSLE4428 = sysCardReaderManager.getSLE4428Card();
    public SLE4442Card sysSLE4442 = sysCardReaderManager.getSLE4442Card();
    public IDCard sysIDCard = sysCardReaderManager.getIDCard();

    public EmvHandler sysEMVHandler = EmvHandler.getInstance();
    public PinPadManager sysPinPadManager = sysDriverManager.getPadManager();
    public Printer sysPrinter = sysDriverManager.getPrinter();
    public FingerprintManager sysFingerPrintmanager = sysDriverManager.getFingerprintManager();
    public Beeper sysBeeper = sysDriverManager.getBeeper();
    public Led sysLed = sysDriverManager.getLedDriver();
    public BluetoothHandler sysBlutoothHandler = sysDriverManager.getBluetoothHandler();
    public ExternalCardManager sysExteranCardManager = sysDriverManager.getExternalCardManager();


}
