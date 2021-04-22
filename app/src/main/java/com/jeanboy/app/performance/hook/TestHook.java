package com.jeanboy.app.performance.hook;

import android.widget.ImageView;

import de.robv.android.xposed.XC_MethodHook;

/**
 * Created by jeanboy on 2021/4/11 18:31.
 */
public class TestHook extends XC_MethodHook {

    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        super.afterHookedMethod(param);
        ImageView imageView= (ImageView) param.thisObject;
    }
}
