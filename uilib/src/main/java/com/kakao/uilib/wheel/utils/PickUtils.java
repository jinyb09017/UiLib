package com.kakao.uilib.wheel.utils;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.TextView;

import com.kakao.uilib.R;
import com.kakao.uilib.UiLibConfig;
import com.kakao.uilib.toast.JToast;
import com.kakao.uilib.wheel.CommonPickPopWinLoop;
import com.kakao.uilib.wheel.DatePickerPopWin;
import com.kakao.uilib.wheel.ProvincePickPopWinNoRecycle;
import com.kakao.uilib.wheel.entity.PickerItem;
import com.kakao.uilib.wheel.entity.ProvinceModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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

    public static void showProvinceCityPicker(Activity activity, final TextView textview, ArrayList<ProvinceModel> provinceList) {
        String province = "";
        String city = "";
        String reg = "-";
        String[] filter = new String[]{};
        if (textview != null && (textview.getText().toString().contains("/") || textview.getText().toString().contains("-"))) {
            String str = textview.getText().toString();
            if (str.indexOf("/") > 0) {
                filter = str.split("/");
                reg = "/";
            } else if (str.indexOf("-") > 0) {
                filter = str.split("-");
                reg = "-";
            }
        }

        if (filter.length != 2) {
            JToast.show("格式异常");
        }else{

            province = filter[0];
            city = filter[1];
        }


        final String finalReg = reg;
        ProvincePickPopWinNoRecycle pickPopWinLoop = new ProvincePickPopWinNoRecycle(activity, province, city, null, new ProvincePickPopWinNoRecycle.OnAddressPickCompletedListener() {
            @Override
            public void onAddressPickCompleted(String province, String provinceId, String city, String cityId) {
                textview.setText(province + finalReg + city);
            }
        });
        pickPopWinLoop.showPop(activity);
    }


    public static void showYearMonthDayPicker(Activity aty, @Nullable final TextView textView, int minYear, int maxYear) {

        if (textView != null) {
            showYearMonthDayPicker(aty, textView.getText().toString(), minYear, maxYear, new DatePickerPopWin.OnDatePickedListener() {
                @Override
                public void onDatePickCompleted(int year, int month, int day, String dateDesc) {
                    textView.setText(dateDesc);
                }
            });
        } else {
            showYearMonthDayPicker(aty, "", minYear, maxYear, null);
        }


    }


    public static void showYearMonthDayPicker(Activity aty, @Nullable final TextView textView) {
        showYearMonthDayPicker(aty, textView, 0, 0);
    }

    public static void showYearMonthDayPicker(Activity aty, @Nullable String value, int minYear, int maxYear,
                                              @Nullable DatePickerPopWin.OnDatePickedListener listener) {

        //yyyy-MM-dd
        if (TextUtils.isEmpty(value)) {
            value = getCurrentDate("yyyy-MM-dd");
        }
        if (listener == null) {
            listener = new DatePickerPopWin.OnDatePickedListener() {
                @Override
                public void onDatePickCompleted(int year, int month, int day, String dateDesc) {

                }
            };
        }
        DatePickerPopWin pickerPopWin = new DatePickerPopWin.Builder(aty, listener).textConfirm(UiLibConfig.getString(R.string.common_ok)) //text of confirm button
                .textCancel(UiLibConfig.getString(R.string.common_cancle)) //text of cancel button
                .btnTextSize(16) // button text size
                .viewTextSize(25) // pick view text size
                .colorCancel(aty.getResources().getColor(R.color.sys_blue)) //color of cancel button
                .colorConfirm(aty.getResources().getColor(R.color.sys_blue))//color of confirm button
                .minYear(minYear <= 0 ? 1950 : minYear) //min year in loop
                .maxYear(maxYear <= 0 ? Calendar.getInstance().get(Calendar.YEAR) + 10 : maxYear)
                .dateChose(value) // date chose when init popwindow
                .showDayMonthYear(true)
                .timeFormate("yyyy-MM-dd")
                .build();
        pickerPopWin.showPop(aty);
    }


    public static String getCurrentDate(String format) {
        String curDateTime = null;
        try {
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            Calendar c = new GregorianCalendar();
            curDateTime = mSimpleDateFormat.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return curDateTime;

    }

}
