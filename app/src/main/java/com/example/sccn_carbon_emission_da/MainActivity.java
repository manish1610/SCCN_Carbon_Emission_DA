package com.example.sccn_carbon_emission_da;

import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.audiofx.Visualizer;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import java.io.IOException;
import java.util.HashMap;


import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);

        initComponent();


    }
    private void initComponent() {

        final Home home=new Home();
        final Visualize visualize=new Visualize();
        final Legend legend=new Legend();
        setFragment(home);


        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {



                switch (item.getItemId()) {
                    case R.id.home:
                        //                      mTextMessage.setText(item.getTitle());

                        setFragment(home);
                        navigation.setBackgroundColor(getResources().getColor(R.color.grey_3));
                        return true;
                    case R.id.visualize:
//                        mTextMessage.setText(item.getTitle());
                        setFragment(visualize);
                        navigation.setBackgroundColor(getResources().getColor(R.color.grey_3));
                        return true;
                    case R.id.legend:
//                        mTextMessage.setText(item.getTitle());
                   //     setFragment(legend);
                        setFragment(legend);
                        navigation.setBackgroundColor(getResources().getColor(R.color.grey_3));
                        return true;

                }
                return false;
            }
        });


    }


    private void setFragment(Fragment fragment){

        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }


}
