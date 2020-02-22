package com.liao.util;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据校验
 */
public class ValidatorMsg {

    Rejson rejson;
    /**
     * 正则判断
     * @param result
     * @return
     */
    public Map validator(BindingResult result) {
        if (result.hasErrors()) {
            //校验失败，应该返回失败，在模态框中显示校验失败的错误信息
            Map<String, Object> map = new HashMap<String, Object>();
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError fieldError : errors) {
                System.out.println("错误的字段名：" + fieldError.getField());
                System.out.println("错误信息：" + fieldError.getDefaultMessage());
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return map;
        }else{
            return  null;
        }

    }

    /**
     * 查询结果集验证
     * @param list 返回值
     * @param message001 成功语句
     * @param message02 失败语句
     * @return
     */
    public Rejson selectVerification(List list,String message001,String message02){
        rejson = new Rejson();

        if (list.size() != 0 && list != null){
            rejson.setList(list);
            rejson.setBool(true);
            rejson.setStatus(200);
            rejson.setMessage(message001);
        }else {
            rejson.setList(null);
            rejson.setBool(false);
            rejson.setStatus(500);
            rejson.setMessage(message02);
        }
        return rejson;
    }

    /**
     * 增删改结果集验证
     * @param integer
     * @param message001
     * @param message02
     * @return
     */
    public Rejson cudVerification(Integer integer,String message001,String message02){
        rejson = new Rejson();
        if (integer != 0){
            rejson.setList(null);
            rejson.setBool(true);
            rejson.setStatus(200);
            rejson.setMessage(message001);
        }else {
            rejson.setList(null);
            rejson.setBool(false);
            rejson.setStatus(500);
            rejson.setMessage(message02);
        }
        return rejson;
    }

    /**
     * 分页查询结果集校验
     * @param rejson
     * @param pn
     * @return
     */
    public Rejson dataPagination(Rejson rejson,Integer pn){
        // 判断是否为空，（防止出现空指针）进行分页
        if (rejson.getList() != null) {
            PageHelper.startPage(pn, 7);
            PageInfo pageInfo = new PageInfo(rejson.getList(), 5);
            rejson.setData(pageInfo);
        }
        return rejson;
    }

}
