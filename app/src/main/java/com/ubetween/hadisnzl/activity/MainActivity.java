package com.ubetween.hadisnzl.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ubetween.hadisnzl.R;
import com.ubetween.hadisnzl.adapter.SecAdapter;
import com.ubetween.hadisnzl.base.BaseActivity;
import com.ubetween.hadisnzl.base.BaseApplication;
import com.ubetween.hadisnzl.interfaces.HttpMethods;
import com.ubetween.hadisnzl.model.HttpResult;
import com.ubetween.hadisnzl.model.Subject;
import com.ubetween.hadisnzl.refresh.MaterialRefreshLayout;
import com.ubetween.hadisnzl.refresh.MaterialRefreshListener;
import com.ubetween.hadisnzl.utils.AppManager;
import com.ubetween.hadisnzl.view.MyListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener {

    @Bind(R.id._listview)
    MyListView listView;
    private SecAdapter adapter;
    private List<Subject> list = new ArrayList<>();
    private int page = 0;
    @Bind(R.id.refresh)
    MaterialRefreshLayout refreshLayout;
    private boolean mIsRefreshing = false;//是否下拉


    private long oneTime = 0;
//    @Bind(R.id.avatar_imageView)
//    ImageView imageView;//抽屉里面的头像

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.fab)
    FloatingActionButton fab;//浮动圆圈

    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;//左边抽屉

    @Bind(R.id.nav_view)
    NavigationView navigationView; // 导航菜单框架 配合着 DrawerLayout使用，在DrawerLayout上显示菜单


    //v7包下的搜索SearchView有关
    SearchView.SearchAutoComplete mEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        initView();
        initData(page);
    }

    private void initView() {
        adapter = new SecAdapter(list, getApplicationContext());
        listView.setAdapter(adapter);
        // refreshLayout.autoRefresh();
        listView.setOnItemClickListener(this);
        refreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                mIsRefreshing = true;
                materialRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 0;
                        initData(page);
//                        adapter.notifyDataSetChanged();
                        materialRefreshLayout.finishRefresh();
                    }
                }, 2000);
                mIsRefreshing = false;
            }

            @Override
            public void onfinish() {
            }

//            @Override
//            public void onRefreshLoadMore(final MaterialRefreshLayout materialRefreshLayout) {
//                materialRefreshLayout.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        page += 1;
//                        initData(page);
//                        materialRefreshLayout.finishRefreshLoadMore();
//
//                    }
//                }, 800);
//            }
        });


        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

    }

    private void initData(final int thisPage) {
        final Subscriber<HttpResult<List<Subject>>> subscriber = new Subscriber<HttpResult<List<Subject>>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(HttpResult<List<Subject>> listHttpResult) {
                List<Subject> tmpList = new ArrayList<>();
                if (thisPage == 0) {
                    list.clear();
                }
                list.addAll(listHttpResult.getSubjects());
                Log.i("sdsd", list + "");
                adapter.notifyDataSetChanged();

            }

        };

        HttpMethods.getInstance().getTopMovie(subscriber, page, 20);//封装后的调用
    }


    @OnClick({R.id.fab})
    void onCLick(View view) {
        switch (view.getId()) {
            case R.id.fab:
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                startActivity(new Intent(getApplicationContext(), MessageActivity.class));
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        final SearchView mSearchView = (SearchView) MenuItemCompat.getActionView(item);
        // mSearchView.setIconifiedByDefault(false); //SearchView 查询图标默认位于编辑框内
        mSearchView.setMaxWidth(1500);
        mEdit = (SearchView.SearchAutoComplete) mSearchView.findViewById(R.id.search_src_text);
        //   mEdit.setText("我是牛爷");
        // mEdit.setSelection("我是牛爷".length());
        mSearchView.setQueryHint("输入你感兴趣的...");

        //搜索框的背景
        final LinearLayout search_edit_frame = (LinearLayout) mSearchView.findViewById(R.id.search_edit_frame);
        search_edit_frame.setBackgroundResource(R.drawable.search_bac);
        search_edit_frame.setClickable(true);

        mEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                search_edit_frame.setPressed(hasFocus);
            }
        });

        mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_edit_frame.setPressed(true);
            }
        });

        mEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
             /*判断是否是“GO”键*/
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                /*隐藏软键盘*/
                    // KeyBoardUtils.closeKeybord(mEdit, ct);
                    if (!TextUtils.isEmpty(v.getText().toString())) {
                        showToast(v.getText().toString());
                        mSearchView.clearFocus();
                        search_edit_frame.setPressed(false);
                    } else {
                        showToast("搜索不能为空！");
                    }
                    return true;
                }
                return false;
            }
        });
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //处理ActionBar栏项点击这里
        switch (item.getItemId()) {
            case R.id.action_search:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_camera:
                showToast("nav_camera");
                break;
            case R.id.nav_gallery:
                showToast("nav_gallery");
                break;
            case R.id.nav_slideshow:
                showToast("nav_slideshow");
                break;
            case R.id.nav_manage:
                showToast("nav_manage");
                break;
            case R.id.nav_share:
                showToast("nav_share");
                break;
            case R.id.nav_send:
                showToast("nav_send");
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
//            super.onBackPressed();
            long twoTime = System.currentTimeMillis();
            if (twoTime - oneTime > 2000) {
                Toast.makeText(getApplication(), R.string.exit_tips, Toast.LENGTH_SHORT)
                        .show();
                oneTime = twoTime;
            } else {
                AppManager.getAppManager().AppExit(BaseApplication.getInstance());
            }
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Snackbar.make(parent, list.get(position).getTitle() + "\n" + list.get(position).getOriginal_title(), Snackbar.LENGTH_SHORT).show();

    }
}
