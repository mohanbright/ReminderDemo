package app.dev.reminder;

import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

import androidx.annotation.NonNull;
import app.dev.reminder.exception.ReminderException;
import app.dev.reminder.helper.OnEventScheduleListener;

public class ReminderManger {
    private static String successMsg;
    private static String failedMsg;
    private static String notificationTitle;
    private static String notificationBody;
    private static Calendar eventDateTime;
    private static Intent notificationCallingIntent;
    protected static final int REMINDER_CODE=1002;
    private static int REQUEST_CODE=-1;

    public static String getNotificationTitle() {
        return notificationTitle;
    }

    public static void setNotificationTitle(String notificationTitle) {
        ReminderManger.notificationTitle = notificationTitle;
    }

    public static String getNotificationBody() {
        return notificationBody;
    }

    public static void setNotificationBody(String notificationBody) {
        ReminderManger.notificationBody = notificationBody;
    }

    public static int getRequestCode() {
        return REQUEST_CODE;
    }

    public static void setRequestCode(int requestCode) {
        REQUEST_CODE = requestCode;
    }

    public static void setSuccessMsg(String successMsg) {
        ReminderManger.successMsg = successMsg;
    }

    public static void setFailedMsg(String failedMsg) {
        ReminderManger.failedMsg = failedMsg;
    }

    public static void setEventDateTime(Calendar eventDateTime) {
        ReminderManger.eventDateTime = eventDateTime;
    }

    public static void setNotificationCallingIntent(Intent notificationCallingIntent) {
        ReminderManger.notificationCallingIntent = notificationCallingIntent;
    }

    public static String getSuccessMsg() {
        if(successMsg==null || successMsg.trim().isEmpty()){
            successMsg="Reminder Added Successfully";
        }
        return successMsg;
    }

    public static String getFailedMsg() {
        if(failedMsg==null || failedMsg.trim().isEmpty()){
            failedMsg="Failed to set reminder";
        }
        return failedMsg;
    }

    public static Calendar getEventDateTime() {
        return eventDateTime;
    }

    public static Intent getNotificationCallingIntent() {
        return notificationCallingIntent;
    }

    static boolean scheduleEvent(@NonNull Context context, @NonNull OnEventScheduleListener listener) throws ReminderException {
        if(context ==null){
            throw new ReminderException("Context is null", ReminderException.CONTEXT_NULL);
        }
        if(eventDateTime==null){
            throw new ReminderException("Provided calender is null or empty",ReminderException.CALENDAR_NULL);
        }
        if(failedMsg==null){
            throw new ReminderException("Provided input msg is null or empty",ReminderException.INPUT_MSG_NULL);
        }
        if(successMsg==null){
            throw new ReminderException("Provided input msg is null or empty",ReminderException.INPUT_MSG_NULL);
        }
        if(notificationTitle==null){
            throw new ReminderException("Provided input msg is null or empty",ReminderException.INPUT_MSG_NULL);
        }
        if(notificationBody==null){
            throw new ReminderException("Provided input msg is null or empty",ReminderException.INPUT_MSG_NULL);
        }

        if(notificationCallingIntent==null){
            throw new ReminderException("Provided intent is null or empty",ReminderException.CALLING_INTENT_NULL);
        }
        if(listener==null){
            throw new ReminderException("Provided callback is null",ReminderException.CALLBACK_NULL);
        }
       return new ScheduleEvent().scheduleEvent(context,eventDateTime,REMINDER_CODE,listener);
    }
}
