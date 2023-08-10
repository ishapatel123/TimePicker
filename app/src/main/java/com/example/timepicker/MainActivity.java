package com.example.timepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView setTime;
    TimePicker timePicker;
    private String am_pm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timePicker=findViewById(R.id.timepicker);
        setTime=findViewById(R.id.setTime);

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hours, int minutes) {
             setTime.setText(""+hours+":"+minutes);
             timePicker.setVisibility(View.GONE);
            }
        });
        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar=Calendar.getInstance();
                TimePickerDialog dialog=new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hours, int minutes) {
                        calendar.set(Calendar.HOUR_OF_DAY,hours);
                        calendar.set(Calendar.MINUTE,minutes);
                        if(calendar.get(Calendar.AM_PM)==Calendar.AM)
                        {
                             am_pm = "AM";
                        }
                        else if(calendar.get(Calendar.AM_PM)==Calendar.PM)
                        {
                            am_pm = "PM";
                        }
                        String strHrsToShow=(calendar.get(Calendar.HOUR)==0)?"12": Integer.toString(minutes);
                        setTime.setText(""+strHrsToShow+":"+minutes+" "+am_pm);
                    }
                },calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false);
                dialog.show();
            }
        });
    }
}