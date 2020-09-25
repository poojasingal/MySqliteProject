package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.Fragments.ContctFragment;
import com.example.myapplication.Fragments.EnterDetailAndSaveInDB;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_contact:

                        fragment = new ContctFragment();
                        /*Bundle bundle = new Bundle();
                        bundle.putString("canEdit", canEdit114_s);
                        bundle.putString("canView", canView114_s);
                        fragment.setArguments(bundle);*/
                        loadFragment(fragment);

                      //  Toast.makeText(MainActivity.this, "Recents", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.action_userss:
                        fragment = new EnterDetailAndSaveInDB();
                        loadFragment(fragment);

                        break;
                    case R.id.action_nearby:
                        Toast.makeText(MainActivity.this, "Nearby", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.framelayout, fragment);
        //  transaction.addToBackStack(null);
        transaction.commit();
    }


}

