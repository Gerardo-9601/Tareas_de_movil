package com.example.gerardo.myapplication;

import android.content.Intent;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.net.Uri;
import android.provider.CallLog;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;
    private Locale locale;
    private Configuration config = new Configuration();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("BlackList");


        Log.d(TAG, "onCreate: Starting.");
        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        try {
            adapter.addFragment(new Tab1Fragment(), "BlackList");
            adapter.addFragment(new Tab2Fragment(), "Log Llamadas");
            adapter.addFragment(new Tab3Fragment(), "Log Mensajes");
            viewPager.setAdapter(adapter);

            Intent service = new Intent(getBaseContext(),  Servicio.class);
            startService(service);


        } catch (Exception err) {
        }
    }


  public class hilo extends Thread{
        public void run(){
            while (true){
                try {
                    Uri CALLLOG_URI = Uri.parse("content://call_log/calls");
                    getApplicationContext().getContentResolver().delete(CALLLOG_URI, CallLog.Calls.NUMBER + "=?", new String[]{"4451455067"});
                }catch (Exception err){

                }
            }
        }
  }

}
