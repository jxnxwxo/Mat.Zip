package com.project.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.BoardDAO;
import com.project.vo.BoardVO;

@Service
public class BoardService {
	@Autowired
	BoardDAO boardDAO;
	
	public List<BoardVO> listArticles(){
		return boardDAO.listArticles();		
	}
	
	public void boardWrite(Map<String, Object> articleMap) {
		boardDAO.boardWrite(articleMap);
	}
	
	public BoardVO viewArticle(int boardNum) {
		return boardDAO.viewArticle(boardNum);
	}
	
	public void updateHit(int boardNum) {
		boardDAO.updateHit(boardNum);
	}
	
	public int countArticle(String searchType, String keyword) {
		return boardDAO.countArticle(searchType, keyword);
	}
	
	public List<BoardVO> listArticlesPaging(int displayPost, int postNum, String searchType, String keyword) {
		return boardDAO.listArticlesPaging(displayPost, postNum, searchType, keyword);
	}
	
	public HashMap<String, Object> getByteImage(int boardNum){
		//System.out.println(boardDAO.getByteImage(boardNum));
		HashMap<String, Object> map = boardDAO.getByteImage(boardNum);
		//System.out.println(map.get("imgFile"));
		return boardDAO.getByteImage(boardNum);
	}
	
	
	public void modify(BoardVO boardVO) {
		boardDAO.modify(boardVO);
	}
	
	public void delete(int boardNum) {
		boardDAO.delete(boardNum);
	}
}
