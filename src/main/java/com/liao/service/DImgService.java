package com.liao.service;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.liao.entity.DImg;
import com.liao.util.Rejson;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DImgService {

    /**
     * 商品图片批量添加
     *
     * @param imgList
     * @return
     */
    Rejson addProductImgList(@Param("imgList") List<DImg> imgList);


    /**
     * 产品图片删除
     *
     * @param record
     * @return
     */
    Rejson delProductImg(DImg record);

}
