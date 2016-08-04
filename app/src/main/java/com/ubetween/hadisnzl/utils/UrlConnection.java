package com.ubetween.hadisnzl.utils;

/**
 * 类描述：管理网络地址的工具类
 *
 * @author hadis on 16.4.19.
 */
public class UrlConnection {

    private static final int DEV = 0;// 开发环境 *** http://www.ubetween.com.cn
    private static final int ONLINE = 1;// 正式环境 ***
    public static String API_SERVER;// 开发环境
    public static String ACCOUNT_URL;
    public static final int URL_TYPE = ONLINE;// 切换服务器

    static {
        switch (URL_TYPE) {
            case DEV:// 配置开发环境
                API_SERVER = "http://www.ubetween.com.cn";// 测试环境服务器
                break;
            case ONLINE:// 配置正式环境
                API_SERVER = "http://family.ubetween.com";
                break;

        }
    }

    // 版本升级检测    http://family.ubetween.com/api/version/current/type/android
    public static final String Update_URL = API_SERVER + "/api/version/current/type/android";
    // 头像上传
    public static final String Avatar_URL = API_SERVER + "/api/mobile/avatar";
    //公用图片上传接口
    public static final String PicUp_Common_URL = API_SERVER + "/api/mobile/uploadimage";

}
