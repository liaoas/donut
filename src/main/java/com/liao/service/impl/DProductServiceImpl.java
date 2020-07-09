package com.liao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liao.config.ServiceLog;
import com.liao.dao.DImgMapper;
import com.liao.dao.DProductMapper;
import com.liao.entity.DImg;
import com.liao.entity.DProduct;
import com.liao.exception.RegistException;
import com.liao.service.DProductService;
import com.liao.util.Rejson;
import com.liao.util.ValidatorMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DProductServiceImpl implements DProductService {

    // 产品
    private final DProductMapper dProductMapper;

    // 产品图片
    private final DImgMapper dImgMapper;

    /**
     *
     * @param dProductMapper
     * @param dImgMapper
     */
    @Autowired
    public DProductServiceImpl(DProductMapper dProductMapper, DImgMapper dImgMapper) {
        this.dProductMapper = dProductMapper;
        this.dImgMapper = dImgMapper;
    }

    /**
     * 商品添加
     * @param record
     * @return
     */
    @Override
    @ServiceLog(description = "产品添加")
    public Rejson productInsertAdd(DProduct record) {
        Rejson rejson = new Rejson();
        try {
            if (record == null){
                throw new RegistException("产品参数为空");
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
            System.out.println("我是返回的自增主键"+record.getId());
            rejson = new ValidatorMsg().cudVerification(sum,"产品添加成功","产品添加失败");
            // 返回的主键
            rejson.setStatus(record.getId());

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
    @ServiceLog(description = "产品修改")
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
     * 产品查询
     * @param record
     * @return
     */
    @Override
    @ServiceLog(description = "产品分页查询")
    public Rejson productDynamicSelect(DProduct record,Integer pn) {
        Rejson rejson = new Rejson();
        PageHelper.startPage(pn, 5);
        PageInfo pageInfo = new PageInfo( dProductMapper.productDynamicSelect(record));
        rejson.setData(pageInfo);
        return rejson;
    }


    /**
     * 根据主键删除
     * @param id
     * @return
     */
    @Override
    @ServiceLog(description = "产品删除")
    public Rejson deleteByPrimaryKey(Integer id) {
        Rejson rejson = new Rejson();
        try {
            if (id == null || id.equals("")){
                throw new RegistException("产品不存在");
            }
            // 产品图片删除
            dImgMapper.delProductImg(new DImg(id));
            // 执行产品删除
            int sum = dProductMapper.deleteByPrimaryKey(id);
            // 验证结果
            rejson = new ValidatorMsg().cudVerification(sum,"删除成功","产品不存在");
        }catch (RegistException e){
            e.printStackTrace();
            rejson.setMessage(e.getMessage());
            rejson.setBool(false);
            rejson.setStatus(500);
        }
        return rejson;
    }

}
