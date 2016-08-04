package com.ubetween.hadisnzl.base;

import android.app.Application;
import android.content.ClipboardManager;
import android.content.Context;
import android.widget.Toast;

/**
 * @author by hadis on 16.7.22.
 */
public class BaseApplication extends Application {
    public static BaseApplication app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }


    public static BaseApplication getInstance() {
        return app;
    }

    /**
     * 此方法描述的是： 复制内容到剪贴板
     */
    public static void copyContent(String content, Context context) {
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content.trim());
        Toast.makeText(context, "已复制到剪贴板", Toast.LENGTH_SHORT).show();
    }

}
