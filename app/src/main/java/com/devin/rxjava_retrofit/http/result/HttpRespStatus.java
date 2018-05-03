package com.devin.rxjava_retrofit.http.result;

/**
 * Description:
 * Company:
 * Email:bjxm2013@163.com
 * Created by Devin Sun on 2017/4/2.
 */
public interface HttpRespStatus {


    int NETWORK_ERROR = 0;
    String NETWORK_ERROR_MSG = "网络异常，请重试";

    int DEFAULT_ERROR_CODE = -1024;
    String DEFAULT_ERROR_MSG = "数据处理异常，请重试";


    int HTTP_RESP_SUCCESS_CODE = 200;
    String HTTP_RESP_SUCCESS_MSG = "请求成功";


    int BAD_REQUEST_RESPONSE_CODE = 400;
    String BAD_REQUEST_RESPONSE_MSG = "参数错误";

    int UNAUTHORIZED_RESPONSE_CODE = 401;
    String UNAUTHORIZED_RESPONSE_MSG = "用户权限非法";

    int FORBIDDEN_RESPONSE_CODE = 403;
    String FORBIDDEN_RESPONSE_MSG = "禁止访问";

    int NOT_FOUND_RESPONSE_CODE = 404;
    String NOT_FOUND_RESPONSE_MSG = "地址未找到";

    int METHOD_NOT_ALLOWED_RESPONSE_CODE = 405;
    String METHOD_NOT_ALLOWED_RESPONSE_MSG = "请求方式错误";

    int REQUEST_TIMEOUT_RESPONSE_CODE = 408;
    String EQUEST_TIMEOUT_RESPONSE_MSG = "请求超时";

    int INTERNAL_SERVER_ERROR_RESPONSE_CODE = 500;
    String INTERNAL_SERVER_ERROR_RESPONSE_MSG = "内部服务器错误";

    int BAD_GATEWAY_RESPONSE_CODE = 502;
    String BAD_GATEWAY_RESPONSE_MSG = "网关错误";

    int SERVICE_UNAVAILABLE_RESPONSE_CODE = 503;
    String SERVICE_UNAVAILABLE_RESPONSE_MSG = "临时的服务器维护";

    int GATEWAY_TIMEOUT_RESPONSE_CODE = 504;
    String GATEWAY_TIMEOUT_RESPONSE_MSG = "网关超时";

}
