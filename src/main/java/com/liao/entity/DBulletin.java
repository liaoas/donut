package com.liao.entity;

import java.util.Date;

public class DBulletin {
    private Integer id;

    private Integer bulletinUserid;

    private String bulletinTitle;

    private Integer bulletinStatus;

    private Date createTime;

    private String bulletinContent;

    private DUser dUser;

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

    public DUser getdUser() {
        return dUser;
    }

    public void setdUser(DUser dUser) {
        this.dUser = dUser;
    }

    @Override
    public String toString() {
        StringBuilder bulider = new StringBuilder("DBulletin [");
        bulider.append("    id=")
                .append(id);
        bulider.append(",    bulletinUserid=")
                .append(bulletinUserid);
        bulider.append(",    bulletinTitle=")
                .append(bulletinTitle);
        bulider.append(",    bulletinStatus=")
                .append(bulletinStatus);
        bulider.append(",    createTime=")
                .append(createTime);
        bulider.append(",    bulletinContent=")
                .append(bulletinContent);
        bulider.append(",    dUser=")
                .append(dUser);
        bulider.append(']');
        return bulider.toString();
    }
}