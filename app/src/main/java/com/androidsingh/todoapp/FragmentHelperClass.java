package com.androidsingh.todoapp;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentHelperClass extends FragmentPagerAdapter {
    public FragmentHelperClass(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0: {
                TasksFragment tasksFragment=new TasksFragment();
                return tasksFragment;
            }
            case 1: {
                AddtaskFragment addtaskFragment=new AddtaskFragment();
                return addtaskFragment;
            }
            case 2: {
                TaskUpdateFragment taskUpdateFragment=new TaskUpdateFragment();
                return taskUpdateFragment;
            }
            case 3: {
                TaskDeleteFragment taskDeleteFragment=new TaskDeleteFragment();
                return taskDeleteFragment;
            }
            default: {
                return null;
            }
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0: {
                return "Tasks";
            }
            case 1: {
                return "Add Task";
            }
            case 2: {
                return "Update Task";
            }
            case 3: {
                return "Delete Task";
            }
            default: {
                return null;
            }
        }
    }
}
