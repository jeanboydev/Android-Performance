package com.jeanboy.app.performance.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by jianbo on 2022/10/18 17:19.
 */
public class HandlerView extends FrameLayout {

    private final static String TAG = HandlerView.class.getSimpleName();

    public HandlerView(@NonNull Context context) {
        this(context, null);
    }

    public HandlerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HandlerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void sendMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "sendMessage() -> run() -> thread name: " + Thread.currentThread().getName());
                post(new Runnable() {
                    @Override
                    public void run() {
                        Log.e(TAG, "post() -> run() -> thread name: " + Thread.currentThread().getName());
                    }
                });
            }
        }).start();
    }
}
