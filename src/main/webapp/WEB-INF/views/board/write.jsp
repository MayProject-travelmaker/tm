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
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="${root}/resources/js/scripts.js"></script>

<!-- 지도 -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d22f00b438fdc58950b8771f7c3989ef&libraries=services"></script>
<link href="${root}/resources/css/styles_map.css" rel="stylesheet" />

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
					<% int boardCode = Integer.parseInt(request.getParameter("boardCode")); %>
					<c:if test="<%= boardCode == 11%>"><div class="panel-heading">👫 동행 게시판</div></c:if>
					<c:if test="<%= boardCode == 12%>"><div class="panel-heading">✏ 동행 후기</div></c:if>
					<c:if test="<%= boardCode == 13%>"><div class="panel-heading">🚩 추천 여행 경로</div></c:if>
					<c:if test="<%= boardCode == 14%>"><div class="panel-heading">✈ 여행지 후기</div></c:if>
					
					<form action="${root}/board/writeOk.do" method="post" enctype="multipart/form-data">
						<div class="table table-responsive">
							<table class="table">
								<tr style="visibility: hidden;">
									<td>게시판코드</td>
									<td><input type="text" name="boardCode" value="<%= boardCode %>"></input></td>
								</tr>
								<c:if test="${memberLevel == 1}">
									<tr>
										<td class="border-right">공지여부</td>
										<td><input type="checkbox" name="notice" value="1" checked> 공지글</td>
									</tr>
								</c:if>
								<tr>
									<td class="border-right">지역</td>
									<td><select class="form-control" name="area">
											<option value="">선택</option>
											<option value="전국">전국</option>
											<option value="서울">서울</option>
											<option value="경기">경기</option>
											<option value="인천">인천</option>
											<option value="부산">부산</option>
											<option value="대전">대전</option>
											<option value="세종">세종</option>
											<option value="충남">충남</option>
											<option value="충북">충북</option>
											<option value="광주">광주</option>
											<option value="제주">제주</option>
											<option value="강원">강원</option>
											<option value="울산">울산</option>
											<option value="경남">경남</option>
											<option value="경북">경북</option>
											<option value="대구">대구</option>
											<option value="전남">전남</option>
											<option value="전북">전북</option>
									</select></td>
								</tr>
								<tr>
									<td class="border-right">작성자</td>
									<td><input type="text" class="form-control" name="postId" value="${sessionScope.id}" readonly></td>
								</tr>
								<tr>
									<td class="border-right">제목</td>
									<td><input type="text" class="form-control" name="title"></td>
								</tr>
								<tr>
									<td class="border-right">글내용</td>
									<td><textarea rows="10" cols="30" name="content" class="form-control"></textarea></td>
								</tr>
								<tr>
									<td class="border-right">첨부파일</td>
									<td><input type="file" name="file" accept="image/*"></td>
								</tr>
								<tr>
									<td class="border-right">지도</td>
									<td>
										<div class="map_wrap">
											<div id="map" style="width: 100%; height: 100%; position: relative; overflow: hidden;"></div>
											<div id="menu_wrap" class="bg_white">
												<div class="option">
													<div>
														키워드 : 
														<input type="text" value="이태원 맛집" id="keyword" size="15"> 
														<input type="button" id="searchBtn" onclick="searchPlaces()" value="검색">
													</div>
												</div>
												<hr>
												<ul id="placesList"></ul>
												<div id="pagination"></div>
											</div>
										</div> 
										<script type="text/javascript" src="${root}/resources/js/map.js"></script>
										
										<input type="hidden" id="placeName" name="placeName" value="">
										<input type="hidden" id="latitude" name="xAxis" value="">
										<input type="hidden" id="longitude" name="yAxis" value="">
										<div id="result"></div> 
									</td>
								</tr>
								<tr>
									<td colspan="2" class="text-center">
										<input type="submit" value="등록" class="btn btn-primary"> 
										<input type="reset" value="취소" class="btn btn-primary">
									</td>
								</tr>
							</table>
						</div>
					</form>
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