package app.dev.reminder;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

import androidx.annotation.NonNull;
import app.dev.reminder.helper.OnEventScheduleListener;
import app.dev.reminder.receiver.ScheduleEventReceiver;
import timber.log.Timber;

public class ScheduleEvent {

    boolean scheduleEvent(@NonNull Context context, @NonNull Calendar calendar, int code, @NonNull OnEventScheduleListener listener){
        if(calendar!=null && context!=null && listener!=null) {
            Intent intent = new Intent(context.getApplicationContext(), ScheduleEventReceiver.class);
            intent.setAction(ScheduleEventReceiver.SCHEDULED_EVENT_ACTION);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(), code, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager = (AlarmManager) context.getApplicationContext().getSystemService(Activity.ALARM_SERVICE);
            if(alarmManager!=null) {
                calendar.set(Calendar.SECOND,0);
                long time = calendar.getTimeInMillis();
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, time, pendingIntent);
                Timber.tag("Reminder").d("Reminder added Successfully");
                listener.onEventSchedule(true, ReminderManger.getSuccessMsg());
                return true;
            }else{
                Timber.tag("ALARM MANAGER").d("Alarm manager is null");
                listener.onEventSchedule(false, ReminderManger.getFailedMsg());
                return false;
            }
        }else{
            Timber.tag("CALENDAR").d("calender is null");
            listener.onEventSchedule(false, ReminderManger.getFailedMsg());
            return false;
        }
    }

}
