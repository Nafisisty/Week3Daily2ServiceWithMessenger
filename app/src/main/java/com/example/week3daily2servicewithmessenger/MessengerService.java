package com.example.week3daily2servicewithmessenger;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.widget.Toast;

public class MessengerService extends Service {
    private String TAG = "MessengerService";

    // Message codes to check against Message.what
    //
    // Message.what is a User-defined message code so
    // that the recipient can identify what the message is about.
    static final int MSG_SAY_HELLO = 1;

    // Messenger object used by clients to send messages to IncomingHandler
    Messenger mMessenger = new Messenger((Handler) new IncomingHandler());

    // Incoming messages Handler
    class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SAY_HELLO:
                    Bundle bundle = msg.getData();
                    String hello = (String) bundle.get("hello");

                    Toast.makeText(getApplicationContext(), hello, Toast.LENGTH_SHORT).show();
                    break;
                default:
                    super.handleMessage(msg);
            }

        }
    }

    public MessengerService() {
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate called");
    }

    /*
    Return our Messenger interface for sending messages to
    the service by the clients.
    */
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind done");
        return mMessenger.getBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return false;
    }
}
