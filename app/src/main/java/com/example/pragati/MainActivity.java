package com.example.pragati;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NoInternetFragment.OnCallbackReceived{
    View view;
    Utility mClass;
    private RecyclerView mMessageRecycler;
    View fragment_view;
    int clickCounter=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment_view=findViewById(R.id.fragment_container);
        view=findViewById(R.id.view);
        mMessageRecycler = findViewById(R.id.reyclerview_message_list);
        //Utility class instance
        mClass= new Utility(this);
        clicked();
    }

    @Override
    public void Update() {
        fragment_view.setVisibility(View.GONE);
        clicked();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int orientation = newConfig.orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT)
            Log.d("tag", "Portrait");
        else if (orientation == Configuration.ORIENTATION_LANDSCAPE){
            mMessageRecycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        }
        else
            Log.w("tag", "other: " + orientation);
    }

    public void clicked(){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(clickCounter==0){
                    if(mClass.isNetworkConnected()){
                        mClass.getData();
                        clickCounter++;
                    }
                    mClass.Clicked_mainActivity(mMessageRecycler,fragment_view);

                }
                else{
                    mClass.clicked_adapter();
                }
            }
        });
    }
}

