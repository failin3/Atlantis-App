package com.tenf.atlantis.Notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.tenf.atlantis.MainActivity;
import com.tenf.atlantis.R;

/**
 * Created by Felix on 8-1-2018.
 */

public class Receiver extends BroadcastReceiver {

    public String title = "Vuilnis!";
    public String text = "Check of het vuilnis al buiten staat";

    @Override
    public void onReceive(Context context, Intent intent) {
        text = intent.getStringExtra("text");
        showNotification(context);
        Log.d("debug_tag", "notification sent with text: " + text);
    }

    public void showNotification(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(context, 1, intent, 0);


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_atlantis)
                .setContentTitle(title)
                .setContentText(text);
        mBuilder.setContentIntent(pi);
        mBuilder.setDefaults(Notification.DEFAULT_SOUND);
        mBuilder.setAutoCancel(true);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mBuilder.build());
    }
}