package com.project.vo;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class BoardVO {
	private int boardNum, writer, hit;
	private String title, contents, bdDel, nickname;
	private Date regDate;
	private MultipartFile imgFile;
	
	public BoardVO() {
		// TODO Auto-generated constructor stub
	}
	
	

	public BoardVO(int boardNum, int writer, int hit, String title, String contents, String bdDel, Date regDate,
			MultipartFile imgFile) {
		super();
		this.boardNum = boardNum;
		this.writer = writer;
		this.hit = hit;
		this.title = title;
		this.contents = contents;
		this.bdDel = bdDel;
		this.regDate = regDate;
		this.imgFile = imgFile;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getBoardNum() {
		return boardNum;
	}
	
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	public int getWriter() {
		return writer;
	}
	public void setWriter(int writer) {
		this.writer = writer;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	


	public MultipartFile getImgFile() {
		return imgFile;
	}



	public void setImgFile(MultipartFile imgFile) {
		this.imgFile = imgFile;
	}



	public String getBdDel() {
		return bdDel;
	}
	public void setBdDel(String bdDel) {
		this.bdDel = bdDel;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	
	@Override
	public String toString() {
		return "BoardVO [boardNum=" + boardNum + ", writer=" + writer + ", hit=" + hit + ", title=" + title
				+ ", contents=" + contents + ", imgFile=" + imgFile + ", bdDel=" + bdDel + ", regDate=" + regDate
				+ "]";
	}
}
