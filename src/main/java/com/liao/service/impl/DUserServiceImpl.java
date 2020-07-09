package com.liao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liao.config.ServiceLog;
import com.liao.dao.DUserMapper;
import com.liao.entity.DUser;
import com.liao.exception.RegistException;
import com.liao.service.DUserService;
import com.liao.util.Rejson;
import com.liao.util.ValidatorMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DUserServiceImpl implements DUserService {

    private final DUserMapper dUserMapper;

    /**
     * 自动注入
     *
     * @param dUserMapper
     */
    @Autowired
    public DUserServiceImpl(DUserMapper dUserMapper) {
        this.dUserMapper = dUserMapper;
    }

    /**
     * 用户注册
     *
     * @param record
     * @return
     */
    @Override
    @ServiceLog(description = "用户注册")
    public Rejson userRegistered(DUser record) {
        Rejson rejson = new Rejson();
        try {
            if (record == null) {
                throw new RegistException("用户注册参数为空");
            }
            // 判断用户名是否重复
            if (dUserMapper.userDynamicSelect(record).size() > 0) {
                throw new RegistException("用户名已被占用");
            }

            // 角色
            if (record.getUserRoles() == null || record.getUserRoles().trim().equals("")) {
                record.setUserRoles("普通用户");
            } else {
                record.setUserRoles("管理员");
            }
            // 状态
            if (record.getUserStatus() == null || record.getUserStatus().equals("")) {
                record.setUserStatus(1);
            }
            // 时间
            if (record.getCreateTime() == null || record.getCreateTime().equals("")) {
                record.setCreateTime(new Date());
            }
            // 注册方法
            int sum = dUserMapper.userRegistered(record);
            // 验证结果
            rejson = new ValidatorMsg().cudVerification(sum, "注册成功，即将跳转到登录", "注册失败");
        } catch (RegistException e) {
            e.printStackTrace();
            rejson.setList(null);
            rejson.setStatus(500);
            rejson.setBool(false);
            rejson.setMessage(e.getMessage());
        }
        return rejson;
    }

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @Override
    @ServiceLog(description = "用户登录")
    public Rejson userLogin(DUser user) {
        Rejson rejson = new Rejson();
        try {
            if (user == null) {
                throw new RegistException("用户登录参数为空");
            }
            // 角色
            if (user.getUserAccount() == null || user.getUserAccount().trim().equals("")) {
                throw new RegistException("账号为空");
            }
            // 角色
            if (user.getUserPassword() == null || user.getUserPassword().trim().equals("")) {
                throw new RegistException("密码为空");
            }
            // 登录方法
            List<DUser> userList = dUserMapper.userDynamicSelect(user);
            // 验证结果
            rejson = new ValidatorMsg().selectVerification(userList, "欢迎您", "账号密码错误");

        } catch (RegistException e) {
            e.printStackTrace();
            rejson.setList(null);
            rejson.setStatus(500);
            rejson.setBool(false);
            rejson.setMessage(e.getMessage());
        }
        return rejson;
    }

    /**
     * 用户信息修改
     *
     * @param record
     * @return
     */
    @Override
    @ServiceLog(description = "用户修改")
    public Rejson userUpdate(DUser record) {
        Rejson rejson = new Rejson();
        try {
            if (record == null) {
                throw new RegistException("用户信息修改数据为空");
            }

            if (record.getId() == null || record.getId().equals("")) {
                throw new RegistException("用户ID为空");
            }
            int sum = dUserMapper.userUpdate(record);
            rejson = new ValidatorMsg().cudVerification(sum, "用户信息修改成功", "修改用户不存在");
        } catch (RegistException e) {
            e.printStackTrace();
            rejson.setMessage(e.getMessage());
            rejson.setBool(false);
            rejson.setStatus(500);
        }
        return rejson;
    }

    /**
     * 用户分页查询
     *
     * @param record
     * @return
     */
    @Override
    @ServiceLog(description = "用户分页查询")
    public Rejson userDynamicSelect(DUser record, Integer pn) {
        Rejson rejson = new Rejson();
        PageHelper.startPage(pn, 5);
        PageInfo pageInfo = new PageInfo(dUserMapper.userDynamicSelect(record));
        rejson.setData(pageInfo);
        return rejson;
    }


}
