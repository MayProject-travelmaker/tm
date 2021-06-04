<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="root" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<meta name="viewport"
		content="width=device-width, initial-scale=1, shrink-to-fit=no" />
	<meta name="description" content="" />
	<meta name="author" content="" />
	<title>Travel Maker</title>
	<!-- Favicon-->
	<link rel="icon" type="image/x-icon"
		href="/resources/assets/favicon.ico" />
	<!-- Core theme CSS (includes Bootstrap)-->
	<link href="${root}/resources/css/styles.css" rel="stylesheet" />
	
	<!-- Bootstrap core JS-->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="${root}/resources/js/scripts.js"></script>
	
	<script src="${root}/resources/js/mypage/mypage.js"></script>
	</head>
<body>
<!-- 마이페이지 - 내가 쓴 댓글  -->
	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container">
			<a class="navbar-brand" href="${root}">Travel Maker</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<c:if test="${memberLevel!=null}">
						<li class="nav-item active"><a class="nav-link"
							href="${root}/member/logout.do"> 로그아웃 <span class="sr-only">(current)</span>
						</a></li>
						<li class="nav-item active"><a class="nav-link"
							href="${root}/member/mypage.do">마이페이지</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</nav>
	
	
	<!-- Page Content-->
	<div class="container">
		<div class="row">
			<div class="col">
				<div class="panel-body">
					<br> <br>
					<div class="panel-heading" id="2">💬 내가 쓴 댓글</div>
					<br>
					<div>
						<c:choose>
							<c:when test="${not empty myReplyList}">
								<!-- 게시글검색 -->
								<div class="form-group row float-right"
									style="padding-right: 10px">
									<div style="padding-right: 10px">
										<select class="form-control" name="searchType" id="searchType">
											<option value="title">댓글</option>
											<option value="postId">원문 작성자</option>
										</select>
									</div>
									<div style="padding-right: 10px">
										<input type="text" class="form-control" name="keyword"
											id="keyword" style="width: 100">
									</div>
									<div>
										<button class="btn btn-primary" name="btnSearch" id="btnSearch">검색</button>
									</div>
								</div>
		
								<!-- 게시글리스트 -->
								<table class="table table-body">
									<thead>
										<tr style="text-align: center;">
											<th>댓글</th>
											<th></th>
											<th>작성일</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="boardDto" items="${myReplyList}">
											<tr style="text-align: center;">
												<td><a href="${root}/board/read.do?boardNo=${boardDto.boardNo}">${boardDto.content}</a></td>
												<td><a href="${root}/board/read.do?boardNo=${boardDto.boardNo}">▶원문보기</a></td>
												<td><fmt:formatDate value="${boardDto.writeDate}" pattern="yyyy-MM-dd" /></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</c:when>
							
							<c:otherwise>
								<div align="center">
									<h5>작성한 댓글이 없습니다</h5>
								</div>
							</c:otherwise>
							</c:choose>

						<!-- 페이징처리 -->
						<div>
							<fmt:parseNumber var="pageCount" value="${count/boardSize+(count%boardSize==0?0:1)}" integerOnly="true"></fmt:parseNumber>
							<c:set var="pageBlock" value="${10}" />
							<fmt:parseNumber var="result" value="${(currentPage-1)/pageBlock}" integerOnly="true"></fmt:parseNumber>
							<c:set var="startPage" value="${result*pageBlock+1}"></c:set>
							<c:set var="endPage" value="${startPage+pageBlock-1}"></c:set>
							<c:if test="${endPage > pageCount}">
								<c:set var="endPage" value="${pageCount}" />
							</c:if>
							
							<ul class="pagination"  style="justify-content: center">
								<li class="page-item">
									<c:if test="${startPage > pageBlock}">
											<a class="page-link" 
											href="${root}/mypage/myReply.do?pageNumber=${startPage-pageBlock}" style="text-decoration: none">◀</a>
									</c:if>
								</li>
								
								<c:forEach var="i" begin="${startPage}" end="${endPage}">
										<li class="page-item">
											<a class="page-link"
											href="${root}/mypage/myReply.do?pageNumber=${i}" style="text-decoration: none">${i}</a>
										</li>
								</c:forEach>
								
								<li class="page-item">
									<c:if test="${endPage < pageCount}">
										<a class="page-link" 
										href="${root}/mypage/myReply.do?pageNumber=${startPage+pageBlock}" style="text-decoration: none">▶</a>
									</c:if>
								</li>
							</ul>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
	<br><br><br><br><br><br><br><br>
	<!-- Footer-->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">김지은 김동인 이송하 윤희영 황민재</p>
		</div>
	</footer>
	
</body>
</html>