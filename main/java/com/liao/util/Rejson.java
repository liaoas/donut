package com.liao.util;

import java.util.List;
import java.util.Map;

/**
 * 结果集
 */
public class Rejson<T> {

    private Integer status;

    private List list;

    private String message;

    private Boolean bool;

    private Map map;

    private  T data;

    public Rejson() {

    }

    public Rejson(Integer status, List list, String message, Boolean bool, Map map, T data) {
        this.status = status;
        this.list = list;
        this.message = message;
        this.bool = bool;
        this.map = map;
        this.data = data;
    }



    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getBool() {
        return bool;
    }

    public void setBool(Boolean bool) {
        this.bool = bool;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Rejson{" +
                "status=" + status +
                ", list=" + list +
                ", message='" + message + '\'' +
                ", bool=" + bool +
                ", map=" + map +
                ", data=" + data +
                '}';
    }
}
