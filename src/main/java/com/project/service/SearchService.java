package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.ServiceDAO;
import com.project.vo.DataVO;
import com.project.vo.PagingVO;
import com.project.vo.RestaurantVO;

@Service
public class SearchService {

	@Autowired
	ServiceDAO serviceDAO;
	
	public DataVO searchList(RestaurantVO restaurantVO, PagingVO pagingVO) throws Exception{
		
		DataVO returnVO = new DataVO();
		
		System.out.println(pagingVO.toString());
		
		// 페이징 적용
		PagingVO paging = new PagingVO();
		paging.setTotalCnt(serviceDAO.searchListTotalCnt(restaurantVO));
		if ( pagingVO.getCurrentPage() != 0) {
			restaurantVO.setCurrentPage(pagingVO.getCurrentPage());
			restaurantVO.pageCal();
			paging.setCurrentPage(pagingVO.getCurrentPage());
		}
		paging.pageCal();
		returnVO.setPaging(paging);
		
		List<RestaurantVO> list = (List<RestaurantVO>) serviceDAO.searchList(restaurantVO);
		
		returnVO.setRtList(list);
		return returnVO;
	}
}
