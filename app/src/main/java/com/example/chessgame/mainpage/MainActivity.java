package com.example.chessgame.mainpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.chessgame.R;
import com.example.chessgame.mainpage.gamerecord_fragment;
import com.example.chessgame.mainpage.main_fragment;
import com.example.chessgame.mainpage.profile_fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity  extends AppCompatActivity  {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView BottomNav = findViewById(R.id.menu);
        BottomNav.setOnNavigationItemSelectedListener(navlistener);
        getSupportFragmentManager().beginTransaction().replace(R.id.framework,new main_fragment()).commit();



    }
    private BottomNavigationView.OnNavigationItemSelectedListener navlistener=
        new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch(item.getItemId()){
                    case R.id.navhome:
                        selectedFragment = new main_fragment();
                        break;
                    case R.id.gamerecord:
                        selectedFragment = new gamerecord_fragment();
                        break;
                    case R.id.profile:
                        selectedFragment = new profile_fragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.framework,selectedFragment).commit();
                return true;
            }
    };

}
