package com.kakao.uidemo.bean;


import com.kakao.uilib.entity.PickerItem;

/**
 * @author jyb jyb_96@sina.com on 2017/7/13.
 * @version V1.0
 * @Description: add comment
 * @date 16-4-21 11:21
 * @copyright www.tops001.com
 */

public class Type implements PickerItem {
    private String name;
    private String id;
    private String grade = "100";

    public String getName() {
        return name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String getText() {
        return name;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Type() {
    }

    public Type(String name, String id) {
        this.name = name;
        this.id = id;
    }
}
