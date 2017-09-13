package com.kakao.uilib.wheel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.kakao.uilib.R;
import com.kakao.uilib.wheel.base.BasePopWindow;
import com.kakao.uilib.wheel.entity.CityModel;
import com.kakao.uilib.wheel.entity.ProvinceModel;
import com.kakao.uilib.wheel.utils.AbPreconditions;
import com.kakao.uilib.wheel.utils.ProvinceInfoParserTask;
import com.kakao.uilib.wheel.utils.ProvinceInfoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 地址选择popwindow
 */
public class ProvincePickPopWinNoRecycle extends BasePopWindow implements OnClickListener {

    private static final int REQUEST_CODE_PROVINCE = 1; // 省份选择标识
    private static final int REQUEST_CODE_CITY = 2; // 城市选择标识

    private Button cancelBtn;
    private Button confirmBtn;
    private LoopView provincePickerV;
    private LoopView cityPickerV;
    private View pickerContainerV;
    private View contentView;

    private boolean isProvinceInit = false; // 是否已经初始化省份信息
    private String mProvince; // 省份名称
    private String mProvinceId; // 省份id
    private String mCity; // 城市名称
    private String mCityId; // 城市id
    private Context mContext; // 上下文
    private OnAddressPickCompletedListener mListener;// 地址选择完成事件监听器
    private ArrayList<ProvinceModel> mProvinceList = null; // 省份信息列表

    private List<String> cityList = new ArrayList<>();
    private List<String> provinceList = new ArrayList<>();
    private ProvinceModel currentProvince = null;

    /**
     * g构造函数
     *
     * @param cxt
     * @param province
     * @param city
     * @param provinceList 省份列表
     * @param l            选中监听
     */
    public ProvincePickPopWinNoRecycle(Context cxt, String province, String city, ArrayList<ProvinceModel> provinceList, OnAddressPickCompletedListener l) {
        super(cxt);
        this.mContext = cxt;
        this.mListener = l;
        this.mProvince = String.valueOf(province);
        this.mCity = String.valueOf(city);
        this.mProvinceList = provinceList;

        init();
    }


    @Override
    protected View getConView(Context context) {
        contentView = LayoutInflater.from(context).inflate(R.layout.layout_province_loopview, null);
        return contentView;
    }

    @SuppressWarnings("deprecation")
    @SuppressLint("InflateParams")
    private void init() {

        cancelBtn = (Button) contentView.findViewById(R.id.btn_cancel);
        confirmBtn = (Button) contentView.findViewById(R.id.btn_confirm);
        provincePickerV = (LoopView) contentView.findViewById(R.id.picker_province);
        cityPickerV = (LoopView) contentView.findViewById(R.id.picker_city);
        pickerContainerV = contentView.findViewById(R.id.container_picker);
//		contentView.findViewById(R.id.picker_day).setVisibility(View.GONE);

        if (null == mProvinceList) {

            (new ProvinceInfoParserTask(mContext, mHandler)).execute();
        } else {

            initPickerViews(mProvinceId, mCityId);
        }

        cancelBtn.setOnClickListener(this);
        confirmBtn.setOnClickListener(this);
        contentView.setOnClickListener(this);


        provincePickerV.setLoopListener(new LoopScrollListener() {
            @Override
            public void onItemSelect(int item) {

                if (mProvinceList != null) {
                    ProvinceModel provinceModel = mProvinceList.get(item);
                    currentProvince = provinceModel;

                    mProvinceId = provinceModel.id;
                    mProvince = provinceModel.label;
                    initCityPickerView(provinceModel, "");
                }


            }
        });
        cityPickerV.setLoopListener(new LoopScrollListener() {
            @Override
            public void onItemSelect(int item) {
                if (currentProvince != null) {

                    CityModel cityModel = currentProvince.getCityList().get(item);

                    mCityId = cityModel.id;
                    mCity = cityModel.label;

                }


            }
        });


        setTouchable(true);
        setFocusable(true);
        // setOutsideTouchable(true);
        setBackgroundDrawable(new BitmapDrawable());
        setAnimationStyle(R.style.FadeInPopWin);
        setContentView(contentView);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
    }

