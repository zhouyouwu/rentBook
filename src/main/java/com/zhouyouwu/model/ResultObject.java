package com.zhouyouwu.model;

/**
 * @author Administrator
 */
public class ResultObject {
    private int code;
    private String message;
    private Object result;

    private static final int SUCCESS_CODE = 0;
    private static final int FAIL_CODE = 1001;
    public static ResultObject ok(){
        ResultObject resultObject = new ResultObject();
        resultObject.setCode(SUCCESS_CODE);

        return resultObject;
    }

    public static ResultObject ok(String message){
        ResultObject resultObject = new ResultObject();
        resultObject.setCode(SUCCESS_CODE);
        resultObject.setMessage(message);

        return resultObject;
    }

    public static ResultObject ok(Object result){
        ResultObject resultObject = new ResultObject();
        resultObject.setCode(SUCCESS_CODE);
        resultObject.setResult(result);

        return resultObject;
    }

    public static ResultObject ok(String message, Object result){
        ResultObject resultObject = new ResultObject();
        resultObject.setCode(SUCCESS_CODE);
        resultObject.setMessage(message);
        resultObject.setResult(result);

        return resultObject;
    }

    public static ResultObject fail(){
        ResultObject resultObject = new ResultObject();
        resultObject.setCode(FAIL_CODE);

        return resultObject;
    }

    public static ResultObject fail(String message){
        ResultObject resultObject = new ResultObject();
        resultObject.setCode(FAIL_CODE);
        resultObject.setMessage(message);

        return resultObject;
    }

    public static ResultObject fail(Object result){
        ResultObject resultObject = new ResultObject();
        resultObject.setCode(FAIL_CODE);
        resultObject.setResult(result);

        return resultObject;
    }

    public static ResultObject fail(String message, Object result){
        ResultObject resultObject = new ResultObject();
        resultObject.setCode(FAIL_CODE);
        resultObject.setMessage(message);
        resultObject.setResult(result);

        return resultObject;
    }



    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
