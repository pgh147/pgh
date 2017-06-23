package com.pgh.requestbean;

/**
 * @ClassName: BatchGetMaterialRequest
 * @Description: 批量获取素材列表请求实体类
 * @author zhutulang
 * @date 2016年1月8日
 * @version V1.0
 */
public class BatchGetMaterialRequest {

	/**
	 * 素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news）
	 */
	private String type;
	/**
	 * 从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
	 */
	private int offset;
	/**
	 * 返回素材的数量，取值在1到20之间
	 */
	private int count;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
