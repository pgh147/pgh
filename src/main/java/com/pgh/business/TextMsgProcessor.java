package com.pgh.business;

import com.pgh.bean.TextMsg;
import com.pgh.constants.MsgTypes;
import com.pgh.responsemsg.ResPonseTextMsg;
import com.pgh.xmlwriter.TextMsgWriter;

/**
 * @ClassName: TextMsgProcessor
 * @Description: 文本消息业务处理类
 * @author zhutulang
 * @date 2016年1月12日
 * @version V1.0
 */
public class TextMsgProcessor {

	/**
	 * <p>Title: process<／p>
	 * <p>Description:处理文本消息 <／p>
	 * @param textMsg
	 * @return
	 * @author zhutulang
	 * @version 1.0
	 */
	public static String process(TextMsg textMsg){
		String toUserName = textMsg.getFromUserName();
		String fromUserName = textMsg.getToUserName();
		String getMsg = textMsg.getContent();
		String msgId = textMsg.getMsgId();
    	String content = null;
    	if(getMsg.equals("1")){
    		content = "you got first!";
    	}else{
    		content = "you发给me的是文本消息："+getMsg;
    	}
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
