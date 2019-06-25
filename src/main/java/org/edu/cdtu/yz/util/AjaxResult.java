package org.edu.cdtu.yz.util;

//Ajax请求响应对象的类
public class AjaxResult {

    private boolean success = true;
    private String message = "操作成功!";
    private Object resultObj;//返回到前台对象
//    private int code=1;//layui:如果成功则默认返回 1 ，失败则返回自定义的code

    // TODO 现在的方式：
    //AjaxResult.me  -》成功
    //AjaxResult.me().setMessage()  -》成功
    //AjaxResult.me.setSuccess(false).setMessage("失败") -》失败
    public static AjaxResult me(){
        return new AjaxResult();
    }

/*  TODO 以前的方式：
    //成功
    public AjaxResult() { }
    //失败并且有提示
    public AjaxResult(String message) {
        this.success = false;
        this.message = message;
    }
    //成功的封装-------------------------------
    public AjaxResult(String msg){
        this.msg = msg;
    }
    //如果调用这个方法,则创建的是成功时的错误信息
    public AjaxResult(String msg,int code){
        this.success = false;
        this.msg = msg;
        this.code = code;
    }
    */

    public boolean isSuccess() {
        return success;
    }

    public AjaxResult setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public AjaxResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getResultObj(){
        return resultObj;
    }

    public AjaxResult setResultObj(Object resultObj){
        this.resultObj = resultObj;
        return this;
    }

}
