package com.jeanboy.app.performance.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeanboy on 2021/4/11 17:54.
 */
public class CallManager {

    private static List<Callback> callbackList = new ArrayList<>();

    public static void add(Callback callback) {
        callbackList.add(callback);
    }

    public static void remove(Callback callback) {
        callbackList.remove(callback);
    }
}
