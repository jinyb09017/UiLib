package com.kakao.uidemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.kakao.uidemo.bean.Type;
import com.kakao.uilib.sheet.SheetView;
import com.kakao.uilib.wheel.entity.PickerItem;

import java.util.ArrayList;
import java.util.List;

public class BottomSheetActivity extends AppCompatActivity implements View.OnClickListener{


    private Button btn_confirm;
    private Button btn_confirm_cancel;
    SheetView<Type> sheetView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);

        btn_confirm = (Button) findViewById(R.id.btn_confirm);
        btn_confirm_cancel = (Button) findViewById(R.id.btn_confirm_cancel);


        btn_confirm.setOnClickListener(this);
        btn_confirm_cancel.setOnClickListener(this);





    }

    List<Type> getTypes(){
        List<Type> types = new ArrayList<>();
        types.add(new Type("good1","good"));
        types.add(new Type("good2","good"));
        types.add(new Type("测试31","测试1"));
        types.add(new Type("测试41","测试1"));

        return types;
    }

    @Override
    public void onClick(View v) {
        if (v == btn_confirm) {

            sheetView = new SheetView.Builder(this)
                    .showCancel(true)
                    .cancelStr("确定")
                    .normalColor("#666666")
                    .chooseColor("#0097ff")
                    .build();
            sheetView.show(this,getTypes(),btn_confirm.getText().toString());
            sheetView.setSheetItemClick(new SheetView.SheetItemClick() {
                @Override
                public void onItemClick(PickerItem item) {
                    Type type = (Type) item;
                    btn_confirm.setText(type.getText());
                }
            });



        } else if (v == btn_confirm_cancel) {

            sheetView = new SheetView.Builder(this)
                    .showCancel(false)
                    .cancelStr("我想要取消")
                    .normalColor("#666666")
                    .chooseColor("#fb3838")
                    .diverColor("#0091e8")
                    .build();
            sheetView.show(this,getTypes(),btn_confirm_cancel.getText().toString());
            sheetView.setSheetItemClick(new SheetView.SheetItemClick() {
                @Override
                public void onItemClick(PickerItem item) {
                    Type type = (Type) item;
                    btn_confirm_cancel.setText(type.getText());
                }
            });

        }
    }
}
