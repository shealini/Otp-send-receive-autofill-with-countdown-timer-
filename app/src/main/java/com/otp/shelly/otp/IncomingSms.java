package com.otp.shelly.otp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.EditText;



/**
 * Created by Thegirlwithspellingmistake on 19/02/16.
 */
public class IncomingSms extends BroadcastReceiver {

    private String TAG = "IncomingSms";
    private Handler handler ;

    public IncomingSms(Handler handler) {
        this.handler = handler;
    }

    public IncomingSms() {

    }




    @Override
    public void onReceive(final Context context, Intent intent) {

        final Bundle bundle = intent.getExtras();
        try {
            if (bundle != null)
                Log.i(TAG, "BUNDLE IS NOT NULL ");
            {
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                for (int i = 0; i < pdusObj.length; i++) {
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                    String senderNum = phoneNumber;
                    final String message = currentMessage.getDisplayMessageBody();
                    Log.i(TAG, "SENDER MESSAGE IS == " + message);

                    try {   // Check with senders number
                        if (message.equals("Hii")) {
//                            Otp Sms = new Otp();
//                            Sms.recivedSms(message);
                            Intent inten = new Intent(context , Otp.class);
                            inten.putExtra("message", message);
                            inten.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(inten);
                            Log.i(TAG, "GOT THE MATCH for OTP ");

                        }
                    } catch (Exception e) {
                        Log.d(TAG , e.getMessage()) ;
                    }
                }
            }

        } catch (Exception e) {
        }
    }

}
