package com.kakao.uilib.wheel.entity;


import java.util.ArrayList;
import java.util.List;

/**
 * 省份信息实体类
 */
public class ProvinceModel implements PickerItem {

    public String id; // 省份编码
    public String label; // 名称
    private ArrayList<CityModel> items; // 城市列表

    /**
     * 添加城市信息
     *
     * @param city
     */
    public void addCity(CityModel city) {

        if (null == city)
            return;

        if (null == items)
            items = new ArrayList<CityModel>();

        items.add(city);
    }

    /**
     * 获取某个城市
     *
     * @param position
     * @return
     */
    public CityModel getCity(int position) {

        return (position >= 0 && position < getCityCount()) ? items.get(position) : null;
    }

    /**
     * @return
     */
    public ArrayList<CityModel> getCityList() {

        return items;
    }

    /**
     * 获取城市数量
     *
     * @return
     */
    public int getCityCount() {

        return (null == items) ? 0 : items.size();
    }

    /**
     * 获取城市id列表
     *
     * @return
     */
    public List<String> getCityIdList() {

        int count = getCityCount();
        List<String> cityIdList = new ArrayList<String>();

        for (int i = 0; i < count; i++) {

            cityIdList.add(items.get(i).id);
        }

        return cityIdList;
    }

    /**
     * 获取城市名称列表
     *
     * @return
     */
    public List<String> getCityNameList() {

        int count = getCityCount();
        List<String> cityList = new ArrayList<String>();

        for (int i = 0; i < count; i++) {

            cityList.add(items.get(i).label);
        }

        return cityList;
    }

    @Override
    public String getText() {

        return (null == label) ? "" : label;
    }

    @Override
    public String getId() {
        return id;
    }

    public String toString() {

        return label + "[" + id + "][" + getCityCount() + " cities]";
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setItems(ArrayList<CityModel> items) {
        this.items = items;
    }

    public void setLabel(String name) {
        this.label = name;
    }
}
