package com.kakao.uidemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kakao.uidemo.bean.Type;
import com.kakao.uilib.entity.PickerItem;
import com.kakao.uilib.toast.JToast;
import com.kakao.uilib.widget.TagLayout;

import java.util.ArrayList;
import java.util.List;

public class TagLayoutActivity extends AppCompatActivity {

    TagLayout tagLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_layout);

        tagLayout = (TagLayout)findViewById(R.id.tagLayout);

        tagLayout.setLabels(getLabels());
        tagLayout.setMaxCheckCount(3);


        tagLayout.setOnCheckChangedListener(new TagLayout.OnCheckChangeListener() {
            @Override
            public void onCheckChanged(PickerItem label, boolean isChecked) {
                Type type = (Type) label;
//                JToast.show(type.getGrade());
            }

            @Override
            public void onBeyondMaxCheckCount() {
                JToast.show("超过数据了");
            }
        });

    }


    List<Type> getLabels(){
        List<Type> types = new ArrayList<>();
        types.add(new Type("haha1","1"));
        types.add(new Type("haha2","2"));
        types.add(new Type("haa2","3"));
        types.add(new Type("ha1ha","4"));
        types.add(new Type("haha8","5"));
        types.add(new Type("hahaq","6"));
        types.add(new Type("hawha","7"));
        types.add(new Type("hahgwa","8"));
        types.add(new Type("hhahha","8"));
        types.add(new Type("hahha","19"));
        types.add(new Type("hahha","1-"));

        return  types;
    }
}
