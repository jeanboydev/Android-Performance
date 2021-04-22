package com.jeanboy.app.performance.memory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import com.jeanboy.app.performance.R;

public class LeakActivity extends AppCompatActivity implements Callback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak);

        ImageView imageView = findViewById(R.id.iv_icon);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round);
        imageView.setImageBitmap(bitmap);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    Log.e(LeakActivity.class.getSimpleName(), "-----" + i);
                }
            }
        }, 10000);


        CallManager.add(this);
    }

    private Handler handler = new Handler(Looper.getMainLooper()) {

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

        }
    };

    @Override
    public void onAction() {
        Log.e(LeakActivity.class.getSimpleName(),"------------------");
    }
}