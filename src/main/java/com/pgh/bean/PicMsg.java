package com.pgh.bean;

/**
 * @ClassName: PicMsg
 * @Description: 图片消息
 * @author zhutulang
 * @date 2016年1月6日
 * @version V1.0
 */
public class PicMsg extends BaseMsg {

	/**
	 * 图片链接
	 */
	private String picUrl;
	
	/**
	 * 图片消息媒体id，可以调用多媒体文件下载接口拉取数据
	 */
	private String mediaId;

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
}
