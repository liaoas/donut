package com.liao.service;

import com.liao.entity.DProduct;
import com.liao.util.Rejson;

import java.util.List;

public interface DProductService {

    /**
     * 商品添加
     * @param record
     * @return
     */
    Rejson productInsertAdd(DProduct record);

    /**
     * 产品动态修改
     * @param record
     * @return
     */
    Rejson productDynamicUpdate(DProduct record);

    /**
     * 产品动态查询
     * @param record
     * @return
     */
    Rejson productDynamicSelect(DProduct record);
}
