<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Bootstrap core JS-->
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme CSS (includes Bootstrap)-->
<link href="${root}/resources/css/styles.css" rel="stylesheet" />
</head>
<body>
	<!-- 세션 확인 -->
<c:if test="${sessionScope.memberLevel == null}">
	<script>
		alert("로그인이 필요한 서비스입니다.");
		location.href = "${root}/member/login.do";
	</script>
</c:if>
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
					<c:if test="${sessionScope.memberLevel!=null}">
						<li class="nav-item active"><a class="nav-link"
							href="${root}/member/logout.do"> 로그아웃 <span class="sr-only">(current)</span>
						</a></li>
						<li class="nav-item"><a class="nav-link"
							href="${root}/member/mypage.do">마이페이지 <span class="sr-only">(current)</span></a></li>
						<li class="nav-item"><a class="nav-link"
							href="${root}/chat/chatRoomList.do">채팅 <span class="sr-only">(current)</span></a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<div class="col">
				<div class="panel-body">
					<br> <br>
					<div class="panel-heading">👨‍👧‍👦 채팅</div>
					<br>
					<c:if test="${chatRoomList == null }">
						<div class="container" align="center">조회된 결과가 없습니다.</div>
					</c:if>
					<c:if test="${chatRoomList != null }">
						<table class="table table-body">
									<thead>
										<tr style="text-align: center;">
											<th>채팅방</th>
											<th>채팅방 개설 회원</th>
											<th>입장</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="chatRoomDto" items="${chatRoomList}">
											<tr style="text-align: center;">
												<td>${chatRoomDto.chatRoomNo}</td>
												<td>${chatRoomDto.postId}</td>
												<td><button type="button" class="btn btn-primary" onclick="location.href='${root}/chat/chat.do?chatRoomNo=${chatRoomDto.chatRoomNo}'">입장</button></td>
											</tr>
										</c:forEach>
									</tbody>
						</table>
					</c:if>
						
				</div>
			</div>
		</div>
			
			
	</div>
	<footer class="py-5 bg-dark" style="position: fixed; width: 100%; bottom: 0;">
          <div class="container"><p class="m-0 text-center text-white">김지은 김동인 이송하 윤희영 황민재</p></div>
     </footer>
</body>
</html>