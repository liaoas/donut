package com.liao.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**
 *
 * TODO: 商品图片类
 * @author LiAo
 * @date 2020/5/20 17:05
 */
@JsonIgnoreProperties(value = {"handler"})
public class DImg {
    private Integer id;

    private Integer productId;

    private String img;

    private Date createTime;

    public DImg() {

    }

    public DImg(Integer productId) {
        this.productId = productId;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", productId=").append(productId);
        sb.append(", img=").append(img);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}