package com.liao.controller;

import com.liao.entity.DUser;
import com.liao.service.impl.DUserServiceImpl;
import com.liao.util.Rejson;
import com.liao.util.ValidatorMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class DuerController {

    @Autowired
    private DUserServiceImpl dUserService;

    private Rejson rejson;

    // 数据校验结果集
    Map Validator_msg;

    /**
     * 用户注册
     *
     * @param user
     * @param result
     */
    @RequestMapping(value = "/registered", method = RequestMethod.POST)
    @ResponseBody
    public Rejson userRegistered(@Valid DUser user, BindingResult result) {
        rejson = new Rejson();
        // 数据校验
        Map Validator_msg = new ValidatorMsg().validator(result);
        if (Validator_msg == null) {
            // 为身份赋值
            user.setUserRoles("普通用户");
            // 执行用户注册
            rejson = dUserService.userRegistered(user);
            rejson.setMap(Validator_msg);

        } else {
            rejson.setMap(Validator_msg);
            System.out.println("注册失败" + rejson.toString());
        }
        System.out.println("注册成功" + rejson.toString());
        return rejson;
    }

    /**
     * 管理员添加
     *
     * @param user
     * @param result
     */
    @RequestMapping(value = "/administratoradd", method = RequestMethod.POST)
    @ResponseBody
    public Rejson administratorAdd(@Valid DUser user, BindingResult result) {
        rejson = new Rejson();
        // 数据校验
        Map Validator_msg = new ValidatorMsg().validator(result);
        if (Validator_msg == null) {
            // 为身份赋值
            user.setUserRoles("管理员");
            // 添加方法方法
            rejson = dUserService.userRegistered(user);
            rejson.setMap(Validator_msg);

        } else {
            rejson.setMap(Validator_msg);
            System.out.println("注册失败" + rejson.toString());
        }
        System.out.println("注册成功" + rejson.toString());
        return rejson;
    }

    /**
     * 用户登录
     *
     * @param user
     * @param result
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Rejson userLogin(DUser user, BindingResult result, HttpServletRequest request) {
        rejson = new Rejson();
        // 登录
        rejson = dUserService.userLogin(user);
        if (rejson.getBool()){
            // 存入session
            HttpSession session = request.getSession();
            session.setAttribute("user",rejson.getList().get(0));
            System.out.println("登录成功" + rejson.toString());
        }else {
            System.out.println("登录失败"+rejson.toString());
        }
        return rejson;
    }

    /**
     * 获取Session
     * @param request
     * @return
     */
    @RequestMapping(value = "/obtainloginuser",method = RequestMethod.POST)
    @ResponseBody
    public Rejson obtainUserSession(HttpServletRequest request){
        rejson = new Rejson();
        // 存储当前登录用户集合
        List<DUser> userList = new ArrayList();
        // 获取session中的当前用户
        HttpSession session = request.getSession();
        if ((DUser) session.getAttribute("user") != null){
            userList.add((DUser) session.getAttribute("user"));
        }
        // 验证用户是否登录
        rejson = new ValidatorMsg().selectVerification(userList,"Session数据获取成功","当前用户未登录");
        return rejson;
    }

    /**
     * 用户信息修改
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    @ResponseBody
    public Rejson userUpdate(@Valid DUser user, BindingResult result) {
        rejson = new Rejson();
        // 数据验证
        Validator_msg = new ValidatorMsg().validator(result);
        if (Validator_msg == null) {
            // 修改方法
            rejson = dUserService.userUpdate(user);
            rejson.setMap(Validator_msg);
        } else {
            rejson.setMap(Validator_msg);
            System.out.println("用户修改失败" + rejson.toString());
        }
        System.out.println("用户修改成功" + rejson.toString());
        return rejson;
    }

    /**
     * 用户信息查询
     * @param user
     * @return
     */
    @RequestMapping(value = "/selectall", method = RequestMethod.POST)
    @ResponseBody
    public Rejson userDynamicSelect(DUser user, BindingResult result,
                                    @RequestParam(value = "pn",defaultValue = "1") Integer pn) {
        System.out.println("查询的数据"+user);

        Rejson rejson = new Rejson();
        // 数据校验
        if (Validator_msg == null) {
            // 查询方法
            rejson = dUserService.userDynamicSelect(user,pn);
            rejson.setMap(Validator_msg);
        } else {
            rejson.setMap(Validator_msg);
            System.out.println("查询用户失败" + rejson.toString());
        }
        System.out.println("查询用户成功" + rejson.toString());
        return rejson;
    }
}
