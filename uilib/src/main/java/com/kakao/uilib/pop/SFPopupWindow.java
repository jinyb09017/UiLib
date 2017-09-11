package com.kakao.uilib.pop;

import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.widget.PopupWindow;

/**
 * @author jyb jyb_96@sina.com on 2017/7/5.
 * @version V1.0
 * @Description: 解决android手机api>23的时候，如果height为match_parent的时候，showAsDropDown（showAtLocation也不起作用了）失效，并且会填充全部屏幕的问题。
 * @date 16-4-21 11:21
 * @copyright www.tops001.com
 */

public class SFPopupWindow extends PopupWindow {



    @Override
    public void showAsDropDown(View anchor) {
        if (Build.VERSION.SDK_INT >= 24 ) {
            Rect rect = new Rect();
            anchor.getGlobalVisibleRect(rect);
            int h = anchor.getResources().getDisplayMetrics().heightPixels - rect.bottom;
            setHeight(h);
        }
        super.showAsDropDown(anchor);
    }
}
