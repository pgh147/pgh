package com.pgh.bean;

/**
 * @ClassName: VoiceMsg
 * @Description: 语音消息
 * @author zhutulang
 * @date 2016年1月6日
 * @version V1.0
 */
public class VoiceMsg extends BaseMsg {

	/**
	 * 语音消息媒体id，可以调用多媒体文件下载接口拉取数据
	 */
	private String mediaId;
	/**
	 * 语音格式，如amr，speex等
	 */
	private String format;
	
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	/**
	 * 语音识别结果
	 */
	private String recongnition;

	public String getRecongnition() {
		return recongnition;
	}
	public void setRecongnition(String recongnition) {
		this.recongnition = recongnition;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
}
