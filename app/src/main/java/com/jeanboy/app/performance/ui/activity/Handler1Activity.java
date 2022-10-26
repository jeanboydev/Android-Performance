package com.jeanboy.app.performance.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.jeanboy.app.performance.R;
import com.jeanboy.app.performance.constants.What;

import java.lang.ref.WeakReference;

public class Handler1Activity extends AppCompatActivity {

    private final static String TAG = Handler1Activity.class.getSimpleName();

    private TextView tv_test;

    public static void toStart(Context context) {
        Intent intent = new Intent(context, Handler1Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler1);

        tv_test = findViewById(R.id.tv_test);
    }

    private int i = 0;

    // 匿名内部类
    private final Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case What.msg:
                    // 主线程 处理消息
                    Log.e(TAG, "匿名内部类 handleMessage() -> thread name: " + Thread.currentThread().getName());
                    tv_test.setText(String.valueOf(msg.obj));
                    break;
            }
        }
    };

    public void toStart(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "run() -> thread name: " + Thread.currentThread().getName());
                Message message = new Message();
                message.what = What.msg;
                message.obj = "测试消息";
                // 子线程 发送消息
                handler.sendMessage(message);
            }
        }).start();
    }

    public void toLeak(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "run() -> thread name: " + Thread.currentThread().getName());
                Message message = new Message();
                message.what = What.msg;
                message.obj = "测试消息 - 匿名内部类";
                // 子线程 发送消息
                handler.sendMessageDelayed(message, 10 * 1000);
            }
        }).start();
        this.finish();
    }

    // 非静态内部类
    private class LeakHandler extends Handler {

        public LeakHandler() {
            super(Looper.getMainLooper());
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case What.msg:
                    // 主线程 处理消息
                    Log.e(TAG, "非静态内部类 handleMessage() -> thread name: " + Thread.currentThread().getName());
                    tv_test.setText(msg.obj + "_" + i);
                    break;
            }
        }
    }

    public void toLeak2(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "run() -> thread name: " + Thread.currentThread().getName());
                Message message = new Message();
                message.what = What.msg;
                message.obj = "测试消息 - 非静态内部类";
                // 子线程 发送消息
                new LeakHandler().sendMessageDelayed(message, 10 * 1000);
            }
        }).start();
        this.finish();
    }


    // 静态内部类+弱引用
    private static class FixedHandler extends Handler {

        WeakReference<Handler1Activity> activityWeakReference;

        public FixedHandler(Handler1Activity activity) {
            super(Looper.getMainLooper());
            this.activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case What.msg:
                    // 主线程 处理消息
                    Log.e(TAG, "静态内部类+弱引用 handleMessage() -> thread name: " + Thread.currentThread().getName());
                    Handler1Activity handler1Activity = activityWeakReference.get();
                    if (handler1Activity != null) {
                        handler1Activity.tv_test.setText(msg.obj + "_" + handler1Activity.i);
                    }
                    break;
            }
        }
    }

    public void toFixed(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "run() -> thread name: " + Thread.currentThread().getName());
                Message message = new Message();
                message.what = What.msg;
                message.obj = "测试消息 - 静态内部类+弱引用";
                // 子线程 发送消息
                new FixedHandler(Handler1Activity.this).sendMessageDelayed(message, 10 * 1000);
            }
        }).start();
        this.finish();
    }
}