package com.pgh.bean;

/**
 * @ClassName: LinkMsg
 * @Description: 链接消息
 * @author zhutulang
 * @date 2016年1月6日
 * @version V1.0
 */
public class LinkMsg extends BaseMsg {

	/**
	 * 消息标题
	 */
	private String title;
	
	/**
	 * 消息描述
	 */
	private String description;
	
	/**
	 * 消息链接
	 */
	private String url;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
