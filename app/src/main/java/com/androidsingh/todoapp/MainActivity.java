package com.androidsingh.todoapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;
    FragmentHelperClass fragmentHelperClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("To Do");
        viewPager=(ViewPager)findViewById(R.id.viewPager);
        fragmentHelperClass=new FragmentHelperClass(getSupportFragmentManager());
        viewPager.setAdapter(fragmentHelperClass);
        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu_bar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.change_theme: {
                changeCurrentTheme();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void changeCurrentTheme() {
        String[] available_modes={"Dark Mode","Light Mode"};
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Change Theme");
        builder.setSingleChoiceItems(available_modes, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which==0) {
                    if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES) {
                        Toast.makeText(MainActivity.this,"Dark mode already enabbled",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();
                        Toast.makeText(MainActivity.this,"Dark mode on",Toast.LENGTH_SHORT).show();
                    }
                }
                else if (which==1) {
                    if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_NO) {
                        Toast.makeText(MainActivity.this,"Light mode already enabbled",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();
                        Toast.makeText(MainActivity.this,"Light mode on",Toast.LENGTH_SHORT).show();
                    }
                }
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
}
