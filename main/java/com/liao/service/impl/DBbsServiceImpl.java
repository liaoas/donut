package com.liao.service.impl;

import com.liao.dao.DBbsMapper;
import com.liao.entity.DBbs;
import com.liao.entity.DUser;
import com.liao.exception.RegistException;
import com.liao.service.DBbsService;
import com.liao.util.Rejson;
import com.liao.util.ValidatorMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DBbsServiceImpl implements DBbsService {

    @Autowired
    private DBbsMapper dBbsMapper;

    /**
     * 根据登录用户ID添加留言
     * @param record
     * @return
     */
    @Override
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
    public Rejson bbsDynamicSelect(DBbs record) {
        Rejson rejson = new Rejson();
        System.out.println("留言查询参数为："+record.toString());
        try {
            if(record == null){
                throw  new RegistException("留言查询参数为空");
            }
            // 登录方法
            List<DBbs> dBbsList = dBbsMapper.bbsDynamicSelect(record);
            String userName;
            // 验证结果
            rejson = new ValidatorMsg().selectVerification(dBbsList,"留言查询成功","留言为空");
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
     * 留言动态修改
     * @param record
     * @return
     */
    @Override
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
