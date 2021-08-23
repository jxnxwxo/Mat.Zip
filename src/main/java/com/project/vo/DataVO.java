package com.project.vo;

import java.util.List;

public class DataVO {

	// 음식점 단건
	private RestaurantVO rtVO;
	
	// 음식점 리스트
	private List<RestaurantVO> rtList;
	
	// 페이징
	private PagingVO paging;

	public RestaurantVO getRtVO() {
		return rtVO;
	}

	public void setRtVO(RestaurantVO rtVO) {
		this.rtVO = rtVO;
	}

	public List<RestaurantVO> getRtList() {
		return rtList;
	}

	public void setRtList(List<RestaurantVO> rtList) {
		this.rtList = rtList;
	}

	public PagingVO getPaging() {
		return paging;
	}

	public void setPaging(PagingVO paging) {
		this.paging = paging;
	}
}
