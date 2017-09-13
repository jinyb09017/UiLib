package com.kakao.uidemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kakao.uilib.widget.JAlignTextView;

public class AlignTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_align_text);

        JAlignTextView jAlignTextView = (JAlignTextView) findViewById(R.id.tv_left_align);
        jAlignTextView.setAlign(JAlignTextView.Align.ALIGN_LEFT);

        JAlignTextView jAlignRightTextView = (JAlignTextView) findViewById(R.id.tv_right_align);
        jAlignRightTextView.setAlign(JAlignTextView.Align.ALIGN_RIGHT);

        JAlignTextView jAlignMiddleTextView = (JAlignTextView) findViewById(R.id.tv_middle_align);
        jAlignMiddleTextView.setAlign(JAlignTextView.Align.ALIGN_CENTER);
    }

}
