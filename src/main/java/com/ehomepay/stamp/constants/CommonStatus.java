package com.ehomepay.stamp.constants;


/**
 * 
* @ClassName: CommonStatus 
* @Description: 接口状态描述
* @author xiaoman 
* @date 2015年5月28日 下午11:17:39 
*
 */
public enum CommonStatus{

    UNKNOWN(-1, ""),

    SUCCESS(200,"成功"),
    
    TOCKEN_NOT_CORRECT(302,"token错误"),
    USER_NOT_EXIST(303,"用户不存在"),
    USER_UPDATE_TOKEN_ERROR(304,"用户更新token失败"),
    LOGIN_ERROR(306,"登录失败，请重试"),
    ACCOUNT_OR_PASSWORD_NOT_CORRECT(307,"用户名或者密码错误，请重试"),
    USER_NOT_AUTHORITY(310,"用户权限不足"),
    ACCOUNT_AUTHORITY_EXIST(311,"该用户账号已经分配过权限"),
    AUDIT_NOT_EXIST(320,"审核单不存在"),
    AUDIT_HAVE_FINISH(321,"审核单已完成"),
    AUDIT_OTHER(322,"别人正在审核"),
    AUDIT_MIS_ERROR(330,"MIS审核失败"),
    AUDIT_MIS_HYSTRIX_ERROR(332,"MIS审核熔断"),
    PARAM_ERROR(400,"参数异常,请检查"),
    SERVER_ERROR(500,"服务器内部错误"),
    
    TOCKEN_NOT_EXIST(40010,"用户未登录"),
    USER_TOKEN_EXPIRED(40011,"用户token过期"),
    ;
    private int    value;
    private String text; 

    private static final KV<Integer, CommonStatus> lookUp = new KV<Integer, CommonStatus>();

    static {
        for (CommonStatus status : CommonStatus.values()) {
            lookUp.put(status.getValue(), status);
        }
        lookUp.putDefault(UNKNOWN);
    }

    private CommonStatus(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public int getValue() {
        return this.value;
    }

    public String getText() {
        return this.text;
    }
    
    public static CommonStatus of(Integer value) {
        return lookUp.get(value);
    }

}
