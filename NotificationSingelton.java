package binarysole.adrive.Utility;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;


import binarysole.adrive.R;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by Opriday on 12/21/2018.
 */

public class NotificationSingelton {

    public static NotificationSingelton notificationSingelton;
    public static Context context;
    Intent main;


    public static NotificationSingelton getInstance(Context mContext) {
        context = mContext;
        if (notificationSingelton == null) {
            notificationSingelton = new NotificationSingelton();
            return notificationSingelton;
        }
        return notificationSingelton;
    }

    public void sendMeNotification(String message) {

        NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder notification = null;


//        if (!SharePreferenceSingelton.getInstance(context).onGetWelcomeNotifyStatus()) {
//            main = new Intent(context, about.class);
//            SharePreferenceSingelton.getInstance(context).onPushWelcomeStatusToPrefrence(true);
//        } else {
//            main = new Intent(context, MainActivity.class);
//        }
//        main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

//        PendingIntent pendingIntent = PendingIntent.getActivity(context, 001, main, PendingIntent.FLAG_ONE_SHOT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("Veezlo","Veezlo", importance);
            channel.setDescription("It's a personal channel");
            channel.enableVibration(false);
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            manager.createNotificationChannel(channel);
            notification = new NotificationCompat.Builder(context, channel.getId());
        } else {
            notification = new NotificationCompat.Builder(context,"Veezlo");
        }
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        notification.setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(Notification.PRIORITY_HIGH)
                .setContentTitle("Adrivee")
                .setContentText(message)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                .setAutoCancel(true)
                .setSound(sound)
                .setLights(Color.RED, 200, 200);
//                .setContentIntent(pendingIntent);

        manager.notify(0, notification.build());
    }


}
