package com.liao.dao;

import com.liao.entity.DProduct;

import java.util.List;

public interface DProductMapper {
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


    int insertSelective(DProduct record);

    DProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DProduct record);

    int updateByPrimaryKeyWithBLOBs(DProduct record);

    int updateByPrimaryKey(DProduct record);
}