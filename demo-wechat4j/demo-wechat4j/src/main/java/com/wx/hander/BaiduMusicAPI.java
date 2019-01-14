package com.wx.hander;
import java.util.Date;

import org.weixin4j.model.message.Music;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


public class BaiduMusicAPI{
    
   
    private static String url = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=qianqian&version=2.1.0&method=baidu.ting.search.catalogSug&format=json&query={QWORD}";
    private static String song_info = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=qianqian&version=2.1.0&method=baidu.ting.song.getInfos&format=json&songid={SONGID}&ts={TS}&e=JoN56kTXnnbEpd9MVczkYJCSx%2FE1mkLx%2BPMIkTcOEu4%3D&nw=2&ucf=1&res=1";
    private static String appid="wx0985b6dbaeeff28c";
    private static String appsecret="d3ba2d045ffc578abd0e9d23f8c93404";
   
	public Music query(String query) throws Exception {
		//使用Post方式，组装参数
    	url=url.replace("{QWORD}", query);
        JSONObject jsonObject=HttpUtils.excuteHttp(url);
        JSONArray jsonArray=(JSONArray) jsonObject.get("song");
        
        JSONObject song=(JSONObject) jsonArray.get(0);
        String songid=song.getString("songid");
        System.out.println("song=="+song);
        //使用Post方式，组装参数
        song_info=song_info.replace("{SONGID}", songid).replace("{TS}", String.valueOf(new Date().getTime()));
        jsonObject=HttpUtils.excuteHttp(song_info);
        System.out.println("jsonObject=="+jsonObject);
        String fileLink=jsonObject.getJSONObject("songurl").getJSONArray("url").getJSONObject(0).getString("file_link");
        Music music=new Music();
        music.setDescription(song.getString("artistname"));
		music.setHQMusicUrl(fileLink);
		music.setMusicUrl(fileLink);
		music.setTitle(song.getString("songname"));
		String access_token = CommonUtil.getToken(appid, appsecret).getAccess_token();
		String type = "thumb";
		String ThumbMediaId=UploadLinshiSucai.getMediaIdFromUrl(jsonObject.getJSONObject("songinfo").getString("artist_480_800"), type,access_token);
		System.out.println(ThumbMediaId);
		music.setThumbMediaId(ThumbMediaId);
        return music;
	}
    
    public static void main(String[] args) {
		try {
			System.out.println(JSONObject.toJSONString(new BaiduMusicAPI().query("新娘不是我")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
