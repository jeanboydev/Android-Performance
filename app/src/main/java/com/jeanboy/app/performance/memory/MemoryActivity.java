package com.jeanboy.app.performance.memory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;

import com.jeanboy.app.performance.R;

public class MemoryActivity extends AppCompatActivity {

    public static Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            for (int i = 0; i < 100; i++) {
                String[] arr = new String[100000];
            }
            handler.sendEmptyMessageDelayed(0, 30);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
    }

    public void toStart(View view) {
        handler.sendEmptyMessage(0);
    }
}