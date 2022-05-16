package com.rgobj.generalproblemdemo.bean;

import java.io.Serializable;

/**
 * 项目类
 */
public class ProjectBean implements Serializable {
    private int id;
    private String name;
    private String time;
    private String content;
    private String price;
    private String detail;
    private String freelance;
    private String customer;


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setFreelance(String freelance) {
        this.freelance = freelance;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }

    public String getPrice() {
        return price;
    }

    public String getFreelance() {
        return freelance;
    }

    public String getCustomer() {
        return customer;
    }

    public int getId() {
        return id;
    }

    public String getDetail() {
        return detail;
    }

    @Override
    public String toString() {
        return "ProjectBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", content='" + content + '\'' +
                ", price='" + price + '\'' +
                ", detail='" + detail + '\'' +
                ", freelance='" + freelance + '\'' +
                ", customer='" + customer + '\'' +
                '}';
    }
}


