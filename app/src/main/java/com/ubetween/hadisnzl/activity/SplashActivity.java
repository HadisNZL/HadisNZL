package com.ubetween.hadisnzl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.ubetween.hadisnzl.R;
import com.ubetween.hadisnzl.base.BaseActivity;
import com.ubetween.hadisnzl.utils.SharePrefenceUtil;


/**
 * 启动页
 */
public class SplashActivity extends BaseActivity {

    private boolean first;
    public static int TIME = 1500; // 进入主程序的延迟时间

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void onResume() {
        into(); // 进入主程序的方法
        super.onResume();
    }


    private void into() {
        first = SharePrefenceUtil.getFirst(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (first) {
                    startActivity(new Intent(SplashActivity.this, GuideActivity.class));
                } else {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                }
                //overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                finish();
            }
        }, TIME);
    }
}
