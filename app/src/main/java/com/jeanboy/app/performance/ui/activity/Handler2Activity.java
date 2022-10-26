package com.jeanboy.app.performance.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jeanboy.app.performance.R;

public class Handler2Activity extends AppCompatActivity {

    private final static String TAG = Handler2Activity.class.getSimpleName();

    private TextView tv_test;

    private final Handler handler = new Handler();
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Log.e(TAG, "run() -> thread name: " + Thread.currentThread().getName());
            tv_test.setText("哈哈哈哈，来自 handler 修改的文本");
        }
    };

    private volatile boolean isFixed = false;

    public static void toStart(Context context) {
        Intent intent = new Intent(context, Handler2Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler2);

        tv_test = findViewById(R.id.tv_test);
    }

    public void toStart(View view) {
        handler.postDelayed(runnable, 10 * 1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isFixed) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    public void toLeak(View view) {
        handler.postDelayed(runnable, 10 * 1000);
        this.finish();
    }

    public void toFixed(View view) {
        isFixed = true;
        handler.postDelayed(runnable, 10 * 1000);
        this.finish();
    }
}