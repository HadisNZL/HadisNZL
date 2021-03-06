package com.ubetween.hadisnzl.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ubetween.hadisnzl.R;
import com.ubetween.hadisnzl.utils.AppManager;

/**
 * @author hadis on 16.3.10.
 */
public class BaseActivity extends AppCompatActivity {
    public Toast mToast = null;
    protected ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
    }


    public void showToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public void back(View v) {
        onBackPressed();
    }

    public void callBack() {
        back = (ImageView) findViewById(R.id.ivTitleBtnLeft);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();

            }
        });

    }

    public void setTitle(String title) {
        TextView tv;
        tv = (TextView) findViewById(R.id.ivTitleName);
        tv.setText(title);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }


}
