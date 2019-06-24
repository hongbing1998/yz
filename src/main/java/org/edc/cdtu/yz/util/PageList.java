package org.edc.cdtu.yz.util;

import java.util.ArrayList;
import java.util.List;

//分页工具：
// 1、easyui只需2个属性，total(总数),datas（分页数据）就能实现分页
// 2、layui 需4个属性，code,msg,count(总数),data（分页数据）实现分页
public class PageList<T> {

    private int code=0;
    private String msg;

    private long count;//总条数
    private List<T> data = new ArrayList<>();//装前台当前页的数据

    //提供有参构造方法，方便测试
    public PageList(long count, List<T> data) {
        this.count = count;
        this.data = data;
    }
    //除了有参构造方法，还需要提供一个无参构造方法
    public PageList() { }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PageList{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", count=" + count +
                ", data=" + data +
                '}';
    }
}
