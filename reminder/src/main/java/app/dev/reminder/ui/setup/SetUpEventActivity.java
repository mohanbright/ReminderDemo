package app.dev.reminder.ui.setup;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import app.dev.reminder.R;
import app.dev.reminder.ReminderBuilder;
import app.dev.reminder.ReminderManger;
import app.dev.reminder.databinding.ActivitySetupBinding;
import app.dev.reminder.exception.ReminderException;
import app.dev.reminder.helper.DataItemCallbackThree;
import app.dev.reminder.helper.OnEventScheduleListener;
import app.dev.reminder.helper.ViewItemHandler;
import app.dev.reminder.ui.dialogs.DateDialog;
import app.dev.reminder.ui.dialogs.TimeDialog;
import timber.log.Timber;


public class SetUpEventActivity extends AppCompatActivity implements ViewItemHandler {
    private ActivitySetupBinding binding;
    private SetUpViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= DataBindingUtil.setContentView(this, R.layout.activity_setup);

        viewModel=new ViewModelProvider(this).get(SetUpViewModel.class);

        binding.setViewModel(viewModel);

        binding.setViewItemHandler(this);
    }

    @Override
    public void onViewClicked(View view) {
        int id = view.getId();

        if (id == R.id.date) {
            openDatePicker();
        } else if (id == R.id.time) {
            openTimePicker();
        } else if (id == R.id.set_reminder) {
            if (viewModel.getObservable().getCalendar() != null && viewModel.getObservable().getDate() != null && viewModel.getObservable().getTime() != null) {
                try {
                    //
                    ReminderBuilder.with(this)
                            .setSuccessMsg("Reminder Added Successfully")
                            .setFailedMsg("Failed to set reminder")
                            .setEventDateTime(viewModel.getObservable().getCalendar())
                            .setEvent(new OnEventScheduleListener() {
                                @Override
                                public void onEventSchedule(boolean isSuccessful, @NonNull String msg) {
                                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                                    if(isSuccessful){
                                        if(ReminderManger.getRequestCode()!=-1){
                                            setResult(ReminderManger.getRequestCode());
                                            finish();
                                        }
                                    }
                                }
                            });
                } catch (ReminderException e) {
                    Timber.d("Failed!!");
                }
            } else {
                Toast.makeText(this, "Set date & time first!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void openTimePicker() {
        DialogFragment dialogFragment = new TimeDialog(new DataItemCallbackThree<Integer, Integer, Integer>() {
            @Override
            public void onItemData(Integer hourOfDay, Integer minute, Integer noNeed) {
                Calendar calendar=viewModel.getObservable().getCalendar()==null?Calendar.getInstance():viewModel.getObservable().getCalendar();
                calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                calendar.set(Calendar.MINUTE,minute);

                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("hh:mm a");
                viewModel.getObservable().setTime(simpleDateFormat.format(calendar.getTime()));

                viewModel.getObservable().setCalendar(calendar);
            }
        }, this);
        dialogFragment.show(getSupportFragmentManager(), "Select Time");
    }

    private void openDatePicker() {
        DialogFragment dialogFragment = new DateDialog(new DataItemCallbackThree<Integer, Integer, Integer>() {
            @Override
            public void onItemData(Integer year, Integer month, Integer day) {
                Calendar calendar=viewModel.getObservable().getCalendar()==null?Calendar.getInstance():viewModel.getObservable().getCalendar();
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,day);

                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MMMM dd, yyyy");

                viewModel.getObservable().setDate(simpleDateFormat.format(calendar.getTime()));

                viewModel.getObservable().setCalendar(calendar);
            }
        }, this);
        dialogFragment.show(getSupportFragmentManager(), "Select Date");
    }
}
