package com.liao.service.impl;

import com.liao.config.ServiceLog;
import com.liao.dao.DImgMapper;
import com.liao.entity.DImg;
import com.liao.exception.RegistException;
import com.liao.service.DImgService;
import com.liao.util.Rejson;
import com.liao.util.ValidatorMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DImgServiceImpl implements DImgService {

    private DImgMapper dImgMapper;

    /**
     * set注入
     * @param dImgMapper
     */
    @Autowired
    public void setdImgMapper(DImgMapper dImgMapper){
        this.dImgMapper = dImgMapper;
    }

    /**
     * 商品图片添加
     *
     * @param imgList
     * @return
     */
    @Override
    @ServiceLog(description = "产品图片添加")
    public Rejson addProductImgList(List<DImg> imgList) {
        Rejson rejson = new Rejson();
        try {
            if (imgList == null) {
                throw new RegistException("图片数据为空");
            }

            for (DImg img : imgList) {
                if (img.getProductId() == null) {
                    throw new RegistException("商品数据为空");
                }

                if (img.getImg() == null) {
                    throw new RegistException("图片数据为空");
                }

                if (img.getCreateTime() == null) {
                    throw new RegistException("无法获取系统时间");
                }
            }
            // 执行添加
            int json = dImgMapper.addProductImgList(imgList);
            // 验证数据
            rejson = new ValidatorMsg().cudVerification(json, "产品图片添加成功", "产品图片添加失败");

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
     * 产品图片删除
     *
     * @param record
     * @return
     */
    @Override
    @ServiceLog(description = "产品图片删除")
    public Rejson delProductImg(DImg record) {
        Rejson rejson = new Rejson();
        try {
            if (record == null) {
                throw new RegistException("产品图片为空");
            }

            if (record.getProductId() == null) {
                throw new RegistException("无法获取产品");
            }
            // 执行删除
            int sum = dImgMapper.delProductImg(record);
            rejson = new ValidatorMsg().cudVerification(sum,"产品图片删除成功","产品图片不存在");
        } catch (RegistException e) {
            e.printStackTrace();
            rejson.setList(null);
            rejson.setStatus(500);
            rejson.setBool(false);
            rejson.setMessage(e.getMessage());
        }
        return rejson;
    }


}
