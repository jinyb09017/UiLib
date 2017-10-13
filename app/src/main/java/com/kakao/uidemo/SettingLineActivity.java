package com.kakao.uidemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.kakao.uilib.settingline.JSettingView;

public class SettingLineActivity extends AppCompatActivity implements View.OnClickListener {

    private JSettingView mSettingItemOne;
    private JSettingView mSettingItemFour;
    private JSettingView mSettingItemFive;
    private ImageView mIvHead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_line);
        mSettingItemOne = (JSettingView) findViewById(R.id.item_one);
        mSettingItemFour = (JSettingView) findViewById(R.id.item_four);
        mSettingItemFive = (JSettingView) findViewById(R.id.item_five);
        mIvHead = (ImageView) findViewById(R.id.headimage);

        mSettingItemOne.setmOnLSettingItemClick(new JSettingView.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                Toast.makeText(getApplicationContext(), "我的消息", Toast.LENGTH_SHORT).show();
            }
        });
        mSettingItemFour.setmOnLSettingItemClick(new JSettingView.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                Toast.makeText(getApplicationContext(), "选中开关：" + isChecked, Toast.LENGTH_SHORT).show();
            }
        });
        mSettingItemOne.setRightText("我是右侧改变的文字");
        mSettingItemFive.setmOnLSettingItemClick(new JSettingView.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                Toast.makeText(getApplicationContext(), "切换开关：" + isChecked, Toast.LENGTH_SHORT).show();
            }
        });
//        Picasso.with(this).load(R.drawable.girl).transform(new CircleTransform()).into(mIvHead);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_one:
                Toast.makeText(getApplicationContext(), "我的消息", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
