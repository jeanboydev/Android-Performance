package com.jeanboy.app.performance;

import android.util.Log;
import android.view.Choreographer;

/**
 * Created by jeanboy on 2021/4/11 18:56.
 */
public class FPSHelper {

    private static final long MONITOR_INTERVAL = 160L; // 首次使用计算FPS使用160ms
    private static final long INTERVAL_UNIT = 1000L; // FPS单位，FPS/s
    private static final long MONITOR_INTERVAL_NANOS = MONITOR_INTERVAL * INTERVAL_UNIT * INTERVAL_UNIT;
    private static long startTime;
    private static int frameCount = 0;

    public static void getFPS() {
        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {
            @Override
            public void doFrame(long frameTimeNanos) {
                if (startTime == 0) {
                    startTime = frameTimeNanos;
                }
                long interval = frameTimeNanos - startTime;
                if (interval > MONITOR_INTERVAL_NANOS) {
                    double fps = (((double) (frameCount * 1000L * 1000L)) / interval) * INTERVAL_UNIT;
                    Log.e(FPSHelper.class.getSimpleName(), "FPS: " + fps);
                    frameCount = 0;
                    startTime = 0;
                } else {
                    ++frameCount;
                }
                Choreographer.getInstance().postFrameCallback(this);
            }
        });
    }
}
