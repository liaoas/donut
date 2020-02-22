package com.liao.dao;

import com.liao.entity.DBbs;

import java.util.List;

public interface DBbsMapper {
    int deleteByPrimaryKey(Integer id);

    /**
     * 根据用户ID添加留言
     * @param record
     * @return
     */
    int userIDAddBbs(DBbs record);

    /**
     * 动态查询留言
     * @param record
     * @return
     */
    List<DBbs> bbsDynamicSelect(DBbs record);

    /**
     * 留言修改
     * @param record
     * @return
     */
    int bbsDynamicUpdate(DBbs record);

    int insertSelective(DBbs record);

    DBbs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DBbs record);

    int updateByPrimaryKey(DBbs record);
}