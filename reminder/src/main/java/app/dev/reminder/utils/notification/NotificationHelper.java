package app.dev.reminder.utils.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.RingtoneManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import app.dev.reminder.R;

public class NotificationHelper extends ContextWrapper {
    private NotificationManager manager;
    public static final String DEFAULT_CHANNEL = "default_channel";
    private static final int NOTIFICATION_ID=2001;

    public NotificationManager getManager() {
        return manager;
    }

    public NotificationHelper(Context context) {
        super(context);

        NotificationChannel mChannel = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            manager=context.getApplicationContext().getSystemService(NotificationManager.class);
        }else{
            manager= (NotificationManager) context.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mChannel = new NotificationChannel(DEFAULT_CHANNEL,
                    context.getResources().getString(R.string.notification_channel_default), NotificationManager.IMPORTANCE_DEFAULT);
            mChannel.setLightColor(Color.BLACK);
            mChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);

            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build();

            mChannel.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION), audioAttributes);
            mChannel.setVibrationPattern(new long[]{0});
            getManager().createNotificationChannel(mChannel);
        }
    }

    public NotificationCompat.Builder getNotification(@NonNull Context context, String title, String body, PendingIntent resultPendingIntent) {
        NotificationCompat.Builder mBuilder;

        mBuilder = new NotificationCompat.Builder(context.getApplicationContext(), DEFAULT_CHANNEL);
        mBuilder.setSmallIcon(getSmallIcon());
        mBuilder.setColor(ContextCompat.getColor(context.getApplicationContext(), R.color.colorAccent));

        mBuilder.setContentTitle(title)
                .setContentText(body)
                .setContentIntent(resultPendingIntent)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION), AudioManager.STREAM_NOTIFICATION)
                .setVibrate(new long[]{0L, 1000L, 500L})
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        mBuilder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
        mBuilder.setAutoCancel(true);

        return mBuilder;
    }

    private int getSmallIcon() {
        return R.drawable.ic_notification;
    }

    private void notifyNow(int id, NotificationCompat.Builder notification) {
        getManager().notify(id, notification.build());
    }

    public void sendNotification(@NonNull NotificationCompat.Builder notification){
        notifyNow(NOTIFICATION_ID,notification);
    }
}
