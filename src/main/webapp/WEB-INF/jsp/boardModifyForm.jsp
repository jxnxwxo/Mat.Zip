<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
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
	<link rel="stylesheet" href="../css/writeform.css" >
	
	<title>미쉐린 가이드:서울 x 맛.ZIP - 글 수정하기</title>
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
	
	<script type="text/javascript">
		function backToList() {
			location.href = "../boardListPaging?num=1";
		}
	
		var cnt = 1;
		function fn_addFile() {
			$("#d_file")
					.append("<br>" + "<input type='file' name='file"+cnt+"' />");
			cnt++;
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
		<h1 style="text-align:center; color:white;">글 수정하기</h1>
		<form id="articleForm" name="articleForm" method="post"
			action="/modify" enctype="multipart/form-data">
			<input type="hidden" name="writer" value="${article.writer}">
			<table class="table table-border">
				<tr>
					<td>글번호 :</td>
					<td colspan=2><input type="text" size="50" maxlength="100" name="boardNum" value="${article.boardNum}" readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td>작성자 :</td>
					<td colspan=2><input type="text" size="50" maxlength="100" value="${article.nickname}" disabled="disabled" /></td>
				</tr>
				<tr>
					<td>글제목 :</td>
					<td colspan="2"><input type="text" size="50" maxlength="300" name="title" value="${article.title}" /></td>
				</tr>
				<tr>
					<td valign="top"><br>글내용 :</td>
					<td colspan=2><textarea name="contents" rows="10" cols="53" maxlength="4000">${article.contents}</textarea></td>
				</tr>
				<tr>
					<td>파일 첨부 :</td>
					<td>
						<form action="../modify" enctype="multipart/form-data" method="post">
							<input type="file" name="imgFile" disabled="disabled" />
						</form>
					</td>
				</tr>
			</table>
				<button type="button" class="btn" id="writeBtn" onclick="goModify(this.form)">저장</button>
				<button type="button" class="btn" id="backBtn" onClick="backToList()">목록보기</button>
		</form>
	</div>
	</div>
	</div>
	</div>
	</section>
	<script>
		function goModify(form) {
			var boardNum = form.boardNum.value;
			var title = form.title.value;
			var contents = form.contents.value;

			form.submit();
		}
	</script>

	<!-- ======= Footer ======= -->
	<footer id="footer">
		<div class="container">
			<div class="copyright">
				&copy; Copyright <strong><span>눈이 침침하조</span></strong>. All Rights
				Reserved
			</div>
		</div>
	</footer>

	<script src="http://lab.alexcican.com/set_cookies/cookie.js" type="text/javascript"></script>
</body>
</html>