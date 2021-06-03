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
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR"> 
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
<script type="text/javascript" src="${root}/resources/js/board.js"></script>
<script type="text/javascript">
function updateFunc(root, boardNo){
	var url=root+"/board/update.do?boardNo="+boardNo;
	location.href=url;
	
}
function delFunc(root, boardNo) {
	var value = confirm("삭제하시겠습니까?");

	if (value == true) {
		var url = root + "/board/deleteOk.do?boardNo=" + boardNo;
		location.href = url;
		
	} else {
		alert("취소되었습니다.");
	}
}
</script>
</head>
<body>
<!-- 세션 확인 -->
<c:if test="${sessionScope.memberLevel == null}">
	<script>
		alert("로그인이 필요한 서비스입니다.");
		location.href = "${root}/member/login.do";
	</script>
</c:if>
	<jsp:include page="../report/report.jsp"></jsp:include>
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
					<div id="boardCode">
						<c:if test="${boardDto.boardCode == 11}"><div class="panel-heading" id="11">👫 동행 게시판</div></c:if>
						<c:if test="${boardDto.boardCode == 12}"><div class="panel-heading" id="12">✏ 동행 후기</div></c:if>
						<c:if test="${boardDto.boardCode == 13}"><div class="panel-heading" id="13">🚩 추천 여행 경로</div></c:if>
						<c:if test="${boardDto.boardCode == 14}"><div class="panel-heading" id="14">✈ 여행지 후기</div></c:if>
					</div>
					<div>
						<input type="hidden" value="${boardDto.boardNo}" name="boardNo">
						<input type="hidden" value="${boardDto.boardCode}" name="boardCode">
						<button class="btn float-right report" id="board_report" style="color: red">신고</button>
						<button class="btn float-right"	id="bm">즐겨찾기</button>
						<div class="table table-responsive">
							<table class="table">
								<tr>
									<td class="border-right" width="100px">작성자</td>
									<td id="postId">${boardDto.postId}</td>
									<td style="color: #919eac; text-align: right;">
										<fmt:formatDate value="${boardDto.writeDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								</tr>
								<tr>
									<td class="border-right">제목</td>
									<c:if test="${boardDto.area == null}"><td colspan="2">${boardDto.title}</td></c:if>
									<c:if test="${boardDto.area != null}"><td colspan="2">[${boardDto.area}] ${boardDto.title}</td></c:if>
								</tr>
								<tr>
									<td class="border-right" height="200px">글내용</td>
									<td colspan="2">${boardDto.content}</td>
								</tr>
								<tr>
									<td class="border-right">첨부파일</td>
									<td colspan="2">
										<c:if test="${boardFileDto.fileNo == null}"> 업로드된 이미지가 없습니다.</c:if>
										<c:if test="${boardFileDto.fileNo != null}">
											<input type="hidden" id="filePath" name="filePath">
											<input type="hidden" id="fileName" name="fileName">
											<a href="#">${boardFileDto.fileName}</a>
											<img src="<c:url value='/img/${boardFileDto.fileName}'/>">
										</c:if>
									</td>
								</tr>
								 
								
								<tr>
									<td class="border-right">지도</td>
									<td colspan="2">
									<c:if test="${mapDto.mapNo == null}"> 선택된 좌표가 없습니다.</c:if>
									<c:if test="${mapDto.mapNo != null}">
										<div id="staticMap" style="width:600px;height:350px;"></div>    
										
										<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d22f00b438fdc58950b8771f7c3989ef"></script>
										<script>
										// 이미지 지도에서 마커가 표시될 위치입니다 
										var markerPosition  = new kakao.maps.LatLng(${mapDto.yAxis}, ${mapDto.xAxis}); 
										
										// 이미지 지도에 표시할 마커입니다
										// 이미지 지도에 표시할 마커는 Object 형태입니다
										var marker = {
										    position: markerPosition
										};
										
										var staticMapContainer  = document.getElementById('staticMap'), // 이미지 지도를 표시할 div  
										    staticMapOption = { 
										        center: new kakao.maps.LatLng(${mapDto.yAxis}, ${mapDto.xAxis}), // 이미지 지도의 중심좌표
										        level: 3, // 이미지 지도의 확대 레벨
										        marker: marker // 이미지 지도에 표시할 마커 
										    };    
										
										// 이미지 지도를 생성합니다
										var staticMap = new kakao.maps.StaticMap(staticMapContainer, staticMapOption);
										</script>
									</c:if>
									</td>
								</tr>
								
								<!-- 채팅 참여 버튼 -->
								<c:if test="${chatRoomNo > 0}">
									<tr>
										<td class="border-right">채팅방</td>
										<td colspan="2" align="center">
											 <a href="${root}/chat/chat.do?chatRoomNo=${chatRoomNo}">채팅 참여하기</a>
											 <!-- <input type="button" id="enterBtn" value="입장"> -->
										</td>
									</tr>
								</c:if>
								
								<tr>
									<td colspan="3" class="text-center">
										<div style="text-align:right;"><!-- 버튼부분 -->
											<c:if test="${sessionScope.id == boardDto.postId}">
												<input class="btn btn-primary" type="button" value="글수정" onclick="updateFunc('${root}', '${boardDto.boardNo}')"/>
											</c:if>
											<c:if test="${sessionScope.id == boardDto.postId || sessionScope.memberLevel == 1}">
												<input class="btn btn-primary" type="button" value="글삭제" onclick="delFunc('${root}', '${boardDto.boardNo}')"/>
											</c:if>
										</div>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<br>
					<!-- 좋아요 -->
					<div align="left">
						<c:if test="${isLiked == 1}">
							<button type="button" class="btn btn-light" id="like_btn"
								onclick="like_btn_click('${boardDto.boardNo}','${boardDto.boardCode}', '${boardDto.postId}')">❤<span>${boardDto.likeCnt}</span>
							</button>
						</c:if>
						<c:if test="${isLiked == 0 }">
							<button type="button" class="btn btn-light" id="like_btn"
								onclick="like_btn_click('${boardDto.boardNo}','${boardDto.boardCode}', '${boardDto.postId}')">🤍<span>${boardDto.likeCnt}</span>
							</button>
						</c:if>
					</div>
					<span id="sid" hidden="true">${sessionScope.id}</span>
					<!-- 댓글 -->
					<div class="container" style="background-color: #eff1f3; border-radius: 5px">
						<div class="panel-heading" style="padding-top: 10px; font-weight: bold">댓글</div><br>
						<div id="replyList" ></div>
						<div><textarea id="replyContent" rows="2" cols="30" name="comment" class="form-control" placeholder="댓글을 작성해주세요"></textarea></div>
						<div><button id="replyBtn" type="button" class="btn btn-primary" style="float: right; margin-top: 10px">등록</button></div>
						<br><br><br>
					</div>
				</div>
			</div>
		</div>
	</div>
	<br><br>

	<!-- Footer-->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">김지은 김동인 이송하 윤희영 황민재</p>
		</div>
	</footer>

</body>
</html>