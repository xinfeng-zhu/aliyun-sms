package com.dbnewyouth.sms.util;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.dbnewyouth.sms.model.AliyunQuerySendDetailsResponse;
import com.dbnewyouth.sms.model.AliyunSendSmsResponse;

/**
 * @author xinfeng
 * @version 1.0
 * @Description 阿里云发送短信工具类
 * @date 2019/9/26 17:53
 */
public class SmsUtil {

    /** 区域，只有default和cn-hangzhou **/
    public static String regionId = "cn-hangzhou";
    /** 阿里云控制台的accessKey **/
    public static String accessKey = "";
    /** 阿里云控制台的secret **/
    public static String secret = "";
    /** 域名，固定值 **/
    public static String domain = "dysmsapi.aliyuncs.com";
    /** 阿里云短信版本，不能随便写！！ **/
    public static String version = "2017-05-25";

    private static DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKey, secret);

    /**
     * 发送短信
     * @param tel 手机号
     * @param code 验证码
     * @return 返回响应
     * @date 2019/9/29 17:53
     */
    public static AliyunSendSmsResponse sendSms(String tel, String code) {
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(domain);
        request.setVersion(version);
        // 需要调用方法值，单一发送固定值
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", tel);
        // 阿里云短信服务中申请的签名
        request.putQueryParameter("SignName", "dbnewyouth");
        // 阿里云短信服务中申请的短信模板代码
        request.putQueryParameter("TemplateCode", "SMS_144151933");
        // 短信模板中的变量使用Json方式替换
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        request.putQueryParameter("TemplateParam", jsonObject.toJSONString());
        try {
            CommonResponse response = client.getCommonResponse(request);
            int httpStatus = response.getHttpStatus();
            if (httpStatus == 200) {
                return JSONObject.parseObject(response.getData(), AliyunSendSmsResponse.class);
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 群发短信
     * @param phoneNumberJson json格式的手机号列表
     * @param templateParamJson json格式的短信模板
     * @return 返回响应
     * @date 2019/9/29 17:55
     */
    public static AliyunSendSmsResponse SendBatchSms(String phoneNumberJson, String templateParamJson) {
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(domain);
        request.setVersion(version);
        request.setAction("SendBatchSms");
        request.putQueryParameter("RegionId", regionId);
        request.putQueryParameter("PhoneNumberJson", phoneNumberJson);
        // 群发时签名数量必须与PhoneNumberJson数量对应，哪怕重复使用一个
        request.putQueryParameter("SignNameJson", "[\"dbnewyouth\",\"dbnewyouth\"]");
        request.putQueryParameter("TemplateCode", "SMS_144151933");
        request.putQueryParameter("TemplateParamJson", templateParamJson);
        try {
            CommonResponse response = client.getCommonResponse(request);
            int httpStatus = response.getHttpStatus();
            if (httpStatus == 200) {
                // 这里转换类还需要改
                return JSONObject.parseObject(response.getData(), AliyunSendSmsResponse.class);
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询发送详情
     *
     * @param tel 手机号码
     * @param date 查询日期 格式：20190929
     * @param pageSize 每页显示数量
     * @param currentPage 当前页
     * @param bizId 发送成功后产生的回执ID
     * @return 返回发送详情
     * @date 2019/9/29 17:56
     */
    public static AliyunQuerySendDetailsResponse querySendDetails(String tel, String date, String pageSize, String currentPage, String bizId) {
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(domain);
        request.setVersion(version);
        request.setAction("QuerySendDetails");
        request.putQueryParameter("RegionId", regionId);
        request.putQueryParameter("PhoneNumber", tel);
        request.putQueryParameter("SendDate", date);
        request.putQueryParameter("PageSize", pageSize);
        request.putQueryParameter("CurrentPage", currentPage);
        request.putQueryParameter("BizId", bizId);
        try {
            CommonResponse response = client.getCommonResponse(request);
            int httpStatus = response.getHttpStatus();
            if (httpStatus == 200) {
                // 这里转换类还需要改
                return JSONObject.parseObject(response.getData(), AliyunQuerySendDetailsResponse.class);
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return null;
    }
}
