<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
        
    </head>
    <body>
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container">
                <a class="navbar-brand" href="${root}">Travel Maker</a>                
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto">
	                    <c:if test = "${memberLevel!=null}">
	                        <li class="nav-item active">
	                        	
		                    	<a class="nav-link" href="${root}/member/logout.do">
		                                로그아웃
		                        	<span class="sr-only">(current)</span>
		                   		</a>
		                        
	                        </li>
	                        <li class="nav-item"><a class="nav-link" href="${root}/member/mypage.do">마이페이지</a></li>
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
                    	<br>
	                    <div class="panel-heading">👫 동행 게시판</div>
	                    
	                    <div>
		                    <br><br>
		                    동행게시판 리스트 만들기
		                    <br><br>
		                    <br><br>
		                    <br><br>
		                    <br><br>
		                    <br><br>
		                    <br><br>
		                    <br><br>
		                    <br><br>
		                    <br><br>
		                    <br><br>
		                    <br><br>
		                    <br><br>
		                    <br><br>
		                    쓰는 만큼 계속 늘어남
	                    </div>
	                    
	                </div>
                </div>
            </div>
        </div>
        <!-- Footer-->
        <footer class="py-5 bg-dark">
            <div class="container"><p class="m-0 text-center text-white">김지은 김동인 이송하 윤희영 황민재</p></div>
        </footer>
        
    </body>
</html>
