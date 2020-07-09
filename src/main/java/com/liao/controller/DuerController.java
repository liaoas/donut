package com.liao.controller;

import com.liao.config.ControllerLog;
import com.liao.entity.DUser;
import com.liao.service.impl.DUserServiceImpl;
import com.liao.util.Rejson;
import com.liao.util.ValidatorMsg;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class DuerController {

    private Rejson rejson;

    // 数据校验结果集
    Map Validator_msg;

    private final DUserServiceImpl dUserService;

    /**
     * set注入
     *
     * @param dUserService
     */
    public DuerController(DUserServiceImpl dUserService) {
        this.dUserService = dUserService;
    }

    /**
     * 用户注册
     *
     * @param user
     * @param result
     */
    @PostMapping("/registered")
    @ResponseBody
    @ControllerLog(description = "用户注册接口")
    public Rejson userRegistered(@Valid DUser user, BindingResult result) {
        rejson = new Rejson();
        // 数据校验
        Map Validator_msg = new ValidatorMsg().validator(result);
        if (Validator_msg == null) {
            rejson.setMap(null);
            // 为身份赋值
            user.setUserRoles("普通用户");
            // 执行用户注册
            rejson = dUserService.userRegistered(user);
        }
        rejson.setMap(Validator_msg);
        return rejson;
    }

    /**
     * 管理员添加
     *
     * @param user
     * @param result
     */
    @PostMapping("/administratoradd")
    @ResponseBody
    @ControllerLog(description = "管理员添加接口")
    public Rejson administratorAdd(@Valid DUser user, BindingResult result) {
        rejson = new Rejson();
        // 数据校验
        Map Validator_msg = new ValidatorMsg().validator(result);
        if (Validator_msg == null) {
            rejson.setMap(null);
            // 执行管理员添加方法
            rejson = dUserService.userRegistered(user);
        }
        rejson.setMap(Validator_msg);
        return rejson;
    }

    /**
     * 用户登录
     *
     * @param user
     * @param result
     */
    @PostMapping("/login")
    @ResponseBody
    @ControllerLog(description = "用户登录接口")
    public Rejson userLogin(DUser user, BindingResult result, HttpServletRequest request) {
        rejson = new Rejson();
        // 登录
        rejson = dUserService.userLogin(user);
        // 登录成功 将用户数据存入Session
        if (rejson.getBool()) {
            // 存入session
            HttpSession session = request.getSession();
            session.setAttribute("user", rejson.getList().get(0));
        }
        return rejson;
    }

    /**
     * 获取Session
     *
     * @param request
     * @return
     */
    @PostMapping("/obtainloginuser")
    @ResponseBody
    @ControllerLog(description = "获取当前登录用户接口")
    public Rejson obtainUserSession(HttpServletRequest request) {
        rejson = new Rejson();
        // 存储当前登录用户集合
        List<DUser> userList = new ArrayList();
        // 获取session中的当前用户
        HttpSession session = request.getSession();
        if ((DUser) session.getAttribute("user") != null) {
            userList.add((DUser) session.getAttribute("user"));
        }
        // 返回登录Session结果
        return new ValidatorMsg().selectVerification(userList, "Session数据获取成功", "当前用户未登录");
    }

    /**
     * 用户信息修改
     *
     * @param user
     * @return
     */
    @PostMapping("/updateUser")
    @ResponseBody
    @ControllerLog(description = "用户信息修改接口")
    public Rejson userUpdate(@Valid DUser user, BindingResult result) {
        rejson = new Rejson();
        // 数据验证
        Validator_msg = new ValidatorMsg().validator(result);
        if (Validator_msg == null) {
            rejson.setMap(null);
            // 修改方法
            rejson = dUserService.userUpdate(user);
        }
        rejson.setMap(Validator_msg);
        return rejson;
    }

    /**
     * 用户信息查询
     *
     * @param user
     * @return
     */
    @PostMapping("/selectall")
    @ResponseBody
    @ControllerLog(description = "用户信息查询接口")
    public Rejson userDynamicSelect(DUser user, BindingResult result,
                                    @RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        rejson = new Rejson();
        // 数据校验
        if (Validator_msg == null) {
            rejson.setMap(null);
            // 查询方法
            rejson = dUserService.userDynamicSelect(user, pn);
        }
        rejson.setMap(Validator_msg);
        return rejson;
    }

    /**
     * 退出登录
     *
     * @param request
     * @return
     */
    @PostMapping("/dropout")
    @ResponseBody
    @ControllerLog(description = "退出登录接口")
    public Rejson signOut(HttpServletRequest request) {
        rejson = new Rejson();
        // 得到Session 所有的属性名
        Enumeration en = request.getSession().getAttributeNames();
        // 遍历清除Session
        while (en.hasMoreElements()) {
            request.getSession().removeAttribute(en.nextElement().toString());
        }
        rejson.setStatus(500);
        rejson.setBool(true);
        rejson.setMessage("成功");
        return rejson;
    }
}

