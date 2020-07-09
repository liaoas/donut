package com.liao.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.liao.entity.DProduct;

import java.util.List;

/**
 *
 * TODO: 商品 Dao接口
 * @author LiAo
 * @date 2020/5/20 17:08
 */
public interface DProductMapper {
    /**
     * 根据产品主键删除
     * @param id 主键
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 商品添加
     * @param record
     * @return
     */
    int productInsertAdd(DProduct record);

    /**
     * 产品动态修改
     * @param record
     * @return
     */
    int productDynamicUpdate(DProduct record);

    /**
     * 产品动态查询
     * @param record
     * @return
     */
    List<DProduct> productDynamicSelect(DProduct record);

}