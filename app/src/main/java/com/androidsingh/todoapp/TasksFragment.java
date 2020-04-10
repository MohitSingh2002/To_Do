package com.androidsingh.todoapp;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class TasksFragment extends Fragment {
    Button show_tasks;
    MyDataBaseHelperClass myDataBaseHelperClass;
    public TasksFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_tasks, container, false);
        myDataBaseHelperClass=new MyDataBaseHelperClass(getActivity());
        show_tasks=(Button)v.findViewById(R.id.show_tasks);
        show_tasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=myDataBaseHelperClass.getAllData();
                if (res.getCount() == 0) {
                    showMessage("Alert","Nothing Found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Id:"+res.getString(0)+"\n");
                    buffer.append("Task: "+res.getString(1)+"\n");
                    buffer.append("Time: "+res.getString(2)+"\n");
                }
                showMessage("Tasks",buffer.toString());
            }
        });
        return v;
    }

    private void showMessage(String title, String Message) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
