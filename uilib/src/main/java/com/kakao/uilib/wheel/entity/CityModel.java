package com.kakao.uilib.wheel.entity;


import com.kakao.uilib.entity.PickerItem;

/**
 * 城市信息实体类
 */
public class CityModel implements PickerItem {

	public String id; // 城市编码
	public String label; // 名称

	@Override
	public String getText() {

		return (null == label) ? "" : label;
	}

	@Override
	public String getId() {
		return id;
	}

	public String toString() {

		return label + "[" + id + "]";
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setLabel(String name) {
		this.label = name;
	}
}