    /**
     * 初始化选择器试图
     */
    private void initPickerViews(String provinceId, String cityId) {


        this.mCityId = ProvinceInfoUtils.getCityIdByName(this.mProvince, this.mCity, mProvinceList);
        this.mProvinceId = ProvinceInfoUtils.getProvinceIdByName(this.mProvince, mProvinceList);

        this.currentProvince = ProvinceInfoUtils.getProviceByName(mProvince,mProvinceList);



        if (!isProvinceInit || !mProvinceId.equals(provinceId)) {

            int selectedPos = -1;
            int count = getProvinceCount();
            ProvinceModel provinceModel;

            provinceList.clear();
            for (int i = 0; i < count; i++) {

                provinceModel = mProvinceList.get(i);

                if (null == provinceModel)
                    continue;

                provinceList.add(provinceModel.label);

                if (provinceModel.id.equals(provinceId)) {

                    selectedPos = i;
                    mProvinceId = provinceModel.id;
                    mProvince = provinceModel.label;
                    initCityPickerView(provinceModel, cityId);
                }
            }

            if (selectedPos == -1) {

                selectedPos = 0;
                provinceModel = mProvinceList.get(0);

                if (null != provinceModel) {

                    mProvinceId = provinceModel.id;
                    mProvince = provinceModel.label;
                }

                initCityPickerView(provinceModel, "");
            }

            provincePickerV.setDataList(provinceList);
            provincePickerV.setInitPosition(selectedPos);
        }

        isProvinceInit = true;
    }

    /**
     * 初始化城市选择器
     *
     * @param provinceModel
     * @param cityId
     */
    private void initCityPickerView(ProvinceModel provinceModel, String cityId) {

        if (null == provinceModel)
            return;

        int selectedPos = -1;
        int count = provinceModel.getCityCount();
        CityModel cityModel;

        cityList.clear();
        for (int i = 0; i < count; i++) {

            cityModel = provinceModel.getCity(i);

            if (null == cityModel)
                continue;

            cityList.add(cityModel.label);

            if (cityModel.id.equals(cityId)) {

                selectedPos = i;
                mCityId = cityModel.id;
                mCity = cityModel.label;
            }
        }

        if (selectedPos == -1) {

            selectedPos = 0;
            if (AbPreconditions.checkNotEmptyList(provinceModel.getCityList())) {
                cityModel = provinceModel.getCity(0);
                mCityId = cityModel.id;
                mCity = cityModel.label;
            }
        }

        cityPickerV.setDataList(cityList);
        cityPickerV.setInitPositionClearScroll(selectedPos);
    }

    /**
     * 获取省份数量
     *
     * @return
     */
    private int getProvinceCount() {

        return (null == mProvinceList) ? 0 : mProvinceList.size();
    }


    /**
     * 消息分发Handler
     */
    private Handler mHandler = new Handler() {

        @SuppressWarnings("unchecked")
        @Override
        public void handleMessage(Message msg) {

            super.handleMessage(msg);

            switch (msg.what) {

                case ProvinceInfoParserTask.MSG_PARSE_RESULT_CALLBACK:
                    mProvinceList = (ArrayList<ProvinceModel>) msg.obj;
                    initPickerViews(mProvinceId, mCityId);
                    break;
            }
        }
    };



    @Override
    public void onClick(View v) {

        if (v == contentView || v == cancelBtn) {

            dismiss();
        } else if (v == confirmBtn) {

            if (null != mListener)
                mListener.onAddressPickCompleted(mProvince, mProvinceId, mCity, mCityId);

            dismiss();
        }
    }

    /**
     * 地址选择完成事件监听器接口
     */
    public interface OnAddressPickCompletedListener {

        /**
         * 完成地址选择事件
         *
         * @param province   省份名称
         * @param provinceId 省份id
         * @param city       城市名称
         * @param cityId     城市id
         */
        void onAddressPickCompleted(String province, String provinceId, String city, String cityId);
    }
}
