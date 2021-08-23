package com.project.controller;


import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.project.service.BoardService;
import com.project.vo.BoardVO;
import com.project.vo.PageVO;

@Controller
public class BoardController {
   	
   @Autowired
   BoardService boardService;
   
   
   
   //페이징 모든 글 보기
   @RequestMapping(value = "/boardListPaging",
                  method = {RequestMethod.GET},
                  produces = "application/text; charset=utf8")
      public ModelAndView boardListPaging(@RequestParam("num")int num,
            @RequestParam(value="searchType", required=false, defaultValue="title") String searchType, 
            @RequestParam(value="keyword", required=false, defaultValue="") String keyword,
            HttpServletRequest request) {
		
		   	PageVO pageVO = new PageVO();
	        pageVO.setNum(num);
	        pageVO.setCount(boardService.countArticle(searchType, keyword));
	        
	        //검색 타입과 검색어
	        pageVO.setSearchType(searchType);
	        pageVO.setKeyword(keyword);
	        
	        ModelAndView mav = new ModelAndView("boardPaging");
	        List<BoardVO> articlesList = boardService.listArticlesPaging(pageVO.getPostNum() * num - 5, pageVO.getPostNum() * num, searchType, keyword);
	        System.out.println(articlesList.size());
	        
	        mav.addObject("articlesList", articlesList);
        
	        mav.addObject("page", pageVO);
	        mav.addObject("select", num);
        
        
         return mav;
      }
   
   
   //글쓰기 화면 얻기
   @RequestMapping(value = "boardWriteForm",
               method = {RequestMethod.GET},
               produces = "application/text; charset=utf8")
   public String boardWriteForm(HttpServletRequest request) {
      return "boardWriteForm";
   }
   
   
	/*
	 * //로그인 화면 얻기
	 * 
	 * @RequestMapping(value = "loginForm", method = {RequestMethod.GET}, produces =
	 * "application/text; charset=utf8") public String loginForm(HttpServletRequest
	 * request) { return "loginForm";
	 * 
	 * }
	 */
   
   
   //글쓰기 처리
   @RequestMapping(value = "boardWrite",
               method = {RequestMethod.POST},
               produces = "application/text; charset=utf8")
   public RedirectView boardWrite(BoardVO boardVO) {
	   try {
			HashMap<String, Object> data = new HashMap<String, Object>();
			data.put("title", boardVO.getTitle());
			data.put("contents", boardVO.getContents());
			data.put("writer", boardVO.getWriter());
			if(boardVO.getImgFile() != null) {
				data.put("imgFile", boardVO.getImgFile().getBytes());
			}
			boardService.boardWrite(data);
			System.out.println("게시물이 작성되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
         
         
//         HttpSession session = multipartRequest.getSession();
//         MemberVO memberVO = null;
//         if(session==null || (memberVO = (MemberVO)session.getAttribute("member")) == null) {
//            return new RedirectView("loginForm");
//         }
//         
//         String id = memberVO.getId();
//         System.out.println("parentNO:"+articleMap.get("parentNO"));
//                  if(articleMap.get("parentNO")==null) {
//                     articleMap.put("parentNO", 0);
//                  }
//         articleMap.put("id", id);

      
      
      return new RedirectView("boardListPaging?num=1");

   }
   
   
   //글 내용 보기
   @RequestMapping(value = "viewArticle", method = RequestMethod.GET)
   public ModelAndView viewArticle(@RequestParam("board_num") int boardNum, HttpServletRequest request,
         HttpServletResponse response) throws Exception {   
      System.out.println(boardNum+"번 글 보기");
      boardService.updateHit(boardNum);
      BoardVO boardVO = boardService.viewArticle(boardNum);
      ModelAndView mav = new ModelAndView();
      mav.setViewName("viewArticle");
      mav.addObject("article", boardVO);
      return mav;
   }
   
   

   
   //이미지파일 받아오기
   @RequestMapping(value="/getByteImage")
   public ResponseEntity<byte[]> getByteImage(@RequestParam("board_num") int boardNum, HttpServletRequest request,
	         HttpServletResponse response) throws SQLException, UnsupportedEncodingException {
	   HashMap<String, Object> imageMap = boardService.getByteImage(boardNum);
	   if (imageMap != null) {
		   Blob blob = (java.sql.Blob) imageMap.get("imgFile");
		   byte[] imageContent = blob.getBytes(1, (int) blob.length());
		   //System.out.println(imageContent.length);
		   final HttpHeaders headers = new HttpHeaders();
		   headers.setContentType(MediaType.IMAGE_JPEG);
		   return new ResponseEntity<byte[]> (imageContent, headers, HttpStatus.OK);
	   }
	   return null;
   }
   
   //글 수정
   
   @RequestMapping(value="/modify", method={RequestMethod.GET})
   public ModelAndView getModify(@RequestParam("board_num") int boardNum, HttpServletRequest request,
	         HttpServletResponse response) {
	   System.out.println(boardNum+"번 글 수정하기");
	   BoardVO boardVO = boardService.viewArticle(boardNum);
	   ModelAndView mav = new ModelAndView();
	   mav.setViewName("boardModifyForm");
	   mav.addObject("article", boardVO);
	   return mav;
   }
   
   @RequestMapping(value="/modify", method= {RequestMethod.POST},
           produces = "application/text; charset=utf8")
   public RedirectView postModify(BoardVO boardVO) {
	   System.out.println("넘어온 객체"+boardVO.toString());
	   boardService.modify(boardVO);
	   return new RedirectView("viewArticle?board_num=" + boardVO.getBoardNum());
   }
   
   
   //게시물 삭제
   @RequestMapping(value="delete", method= {RequestMethod.GET},
		   produces = "application/text; charset=utf8")
   public RedirectView getDelete(@RequestParam("board_num") int boardNum) {
	   boardService.delete(boardNum);
	   System.out.println(boardNum + "번 글 삭제");
	   return new RedirectView("boardListPaging?num=1");
   }
   
   
   //파일 업로드하기
//   private String upload(MultipartHttpServletRequest multipartRequest) throws Exception{
//      String imgFile= null;
//      Iterator<String> fileNames = multipartRequest.getFileNames();
//      
//      while(fileNames.hasNext()){
//         String fileName = fileNames.next();
//         MultipartFile mFile = multipartRequest.getFile(fileName);
//         imgFile=mFile.getOriginalFilename();
//         File file = new File("c:\\temp\\"+ fileName);
//         if(mFile.getSize()!=0){ //File Null Check
//            if(! file.exists()){ //경로상에 파일이 존재하지 않을 경우
//               if(file.getParentFile().mkdirs()){ //경로에 해당하는 디렉토리들을 생성
//                     file.createNewFile(); //이후 파일 생성
//               }
//            }
//            mFile.transferTo(new File("c:\\temp\\"+imgFile)); //임시로 저장된 multipartFile을 실제 파일로 전송
//         }
//      }
//      return imgFile;
//   }

}