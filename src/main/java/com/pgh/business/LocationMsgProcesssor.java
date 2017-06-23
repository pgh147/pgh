package com.pgh.business;

import com.pgh.bean.LocationMsg;
import com.pgh.constants.MsgTypes;
import com.pgh.responsemsg.ResPonseTextMsg;
import com.pgh.xmlwriter.TextMsgWriter;

/**
 * @ClassName: LocationMsgProcesssor
 * @Description: 地理消息业务处理类
 * @author zhutulang
 * @date 2016年1月13日
 * @version V1.0
 */
public class LocationMsgProcesssor {

	/**
	 * <p>Title: process<／p>
	 * <p>Description: 处理地理消息<／p>
	 * @param locationMsg
	 * @return
	 * @author zhutulang
	 * @version 1.0
	 */
	public static String process(LocationMsg locationMsg){
		String toUserName = locationMsg.getFromUserName();
		String fromUserName = locationMsg.getToUserName();
		String msgId = locationMsg.getMsgId();
    	String content = "你发给我的地理位置，经度："+locationMsg.getLocationY()+",纬度："+locationMsg.getLocationX()
    			+"\r\n 位置信息:"+locationMsg.getLabel();
		String createTime = String.valueOf(System.currentTimeMillis());
    	ResPonseTextMsg responseMsg = 
    			new ResPonseTextMsg(toUserName, fromUserName, 
    					createTime, MsgTypes.TEXT, msgId, content);
        //将responseMsg 转换成xml字符串返回
        try {
			return  TextMsgWriter.getXmlString(responseMsg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
	}
}
