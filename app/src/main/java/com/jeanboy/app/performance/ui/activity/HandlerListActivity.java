package com.jeanboy.app.performance.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.jeanboy.app.performance.R;

public class HandlerListActivity extends AppCompatActivity {

    public static void toStart(Context context) {
        Intent intent = new Intent(context, HandlerListActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_list);
    }

    public void toTest1(View view) {
        Handler1Activity.toStart(this);
    }

    public void toTest2(View view) {
        Handler2Activity.toStart(this);
    }

    public void toTest3(View view) {
        Handler3Activity.toStart(this);
    }

    public void toTest4(View view) {
        IdleHandlerActivity.toStart(this);
    }
}