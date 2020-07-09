package com.liao.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
@JsonIgnoreProperties(value = { "handler" })
public class DBbs {
    private Integer id;

    private Integer bbsUserid;

    private String bbsContent;

    private Integer bbsStatus;

    private Date createTime;

    private DUser dUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBbsUserid() {
        return bbsUserid;
    }

    public void setBbsUserid(Integer bbsUserid) {
        this.bbsUserid = bbsUserid;
    }

    public String getBbsContent() {
        return bbsContent;
    }

    public void setBbsContent(String bbsContent) {
        this.bbsContent = bbsContent == null ? null : bbsContent.trim();
    }

    public Integer getBbsStatus() {
        return bbsStatus;
    }

    public void setBbsStatus(Integer bbsStatus) {
        this.bbsStatus = bbsStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public DUser getdUser() {
        return dUser;
    }

    public void setdUser(DUser dUser) {
        this.dUser = dUser;
    }

    @Override
    public String toString() {
        StringBuilder bulider = new StringBuilder("DBbs [");
        bulider.append("    id=")
                .append(id);
        bulider.append(",    bbsUserid=")
                .append(bbsUserid);
        bulider.append(",    bbsContent=")
                .append(bbsContent);
        bulider.append(",    bbsStatus=")
                .append(bbsStatus);
        bulider.append(",    createTime=")
                .append(createTime);
        bulider.append(",    dUser=")
                .append(dUser);
        bulider.append(']');
        return bulider.toString();
    }
}