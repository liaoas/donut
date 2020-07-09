package com.liao.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.liao.entity.DImg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DImgMapper {

    /**
     * 商品图片批量添加
     * @param imgList
     * @return
     */
    int addProductImgList(@Param("imgList")List<DImg> imgList);

    /**
     * 产品图片删除
     * @param record
     * @return
     */
    int delProductImg(DImg record);

}