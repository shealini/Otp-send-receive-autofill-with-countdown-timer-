package com.otp.shelly.otp;


import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends ActionBarActivity  {

    private String TAG = "MainActivity";
    private Button sendOtp;
    private EditText mobileBox;
    private String mobileNumber;


    private IncomingSms incomingSms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendOtp = (Button) findViewById(R.id.sendOtp);
        mobileBox = (EditText) findViewById(R.id.mobileBox);


        sendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mobileNumber = mobileBox.getText().toString();
                if (!(mobileNumber == null || mobileNumber.isEmpty())) {

                    SmsManager smsManager = SmsManager.getDefault();

                    //TODO : generate OTP & replace that with "Hii shelly"

                    smsManager.sendTextMessage(mobileNumber, null, "Hii", null, null);
                    Log.i(TAG, "MESSAGE SENT Hii ");

                    //Call Otp activity
                    Intent inten = new Intent(MainActivity.this, Otp.class);
                    inten.putExtra("message", " ");
                    inten.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(inten);
                    Log.i(TAG, "GOT THE MATCH for OTP ");

                } else {
                    Log.i(TAG, "MOBILE NUMBER IS NOT AVAILABLE ");
                }
            }
        });

        incomingSms = new IncomingSms(new Handler());

    }





}