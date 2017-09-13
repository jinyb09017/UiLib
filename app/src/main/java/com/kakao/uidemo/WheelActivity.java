package com.kakao.uidemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.kakao.uidemo.bean.Type;
import com.kakao.uilib.wheel.CommonPickPopWinLoop;
import com.kakao.uilib.wheel.utils.PickUtils;

import java.util.ArrayList;
import java.util.List;

public class WheelActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_common;
    private Button btn_time;
    private Button btn_province;
    private String chooseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheel);

        btn_common = (Button) findViewById(R.id.btn_common);
        btn_time = (Button) findViewById(R.id.btn_time);
        btn_province = (Button) findViewById(R.id.btn_province);

        btn_common.setOnClickListener(this);
        btn_time.setOnClickListener(this);
        btn_province.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v == btn_common) {
            PickUtils.showCommonPicker(this, chooseId, getListTypes(), new CommonPickPopWinLoop.OnPickCompletedListener<Type>() {


                @Override
                public void onPickCompleted(String id, String name, Type type) {
                    chooseId = id;
                    btn_common.setText("类型选择：" + type.getText());
                }
            });

        } else if (v == btn_time) {

            PickUtils.showYearMonthDayPicker(this, btn_time);
        } else if (v == btn_province) {

            PickUtils.showProvinceCityPicker(this, btn_province,null);
        }
    }


    public List<Type> getListTypes() {
        List<Type> types = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Type type = new Type();
            type.setId((i + 1) + "");
            type.setName("类型" + (i + 1));

            types.add(type);
        }

        return types;
    }
}
