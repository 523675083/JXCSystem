package com.friday.model;

public class OrderDetail {
    private Integer oId;

    private String orderId;

    private Integer pId;

    private Integer oNum;

    private Integer oInnum;

    private Float pPrice;

    private Float pPrices;

    public Integer getoId() {
        return oId;
    }

    public void setoId(Integer oId) {
        this.oId = oId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public Integer getoNum() {
        return oNum;
    }

    public void setoNum(Integer oNum) {
        this.oNum = oNum;
    }

    public Integer getoInnum() {
        return oInnum;
    }

    public void setoInnum(Integer oInnum) {
        this.oInnum = oInnum;
    }

    public Float getpPrice() {
        return pPrice;
    }

    public void setpPrice(Float pPrice) {
        this.pPrice = pPrice;
    }

    public Float getpPrices() {
        return pPrices;
    }

    public void setpPrices(Float pPrices) {
        this.pPrices = pPrices;
    }
}