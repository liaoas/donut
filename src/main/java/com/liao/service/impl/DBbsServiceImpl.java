package com.liao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liao.config.ServiceLog;
import com.liao.dao.DBbsMapper;
import com.liao.entity.DBbs;
import com.liao.exception.RegistException;
import com.liao.service.DBbsService;
import com.liao.util.Rejson;
import com.liao.util.ValidatorMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DBbsServiceImpl implements DBbsService {

    private DBbsMapper dBbsMapper;

    /**
     * set 注入
     * @param dBbsMapper
     */
    @Autowired
    public void setdBbsMapper(DBbsMapper dBbsMapper){
        this.dBbsMapper = dBbsMapper;
    }

    /**
     * 根据登录用户ID添加留言
     * @param record
     * @return
     */
    @Override
    @ServiceLog(description = "登录用户添加留言")
    public Rejson userIDAddBbs(DBbs record) {
        Rejson rejson = new Rejson();
        try {
            if (record == null){
                throw new RegistException("留言参数为空");

            }

            if (record.getBbsUserid() == null || record.getBbsUserid().equals("")){
                throw new RegistException("用户ID为空");
            }

            if (record.getBbsContent() == null || record.getBbsContent().trim().equals("")){
                throw new RegistException("留言内容为空");
            }

            // 判断状态
            if (record.getBbsStatus() == null || record.getBbsStatus().equals("")){
                record.setBbsStatus(1);
            }

            // 时间
            if (record.getCreateTime() == null || record.getCreateTime().equals("")){
                record.setCreateTime(new Date());
            }
            // 执行添加
            int sum = dBbsMapper.userIDAddBbs(record);
            rejson = new ValidatorMsg().cudVerification(sum,"留言添加成功","留言添加失败");
        }catch (RegistException e){
            e.printStackTrace();
            rejson.setList(null);
            rejson.setStatus(500);
            rejson.setBool(false);
            rejson.setMessage(e.getMessage());
        }
        return rejson;
    }

    /**
     * 用户留言动态查询
     * @param record
     * @return
     */
    @Override
    @ServiceLog(description = "留言分页查询")
    public Rejson bbsDynamicSelect(DBbs record,Integer pn) {
        Rejson rejson = new Rejson();
        PageHelper.startPage(pn, 5);
        PageInfo pageInfo = new PageInfo(dBbsMapper.bbsDynamicSelect(record));
        rejson.setData(pageInfo);
        return rejson;
    }

    /**
     * 留言动态修改
     * @param record
     * @return
     */
    @Override
    @ServiceLog(description = "留言修改")
    public Rejson bbsDynamicUpdate(DBbs record) {
        Rejson rejson = new Rejson();
        try {
            if (record ==  null){
                throw new RegistException("留言修改数据为空");
            }

            if (record.getId() == null || record.getId().equals("")){
                throw new RegistException("留言ID为空");
            }

            if (record.getBbsContent() == null || record.getBbsContent().equals("")){
                throw new RegistException("留言参数为空");
            }
            // 执行修改
            int sum = dBbsMapper.bbsDynamicUpdate(record);
            // 验证结果
            rejson = new ValidatorMsg().cudVerification(sum,"留言修改成功","留言不存在");

        }catch (RegistException e){
            e.printStackTrace();
            rejson.setMessage(e.getMessage());
            rejson.setBool(false);
            rejson.setStatus(500);
        }
        return rejson;
    }


}
