package app.dev.reminder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

import androidx.annotation.NonNull;
import app.dev.reminder.exception.ReminderException;
import app.dev.reminder.helper.OnEventScheduleListener;
import app.dev.reminder.ui.setup.SetUpEventActivity;

public final class ReminderBuilder {
    private Context context;

    private ReminderBuilder(Context context) {
        this.context = context;
    }

    public static ReminderBuilder with(@NonNull Context context) throws ReminderException {
        if(context==null){
            throw new ReminderException("Provided context is null",ReminderException.CONTEXT_NULL);
        }
        return new ReminderBuilder(context);
    }

    public final ReminderBuilder setFailedMsg(@NonNull String failedMsg) throws ReminderException{
        if(failedMsg==null|| failedMsg.trim().isEmpty()){
            throw new ReminderException("Provided input msg is null or empty",ReminderException.INPUT_MSG_NULL);
        }
        ReminderManger.setFailedMsg(failedMsg);
        return this;
    }

    public final ReminderBuilder setSuccessMsg(@NonNull String successMsg) throws ReminderException{
        if(successMsg==null|| successMsg.trim().isEmpty()){
            throw new ReminderException("Provided input msg is null or empty",ReminderException.INPUT_MSG_NULL);
        }
        ReminderManger.setSuccessMsg(successMsg);
        return this;
    }

    public final ReminderBuilder setNotificationTitle(@NonNull String tile) throws ReminderException{
        if(tile==null|| tile.trim().isEmpty()){
            throw new ReminderException("Provided input msg is null or empty",ReminderException.INPUT_MSG_NULL);
        }
        ReminderManger.setNotificationTitle(tile);
        return this;
    }
    public final ReminderBuilder setNotificationBody(@NonNull String body) throws ReminderException{
        if(body==null|| body.trim().isEmpty()){
            throw new ReminderException("Provided input msg is null or empty",ReminderException.INPUT_MSG_NULL);
        }
        ReminderManger.setNotificationBody(body);
        return this;
    }

    public final ReminderBuilder setEventDateTime(@NonNull Calendar calendar) throws ReminderException{
        if(calendar==null){
            throw new ReminderException("Provided calender is null or empty",ReminderException.CALENDAR_NULL);
        }
        ReminderManger.setEventDateTime(calendar);
        return this;
    }

    public final ReminderBuilder setCallingIntent(@NonNull Intent intent) throws ReminderException{
        if(intent==null){
            throw new ReminderException("Provided intent is null or empty",ReminderException.CALLING_INTENT_NULL);
        }
        ReminderManger.setNotificationCallingIntent(intent);
        return this;
    }

    public final void setEvent(@NonNull OnEventScheduleListener listener) throws ReminderException{
        ReminderManger.scheduleEvent(context,listener);
    }

    public final void start(@NonNull Activity activity, Integer requestCode) throws ReminderException{
        if(activity==null){
            throw new ReminderException("Provided activity is null",ReminderException.CALLING_INTENT_NULL);
        }

        if(ReminderManger.getNotificationTitle()==null){
            throw new ReminderException("Provided input msg is null or empty",ReminderException.INPUT_MSG_NULL);
        }
        if(ReminderManger.getNotificationBody()==null){
            throw new ReminderException("Provided input msg is null or empty",ReminderException.INPUT_MSG_NULL);
        }

        if(requestCode==null){
            throw new ReminderException("Unable to start activity",ReminderException.FAILED_TO_START);
        }
        if(ReminderManger.getNotificationCallingIntent()==null){
            throw new ReminderException("Provided intent is null or empty",ReminderException.FAILED_TO_START);
        }

        ReminderManger.setRequestCode(requestCode);

        activity.startActivityForResult(new Intent(activity.getApplicationContext(), SetUpEventActivity.class),requestCode);
    }
}
