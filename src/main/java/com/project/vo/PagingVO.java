package com.project.vo;

public class PagingVO {

	// 게시물 전체갯수
	private int totalCnt;

	// 화면당 페이지 갯수
	private int listSize;

	// 현재 페이지
	private int currentPage;

	// 화면당 게시물 갯수
	private int postSize;

	// 전체 페이징 갯수
	private int pageCnt;

	// 시작페이지 번호
	private int startPage;

	// 마지막페이지 번호
	private int endPage;
	
	// 이전
	private int prev;
	
	// 다음
	private int next;

	public PagingVO() {
		this.listSize = 5;
		this.postSize = 5;
		this.currentPage = 1;
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	public int getListSize() {
		return listSize;
	}

	public void setListSize(int listSize) {
		this.listSize = listSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		
		if ( currentPage < 1 ) {
			this.currentPage = 1;
		} else {
			this.currentPage = currentPage;
		}
	}

	public int getPostSize() {
		return postSize;
	}

	public void setPostSize(int postSize) {
		this.postSize = postSize;
	}

	public int getPageCnt() {
		return pageCnt;
	}

	public void setPageCnt(int pageCnt) {
		this.pageCnt = pageCnt;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getPrev() {
		return prev;
	}

	public void setPrev(int prev) {
		if ( prev < 1 ) {
			this.prev = 1;
		} else {
			this.prev = prev;
		}
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		if (next > pageCnt) {
			next = pageCnt;
		} else {
			this.next = next;
		}
	}

	public void pageCal() {

		pageCnt = (int) Math.ceil((double) totalCnt / postSize);

		if (currentPage % 5 == 0) {
			startPage = (int)(currentPage / listSize)*listSize  + 1 - listSize;
			endPage = (int)(currentPage / listSize)*listSize;
		} else {
			startPage = currentPage - currentPage % listSize + 1;
			endPage = currentPage - currentPage % listSize + listSize;
		}
		

		if (endPage > pageCnt) endPage = pageCnt;
		
		setPrev(currentPage - 1);
		setNext(currentPage + 1);
		
	}

	@Override
	public String toString() {
		return "PagingVO [totalCnt=" + totalCnt + ", listSize=" + listSize + ", postSize=" + postSize + ", currentPage="
				+ currentPage + ", pageNum=" + pageCnt + ", startPage=" + startPage + ", endPage=" + endPage + "]";
	}
}
