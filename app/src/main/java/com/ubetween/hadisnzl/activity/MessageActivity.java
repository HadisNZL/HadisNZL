package com.ubetween.hadisnzl.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ubetween.hadisnzl.R;
import com.ubetween.hadisnzl.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MessageActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);
        initToolBar();
        initView();
    }

    private void initToolBar() {
        toolbar.setTitle("详情");//setSupportActionBar之前调用
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//显示返回按钮
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void initView() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                MessageActivity.this.finish();
                break;
            case R.id.action_shoucang:
                showToast("收藏");
                break;
            case R.id.action_share:
                showToast("分享");
                break;
        }
        return true;
    }
}
