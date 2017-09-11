package com.kakao.uilib.toast;

import android.content.Context;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.kakao.uilib.UiLibConfig;
import com.kakao.uilib.R;

/**
 * 提供toast的相关操作
 */
public class JToast {

    public static void show(@StringRes int resId) {
        showToast(UiLibConfig.getContext().getResources().getText(resId), Toast.LENGTH_SHORT);
    }

    public static void show(@StringRes int resId, int showTime) {
        showToast(UiLibConfig.getContext().getResources().getText(resId), showTime);
    }

    public static void show(String str) {
        showToast(str, Toast.LENGTH_SHORT);
    }

    public static void show(String str, int showTime) {
        showToast(String.valueOf(str), showTime);
    }


    public static void showLong(String str) {
        showToast(String.valueOf(str), Toast.LENGTH_LONG);
    }


    public static void showLong(int resId) {
        showToast(String.valueOf(UiLibConfig.getContext().getResources().getText(resId)), Toast.LENGTH_LONG);
    }


    public static void showTop(String str) {
        showToast(str, Toast.LENGTH_SHORT,Gravity.TOP);
    }

    public static void showTopLong(String str) {
        showToast(str, Toast.LENGTH_LONG,Gravity.TOP);
    }


    public static void showBottom(String str) {
        showToast(str, Toast.LENGTH_SHORT,Gravity.BOTTOM);
    }

    public static void showBottomLong(String str) {
        showToast(str, Toast.LENGTH_LONG,Gravity.BOTTOM);
    }



    private static void showToast(CharSequence toastStr, int showTime){
        Toast toast = Toast.makeText(UiLibConfig.getContext(), "", showTime);
        LayoutInflater li = (LayoutInflater) UiLibConfig.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = li.inflate(R.layout.common_toastlayout, null);
        TextView context = (TextView) view.findViewById(R.id.tishi);
        context.setText(toastStr);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setView(view);
        toast.show();
    }

    private static void showToast(CharSequence toastStr, int showTime,int align){
        Toast toast = Toast.makeText(UiLibConfig.getContext(), "", showTime);
        LayoutInflater li = (LayoutInflater) UiLibConfig.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = li.inflate(R.layout.common_toastlayout, null);
        TextView context = (TextView) view.findViewById(R.id.tishi);
        context.setText(toastStr);
        toast.setGravity(align, 0, 200);
        toast.setView(view);
        toast.show();
    }


}
