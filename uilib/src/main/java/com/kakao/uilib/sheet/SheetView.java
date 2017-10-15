package com.kakao.uilib.sheet;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kakao.uilib.R;
import com.kakao.uilib.recycler.CommonRecyclerviewAdapter;
import com.kakao.uilib.recycler.MultiItemTypeRecyclerAdapter;
import com.kakao.uilib.recycler.base.ViewRecycleHolder;
import com.kakao.uilib.wheel.base.BasePopWindow;
import com.kakao.uilib.entity.PickerItem;

import java.util.List;

/**
 * Created by jinyabo on 13/10/2017.
 */

public class SheetView<T extends PickerItem> extends BasePopWindow implements View.OnClickListener {

    TextView tv_cancel;
    LinearLayout rl_bottom;
    RecyclerView recycler;
    CommonRecyclerviewAdapter<T> commonRecyclerviewAdapter;
    public View contentView;//root view

    private SheetItemClick sheetItemClick;

    private int mPosition = -1;//选中位置


    boolean showCancel = true;
    String cancelStr = "取消";

    String normalColor = "#666666";
    String chooseColor = "#0091e8";
    String diverColor = "#dddddd";

    public static class Builder {
        boolean showCancel;
        String cancelStr;

        String normalColor;
        String chooseColor;

        Context context;

        String diverColor;


        public Builder(Context context) {
            this.context = context;
        }

        public Builder showCancel(boolean showCancel) {
            this.showCancel = showCancel;
            return this;
        }

        public Builder diverColor(String diverColor){
            this.diverColor = diverColor;
            return this;
        }

        public Builder cancelStr(String cancelStr) {
            this.cancelStr = cancelStr;
            return this;
        }

        public Builder normalColor(String normalColor) {
            this.normalColor = normalColor;
            return this;
        }

        public Builder chooseColor(String chooseColor) {
            this.chooseColor = chooseColor;
            return this;
        }

        public SheetView build() {

            return new SheetView(this);
        }
    }

    public void setSheetItemClick(SheetItemClick sheetItemClick) {
        this.sheetItemClick = sheetItemClick;
    }

    public SheetView(Builder builder) {
        super(builder.context);
        cancelStr = builder.cancelStr;
        showCancel = builder.showCancel;

        if (builder.normalColor != null) {

            normalColor = builder.normalColor;
        }

        if (builder.chooseColor != null) {

            chooseColor = builder.chooseColor;
        }

        if(builder.diverColor != null){
            diverColor = builder.diverColor;
        }

        init();
    }

    @Override
    protected View getConView(Context context) {

        contentView = LayoutInflater.from(context).inflate(R.layout.layout_sheet, null);
        return contentView;
    }


    public void init() {
        tv_cancel = (TextView) contentView.findViewById(R.id.tv_cancel);
        rl_bottom = (LinearLayout) contentView.findViewById(R.id.rl_bottom);
        tv_cancel.setText(cancelStr);

        if (showCancel) {
            rl_bottom.setVisibility(View.VISIBLE);
        } else {
            rl_bottom.setVisibility(View.GONE);
        }
        recycler = (RecyclerView) contentView.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(mContext));

        commonRecyclerviewAdapter = new CommonRecyclerviewAdapter<T>(mContext, R.layout.sheet_item) {
            @Override
            protected void convert(ViewRecycleHolder holder, T item, int position) {
                holder.setText(R.id.tv_name, item.getText());
                holder.setBackgroundColor(R.id.line,Color.parseColor(diverColor));

                if (mPosition == position) {
                    holder.setTextColor(R.id.tv_name, Color.parseColor(SheetView.this.chooseColor));
                } else {
                    holder.setTextColor(R.id.tv_name, Color.parseColor(SheetView.this.normalColor));
                }
            }

        };

        tv_cancel.setOnClickListener(this);
        contentView.setOnClickListener(this);

        recycler.setAdapter(commonRecyclerviewAdapter);

        commonRecyclerviewAdapter.setOnItemClickListener(new MultiItemTypeRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                T item = commonRecyclerviewAdapter.getItem(position);

                if (sheetItemClick != null) {
                    sheetItemClick.onItemClick(item);
                }
                dismiss();

            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

    }

    /**
     * @param aty
     * @param list
     * @param value 记录选中的值
     */
    public void show(Activity aty, List<T> list, String value) {

        for (int i = 0; i < list.size(); i++) {
            PickerItem pickerItem = list.get(i);
            if (pickerItem.getText().equals(value)) {
                mPosition = i;

                break;
            }
        }
        commonRecyclerviewAdapter.replaceAll(list);
        showPop(aty);
    }

    @Override
    public void onClick(View v) {
        if (v == tv_cancel) {
            dismiss();
        } else if (v == contentView) {
            dismiss();
        }

    }

    public interface SheetItemClick<T extends PickerItem> {

        void onItemClick(T item);
    }

}
