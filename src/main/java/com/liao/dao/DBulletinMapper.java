package com.liao.dao;

import com.liao.entity.DBulletin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DBulletinMapper {
    /**
     * 公告添加
     * @param record
     * @return
     */
    int userBulletinAdd(DBulletin record);

    /**
     * 公告动态查询  根据时间戳
     * @param record
     * @return
     */
    List<DBulletin> bulletinDynamicSelect(@Param("record") DBulletin record,@Param("slectTime") String slectTime);

    /**
     * 公告动态修改
     * @param record
     * @return
     */
    int bulletinDynamicUpdate(DBulletin record);

}