<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
  request.setCharacterEncoding("UTF-8");
%>  
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/js/my.js"></script>
	
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-material-design/4.0.2/bootstrap-material-design.css">
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
	<link rel="stylesheet" href="../css/board.css" >
	
	<!-- Template Main CSS File -->
    <link rel="stylesheet" href="../css/style.css">
	<title>미쉐린 가이드:서울 x 맛.ZIP - 공지사항</title>
	<meta content="" name="description">
	<meta content="" name="keywords">
	
	<!-- Favicons -->
	<link href="/img/favicon.png" rel="icon">
	
	<!-- Google Fonts -->
	<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,600,600i,700,700i" rel="stylesheet">
	
	<!-- Vendor CSS Files -->
	<link href="/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="/vendor/icofont/icofont.min.css" rel="stylesheet">
	
	<!-- Template Main CSS File -->
	<link href="/css/style.css" rel="stylesheet">
	
	<script>
		function fn_articleForm(articleForm){
			var login=$.cookie('logined');
			if(login){
			  location.href=articleForm;
			}else{
			  alert("로그인 후 글쓰기가 가능합니다.")
			  location.href="/html/login.html";
			}
		}
	</script>
</head>
<body>
	  <!-- Header -->
	<header id="header" class="fixed-top">
	   <div class="container-fluid">
	      <div class="logo float-left">
	         <a href="/index.html" class="logo mr-auto"><img src="/img/matzip_blk.png" alt=""></a>
	     </div>
	     <div class="row justify-content-center float-right">
	       <div class="d-flex align-items-center">
	         <span id="msgDiv"><a href="html/login.html" class="btn scrollto">로그인</a></span>
	       </div>
	     </div>
	   </div>
	</header>
    <section id="hero">
    <div class="hero-container">
    <div class="container">
    <div class="row height d-flex justify-content-center align-items-center">
	    <div class="board_wrap">
		<h1 style="text-align:center; color:white;">공지사항</h1>
		<table class="table" style="align:center; border:0;  width:100%;">
		  <tr height="10" align="center" bgcolor="lightgray">
		     <td width="10%">글번호</td>
		     <td >제목</td>
		     <td >작성자</td>              
		     <td >작성일</td>
		     <td >조회수</td>
		  </tr>
		<c:choose>
			<c:when test="${articlesList ==null }" >
			    <tr  height="10">
			      <td colspan="4">
			         <p align="center">
			            <b><span style="font-size:9pt;">등록된 글이 없습니다.</span></b>
			         </p>
			      </td>  
			    </tr>
			</c:when>
			<c:when test="${articlesList !=null }" >
			    <c:forEach  var="article" items="${articlesList }" varStatus="articleNum" >
			     <tr align="center">
					<td width="5%">${article.boardNum}</td>
					<td align='left'  width="35%">
				  		<span style="padding-right:30px"></span>
				   		<a class='cls1' href="../viewArticle?board_num=${article.boardNum}">${article.title }</a>
				  	</td>
					<td width="10%">${article.nickname }</td>
				 	<td  width="30%"><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${article.regDate}"/></td>
				 	<td>${article.hit}</td> 
				 </tr>
			   	</c:forEach>
		     </c:when>
		    </c:choose>
	<!-- <a  class="cls1"  href="#"><p class="cls2">글쓰기</p></a> -->
		<c:if test="${page.prev}">
			<span>[ <a href="../boardListPaging?num=${page.startPageNum - 1}${page.searchTypeKeyword}">이전</a> ]</span>
		</c:if>
		
		<tr height="15px" align="center">
		<td colspan="5">
		<c:forEach begin="${page.startPageNum}" end="${page.endPageNum}" var="num">
			<span>
				<c:if test="${select != num}">
					<a href="../boardListPaging?num=${num}${page.searchTypeKeyword}">${num}</a>
				</c:if>
				<c:if test="${select == num}">
					<font color="black" ><i>[${num}]</i></font>
				</c:if>
			</span>
		</c:forEach>
		<c:if test="${page.next}">
			<span>[ <a href="../boardListPaging?num=${page.endPageNum + 1}${page.searchTypeKeyword}">다음</a> ]</span>
		</c:if>
		</td>
		</tr>		
		</table>
	<div id="searchBar">
		<select id="option" name="searchType" style="background-color:white">
			<option value="title" <c:if test="${page.searchType eq 'title'}">selected</c:if>>제목</option>
			<option value="contents" <c:if test="${page.searchType eq 'contents'}">selected</c:if>>내용</option>
			<option value="title_contents" <c:if test="${page.searchType eq 'title_contents'}">selected</c:if>>제목+내용</option>
			<option value="writer" <c:if test="${page.searchType eq 'writer'}">selected</c:if>>작성자</option>
		</select>
		<input id="inputBox" type="text" name="keyword" value="${page.keyword}"/>
		<button type="button" class="btn" id="searchBtn">검색</button>
		<button type="button" class="btn" id="writeBtn" onclick="javascript:fn_articleForm('../boardWriteForm')">글 쓰기</button>
	</div>
	</div>
	</div>
	</div>
	</div>
    </section>
	<!-- ======= Footer ======= -->
	<footer id="footer">
		<div class="container">
			<div class="copyright">
				&copy; Copyright <strong><span>눈이 침침하조</span></strong>. All Rights Reserved
			</div>
		</div>
	</footer>

	<script type="text/javascript">
		document.getElementById("searchBtn").onclick = function(){
			
			let searchType = document.getElementsByName("searchType")[0].value;
			let keyword = document.getElementsByName("keyword")[0].value;
			
			location.href = "../boardListPaging?num=1" + "&searchType=" + searchType + "&keyword=" + keyword;
		};
	</script>
	<script src="http://lab.alexcican.com/set_cookies/cookie.js" type="text/javascript" ></script>
</body>
</html>
