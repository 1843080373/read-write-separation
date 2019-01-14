package com.wx.hander;

import org.weixin4j.model.base.Token;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

public class CommonUtil {
  
    // 凭证获取（GET）
    public final static String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    /**
     * 获取接口访问凭证
     * 
     * @param appid 凭证
     * @param appsecret 密钥
     * @return
     */
    public static Token getToken(String appid, String appsecret) {
        Token token = null;
        String requestUrl = token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
        // 发起GET请求获取凭证
        JSONObject jsonObject = HttpUtils.excuteHttp(requestUrl);
        if (null != jsonObject) {
            try {
                token = new Token();
                token.setAccess_token(jsonObject.getString("access_token"));
                token.setExpires_in(jsonObject.getInteger("expires_in"));
            } catch (JSONException e) {
                token = null;
            }
        }
        return token;
    }
}