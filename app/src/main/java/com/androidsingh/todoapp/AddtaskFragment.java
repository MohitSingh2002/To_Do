package com.androidsingh.todoapp;

import android.app.TimePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddtaskFragment extends Fragment {
    EditText task,time;
    Button add_task;
    TimePickerDialog timePickerDialog;
    Calendar calendar;
    int currentHour,currentMinute;
    String amPM;
    MyDataBaseHelperClass myDataBaseHelperClass;
    private SQLiteDatabase sqLiteDatabase;
    public AddtaskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_addtask, container, false);
        myDataBaseHelperClass=new MyDataBaseHelperClass(getActivity());
        task=(EditText)v.findViewById(R.id.task);
        time=(EditText)v.findViewById(R.id.time);
        add_task=(Button)v.findViewById(R.id.add_task);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    calendar = Calendar.getInstance();
                    currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                    currentMinute = calendar.get(Calendar.MINUTE);
                    timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            if (hourOfDay >= 12) {
                                amPM = "PM";
                                hourOfDay = hourOfDay - 12;
                            } else {
                                amPM = "AM";
                            }
                            time.setText("" + hourOfDay + ":" + minute + " " + amPM);
                        }
                    }, currentHour, currentMinute, false);
                    timePickerDialog.show();
                }
        });
        add_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task1=task.getText().toString();
                String time1=time.getText().toString();
                boolean inserted=myDataBaseHelperClass.insertData(task1,time1);
                if (inserted == true) {
                    Toast.makeText(getActivity(),"New Task Added",Toast.LENGTH_SHORT).show();
                }
                task.setText("");
                time.setText("");
            }
        });
        return v;
    }
}
