package com.liao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liao.dao.DBulletinMapper;
import com.liao.entity.DBulletin;
import com.liao.exception.RegistException;
import com.liao.service.DBulletinService;
import com.liao.util.Rejson;
import com.liao.util.ValidatorMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DBulletinServiceImpl implements DBulletinService {
    @Autowired
    private DBulletinMapper dBulletinMapper;

    /**
     * 公告添加
     * @param record
     * @return
     */
    @Override
    public Rejson userBulletinAdd(DBulletin record) {
        Rejson rejson = new Rejson();
        try {
            if (record == null){
                throw new RegistException("留言参数为空");
            }

            if (record.getBulletinUserid() == null || record.getBulletinUserid().equals("")){
                throw new RegistException("用户ID为空");
            }

            if (record.getBulletinTitle() == null || record.getBulletinTitle().trim().equals("")){
                throw new RegistException("公告标题为空");
            }

            if (record.getBulletinContent() == null || record.getBulletinContent().trim().equals("")){
                throw new RegistException("公告内容为空");
            }

            // 判断状态
            if (record.getBulletinStatus() == null || record.getBulletinStatus().equals("")){
                record.setBulletinStatus(1);
            }

            // 时间
            if (record.getCreateTime() == null || record.getCreateTime().equals("")){
                record.setCreateTime(new Date());
            }
            // 执行添加
            int sum = dBulletinMapper.userBulletinAdd(record);
            rejson = new ValidatorMsg().cudVerification(sum,"公告添加成功","公告添加失败");
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
     * 公告动态查询
     * @param record
     * @return
     */
    @Override
    public Rejson bulletinDynamicSelect(DBulletin record, Integer pn) {
        Rejson rejson = new Rejson();
        PageHelper.startPage(pn, 5);
        PageInfo pageInfo = new PageInfo(dBulletinMapper.bulletinDynamicSelect(record));
        rejson.setData(pageInfo);
        return rejson;
    }

    /**
     * 公告动态修改
     * @param record
     * @return
     */
    @Override
    public Rejson bulletinDynamicUpdate(DBulletin record) {
        Rejson rejson = new Rejson();
        try {
            if (record ==  null){
                throw new RegistException("留言修改数据为空");
            }

            if (record.getId() == null || record.getId().equals("")){
                throw new RegistException("公告ID为空");
            }

            if (record.getBulletinTitle() == null || record.getBulletinTitle().equals("")){
                throw new RegistException("标题不可为空");
            }

            if (record.getBulletinContent() == null || record.getBulletinContent().equals("")){
                throw new RegistException("内容不可为空");
            }
            // 执行修改
            int sum = dBulletinMapper.bulletinDynamicUpdate(record);
            // 验证结果
            rejson = new ValidatorMsg().cudVerification(sum,"公告修改成功","公告不存在");

        }catch (RegistException e){
            e.printStackTrace();
            rejson.setMessage(e.getMessage());
            rejson.setBool(false);
            rejson.setStatus(500);
        }
        return rejson;
    }
}
