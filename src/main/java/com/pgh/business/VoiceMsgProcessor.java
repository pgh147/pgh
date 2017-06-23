package com.pgh.business;

import com.pgh.bean.VoiceMsg;
import com.pgh.constants.MsgTypes;
import com.pgh.responsemsg.ResPonseTextMsg;
import com.pgh.xmlwriter.TextMsgWriter;

/**
 * @ClassName: VoiceMsgProcessor
 * @Description: 语音消息业务处理类
 * @author zhutulang
 * @date 2016年1月13日
 * @version V1.0
 */
public class VoiceMsgProcessor {

	/**
	 * <p>Title: process<／p>
	 * <p>Description: 处理语言消息<／p>
	 * @param voiceMsg
	 * @return
	 * @author pgh
	 * @version 1.0
	 */
	public static String process(VoiceMsg voiceMsg){
		String toUserName = voiceMsg.getFromUserName();
		String fromUserName = voiceMsg.getToUserName();
		String getMsg = voiceMsg.getRecongnition();
		String msgId = voiceMsg.getMsgId();
    	String content = "你发给我的语音是："+getMsg;
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
