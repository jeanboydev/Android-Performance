package com.jeanboy.app.performance.ui.activity;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.MessageQueue;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.jeanboy.app.performance.R;
import com.jeanboy.app.performance.ui.view.DrawImageView;

public class IdleHandlerActivity extends AppCompatActivity {

    private final static String TAG = IdleHandlerActivity.class.getSimpleName();

    public static void toStart(Context context) {
        Intent intent = new Intent(context, IdleHandlerActivity.class);
        context.startActivity(intent);
    }

    private FrameLayout view_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idle_handler);

        view_container = findViewById(R.id.view_container);


    }

    private final MessageQueue.IdleHandler idleHandler1 = new MessageQueue.IdleHandler() {
        @Override
        public boolean queueIdle() {
            Log.i(TAG, "queueIdle() -> thread name: " + Thread.currentThread().getName());
            Log.e(TAG, "IdleHandler 执行一一一一一一一一一一一一次 return false;");
            return false;
        }
    };

    private final MessageQueue.IdleHandler idleHandler2 = new MessageQueue.IdleHandler() {
        @Override
        public boolean queueIdle() {
            Log.i(TAG, "queueIdle() -> thread name: " + Thread.currentThread().getName());
            Log.e(TAG, "IdleHandler 执行 return true;");
            return true;
        }
    };

    public void toAnim(View view) {
        Log.w(TAG, "---------------------------------------------- toAnim ----------------------------------------------------");
        if (animation != null) {
            animation.cancel();
        }
        if (animView != null) {
            animView.clearAnimation();
        }
        Looper.myQueue().removeIdleHandler(idleHandler1);
        Looper.myQueue().removeIdleHandler(idleHandler2);
        view_container.removeAllViews();
        View animView = new View(this);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(200, 200);
        layoutParams.gravity = Gravity.START;
        animView.setLayoutParams(layoutParams);
        animView.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
        view_container.addView(animView);
        ValueAnimator animator = ValueAnimator.ofFloat(0, 100);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                animView.setTranslationX(value);
                animView.setTranslationY(value);
            }
        });
        animator.start();
        Looper.myQueue().addIdleHandler(idleHandler1);
        Looper.myQueue().addIdleHandler(idleHandler2);
    }

    public void toCanvas(View view) {
        Log.w(TAG, "---------------------------------------------- toCanvas ----------------------------------------------------");
        if (animation != null) {
            animation.cancel();
        }
        if (animView != null) {
            animView.clearAnimation();
        }
        Looper.myQueue().removeIdleHandler(idleHandler1);
        Looper.myQueue().removeIdleHandler(idleHandler2);
        view_container.removeAllViews();
        DrawImageView imageView = new DrawImageView(this);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(200, 200);
        layoutParams.gravity = Gravity.END;
        imageView.setLayoutParams(layoutParams);
        imageView.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
        view_container.addView(imageView);
        Looper.myQueue().addIdleHandler(idleHandler1);
        Looper.myQueue().addIdleHandler(idleHandler2);
    }

    private AlphaAnimation animation;
    private View animView;

    public void toRepeatAnim(View view) {
        Log.w(TAG, "---------------------------------------------- toRepeatAnim ----------------------------------------------------");
        if (animation != null) {
            animation.cancel();
        }
        if (animView != null) {
            animView.clearAnimation();
        }
        Looper.myQueue().removeIdleHandler(idleHandler1);
        Looper.myQueue().removeIdleHandler(idleHandler2);
        view_container.removeAllViews();

        if (animView == null) {
            animView = new View(this);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(200, 200);
            layoutParams.gravity = Gravity.BOTTOM;
            animView.setLayoutParams(layoutParams);
            animView.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
        }
        view_container.addView(animView);
        animation = new AlphaAnimation(1f, 0f);
        animation.setDuration(800);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);
        animView.setAnimation(animation);
        Looper.myQueue().addIdleHandler(idleHandler1);
        Looper.myQueue().addIdleHandler(idleHandler2);
    }
}