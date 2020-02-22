package com.liao.controller;

import com.liao.entity.DBulletin;
import com.liao.service.impl.DBulletinServiceImpl;
import com.liao.util.Rejson;
import com.liao.util.ValidatorMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/bulletin")
public class DBulletinController {
    @Autowired
    private DBulletinServiceImpl dBulletinService;

    private Rejson rejson;

    // 数据校验结果集
    Map Validator_msg;

    /**
     * 公告添加
     *
     * @param dBulletin
     * @param result
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Rejson userRegistered(@Valid DBulletin dBulletin, BindingResult result) {
        rejson = new Rejson();
        // 数据校验
        Map Validator_msg = new ValidatorMsg().validator(result);
        if (Validator_msg == null) {
            // 添加方法
            rejson = dBulletinService.userBulletinAdd(dBulletin);
            rejson.setMap(Validator_msg);

        } else {
            rejson.setMap(Validator_msg);
            System.out.println("添加失败" + rejson.toString());
        }
        System.out.println("添加成功" + rejson.toString());
        return rejson;
    }

    /**
     * 公告动态查询
     *
     * @param dBulletin
     */
    @RequestMapping(value = "/select", method = RequestMethod.POST)
    @ResponseBody
    public Rejson bulletinDynamicSelect(DBulletin dBulletin) {
        rejson = new Rejson();
        rejson = dBulletinService.bulletinDynamicSelect(dBulletin);
        return rejson;
    }

    /**
     * 公告修改
     * @param dBulletin
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Rejson bulletinDynamicUpdate(@Valid DBulletin dBulletin, BindingResult result) {
        rejson = new Rejson();
        // 数据验证
        Validator_msg = new ValidatorMsg().validator(result);
        if (Validator_msg == null) {
            // 修改方法
            rejson = dBulletinService.bulletinDynamicUpdate(dBulletin);
            rejson.setMap(Validator_msg);
        } else {
            rejson.setMap(Validator_msg);
            System.out.println("公告修改成功" + rejson.toString());
        }
        System.out.println("公告修改失败" + rejson.toString());
        return rejson;
    }
}
