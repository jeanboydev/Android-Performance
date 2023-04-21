package com.jeanboy.app.performance.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.MessageQueue;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;

import com.jeanboy.app.performance.R;

public class AnimActivity extends AppCompatActivity {


    public static void toStart(Context context) {
        Intent intent = new Intent(context, AnimActivity.class);
        context.startActivity(intent);
    }

    private FrameLayout view_anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);

        view_anim = findViewById(R.id.view_anim);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Looper.getMainLooper().getQueue().addIdleHandler(new MessageQueue.IdleHandler() {
                @Override
                public boolean queueIdle() {
                    Log.e("jianbo","----------- IdleHandler ------------");
                    return true;
                }
            });
        }

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1, 360);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                view_anim.setRotation(value);
            }
        });

        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                Log.e("jianbo","ValueAnimator ----------- onAnimationEnd ------------");
            }
        });
//        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatCount(1);
        valueAnimator.setDuration(10000);
        valueAnimator.start();

        RotateAnimation animation = new RotateAnimation(1f, 360f);
        animation.setDuration(10000);
//        animation.setRepeatCount(Animation.INFINITE);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.e("jianbo","RotateAnimation ----------- onAnimationEnd ------------");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animation.setRepeatCount(1);
        animation.setRepeatMode(Animation.REVERSE);
//        view_anim.setAnimation(animation);
    }
}