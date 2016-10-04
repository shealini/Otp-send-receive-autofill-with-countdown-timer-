package com.otp.shelly.otp;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Otp extends AppCompatActivity {

    private String TAG = "Otp" ;
    private String messageText;
    private EditText otpNumber;
    private Button startB;
    public TextView text;
    private CountDownTimer countDownTimer;

    private boolean startTimer = false;

    private final long startTime = (120 * 1000);
    private final long interval = 1 * 1000;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG , "ONCREATE OTP ");
        setContentView(R.layout.activity_otp);
        otpNumber = (EditText) findViewById(R.id.otpBox);

        messageText = getIntent().getStringExtra("message");
       otpNumber.setText(messageText);

        startB = (Button)findViewById(R.id.button);
        text = (TextView) findViewById(R.id.timer);

        countDownTimer = new MyCountDownTimer(startTime, interval);

        text.setText(text.getText() + String.valueOf(startTime / (60 * 1000)));
        timerControl(true);


        //    recivedSms(messageText);
        Log.i(TAG, "OTP ACTIVITY ") ;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG , "ONNEWINTENT OTP ") ;
        messageText = getIntent().getStringExtra("message");
        otpNumber.setText(messageText);
        timerControl(false);

    }

    public void timerControl(Boolean startTimer) {
        if (startTimer) {
            countDownTimer.start();
            startB.setText("STOP");

        } else {
            countDownTimer.cancel();
            startB.setText("RESTART");

        }

    }


    public class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }


        @Override
        public void onFinish() {
            text.setText("Time's up!");
        }


        @Override
        public void onTick(long millisUntilFinished) {

            long currentTime = millisUntilFinished/1000 ;

            text.setText("" + currentTime/60 + " : " +((currentTime % 60)>=10 ? currentTime % 60:"0" +( currentTime % 60)));

        }

    }

    public void recivedSms(final String message) {
        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    otpNumber.setText(message);
                }
            });
            Log.i(TAG, "OTP MESSAGE SET & OTP IS == " + message ) ;
        } catch (Exception e) {
            Log.d("Random",  e.getMessage());
        }
    }
}
