package com.dbnewyouth.sms.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xinfeng
 * @version 1.0
 * @Description
 * @date 2019/9/29 15:27
 */
@Data
public class AliyunSmsResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 状态码的描述。 **/
    @JSONField(name = "Message")
    private String message;

    /** 请求ID。 **/
    @JSONField(name = "RequestId")
    private String requestId;

    /** 请求状态码。 **/
    @JSONField(name = "Code")
    private String code;
}
