package app.dev.reminder.application;

import android.app.Application;

public class ReminderApp extends Application {
    static ReminderApp reminderApp;

    @Override
    public void onCreate() {
        super.onCreate();

        reminderApp=this;
    }

    public static ReminderApp getReminderApp() {
        return reminderApp;
    }
}
