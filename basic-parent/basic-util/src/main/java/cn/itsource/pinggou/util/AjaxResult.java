package cn.itsource.pinggou.util;

/**
 * @author zb
 * @version 1.0
 * @classname AjaxResult
 * @description 封装ajax请求处理结果工具类
 * @date 2019/5/11
 */
public class AjaxResult {
    /**是否处理成功*/
    private boolean success = true;
    /**提示信息*/
    private String message = "操作成功";
    /**错误码*/
    private String errorCode;
    /**封装返回数据*/
    private Object data;

    /**
     * @author zb
     * @description 表示操作成功
     * @date 2019/5/11
     * @name me
     * @param
     * @return cn.itsource.pinggou.util.AjaxResult
     */
    public static AjaxResult me(){
        return new AjaxResult();
    }

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

    public String getErrorCode() {
        return errorCode;
    }

    public AjaxResult setErrorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public Object getData() {
        return data;
    }

    public AjaxResult setData(Object data) {
        this.data = data;
        return this;
    }
}
