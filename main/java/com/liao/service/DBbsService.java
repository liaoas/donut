package com.liao.service;

import com.liao.entity.DBbs;
import com.liao.util.Rejson;

public interface DBbsService {

    /**
     * 根据用户ID添加留言
     * @param record
     * @return
     */
    Rejson userIDAddBbs(DBbs record);

    /**
     * 留言动态查询
     * @param record
     * @return
     */
    Rejson bbsDynamicSelect(DBbs record,Integer pn);

    /**
     * 留言修改
     * @param record
     * @return
     */
    Rejson bbsDynamicUpdate(DBbs record);
}
