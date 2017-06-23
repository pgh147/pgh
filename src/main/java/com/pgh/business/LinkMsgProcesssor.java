package com.pgh.business;

import com.pgh.bean.LinkMsg;
import com.pgh.constants.MsgTypes;
import com.pgh.responsemsg.ResPonseTextMsg;
import com.pgh.xmlwriter.TextMsgWriter;

/**
 * @ClassName: LinkMsgProcesssor
 * @Description: 链接消息业务处理类
 * @author zhutulang
 * @date 2016年1月13日
 * @version V1.0
 */
public class LinkMsgProcesssor {

	/**
	 * <p>Title: process<／p>
	 * <p>Description: 处理链接消息<／p>
	 * @param linkMsg
	 * @return
	 * @author zhutulang
	 * @version 1.0
	 */
	public static String process(LinkMsg linkMsg){
		String toUserName = linkMsg.getFromUserName();
		String fromUserName = linkMsg.getToUserName();
		String msgId = linkMsg.getMsgId();
    	String content = "你发给我的是链接消息，消息标题："+linkMsg.getTitle()+",\r\n消息描述："+linkMsg.getDescription();
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
