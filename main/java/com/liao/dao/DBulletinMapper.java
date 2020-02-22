package com.liao.dao;

import com.liao.entity.DBulletin;

import java.util.List;

public interface DBulletinMapper {
    int deleteByPrimaryKey(Integer id);

    /**
     * 公告添加
     * @param record
     * @return
     */
    int userBulletinAdd(DBulletin record);

    /**
     * 公告动态查询
     * @param record
     * @return
     */
    List<DBulletin> bulletinDynamicSelect(DBulletin record);

    /**
     * 公告动态修改
     * @param record
     * @return
     */
    int bulletinDynamicUpdate(DBulletin record);

    int insertSelective(DBulletin record);

    DBulletin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DBulletin record);

    int updateByPrimaryKeyWithBLOBs(DBulletin record);

    int updateByPrimaryKey(DBulletin record);
}