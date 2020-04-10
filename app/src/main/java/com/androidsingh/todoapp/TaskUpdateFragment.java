package com.androidsingh.todoapp;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class TaskUpdateFragment extends Fragment {
    EditText id,task,time;
    Button update_task;
    Calendar calendar;
    int currentHour,currentMinute;
    String amPM;
    MyDataBaseHelperClass myDataBaseHelperClass;
    TimePickerDialog timePickerDialog;
    public TaskUpdateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_task_update, container, false);
        myDataBaseHelperClass=new MyDataBaseHelperClass(getActivity());
        id=(EditText)view.findViewById(R.id.id);
        task=(EditText)view.findViewById(R.id.task);
        time=(EditText)view.findViewById(R.id.time);
        update_task=(Button)view.findViewById(R.id.update_task);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar=Calendar.getInstance();
                currentHour=calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute=calendar.get(Calendar.MINUTE);
                timePickerDialog=new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if (hourOfDay >= 12) {
                            amPM="PM";
                            hourOfDay=hourOfDay-12;
                        } else {
                            amPM="AM";
                        }
                        time.setText(""+hourOfDay+":"+minute+" "+amPM);
                    }
                },currentHour,currentMinute,false);
                timePickerDialog.show();
            }
        });
        update_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id1=id.getText().toString();
                String task1=task.getText().toString();
                String time1=time.getText().toString();
                boolean update=myDataBaseHelperClass.updateData(id1,task1,time1);
                if (update==true) {
                    Toast.makeText(getActivity(),"Task Updated",Toast.LENGTH_SHORT).show();
                    id.setText("");
                    task.setText("");
                    time.setText("");
                } else {
                    Toast.makeText(getActivity(),"Task not updated",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}
