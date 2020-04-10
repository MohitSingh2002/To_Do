package com.androidsingh.todoapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class TaskDeleteFragment extends Fragment {
    EditText deleteID;
    ImageButton delete_task;
    MyDataBaseHelperClass myDataBaseHelperClass;
    public TaskDeleteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_task_delete, container, false);
        myDataBaseHelperClass=new MyDataBaseHelperClass(getActivity());
        deleteID=(EditText)view.findViewById(R.id.deleteID);
        delete_task=(ImageButton)view.findViewById(R.id.delete_task);
        delete_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string=deleteID.getText().toString();
                Integer integer=myDataBaseHelperClass.deleteData(string);
                if (integer>0) {
                    Toast.makeText(getActivity(),"Task Deleted",Toast.LENGTH_SHORT).show();
                    deleteID.setText("");
                } else {
                    Toast.makeText(getActivity(),"Task not delete",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}
