package app.dev.reminder.ui.setup;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import app.dev.reminder.ReminderBuilder;
import app.dev.reminder.ReminderManger;
import app.dev.reminder.ScheduleEvent;

public class SetUpViewModel extends ViewModel {
    private MainObservable observable;

    public static final int REMINDER_CODE=1002;

    private MutableLiveData<String> msgLive;

    public MutableLiveData<String> getMsgLive() {
        if(msgLive==null){
            msgLive=new MutableLiveData<>();
        }
        return msgLive;
    }

    public void setMsgLive(MutableLiveData<String> msgLive) {
        this.msgLive = msgLive;
    }

    public MainObservable getObservable() {
        if(observable==null){
            observable=new MainObservable();
        }
        return observable;
    }

    public void setObservable(MainObservable observable) {
        this.observable = observable;
    }

    public class MainObservable extends BaseObservable {
        private String time;
        private String date;

        private Calendar calendar;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
            notifyChange();
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
            notifyChange();
        }

        public Calendar getCalendar() {
            return calendar;
        }

        public void setCalendar(Calendar calendar) {
            this.calendar = calendar;
            notifyChange();
        }
    }
}
