package com.kakao.uidemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.kakao.uilib.dialog.AbDialog;
import com.kakao.uilib.toast.JToast;

public class DialogActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_confirm;
    private Button btn_confirm_cancel;
    private Button btn_confirm_cancel_tips;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        btn_confirm = (Button) findViewById(R.id.btn_confirm);
        btn_confirm_cancel = (Button) findViewById(R.id.btn_confirm_cancel);
        btn_confirm_cancel_tips = (Button) findViewById(R.id.btn_confirm_cancel_tips);


        btn_confirm.setOnClickListener(this);
        btn_confirm_cancel.setOnClickListener(this);
        btn_confirm_cancel_tips.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        if (v == btn_confirm) {

            AbDialog.showConfirmMdDialog(this, "确定操作吗", new AbDialog.DialogCallback() {
                @Override
                public void onclick(int tag) {
                    if(tag == AbDialog.DialogCallback.ok){
                        JToast.show("ok");
                    }else{
                        JToast.show("cancel");
                    }
                }
            });


        } else if (v == btn_confirm_cancel) {
            AbDialog.showConfirmAndCancelMdDialog(this, "确定操作吗", new AbDialog.DialogCallback() {
                @Override
                public void onclick(int tag) {
                    if(tag == AbDialog.DialogCallback.ok){
                        JToast.show("ok");
                    }else{
                        JToast.show("cancel");
                    }
                }
            });

        }else if (v == btn_confirm_cancel_tips) {
            AbDialog.showConfirmAndCancelMdDialogWithTips(this, "确定操作吗","warn", new AbDialog.DialogCallback() {
                @Override
                public void onclick(int tag) {
                    if(tag == AbDialog.DialogCallback.ok){
                        JToast.show("ok");
                    }else{
                        JToast.show("cancel");
                    }
                }
            });

        }
    }
}
