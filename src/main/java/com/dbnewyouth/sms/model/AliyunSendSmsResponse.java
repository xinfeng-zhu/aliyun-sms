package com.dbnewyouth.sms.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author xinfeng
 * @version 1.0
 * @Description
 * @date 2019/9/26 18:17
 */
@Data
public class AliyunSendSmsResponse extends AliyunSmsResponse {

    private static final long serialVersionUID = 1L;

    /** 发送回执ID，可根据该ID在接口QuerySendDetails中查询具体的发送状态。 **/
    @JSONField(name = "BizId")
    private String bizId;
}
