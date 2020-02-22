package com.liao.entity;

import java.util.Date;

public class DBbs {
    private Integer id;

    private Integer bbsUserid;

    private String bbsContent;

    private Integer bbsStatus;

    private Date createTime;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", bbsUserid=").append(bbsUserid);
        sb.append(", bbsContent=").append(bbsContent);
        sb.append(", bbsStatus=").append(bbsStatus);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}