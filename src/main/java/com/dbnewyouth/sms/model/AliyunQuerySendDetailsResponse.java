package com.dbnewyouth.sms.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * @author xinfeng
 * @version 1.0
 * @Description
 * @date 2019/9/29 15:34
 */
@Data
public class AliyunQuerySendDetailsResponse extends AliyunSmsResponse {

    private static final long serialVersionUID = 1L;

    /** 短信发送总条数 **/
    @JSONField(name = "TotalCount")
    private Integer totalCount;

    /** 短信发送明细 **/
    @JSONField(name = "SmsSendDetailDTOs")
    private SmsSendDetailDTOs smsSendDetailDTOs;

    @Data
    public static class SmsSendDetailDTOs{
        @JSONField(name = "SmsSendDetailDTO")
        private List<SmsSendDetailDTO> SmsSendDetailDTO;
    }

    @Data
    public static class SmsSendDetailDTO{

        /** 短信内容 **/
        @JSONField(name = "Content")
        private String content;

        /** 运营商短信状态码 DELIVRD为成功 **/
        @JSONField(name = "ErrCode")
        private String errCode;

        /** 接收短信的手机号码 **/
        @JSONField(name = "PhoneNum")
        private String phoneNum;

        /** 短信接收日期和时间 **/
        @JSONField(name = "ReceiveDate")
        private String receiveDate;

        /** 短信发送日期和时间 **/
        @JSONField(name = "SendDate")
        private String sendDate;

        /** 短信发送状态[1：等待回执；2：发送失败；3：发送成功] **/
        @JSONField(name = "SendStatus")
        private Long sendStatus;

        /** 短信模板ID **/
        @JSONField(name = "TemplateCode")
        private String templateCode;
    }
}
