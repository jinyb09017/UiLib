package com.kakao.uidemo;

import android.app.Application;

import com.kakao.uilib.UiLibConfig;

/**
 * @author jyb jyb_96@sina.com on 2017/9/11.
 * @version V1.0
 * @Description: add comment
 * @date 16-4-21 11:21
 * @copyright www.tops001.com
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        UiLibConfig.init(this);
    }
}
