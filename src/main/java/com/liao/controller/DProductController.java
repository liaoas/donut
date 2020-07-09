package com.liao.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.liao.config.ControllerLog;
import com.liao.entity.DProduct;
import com.liao.service.impl.DProductServiceImpl;
import com.liao.util.Rejson;
import com.liao.util.ValidatorMsg;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/product")
@JsonIgnoreProperties(value = {"handler"})
public class DProductController {



    private Rejson rejson;

    // 数据校验结果集
    Map Validator_msg;

    private final DProductServiceImpl dProductService;

    /**
     * set注入
     * @param dProductService
     */
    public DProductController(DProductServiceImpl dProductService) {
        this.dProductService = dProductService;
    }

    /**
     * 产品添加
     *
     * @param dProduct
     * @param result
     */
    @PostMapping("/add")
    @ResponseBody
    @ControllerLog(description = "产品添加接口")
    public Rejson productInsertAdd(@Valid DProduct dProduct, BindingResult result) {
        rejson = new Rejson();
        // 数据校验
        Map Validator_msg = new ValidatorMsg().validator(result);
        if (Validator_msg == null) {
            rejson.setMap(null);
            // 添加方法
            rejson = dProductService.productInsertAdd(dProduct);
        }
        rejson.setMap(Validator_msg);

        return rejson;
    }


    /**
     * 产品修改
     *
     * @param dProduct
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    @ControllerLog(description = "产品修改接口")
    public Rejson productDynamicUpdate(@Valid DProduct dProduct, BindingResult result) {
        rejson = new Rejson();
        // 数据验证
        Validator_msg = new ValidatorMsg().validator(result);
        if (Validator_msg == null) {
            rejson.setMap(null);
            // 修改方法
            rejson = dProductService.productDynamicUpdate(dProduct);
        }
        rejson.setMap(Validator_msg);
        return rejson;
    }

    /**
     * 产品动态查询
     *
     * @param dProduct
     */
    @PostMapping("/select")
    @ResponseBody
    @ControllerLog(description = "产品查询接口")
    public Rejson productDynamicSelect(DProduct dProduct,
                                       @RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        // 返回查询数据
        return dProductService.productDynamicSelect(dProduct, pn);
    }

    @PostMapping("/del")
    @ResponseBody
    @ControllerLog(description = "产品删除接口")
    public Rejson deleteByPrimaryKey(Integer id) {
        // 返回产品删除数据
        return dProductService.deleteByPrimaryKey(id);
    }

}
