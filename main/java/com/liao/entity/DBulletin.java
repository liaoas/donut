package com.liao.entity;

import java.util.Date;

public class DBulletin {
    private Integer id;

    private Integer bulletinUserid;

    private String bulletinTitle;

    private Integer bulletinStatus;

    private Date createTime;

    private String bulletinContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBulletinUserid() {
        return bulletinUserid;
    }

    public void setBulletinUserid(Integer bulletinUserid) {
        this.bulletinUserid = bulletinUserid;
    }

    public String getBulletinTitle() {
        return bulletinTitle;
    }

    public void setBulletinTitle(String bulletinTitle) {
        this.bulletinTitle = bulletinTitle == null ? null : bulletinTitle.trim();
    }

    public Integer getBulletinStatus() {
        return bulletinStatus;
    }

    public void setBulletinStatus(Integer bulletinStatus) {
        this.bulletinStatus = bulletinStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getBulletinContent() {
        return bulletinContent;
    }

    public void setBulletinContent(String bulletinContent) {
        this.bulletinContent = bulletinContent == null ? null : bulletinContent.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", bulletinUserid=").append(bulletinUserid);
        sb.append(", bulletinTitle=").append(bulletinTitle);
        sb.append(", bulletinStatus=").append(bulletinStatus);
        sb.append(", createTime=").append(createTime);
        sb.append(", bulletinContent=").append(bulletinContent);
        sb.append("]");
        return sb.toString();
    }
}