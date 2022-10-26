package com.jeanboy.app.performance;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.jeanboy.app.performance.ui.activity.HandlerListActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void toHandler(View view) {
        HandlerListActivity.toStart(this);
    }
}