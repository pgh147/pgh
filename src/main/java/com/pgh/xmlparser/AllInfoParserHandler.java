package com.pgh.xmlparser;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.pgh.bean.AllInfo;
import com.pgh.bean.BaseMsg;
import com.pgh.bean.LinkMsg;
import com.pgh.bean.LocationMsg;
import com.pgh.bean.PicMsg;
import com.pgh.bean.ShortVideoMsg;
import com.pgh.bean.TextMsg;
import com.pgh.bean.VideoMsg;
import com.pgh.bean.VoiceMsg;
import com.pgh.constants.MsgTypes;

/**
 * @ClassName: AllInfoParserHandler
 * @Description: 微信发来的消息解析类
 * @author pgh
 * @date 2017年6月6日
 * @version V1.0
 */
public class AllInfoParserHandler extends DefaultHandler {

	
	 protected AllInfo allInfo;
	 //用来存放每次遍历后的元素名称(节点名称)  
	 protected String tagName;
	 //用来保存每次解析的数据
	 protected String data;
	
	 @Override  
	 public void startDocument() throws SAXException {  
		
	 }
	 
	 @Override  
	 public void startElement(String uri, String localName, String qName,  
	            Attributes attributes) throws SAXException {  
		 if(qName.equals("xml")){
			 allInfo = new AllInfo();
		 }
		 tagName = qName;
	 }
	 
	 @Override  
	 public void endElement(String uri, String localName, String qName)  
	 throws SAXException { 
		 tagName = null;
		 data = null;
	 }
	 
	 @Override  
	 public void endDocument() throws SAXException {
		 
	 }
	 
	 @Override  
	 public void characters(char[] ch, int start, int length) throws SAXException {  
		 if(tagName != null){
			 data = new String(ch,start,length);
			 if(tagName.equals("ToUserName")){
				 allInfo.setToUserName(data);
			 }else if(tagName.equals("FromUserName")){
				 allInfo.setFromUserName(data);
			 }else if(tagName.equals("CreateTime")){
				 allInfo.setCreateTime(data);
			 }else if(tagName.equals("MsgType")){
				 allInfo.setMsgType(data);
			 }else if(tagName.equals("MsgId")){
				 allInfo.setMsgId(data);
			 }else if(tagName.equals("Content")){
				 allInfo.setContent(data);
			 }else if(tagName.equals("PicUrl")){
				 allInfo.setPicUrl(data);
			 }else if(tagName.equals("MediaId")){
				 allInfo.setMediaId(data);
			 }else if(tagName.equals("Format")){
				 allInfo.setFormat(data);
			 }else if(tagName.equals("Recognition")){
				 allInfo.setRecongnition(data);
			 }else if(tagName.equals("ThumbMediaId")){
				 allInfo.setThumbMediaId(data);
			 }else if(tagName.equals("Location_X")){
				 allInfo.setLocationX(data);
			 }else if(tagName.equals("Location_Y")){
				 allInfo.setLocationY(data);
			 }else if(tagName.equals("Scale")){
				 allInfo.setScale(data);
			 }else if(tagName.equals("Label")){
				 allInfo.setLabel(data);
			 }else if(tagName.equals("Title")){
				 allInfo.setTitle(data);
			 }else if(tagName.equals("Description")){
				 allInfo.setDescription(data);
			 }else if(tagName.equals("Url")){
				 allInfo.setUrl(data);
			 }
		 }
	 }

	/**
	 * <p>Title: getMsg<／p>
	 * <p>Description: 解析返回Msg，可能是文本、图片、语音等消息<／p>
	 * @param is
	 * @return
	 * @author zhutulang
	 * @version 1.0
	 */
	 public BaseMsg getMsg(InputStream is) {
		try {
			SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
			saxParser.parse(is, this);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String msgType = allInfo.getMsgType();
		BaseMsg baseMsg = null;
		if(MsgTypes.TEXT.equals(msgType)){
			baseMsg = new TextMsg();
			((TextMsg)baseMsg).setContent(allInfo.getContent()); 
		}else if(MsgTypes.IMAGE.equals(msgType)){
			baseMsg = new PicMsg();
			((PicMsg)baseMsg).setPicUrl(allInfo.getPicUrl());
			((PicMsg)baseMsg).setMediaId(allInfo.getMediaId());
		}else if(MsgTypes.VOICE.equals(msgType)){
			baseMsg = new VoiceMsg();
			((VoiceMsg)baseMsg).setMediaId(allInfo.getMediaId());
			((VoiceMsg)baseMsg).setFormat(allInfo.getFormat());
			((VoiceMsg)baseMsg).setRecongnition(allInfo.getRecongnition());
		}else if(MsgTypes.VIDEO.equals(msgType)){
			baseMsg = new VideoMsg();
			((VideoMsg)baseMsg).setMediaId(allInfo.getMediaId());
			((VideoMsg)baseMsg).setThumbMediaId(allInfo.getThumbMediaId());
		}else if(MsgTypes.SHORTVIDEO.equals(msgType)){
			baseMsg = new ShortVideoMsg();
			((ShortVideoMsg)baseMsg).setMediaId(allInfo.getMediaId());
			((ShortVideoMsg)baseMsg).setThumbMediaId(allInfo.getThumbMediaId());
		}else if(MsgTypes.LOCATION.equals(msgType)){
			baseMsg = new LocationMsg();
			((LocationMsg)baseMsg).setLocationX(allInfo.getLocationX());
			((LocationMsg)baseMsg).setLocationY(allInfo.getLocationY());
			((LocationMsg)baseMsg).setScale(allInfo.getScale());
			((LocationMsg)baseMsg).setLabel(allInfo.getLabel());	
		}else if(MsgTypes.LINK.equals(msgType)){
			baseMsg = new LinkMsg();
			((LinkMsg)baseMsg).setTitle(allInfo.getTitle());
			((LinkMsg)baseMsg).setDescription(allInfo.getDescription());
			((LinkMsg)baseMsg).setUrl(allInfo.getUrl());
		}
		
		if(baseMsg != null){
			//公共信息
			baseMsg.setCreateTime(allInfo.getCreateTime());
			baseMsg.setFromUserName(allInfo.getFromUserName());
			baseMsg.setMsgId(allInfo.getMsgId());
			baseMsg.setMsgType(allInfo.getMsgType());
			baseMsg.setToUserName(allInfo.getToUserName());
		}
		return baseMsg;
	} 
}

