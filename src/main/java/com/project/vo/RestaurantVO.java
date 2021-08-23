package com.project.vo;

public class RestaurantVO extends PagingVO{

	// 음식점 번호
	private int rtNum;
	
	// 음식점 이름
	private String rtName;
	
	// 음식점 주소
	private String rtAddress;
	
	// 음식점 번호 
	private String rtTel;
	
	// 음식점 최저가
	private int rtLowprice;
	
	// 음식점 최고가
	private int rtHighprice;
	
	// 음식점 카테고리
	private String rtCategory;
	
	// 음식점 정보
	private String rtInfo;
	
	// 음식점 등급
	private String rtGrade;
	
	// 음식점 예약
	private String rtRsv;
	
	// 음식점 주차
	private String rtValet;
	
	// 음식점 위도
	private String rtX;
	
	// 음식점 경도
	private String rtY;
	
	// 음식점 홈페이지 
	private String rtHp;
	
	// 음식점 비고
	private String rtEtc;
	
	// 임시변수
	private int temp;

	public int getTemp() {
		return temp;
	}

	public void setTemp(int temp) {
		this.temp = temp;
	}

	public int getRtNum() {
		return rtNum;
	}

	public void setRtNum(int rtNum) {
		this.rtNum = rtNum;
	}

	public String getRtName() {
		return rtName;
	}

	public void setRtName(String rtName) {
		this.rtName = rtName;
	}

	public String getRtAddress() {
		return rtAddress;
	}

	public void setRtAddress(String rtAddress) {
		this.rtAddress = rtAddress;
	}

	public String getRtTel() {
		return rtTel;
	}

	public void setRtTel(String rtTel) {
		this.rtTel = rtTel;
	}

	public int getRtLowprice() {
		return rtLowprice;
	}

	public void setRtLowprice(int rtLowprice) {
		this.rtLowprice = rtLowprice;
	}

	public int getRtHighprice() {
		return rtHighprice;
	}

	public void setRtHighprice(int rtHighprice) {
		this.rtHighprice = rtHighprice;
	}

	public String getRtCategory() {
		return rtCategory;
	}

	public void setRtCategory(String rtCategory) {
		this.rtCategory = rtCategory;
	}

	public String getRtInfo() {
		return rtInfo;
	}

	public void setRtInfo(String rtInfo) {
		this.rtInfo = rtInfo;
	}

	public String getRtGrade() {
		return rtGrade;
	}

	public void setRtGrade(String rtGrade) {
		this.rtGrade = rtGrade;
	}

	public String getRtRsv() {
		return rtRsv;
	}

	public void setRtRsv(String rtRsv) {
		this.rtRsv = rtRsv;
	}

	public String getRtValet() {
		return rtValet;
	}

	public void setRtValet(String rtValet) {
		this.rtValet = rtValet;
	}

	public String getRtX() {
		return rtX;
	}

	public void setRtX(String rtX) {
		this.rtX = rtX;
	}

	public String getRtY() {
		return rtY;
	}

	public void setRtY(String rtY) {
		this.rtY = rtY;
	}

	public String getRtHp() {
		return rtHp;
	}

	public void setRtHp(String rtHp) {
		this.rtHp = rtHp;
	}

	public String getRtEtc() {
		return rtEtc;
	}

	public void setRtEtc(String rtEtc) {
		this.rtEtc = rtEtc;
	}

	@Override
	public String toString() {
		return "RestaurantVO [rtNum=" + rtNum + ", rtName=" + rtName + ", rtAddress=" + rtAddress + ", rtTel=" + rtTel
				+ ", rtLowprice=" + rtLowprice + ", rtHighprice=" + rtHighprice + ", rtCategory=" + rtCategory
				+ ", rtInfo=" + rtInfo + ", rtGrade=" + rtGrade + ", rtRsv=" + rtRsv + ", rtValet=" + rtValet + ", rtX="
				+ rtX + ", rtY=" + rtY + ", rtHp=" + rtHp + ", rtEtc=" + rtEtc + "]";
	}
}
