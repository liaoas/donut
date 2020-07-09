package com.liao.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;


/**
 *
 * TODO: 商品类
 * @author LiAo
 * @date 2020/5/20 17:04
 */
@JsonIgnoreProperties(value = {"handler"})
public class DProduct {
    private Integer id;

    private String productName;

    private Integer productCategory;

    private Integer productStatus;

    private Date createTime;

    private String productIntroduction;

    private List<DImg> dImgs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public Integer getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(Integer productCategory) {
        this.productCategory = productCategory;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getProductIntroduction() {
        return productIntroduction;
    }

    public void setProductIntroduction(String productIntroduction) {
        this.productIntroduction = productIntroduction == null ? null : productIntroduction.trim();
    }

    public List<DImg> getdImgs() {
        return dImgs;
    }

    public void setdImgs(List<DImg> dImgs) {
        this.dImgs = dImgs;
    }

    @Override
    public String toString() {
        StringBuilder bulider = new StringBuilder("DProduct [");
        bulider.append("    id=")
                .append(id);
        bulider.append(",    productName=")
                .append(productName);
        bulider.append(",    productCategory=")
                .append(productCategory);
        bulider.append(",    productStatus=")
                .append(productStatus);
        bulider.append(",    createTime=")
                .append(createTime);
        bulider.append(",    productIntroduction=")
                .append(productIntroduction);
        bulider.append(",    dImgs=")
                .append(dImgs);
        bulider.append(']');
        return bulider.toString();
    }
}