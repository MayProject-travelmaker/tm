<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="root" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Travel Maker</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="/resources/assets/favicon.ico" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="${root}/resources/css/styles.css" rel="stylesheet" />
<!-- Bootstrap core JS-->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="${root}/resources/js/scripts.js"></script>
<script type="text/javascript" src="${root}/resources/js/board.js?v=<%=System.currentTimeMillis() %>"></script>

</head>
<body>
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
						<li class="nav-item"><a class="nav-link"
							href="${root}/member/mypage.do">마이페이지</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</nav>
	<!-- Page Content-->
	<div class="container">
		<div class="row">
			<div class="col-lg-3">
				<h1 class="my-4">카테고리</h1>
				<div class="list-group">
					<a class="list-group-item" href="${root}/board/accompanylist.do">👫 동행 게시판</a> 
					<a class="list-group-item" href="${root}/board/accompanyreview.do">✏ 동행 후기</a> 
					<a class="list-group-item" href="${root}/board/recommendpath.do">🚩 추천 여행 경로</a> 
					<a class="list-group-item" href="${root}/board/travelreview.do">✈ 여행지 후기</a>
				</div>
			</div>
			<div class="col-lg-9">
				<div class="panel-body">
					<br> <br>
					<div class="panel-heading" id="14">✈ 여행지 후기</div>
					<br>
					<div>

						<!-- 게시글검색 -->
						<form action="accompanylist.do" method="post">
							<div class="form-group row float-right" style="padding-right: 10px">
								<div style="padding-right: 10px">
									<select class="form-control" name="searchType" id="searchType">
										<option value="title" <c:out value="${searchType eq 'title'?'selected':''}"/>>제목</option>
										<option value="postId" <c:out value="${searchType eq 'postId'?'selected':''}"/>>작성자</option>
										<option value="tc" <c:out value="${searchType eq 'tc'?'selected':''}"/>>제목 + 내용</option>
									</select>
								</div>
								<div style="padding-right: 10px">
									<input type="text" class="form-control" name="keyword" id="keyword" style="width: 100" value="${keyword}">
								</div>
								<div>
									<button class="btn btn-primary" name="btnSearch" id="btnSearch">검색</button>
								</div>
							</div>
						</form>

						<!-- 게시글리스트 -->
						<table class="table table-body">
							<thead>
								<tr class="text-center">
									<th width="60px">글번호</th>
									<th width="230px">제목</th>
									<th width="80px">작성자</th>
									<th width="90px">작성일</th>
									<th width="60px">조회수</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${boardList == null }">
									<tr><td colspan="5" align="center">조회된 결과가 없습니다.</td></tr>
								</c:if>
								<c:forEach var="boardDto" items="${boardList}">
									<tr>
										<c:if test="${boardDto.isNotice == 1}">
											<td class="text-center">공지</td>
										</c:if>
										<c:if test="${boardDto.isNotice == 0 && boardDto.isPopular == 0}">
											<td class="text-center">${boardDto.boardNo}</td>
										</c:if>
										<c:if test="${boardDto.isPopular == 1}">
											<td class="text-center">인기글</td>
										</c:if>
										<td>
											<c:if test="${boardDto.area == null}">	<!-- 공지글일경우 -->
												<a href="${root}/board/read.do?boardNo=${boardDto.boardNo}">${boardDto.title}</a></c:if>
											<c:if test="${boardDto.area != null}">	<!-- 공지글아닐경우 -->
												<a href="${root}/board/read.do?boardNo=${boardDto.boardNo}">[${boardDto.area}] ${boardDto.title}</a></c:if>
										</td>
										<td>${boardDto.postId}</td>
										<td class="text-center"><fmt:formatDate value="${boardDto.writeDate}" pattern="yyyy-MM-dd" /></td>
										<td class="text-center">${boardDto.readCnt}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>

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
										href="${root}/board/travelreview.do?pageNumber=${startPage-pageBlock}&searchType=${searchType}&keyword=${keyword}">◀</a>
									</c:if>
								</li>
								
								<c:forEach var="i" begin="${startPage}" end="${endPage}">
									<li class="page-item">
										<a class="page-link"href="${root}/board/travelreview.do?pageNumber=${i}&searchType=${searchType}&keyword=${keyword}">${i}</a>
									</li>
								</c:forEach>
								
								<li class="page-item">
									<c:if test="${endPage < pageCount}">
										<a class="page-link" 
										href="${root}/board/travelreview.do?pageNumber=${startPage+pageBlock}&searchType=${searchType}&keyword=${keyword}">▶</a>
									</c:if>
								</li>
							</ul>
						</div>
							<button class="btn btn-primary" type="button"
								onclick="location.href='write.do?boardCode=14'" style="float: right;">글작성</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<br>
	<br>

	<!-- Footer-->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">김지은 김동인 이송하 윤희영 황민재</p>
		</div>
	</footer>

</body>
</html>
