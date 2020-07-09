package com.liao.controller;

import com.liao.config.ControllerLog;
import com.liao.entity.DBulletin;
import com.liao.service.impl.DBulletinServiceImpl;
import com.liao.util.Rejson;
import com.liao.util.ValidatorMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/bulletin")
public class DBulletinController {


    private Rejson rejson;

    // 数据校验结果集
    Map Validator_msg;

    private final DBulletinServiceImpl dBulletinService;

    /**
     * set注入
     * @param dBulletinService
     */
    @Autowired
    public DBulletinController(DBulletinServiceImpl dBulletinService) {
        this.dBulletinService = dBulletinService;
    }


    /**
     * 公告添加
     *
     * @param dBulletin
     * @param result
     */
    @PostMapping("/add")
    @ResponseBody
    @ControllerLog(description = "公告添加接口")
    public Rejson userRegistered(@Valid DBulletin dBulletin, BindingResult result) {
        rejson = new Rejson();

        // 数据校验
        Map Validator_msg = new ValidatorMsg().validator(result);

        if (Validator_msg == null) {
            // 添加方法
            rejson = dBulletinService.userBulletinAdd(dBulletin);

        }
        rejson.setMap(Validator_msg);
        return rejson;
    }

    /**
     * 公告动态查询
     *
     * @param dBulletin
     */
    @PostMapping("/select")
    @ResponseBody
    @ControllerLog(description = "公告查询接口")
    public Rejson bulletinDynamicSelect(DBulletin dBulletin,
                                        @RequestParam(value = "pn", defaultValue = "1") Integer pn, String selectTime) {
        // 公告动态查询
        return dBulletinService.bulletinDynamicSelect(dBulletin, pn, selectTime);
    }

    /**
     * 公告修改
     *
     * @param dBulletin
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    @ControllerLog(description = "公告修改接口")
    public Rejson bulletinDynamicUpdate(@Valid DBulletin dBulletin, BindingResult result) {
        rejson = new Rejson();
        // 数据验证
        Validator_msg = new ValidatorMsg().validator(result);
        if (Validator_msg == null) {
            // 修改方法
            rejson = dBulletinService.bulletinDynamicUpdate(dBulletin);
        }
        rejson.setMap(Validator_msg);
        return rejson;
    }
}
