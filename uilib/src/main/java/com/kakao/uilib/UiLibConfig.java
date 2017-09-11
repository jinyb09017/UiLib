package com.kakao.uilib;

import android.content.Context;

/**
 * @author jyb jyb_96@sina.com on 2017/9/11.
 * @version V1.0
 * @Description: ui控件的初始化，主要提供context引用。
 * @date 16-4-21 11:21
 * @copyright www.tops001.com
 */

public class UiLibConfig {
    public static Context context;

    public static void init(Context con) {
        context = con;
    }


    public static Context getContext() {
        return context;
    }
}
