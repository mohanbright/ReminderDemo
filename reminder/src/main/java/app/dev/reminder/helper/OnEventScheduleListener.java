package app.dev.reminder.helper;

import androidx.annotation.NonNull;

public interface OnEventScheduleListener {
    void onEventSchedule(boolean isSuccessful, @NonNull String msg);
}
