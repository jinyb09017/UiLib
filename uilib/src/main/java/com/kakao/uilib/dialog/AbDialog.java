package com.kakao.uilib.dialog;

import android.content.Context;
import android.view.View;

import com.kakao.uilib.R;
import com.kakao.uilib.UiLibConfig;


/**
 * @author jyb jyb_96@sina.com on 2016/11/7.
 * @version V1.0
 * @Description:请添加描述
 * @date 16-4-21 11:21
 * @copyright www.tops001.com
 */

public class AbDialog {
    /**
     * 只显示带成功的md风格的弹窗。
     *
     * @param mContext 提示
     *                 内容
     *                 确定
     */
    public static void showConfirmMdDialog(final Context mContext, String msg) {
        final JMaterialDialog mJMaterialDialog = new JMaterialDialog(mContext);

        mJMaterialDialog
                .setTitle(R.string.sys_tips)
                .setMessage(msg)
                .setPositiveButton(R.string.common_ok, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        mJMaterialDialog.dismiss();
                    }
                })

                .setCanceledOnTouchOutside(true)
                .show();
    }

    /**
     * 只显示带成功的md风格的弹窗。
     * 只能点击确定关闭
     *
     * @param mContext 提示
     *                 内容
     *                 确定
     *                 回调
     */
    public static void showConfirmMdDialog(final Context mContext, String msg, final DialogCallback dialogCallback) {
        final JMaterialDialog mJMaterialDialog = new JMaterialDialog(mContext);

        mJMaterialDialog
                .setTitle(R.string.sys_tips)
                .setMessage(msg)
                .setPositiveButton(R.string.common_ok, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        mJMaterialDialog.dismiss();

                        if (dialogCallback != null) {
                            dialogCallback.onclick(DialogCallback.ok);
                        }
                    }
                })
                .setCanceledOnTouchOutside(false)

                .show();
    }

    /**
     * 只显示带成功的md风格的弹窗。
     *
     * @param mContext 提示
     *                 内容
     *                 确定
     *                 取消
     *                 回调
     */
    public static void showConfirmAndCancelMdDialog(final Context mContext, String msg, final DialogCallback dialogCallback) {
        final JMaterialDialog mJMaterialDialog = new JMaterialDialog(mContext);

        mJMaterialDialog
                .setTitle(R.string.sys_tips)
                .setMessage(msg)
                .setPositiveButton(R.string.common_ok, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mJMaterialDialog.dismiss();

                        if (dialogCallback != null) {
                            dialogCallback.onclick(DialogCallback.ok);
                        }
                    }
                })
                .setNegativeButton(R.string.common_cancle, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mJMaterialDialog.dismiss();

                        if (dialogCallback != null) {
                            dialogCallback.onclick(DialogCallback.cancel);
                        }
                    }
                })
                .setCanceledOnTouchOutside(true)
                .show();
    }


    public static void showConfirmAndCancelMdDialogWithTips(final Context mContext, String msg, String tips, final DialogCallback dialogCallback) {
        final JMaterialDialog mJMaterialDialog = new JMaterialDialog(mContext);

        mJMaterialDialog
                .setTitle(tips != null ? tips : UiLibConfig.getString(R.string.sys_tips))
                .setMessage(msg)
                .setPositiveButton(R.string.common_ok, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mJMaterialDialog.dismiss();

                        if (dialogCallback != null) {
                            dialogCallback.onclick(DialogCallback.ok);
                        }
                    }
                })
                .setNegativeButton(R.string.common_cancle, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mJMaterialDialog.dismiss();

                        if (dialogCallback != null) {
                            dialogCallback.onclick(DialogCallback.cancel);
                        }
                    }
                })
                .setCanceledOnTouchOutside(true)
                .show();
    }

    public interface DialogCallback {
        int ok = 1;
        int cancel = 2;

        void onclick(int tag);
    }
}
