package com.wx.hander;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.weixin4j.model.message.Image;
import org.weixin4j.model.message.OutputMessage;
import org.weixin4j.model.message.normal.ImageInputMessage;
import org.weixin4j.model.message.normal.LinkInputMessage;
import org.weixin4j.model.message.normal.LocationInputMessage;
import org.weixin4j.model.message.normal.ShortVideoInputMessage;
import org.weixin4j.model.message.normal.TextInputMessage;
import org.weixin4j.model.message.normal.VideoInputMessage;
import org.weixin4j.model.message.normal.VoiceInputMessage;
import org.weixin4j.model.message.output.ImageOutputMessage;
import org.weixin4j.model.message.output.MusicOutputMessage;
import org.weixin4j.model.message.output.TextOutputMessage;
import org.weixin4j.spi.INormalMessageHandler;

import com.alibaba.fastjson.JSONObject;

/**
 * 自定义普通消息处理器
 *
 * @author yangqisheng
 */
@Component
public class AtsNormalMessageHandler implements INormalMessageHandler {

	private static final String APIKEY = "f0d99d4e9fc44ec096afdf510c575437";
	protected final Logger LOG = LoggerFactory.getLogger(AtsNormalMessageHandler.class);
	private BaiduTranslateAPI baiduTranslateAPI=new BaiduTranslateAPI();
	private BaiduMusicAPI baiduMusicAPI=new BaiduMusicAPI();
	@Override
	public OutputMessage textTypeMsg(TextInputMessage msg) {
		TextOutputMessage out = new TextOutputMessage();
		try {
			LOG.debug("文本消息：" + msg.getContent());
			String msgContent=msg.getContent();
			if(msgContent.startsWith("翻译")) {
				String res=baiduTranslateAPI.translate(msgContent.substring(2),"auto","en");
				out.setContent(res);
				return out;
			}
			if(msgContent.startsWith("音乐")) {
				MusicOutputMessage musicOutputMessage = new MusicOutputMessage(baiduMusicAPI.query(msgContent.substring(2)));
				return musicOutputMessage;
			}
			String INFO = URLEncoder.encode(msg.getContent(), "utf-8");
			String getURL = "http://www.tuling123.com/openapi/api?key=" + APIKEY + "&info=" + INFO;
			URL getUrl = new URL(getURL);
			HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
			connection.connect();

			// 取得输入流，并使用Reader读取
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			StringBuffer sb = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			reader.close();
			// 断开连接
			connection.disconnect();
			JSONObject jsonObject = JSONObject.parseObject(sb.toString());
			if (100000 == jsonObject.getInteger("code")) {
				out.setContent(jsonObject.getString("text"));
				return out;
			}
			out.setContent("服务器错误");
			return out;
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.setContent("服务器错误");
		return out;
	}

	
	@Override
	public ImageOutputMessage imageTypeMsg(ImageInputMessage msg) {
		ImageOutputMessage imageOutputMessage=new ImageOutputMessage();
		Image image = new Image();
		image.setMediaId("KvAPmz_-dIGPLXTEzP8NxvV4V97xHh84Ppu9uOzG0nZGtQx9lTvymuGMOA_R-ErO");
		imageOutputMessage.setImage(image);
		return imageOutputMessage;
	}

	@Override
	public OutputMessage voiceTypeMsg(VoiceInputMessage msg) {
		TextOutputMessage out = new TextOutputMessage();
		out.setContent("你的消息已经收到！");
		return out;
	}

	@Override
	public OutputMessage videoTypeMsg(VideoInputMessage msg) {
		TextOutputMessage out = new TextOutputMessage();
		out.setContent("你的消息已经收到！");
		return out;
	}

	@Override
	public OutputMessage shortvideoTypeMsg(ShortVideoInputMessage msg) {
		TextOutputMessage out = new TextOutputMessage();
		out.setContent("你的消息已经收到！");
		return out;
	}

	@Override
	public OutputMessage locationTypeMsg(LocationInputMessage msg) {
		TextOutputMessage out = new TextOutputMessage();
		out.setContent("你的消息已经收到！");
		return out;
	}

	@Override
	public OutputMessage linkTypeMsg(LinkInputMessage msg) {
		TextOutputMessage out = new TextOutputMessage();
		out.setContent("你的消息已经收到！");
		return out;
	}
	

}