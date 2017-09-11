package com.kakao.uilib.recycler;

import android.content.Context;
import android.view.LayoutInflater;

import com.kakao.uilib.recycler.base.ItemViewDelegate;
import com.kakao.uilib.recycler.base.ViewRecycleHolder;

import java.util.List;

/**
 * Created by zhy on 16/4/9.
 */
public abstract class CommonRecyclerviewAdapter<T> extends MultiItemTypeRecyclerAdapter<T>
{
    protected Context mContext;
    protected int mLayoutId;
    protected LayoutInflater mInflater;
    protected int headCount = 0;

    public void setHeadCount(int headCount) {
        this.headCount = headCount;
    }

    public CommonRecyclerviewAdapter(final Context context, final int layoutId)
    {
        super(context);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;

        addItemViewDelegate(new ItemViewDelegate<T>()
        {
            @Override
            public int getItemViewLayoutId()
            {
                return layoutId;
            }

            @Override
            public boolean isForViewType( T item, int position)
            {
                return true;
            }

            @Override
            public void convert(ViewRecycleHolder holder, T t, int position)
            {
                CommonRecyclerviewAdapter.this.convert(holder, t, position);
            }
        });
    }


    public CommonRecyclerviewAdapter(final Context context, final int layoutId, List<T> datas)
    {
        super(context, datas);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;

        addItemViewDelegate(new ItemViewDelegate<T>()
        {
            @Override
            public int getItemViewLayoutId()
            {
                return layoutId;
            }

            @Override
            public boolean isForViewType( T item, int position)
            {
                return true;
            }

            @Override
            public void convert(ViewRecycleHolder holder, T t, int position)
            {
                CommonRecyclerviewAdapter.this.convert(holder, t, position);
            }
        });
    }

    protected abstract void convert(ViewRecycleHolder holder, T t, int position);


}
