package app.dev.reminder.ui.dialogs;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import app.dev.reminder.helper.DataItemCallbackThree;

public class DateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private DataItemCallbackThree<Integer, Integer, Integer> itemCallbackThree;
    private Activity activity;

    public DateDialog(DataItemCallbackThree<Integer, Integer, Integer> itemCallbackThree, Activity activity) {
        this.itemCallbackThree = itemCallbackThree;
        this.activity = activity;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog=new DatePickerDialog(activity,this,year,month,day);
        datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis()- 1000);
        return datePickerDialog;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        itemCallbackThree.onItemData(year,month,day);
    }
}
