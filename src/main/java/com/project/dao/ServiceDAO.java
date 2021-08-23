package com.project.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.vo.RestaurantVO;

@Repository
public class ServiceDAO {

	@Autowired
	SqlSession sqlSession;
	
	
	// 음식점 리스트 목록 조회
	public List<? extends Object> searchList(RestaurantVO restaurantVO) {
		restaurantVO.setTemp((restaurantVO.getCurrentPage()-1)*restaurantVO.getPostSize());
		return sqlSession.selectList("search.searchRestaurantList", restaurantVO);
	}
	
	// 음식점 리슽트 목록 전체 갯수
	public int searchListTotalCnt(RestaurantVO restaurantVO) {
		
		return sqlSession.selectOne("search.searchRestaurantTotalCnt", restaurantVO);
	}
}
