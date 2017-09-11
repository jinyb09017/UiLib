package com.kakao.uidemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kakao.uilib.toast.JToast;

/**
 * @author jyb jyb_96@sina.com on 2017/9/11.
 * @version V1.0
 * @Description: add comment
 * @date 16-4-21 11:21
 * @copyright www.tops001.com
 */

public class ToastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);

        findViewById(R.id.btn_long).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JToast.showLong("toast long show");
            }
        });

        findViewById(R.id.btn_short).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JToast.show("toast show");
            }
        });


        findViewById(R.id.btn_long_top).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JToast.showTopLong("toast long top show");
            }
        });

        findViewById(R.id.btn_short_top).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JToast.showTop("toast top show");
            }
        });


        findViewById(R.id.btn_long_bot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JToast.showBottomLong("toast long bottom show");
            }
        });

        findViewById(R.id.btn_short_bot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JToast.showBottom("toast bottom show");
            }
        });




    }
}
