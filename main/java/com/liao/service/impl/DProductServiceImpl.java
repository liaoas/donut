package com.liao.service.impl;

import com.liao.dao.DProductMapper;
import com.liao.entity.DProduct;
import com.liao.exception.RegistException;
import com.liao.service.DProductService;
import com.liao.util.Rejson;
import com.liao.util.ValidatorMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service

public class DProductServiceImpl implements DProductService {

    @Autowired
    private DProductMapper dProductMapper;
    /**
     * 商品添加
     * @param record
     * @return
     */
    @Override
    public Rejson productInsertAdd(DProduct record) {
        Rejson rejson = new Rejson();
        try {
            if (record == null){
                throw new RegistException("留言参数为空");
            }

            if (record.getProductName() == null || record.getProductName().equals("")){
                throw new RegistException("产品名称为空");
            }

            // 判断状态
            if (record.getProductStatus() == null || record.getProductStatus().equals("")){
                record.setProductStatus(1);
            }

            // 时间
            if (record.getCreateTime() == null || record.getCreateTime().equals("")){
                record.setCreateTime(new Date());
            }
            // 执行添加
            int sum = dProductMapper.productInsertAdd(record);
            rejson = new ValidatorMsg().cudVerification(sum,"产品添加成功","产品添加失败");
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
     * 产品动态修改
     * @param record
     * @return
     */
    @Override
    public Rejson productDynamicUpdate(DProduct record) {
        Rejson rejson = new Rejson();
        try {
            if (record ==  null){
                throw new RegistException("产品修改数据为空");
            }

            if (record.getId() == null || record.getId().equals("")){
                throw new RegistException("产品ID为空");
            }

            if (record.getProductName() == null || record.getProductName().equals("")){
                throw new RegistException("产品名称不可为空");
            }

            if (record.getProductIntroduction() == null || record.getProductIntroduction().equals("")){
                throw new RegistException("介绍不可为空");
            }
            // 执行修改
            int sum = dProductMapper.productDynamicUpdate(record);
            // 验证结果
            rejson = new ValidatorMsg().cudVerification(sum,"产品修改成功","产品不存在");

        }catch (RegistException e){
            e.printStackTrace();
            rejson.setMessage(e.getMessage());
            rejson.setBool(false);
            rejson.setStatus(500);
        }
        return rejson;
    }

    /**
     * 产品查询为空
     * @param record
     * @return
     */
    @Override
    public Rejson productDynamicSelect(DProduct record) {
        Rejson rejson = new Rejson();
        System.out.println("产品查询参数为："+record.toString());
        try {
            if(record == null){
                throw  new RegistException("公告查询参数为空");
            }
            // 登录方法
            List<DProduct> dProducts = dProductMapper.productDynamicSelect(record);
            String userName;
            // 验证结果
            rejson = new ValidatorMsg().selectVerification(dProducts,"产品查询成功","产品为空");
        }catch (RegistException e){
            e.printStackTrace();
            rejson.setList(null);
            rejson.setStatus(500);
            rejson.setBool(false);
            rejson.setMessage(e.getMessage());
        }
        return rejson;
    }

}
