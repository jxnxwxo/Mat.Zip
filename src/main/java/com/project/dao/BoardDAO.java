package com.project.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.vo.BoardVO;


@Repository
public class BoardDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	public List<BoardVO> listArticles(){
		return sqlSession.selectList("mapper.board.selectAllBoard");
	}
		
	public void boardWrite(Map<String, Object> articleMap) {
		sqlSession.insert("mapper.board.insert", articleMap);
	}
	
	public BoardVO viewArticle(int boardNum) {
		return sqlSession.selectOne("mapper.board.selectArticle", boardNum);
	}
	
	public void updateHit(int boardNum) {
		sqlSession.update("mapper.board.updateHit", boardNum);
	}
	
	public int countArticle(String searchType, String keyword) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		data.put("searchType", searchType);
		data.put("keyword", keyword);
		
		return sqlSession.selectOne("mapper.board.countArticle", data);
	}
	
	public List<BoardVO> listArticlesPaging(int displayPost, int postNum, String searchType, String keyword) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		data.put("displayPost", displayPost);
		data.put("postNum", postNum);
		data.put("searchType", searchType);
		data.put("keyword", keyword);
		
		return sqlSession.selectList("mapper.board.selectAllBoardPaging", data);
	}
	
	
	public HashMap<String, Object> getByteImage(int boardNum){
		return sqlSession.selectOne("mapper.board.getByteImage", boardNum);
	}
	
	public void modify(BoardVO boardVO) {
		sqlSession.update("mapper.board.modify", boardVO);
	}
	
	public void delete(int boardNum) {
		sqlSession.update("mapper.board.delete", boardNum);
	}
	
	
}
