package com.jeanboy.app.performance.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.jeanboy.app.performance.R;
import com.jeanboy.app.performance.ui.view.HandlerView;

public class Handler3Activity extends AppCompatActivity {

    private final static String TAG = Handler2Activity.class.getSimpleName();

    private HandlerView handler_view;

    public static void toStart(Context context) {
        Intent intent = new Intent(context, Handler3Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler3);

        handler_view = findViewById(R.id.handler_view);
    }

    public void toStart(View view) {
        handler_view.sendMessage();
    }
}