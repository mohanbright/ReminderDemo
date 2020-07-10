package app.dev.reminder.receiver;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import app.dev.reminder.ReminderManger;
import app.dev.reminder.utils.notification.NotificationHelper;

public class ScheduleEventReceiver extends BroadcastReceiver {

    public static final String SCHEDULED_EVENT_ACTION="app.dev.reminder.SCHEDULED_EVENT";

    @Override
    public void onReceive(Context context, Intent intent) {
        //set up notification

        switch (intent.getAction()){
            case SCHEDULED_EVENT_ACTION:{
                String title=ReminderManger.getNotificationTitle();//intent.getStringExtra(BaseConstants.PARCEL_DATA);
                String body=ReminderManger.getNotificationBody();//intent.getStringExtra(BaseConstants.PARCEL_EXTRA_DATA);
                notifyEvent(context,title,body);
                break;
            }
        }
    }

    private void notifyEvent(@NonNull Context context, @Nullable String title, @Nullable String msg) {
        NotificationHelper notificationHelper=new NotificationHelper(context);

        PendingIntent pendingIntent=PendingIntent.getActivity(context,0, ReminderManger.getNotificationCallingIntent(),PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder=notificationHelper.getNotification(context,title,msg,pendingIntent);

        notificationHelper.sendNotification(builder);
    }
}
