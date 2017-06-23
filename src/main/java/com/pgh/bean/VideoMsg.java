package com.pgh.bean;

/**
 * @ClassName: VedioMsg
 * @Description: 视频消息
 * @author zhutulang
 * @date 2016年1月6日
 * @version V1.0
 */
public class VideoMsg extends BaseMsg {

	/**
	 * 视频消息媒体id，可以调用多媒体文件下载接口拉取数据
	 */
	private String mediaId;
	/**
	 * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据
	 */
	private String thumbMediaId;

	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public String getThumbMediaId() {
		return thumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}	
}
