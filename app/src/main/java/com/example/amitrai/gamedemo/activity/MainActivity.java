package com.example.amitrai.gamedemo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.amitrai.gamedemo.AndEngineSimplePhysicsExampleActivity;
import com.example.amitrai.gamedemo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(this, AndEngineSimplePhysicsExampleActivity.class));
    }
}
