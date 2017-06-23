package com.pgh.business;

import com.pgh.bean.PicMsg;
import com.pgh.constants.MsgTypes;
import com.pgh.responsemsg.ResPonseTextMsg;
import com.pgh.xmlwriter.TextMsgWriter;

/**
 * @ClassName: PicMsgProcesssor
 * @Description: 图片消息业务处理类
 * @author zhutulang
 * @date 2016年1月13日
 * @version V1.0
 */
public class PicMsgProcesssor {

	/**
	 * <p>Title: process<／p>
	 * <p>Description: 处理图片消息<／p>
	 * @param picMsg
	 * @return
	 * @author zhutulang
	 * @version 1.0
	 */
	public static String process(PicMsg picMsg){
		String toUserName = picMsg.getFromUserName();
		String fromUserName = picMsg.getToUserName();
		String msgId = picMsg.getMsgId();
    	String content = "你发给我的是图片 /::)";
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
