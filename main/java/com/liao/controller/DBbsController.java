package com.liao.controller;

import com.liao.entity.DBbs;
import com.liao.entity.DUser;
import com.liao.service.impl.DBbsServiceImpl;
import com.liao.util.Rejson;
import com.liao.util.ValidatorMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/bbs")
public class DBbsController {
    @Autowired
    private DBbsServiceImpl dBbsService;

    // 数据校验结果集
    Map Validator_msg;

    // 返回结果集
    private Rejson rejson;

    /**
     * 用户添加留言
     *
     * @param dBbs
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Rejson userRegistered(DBbs dBbs) {
        rejson = new Rejson();
        // 注册方法
        rejson = dBbsService.userIDAddBbs(dBbs);
        if (rejson.getBool()) {
            System.out.println("添加成功" + rejson.toString());
        } else {
            System.out.println("添加失败" + rejson.toString());
        }
        return rejson;
    }

    /**
     * 留言动态查询
     *
     * @param dBbs
     */
    @RequestMapping(value = "/select", method = RequestMethod.POST)
    @ResponseBody
    public Rejson bbsDynamicSelect(DBbs dBbs) {
        rejson = new Rejson();
        rejson = dBbsService.bbsDynamicSelect(dBbs);
        return rejson;
    }

    /**
     * 留言修改
     *
     * @param dBbs
     * @return
     */
    @RequestMapping(value = "/updateBbs", method = RequestMethod.POST)
    @ResponseBody
    public Rejson bbsDynamicUpdate(@Valid DBbs dBbs, BindingResult result) {
        rejson = new Rejson();
        // 数据验证
        Validator_msg = new ValidatorMsg().validator(result);
        if (Validator_msg == null) {
            // 修改方法
            rejson = dBbsService.bbsDynamicUpdate(dBbs);
            rejson.setMap(Validator_msg);
        } else {
            rejson.setMap(Validator_msg);
            System.out.println("留言修改成功" + rejson.toString());
        }
        System.out.println("留言修改失败" + rejson.toString());
        return rejson;
    }

}
