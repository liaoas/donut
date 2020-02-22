package com.liao.dao;

import com.liao.entity.DImg;

public interface DImgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DImg record);

    int insertSelective(DImg record);

    DImg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DImg record);

    int updateByPrimaryKey(DImg record);
}