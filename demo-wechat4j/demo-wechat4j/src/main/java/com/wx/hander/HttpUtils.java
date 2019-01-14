package com.wx.hander;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.alibaba.fastjson.JSONObject;

public class HttpUtils {
	 private static final String UTF8 = "utf-8";
	public static JSONObject excuteHttp(String url) {
		try {
			// 使用Post方式，组装参数
			HttpGet httpGet = new HttpGet(url);

			// 创建httpclient链接，并执行
			CloseableHttpClient httpclient = HttpClients.createDefault();
			CloseableHttpResponse response = httpclient.execute(httpGet);

			// 对于返回实体进行解析
			HttpEntity entity = response.getEntity();
			InputStream returnStream = entity.getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(returnStream, UTF8));
			StringBuilder result = new StringBuilder();
			String str = null;
			while ((str = reader.readLine()) != null) {
				result.append(str).append("\n");
			}
			JSONObject jsonObject = JSONObject.parseObject(result.toString());
			return jsonObject;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
