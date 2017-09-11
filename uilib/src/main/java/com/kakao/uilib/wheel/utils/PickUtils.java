package com.kakao.uilib.wheel.utils;

import android.app.Activity;
import android.support.annotation.Nullable;

import com.kakao.uilib.wheel.CommonPickPopWinLoop;
import com.kakao.uilib.wheel.entity.PickerItem;

import java.util.List;

/**
 * @author jyb jyb_96@sina.com on 2017/7/13.
 * @version V1.0
 * @Description: add comment
 * @date 16-4-21 11:21
 * @copyright www.tops001.com
 */

public class PickUtils {

    public static void showCommonPicker(Activity activity, String currentId, List<? extends PickerItem> modelList, @Nullable CommonPickPopWinLoop.OnPickCompletedListener listener) {
        CommonPickPopWinLoop pickPopWinLoop = new CommonPickPopWinLoop(activity, modelList, listener, currentId);
        pickPopWinLoop.showPop(activity);
    }
}
