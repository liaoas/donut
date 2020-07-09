package com.liao.dao;

import com.liao.entity.DUser;
import com.liao.util.Rejson;

import java.io.Reader;
import java.util.List;

public interface DUserMapper {

    /**
     * 用户动态查询
     * 判断用户是否重复
     * @param user
     * @return
     */
    List<DUser> userDynamicSelect (DUser user);

    /**
     * 用户注册
     * @param record
     * @return
     */
    int userRegistered(DUser record);

    /**
     * 用户动态修改
     * @param record
     * @return
     */
    int userUpdate(DUser record);


}