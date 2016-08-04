package com.ubetween.hadisnzl.base;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ubetween.hadisnzl.R;

/**
 * fragment基本类
 *
 * @author hadis on 16.7.22.
 */
public class BaseFragment extends Fragment {
    public Toast mToast = null;
    private ImageView back;

    public void showToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public void callBack(View v) {
        back = (ImageView) v.findViewById(R.id.ivTitleBtnLeft);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getActivity().finish();

            }
        });

    }

    // 传入view和标题名设置标题
    public void setTitle(View v, String title) {
        TextView tv;
        tv = (TextView) v.findViewById(R.id.ivTitleName);
        tv.setText(title);

    }
}
