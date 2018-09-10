package com.example.account;

/**
 * Created by 黎俐 on 2018/9/9.
 * 明细页面的项目列表
 */

public class Detail_Item {
    private int imageId;
    private  String  name;
    private String sort;
    private String time;

    public Detail_Item(int imageId, String name, String sort, String time, String amount) {
        this.imageId = imageId;
        this.name = name;
        this.sort = sort;
        this.time = time;
        this.amount = amount;
    }

    public String getAmount() {
        return amount;

    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String amount;
}
