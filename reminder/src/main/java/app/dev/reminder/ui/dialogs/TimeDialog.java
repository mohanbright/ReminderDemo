package app.dev.reminder.ui.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.TimePicker;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import app.dev.reminder.helper.DataItemCallbackThree;

public class TimeDialog extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    private DataItemCallbackThree<Integer, Integer,Integer> dataItemCallback;
    private Activity activity;

    public TimeDialog(DataItemCallbackThree<Integer, Integer,Integer> dataItemCallback, Activity activity) {
        this.dataItemCallback = dataItemCallback;
        this.activity = activity;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        c.add(Calendar.HOUR,1);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(activity, this, hour, minute,
                false);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        dataItemCallback.onItemData(hourOfDay,minute,0);
    }
}
