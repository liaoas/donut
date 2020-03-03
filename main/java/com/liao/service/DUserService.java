package com.liao.service;

import com.liao.entity.DUser;
import com.liao.util.Rejson;

public interface DUserService {

    /**
     * 用户注册
     * @param record
     * @return
     */
    Rejson userRegistered(DUser record);

    /**
     * 用户登录
     * @param user
     * @return
     */
    Rejson userLogin(DUser user);

    /**
     * 用户动态修改
     * @param record
     * @return
     */
    Rejson userUpdate(DUser record);

    /**
     * 用户动态查询
     * @param record
     * @return
     */
    Rejson userDynamicSelect(DUser record,Integer pn);
}
