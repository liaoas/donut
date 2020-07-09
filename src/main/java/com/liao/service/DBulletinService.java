package com.liao.service;

import com.liao.entity.DBulletin;
import com.liao.util.Rejson;

import java.util.List;

public interface DBulletinService {

    /**
     * 公告添加
     * @param record
     * @return
     */
    Rejson userBulletinAdd(DBulletin record);

    /**
     * 公告动态查询
     * @param record
     * @return
     */
    Rejson bulletinDynamicSelect(DBulletin record,Integer pn,String stectTime);

    /**
     * 公告动态修改
     * @param record
     * @return
     */
    Rejson bulletinDynamicUpdate(DBulletin record);
}
