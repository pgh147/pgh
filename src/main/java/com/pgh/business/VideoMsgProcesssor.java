package com.pgh.business;

import com.pgh.bean.VideoMsg;
import com.pgh.constants.MsgTypes;
import com.pgh.responsemsg.ResPonseTextMsg;
import com.pgh.xmlwriter.TextMsgWriter;

/**
 * @ClassName: VideoMsgProcesssor
 * @Description: 视频消息业务处理类
 * @author zhutulang
 * @date 2016年1月13日
 * @version V1.0
 */
public class VideoMsgProcesssor {

	/**
	 * <p>Title: process<／p>
	 * <p>Description: 处理视频消息<／p>
	 * @param videoMsg
	 * @return
	 * @author zhutulang
	 * @version 1.0
	 */
	public static String process(VideoMsg videoMsg){
		String toUserName = videoMsg.getFromUserName();
		String fromUserName = videoMsg.getToUserName();
		String msgId = videoMsg.getMsgId();
    	String content = "你发给我的是视频 /::)";
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
