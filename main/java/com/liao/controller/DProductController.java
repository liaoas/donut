package com.liao.controller;

import com.liao.entity.DProduct;
import com.liao.service.impl.DProductServiceImpl;
import com.liao.util.Rejson;
import com.liao.util.ValidatorMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class DProductController {

    @Autowired
    private DProductServiceImpl dProductService;

    private Rejson rejson;

    // 数据校验结果集
    Map Validator_msg;

    /**
     * 产品添加
     *
     * @param dProduct
     * @param result
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Rejson productInsertAdd(@Valid DProduct dProduct, BindingResult result) {
        rejson = new Rejson();
        // 数据校验
        Map Validator_msg = new ValidatorMsg().validator(result);
        if (Validator_msg == null) {
            // 添加方法
            rejson = dProductService.productInsertAdd(dProduct);
            rejson.setMap(Validator_msg);

        } else {
            rejson.setMap(Validator_msg);
            System.out.println("添加失败" + rejson.toString());
        }
        System.out.println("添加成功" + rejson.toString());
        return rejson;
    }


    /**
     * 产品修改
     * @param dProduct
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Rejson productDynamicUpdate(@Valid DProduct dProduct, BindingResult result) {
        rejson = new Rejson();
        // 数据验证
        Validator_msg = new ValidatorMsg().validator(result);
        if (Validator_msg == null) {
            // 修改方法
            rejson = dProductService.productDynamicUpdate(dProduct);
            rejson.setMap(Validator_msg);
        } else {
            rejson.setMap(Validator_msg);
            System.out.println("产品修改成功" + rejson.toString());
        }
        System.out.println("产品修改失败" + rejson.toString());
        return rejson;
    }

    /**
     * 产品动态查询
     *
     * @param dProduct
     */
    @RequestMapping(value = "/select", method = RequestMethod.POST)
    @ResponseBody
    public Rejson productDynamicSelect(DProduct dProduct,
                                       @RequestParam(value = "pn",defaultValue = "1") Integer pn) {
        rejson = new Rejson();
        rejson = dProductService.productDynamicSelect(dProduct,pn);
        return rejson;
    }

}
