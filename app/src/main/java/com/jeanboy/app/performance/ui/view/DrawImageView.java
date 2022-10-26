package com.jeanboy.app.performance.ui.view;


import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

import com.jeanboy.app.performance.R;

/**
 * Created by jianbo on 2022/10/26 10:12.
 */
public class DrawImageView extends AppCompatImageView {

    public DrawImageView(Context context) {
        this(context, null);
    }

    public DrawImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher_background));
        super.onDraw(canvas);
    }
}
