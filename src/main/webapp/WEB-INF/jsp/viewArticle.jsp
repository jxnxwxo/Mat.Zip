<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%-- 
<c:set var="article"  value="${articleMap.article}"  />
<c:set var="imageFileList"  value="${articleMap.imageFileList}"  />

 --%>
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
	<link rel="stylesheet" type="text/css" href="../css/writeform.css">
	
	<title>미쉐린 가이드:서울 x 맛.ZIP - 글 내용 보기</title>
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

	<style>
		#tr_file_upload {
			display: none;
		}
		
		#tr_btn_modify {
			display: none;
		}
	</style>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>

	<script type="text/javascript">
		function backToList() {
			location.href = "../boardListPaging?num=1";
		}
	
		function fn_enable(obj) {
			document.getElementById("i_title").disabled = false;
			document.getElementById("i_contents").disabled = false;
			document.getElementById("i_imgFile").disabled = false;
			document.getElementById("tr_btn_modify").style.display = "block";
			document.getElementById("tr_file_upload").style.display = "block";
			document.getElementById("tr_btn").style.display = "none";
		}
	
		function fn_modify_article(obj) {
			obj.action = "${contextPath}/board/modArticle.do";
			obj.submit();
		}
	
		function fn_remove_article(url, board_num) {
			var form = document.createElement("form");
			form.setAttribute("method", "post");
			form.setAttribute("action", url);
			var articleNOInput = document.createElement("input");
			articleNOInput.setAttribute("type", "hidden");
			articleNOInput.setAttribute("name", "boardNum");
			articleNOInput.setAttribute("value", boardNum);
	
			form.appendChild(articleNOInput);
			document.body.appendChild(form);
			form.submit();
	
		}
	
		function fn_reply_form(url, parentNO) {
			var form = document.createElement("form");
			form.setAttribute("method", "post");
			form.setAttribute("action", url);
			var parentNOInput = document.createElement("input");
			parentNOInput.setAttribute("type", "hidden");
			parentNOInput.setAttribute("name", "parentNO");
			parentNOInput.setAttribute("value", parentNO);
	
			form.appendChild(parentNOInput);
			document.body.appendChild(form);
			form.submit();
		}
	
		function readURL(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();
				reader.onload = function(e) {
					$('#preview').attr('src', e.target.result);
				}
				reader.readAsDataURL(input.files[0]);
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
	         <span id="msgDiv">
	         <a href="html/login.html" class="btn scrollto">로그인</a></span>
	       </div>
	     </div>
	   </div>
	</header>
	<!-- End Header -->
	<section id="hero">
    <div class="hero-container">
    <div class="container">    
    <div class="row height d-flex justify-content-center align-items-center">
	<div class="write_wrap">
		<h1 style="text-align:center; color:white;">글 내용 보기</h1>
		<form name="frmArticle" method="post" action="${contextPath}" enctype="multipart/form-data">
			<table border=0 align="center" class="table table-condensed">
				<tr>
					<td width=150 align="center" bgcolor=lightgray>번호</td>
					<td>
						<input type="text" value="${article.boardNum }" disabled />
						<input type="hidden" name="articleNO" value="${article.boardNum}"/>
					</td>
				</tr>
				<tr>
					<td width="150" align="center" bgcolor="lightgray">작성자</td>
					<td><input type=text value="${article.nickname }" name="writer" disabled /></td>
				</tr>
				<tr>
					<td width="150" align="center" bgcolor="lightgray">제목</td>
					<td><input type=text value="${article.title }" name="title" id="i_title" disabled /></td>
				</tr>
				<tr>
					<td width="150" align="center" bgcolor="lightgray">내용</td>
					<td><textarea rows="10" cols="60" name="content" id="i_contents" disabled />${article.contents }</textarea></td>
				</tr>
				<tr>
					<td width="150" align="center" bgcolor="lightgray">이미지</td>
					<td><img alt="이미지없음" src="/getByteImage?board_num=${article.boardNum}" width="200px" height="150px" /></td>
				</tr>
				<tr>
					<td width="150" align="center" bgcolor="lightgray">작성일</td>
					<td><input type=text
						value="<fmt:formatDate value="${article.regDate}" />" disabled />
					</td>
				</tr>
				<tr>
					<td width="150" align="center" bgcolor="lightgray">조회수</td>
					<td><input type=text value="${article.hit}" /></td>
				</tr>
				<tr id="tr_btn_modify" align="center">
					<td colspan="2"><input type=button value="수정반영하기" onClick="fn_modify_article(frmArticle)" class="btn btn-success">
						<input type=button value="취소" onClick="backToList(frmArticle)">
					</td>
				</tr>
			</table>
		</form>
		<div id="btn-group">
			<button type="button" id="writeBtn" onClick="location.href='../modify?board_num=${article.boardNum}'">수정하기</button>
			<button type="button" id="writeBtn" onClick="location.href='../delete?board_num=${article.boardNum}'">삭제하기</button>
			<button type="button" id="backBtn" onClick="backToList()">목록보기</button>
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
				&copy; Copyright <strong><span>눈이 침침하조</span></strong>. All Rights
				Reserved
			</div>
		</div>
	</footer>

	<script src="http://lab.alexcican.com/set_cookies/cookie.js"
		type="text/javascript"></script>
</body>
</html>
