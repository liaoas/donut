package com.liao.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.liao.config.ControllerLog;
import com.liao.entity.DBbs;
import com.liao.entity.DUser;
import com.liao.service.impl.DBbsServiceImpl;
import com.liao.util.Rejson;
import com.liao.util.ValidatorMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/bbs")
@JsonIgnoreProperties(value = {"handler"})
public class DBbsController {

    // 数据校验结果集
    Map Validator_msg;

    // 返回结果集
    private Rejson rejson;

    private final DBbsServiceImpl dBbsService;

    /**
     * set 注入
     * @param dBbsService
     */
    @Autowired
    public DBbsController(DBbsServiceImpl dBbsService) {
        this.dBbsService = dBbsService;
    }

    /**
     * 用户添加留言
     *
     * @param dBbs
     */
    @PostMapping("/add")
    @ResponseBody
    @ControllerLog(description = "留言添加接口")
    public Rejson userRegistered(DBbs dBbs) {
        // 留言添加方法
        return dBbsService.userIDAddBbs(dBbs);
    }

    /**
     * 留言动态查询
     *
     * @param dBbs
     */
    @PostMapping("/select")
    @ResponseBody
    @ControllerLog(description = "留言查询接口")
    public Rejson bbsDynamicSelect(DBbs dBbs,
                                   @RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        return dBbsService.bbsDynamicSelect(dBbs, pn);
    }

    /**
     * 获取登录用户的留言
     *
     * @param dBbs
     * @param pn
     * @param request
     * @return
     */
    @PostMapping("/selectsessionuserid")
    @ResponseBody
    @ControllerLog(description = "获取登录用户的留言接口")
    public Rejson bbsDynamicSelectBySeeionUserId(DBbs dBbs,
                                                 @RequestParam(value = "pn", defaultValue = "1") Integer pn, HttpServletRequest request) {

        rejson = new Rejson();
        DUser dUser = new DUser();
        // 获取session中的当前用户
        HttpSession session = request.getSession();

        if ((DUser) session.getAttribute("user") != null) {
            dUser = (DUser) session.getAttribute("user");
            dBbs.setBbsUserid(dUser.getId());
            rejson = dBbsService.bbsDynamicSelect(dBbs, pn);
        }
        return rejson;
    }


    /**
     * 留言修改
     *
     * @param dBbs
     * @return
     */
    @PostMapping("/updateBbs")
    @ResponseBody
    @ControllerLog(description = "留言修改接口")
    public Rejson bbsDynamicUpdate(@Valid DBbs dBbs, BindingResult result) {
        rejson = new Rejson();
        // 数据验证
        Validator_msg = new ValidatorMsg().validator(result);
        if (Validator_msg == null) {
            // 修改方法
            rejson = dBbsService.bbsDynamicUpdate(dBbs);
        }
        rejson.setMap(Validator_msg);
        return rejson;
    }

}
