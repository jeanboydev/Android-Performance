package com.jeanboy.app.performance;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.jeanboy.app.performance.ui.activity.AnimActivity;
import com.jeanboy.app.performance.ui.activity.HandlerListActivity;
import com.jeanboy.app.performance.ui.activity.MMKVActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void toHandler(View view) {
        HandlerListActivity.toStart(this);
    }

    public void toMMKV(View view) {
        MMKVActivity.toStart(this);
    }

    public void toAnim(View view) {
        AnimActivity.toStart(this);
    }
}