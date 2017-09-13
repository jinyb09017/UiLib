package com.kakao.uilib.wheel.utils;

import android.content.Context;
import android.content.res.Resources;

import com.kakao.uilib.wheel.entity.CityModel;
import com.kakao.uilib.wheel.entity.ProvinceModel;

import java.util.ArrayList;
import java.util.List;


/**
 * 匹配地址信息Utils
 */
public class ProvinceInfoUtils {

    /**
     * 匹配出出生地址信息
     *
     * @param cxt          上下文
     * @param provinceId   选中省份id
     * @param cityId       选择城市id
     * @param provinceList 省份列表
     * @return 出生地（如： 广东-广州）
     */
    public static String matchAddress(Context cxt, String provinceId, String cityId, ArrayList<ProvinceModel> provinceList) {

        int count = (null == provinceList) ? 0 : provinceList.size();
        Resources res = cxt.getResources();
        String province = "其他地区";
        String city = "其他";
        StringBuffer sb = new StringBuffer();
        ProvinceModel provinceModel = null;

        for (int i = 0; i < count; i++) {

            provinceModel = provinceList.get(i);

            if (null != provinceModel && provinceModel.id.equals(provinceId)) {

                int cityCount;
                CityModel cityModel = null;

                province = provinceModel.label;
                cityCount = provinceModel.getCityCount();

                for (int j = 0; j < cityCount; j++) {

                    cityModel = provinceModel.getCity(j);

                    if (null != cityModel && cityModel.id.equals(cityId)) {

                        city = cityModel.label;
                        break;
                    }
                }
                break;
            }
        }

        sb.append(province);
        sb.append("/");
        sb.append(city);

        return sb.toString();
    }

    public static String getProvinceIdByName(String province, ArrayList<ProvinceModel> provinceList) {
        for (ProvinceModel provinceModel : provinceList) {
            if (provinceModel.getText().equals(province)) {
                return provinceModel.getId();
            }
        }
        return "";

    }

    public static String getCityIdByName(String province, String city, ArrayList<ProvinceModel> provinceList) {
        for (ProvinceModel provinceModel : provinceList) {
            if (provinceModel.getText().equals(province)) {
                List<CityModel> cityModels = provinceModel.getCityList();
                for (CityModel cityModel : cityModels) {
                    if (cityModel.getText().equals(city)) {
                        return cityModel.getId();
                    }
                }
            }
        }
        return "";

    }

    public static ProvinceModel getProviceByName(String province, ArrayList<ProvinceModel> provinceList) {
        for (ProvinceModel provinceModel : provinceList) {
            if (provinceModel.getText().equals(province)) {
                return provinceModel;
            }
        }
        return null;

    }


    public static ProvinceModel getProviceByPos(int pos, ArrayList<ProvinceModel> provinceList) {
        return provinceList.get(pos);

    }
}
