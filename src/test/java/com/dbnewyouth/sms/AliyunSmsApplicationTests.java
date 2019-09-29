package com.dbnewyouth.sms;

import com.alibaba.fastjson.JSONObject;
import com.dbnewyouth.sms.model.AliyunQuerySendDetailsResponse;
import com.dbnewyouth.sms.model.AliyunSendSmsResponse;
import com.dbnewyouth.sms.util.SmsUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class AliyunSmsApplicationTests {

    @Test
    public void sendSms() {
        AliyunSendSmsResponse response = SmsUtil.sendSms("15000000000", "1234");
        Assertions.assertNotNull(response);
        Assertions.assertEquals("OK", response.getCode());
        System.out.println(JSONObject.toJSONString(response));
    }

    @Test
    public void sendBatchSms(){
        List<String> phoneNumberList = new ArrayList<>();
        phoneNumberList.add("15000000000");
        phoneNumberList.add("17000000000");

        List<Map<String,String>> templateParamList = new ArrayList<>();
        for (int i = 0; i < phoneNumberList.size(); i++) {
            String code = String.valueOf(new Random().nextInt(9999));
            Map<String, String> map = new HashMap<>();
            map.put("code",code);
            templateParamList.add(map);
        }

        String phoneNumberJson = JSONObject.toJSONString(phoneNumberList);
        String templateParamJson = JSONObject.toJSONString(templateParamList);

        AliyunSendSmsResponse response = SmsUtil.SendBatchSms(phoneNumberJson, templateParamJson);
        Assertions.assertNotNull(response);
        Assertions.assertEquals("OK", response.getCode());
        System.out.println(JSONObject.toJSONString(response));
    }

    @Test
    public void querySendDetails(){
        AliyunQuerySendDetailsResponse response = SmsUtil.querySendDetails("15000000000", "20190929", "10", "1", "144415469737274380^0");
        Assertions.assertNotNull(response);
        Assertions.assertEquals("OK", response.getCode());
        System.out.println(JSONObject.toJSONString(response));
    }
}
