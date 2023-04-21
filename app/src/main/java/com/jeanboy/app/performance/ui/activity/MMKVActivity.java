package com.jeanboy.app.performance.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.jeanboy.app.performance.R;
import com.tencent.mmkv.MMKV;

import java.util.Random;

public class MMKVActivity extends AppCompatActivity {

    public static void toStart(Context context) {
        Intent intent = new Intent(context, MMKVActivity.class);
        context.startActivity(intent);
    }

    private MMKV mmkv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mmkvactivity);

        mmkv = MMKV.defaultMMKV();
    }

    private int startIndex = 0;
    private static final int count = 512 * 100;

    public void toAdd(View view) {
        for (int i = startIndex; i < startIndex + count; i++) {
            String key = "key_" + i;
            String random = getRandom(100);
            mmkv.encode(key, random); // char[1] = 2 Byte  = 2*8
            Log.e("jianbo", key + ", " + random);
            startIndex = i;
        }
    }

    public void toClear(View view) {
        mmkv.clearAll();
    }

    public String getRandom(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            // 随机判断判断该字符是数字还是字母
            if (random.nextInt(2) % 2 == 0) {
                // 随机判断是大写字母还是小写字母
                int start = random.nextInt(2) % 2 == 0 ? 65 : 97;
                sb.append((char) (start + random.nextInt(26)));
            } else {
                sb.append(random.nextInt(10));
            }
        }
        return sb.toString();
    }
}