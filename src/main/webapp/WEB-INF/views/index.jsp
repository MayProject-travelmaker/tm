<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
      
      <!-- img style -->  
     <style>
      img {
        max-width: 100%;
        max-height: 100%;
      }
    </style>   
        
    </head>
    <body>
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container">
                <a class="navbar-brand" href="${root}">Travel Maker</a>                
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto">
	                    <c:if test = "${sessionScope.memberLevel==null}">
	                        <li class="nav-item active">
	                        	
		                    	<a class="nav-link" href="${root}/member/login.do">
		                                로그인
		                        	<span class="sr-only">(current)</span>
		                   		</a>
		                   	</li>
		                   	<li class="nav-item active">
		                   		<a class="nav-link" href="${root}/member/register.do">
		                                회원가입
		                        	<span class="sr-only">(current)</span>
		                   		</a>
		                   	</li>
		                        
	                        <%-- <li class="nav-item"><a class="nav-link" href="${root}/member/register.do">회원가입</a></li>
	                        <li class="nav-item"><a class="nav-link" href="${root}/member/management.do">회원관리</a></li>
	                        <li class="nav-item"><a class="nav-link" href="${root}/member/mypage.do">마이페이지</a></li> --%>
	                    </c:if> 
	                    <c:if test = "${sessionScope.memberLevel!=null}">
	                    	<li class="nav-item active"><span class="nav-link">${sessionScope.id }님 반갑습니다.</span></li>
	                        <li class="nav-item active">
		                    	<a class="nav-link" href="${root}/member/logout.do">
		                                로그아웃
		                        	<span class="sr-only">(current)</span>
		                   		</a>		                        
	                        </li>
	                        <li class="nav-item">
	                        	<a class="nav-link" href="${root}/member/mypage.do">마이페이지</a>
	                        </li>
		                    <c:if test="${sessionScope.memberLevel=='1'}">
					  			<li class="nav-item"><a class="nav-link" href="${root}/member/management.do">회원관리</a></li>
					  		</c:if>
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
                    <div class="carousel slide my-4" id="carouselExampleIndicators" data-ride="carousel" style="margin-left:20px">
                        <ol class="carousel-indicators">
                            <li class="active" data-target="#carouselExampleIndicators" data-slide-to="0"></li>
                            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                            <li data-target="#carouselExampleIndicators" data-slide-to="3"></li>
                        </ol>
                        <div class="carousel-inner" role="listbox">
                            <div class="carousel-item active"><img class="d-block img-fluid" src="<c:url value='/img/main1.png'/>" style ="width:750px; height:300px" alt="First slide" /></div>
                            <div class="carousel-item"><img class="d-block img-fluid" src="<c:url value='/img/main2.png'/>" style ="width:750px; height:300px" alt="Second slide" /></div>
                            <div class="carousel-item"><img class="d-block img-fluid" src="<c:url value='/img/main3.png'/>" style ="width:750px; height:300px" alt="Third slide" /></div>
                            <div class="carousel-item"><img class="d-block img-fluid" src="<c:url value='/img/main4.png'/>" style ="width:750px; height:300px" alt="Fourth slide" /></div>
                        </div>
                        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>
                    
                    <div class="panel-body">
	                    <div class="panel-heading">👫 동행 게시판</div>
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
											<tr style="background-color: #f1f1f1">
										</c:if>
										<c:if test="${boardDto.isPopular == 1}">
											<tr>
										</c:if>
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
												<a href="${root}/board/read.do?boardNo=${boardDto.boardNo}">📢 ${boardDto.title}</a></c:if>
											<c:if test="${boardDto.area != null}">	<!-- 공지글아닐경우 -->
												<a href="${root}/board/read.do?boardNo=${boardDto.boardNo}">[${boardDto.area}] ${boardDto.title}</a></c:if>
										</td>
										<td>${boardDto.postId}</td>
										<td class="text-center"><fmt:formatDate value="${boardDto.writeDate}"
												pattern="yyyy-MM-dd" /></td>
										<td class="text-center">${boardDto.readCnt}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
	                </div>
	                
	                <div class="panel-heading">✈ 여행지 후기 TOP 3</div>
                     <div class="row">
                     <input type = "hidden" value="${boardFileList}" name="boardFileList">
                     
                     <input type="hidden" value="${boardDto.boardNo}" name="boardNo">

                    <c:forEach var="boardDto" items="${boardReviewList}" begin="0" end="2" step="1" varStatus="status">
					    <div class="col-lg-4 col-md-6 mb-4">
					        <div class="card h-100">
					            <a href="${root}/board/read.do?boardNo=${boardDto.boardNo}"> 
					            <c:forEach var="boardFileDto" items="${boardFileList}" begin="0" end="2" varStatus="FileStatus">
					                <c:if test="${status.index == FileStatus.index }">
					                    <img src="<c:url value='/img/${boardFileDto.fileName}'/>">
					                </c:if>
					            </c:forEach>
					
					            </a>
					
					            <div class="card-body">
					                <h4 class="card-title">
					                    <a href="${root}/board/read.do?boardNo=${boardDto.boardNo}">${boardDto.title}</a>
					                </h4>
					            </div>
					        </div>
					    </div>
					</c:forEach>
                    </div>                     
                
                </div>
                <div class="col">
	                <c:if test = "${memberLevel!=null}">
		                <div class="row">
		                	<div class="right">
		                		<div class="list-group">
		                			
			                		<div class="list-group">
				                        <a class="list-group-item" href="${root}/board/mydiary.do">📒 나의 여행기록</a>
			                    	</div>
		                		</div>
		                	</div>
		                </div> 
	                </c:if>
                </div>
            </div>
        </div>
        <!-- Footer-->
        <footer class="py-5 bg-dark">
            <div class="container"><p class="m-0 text-center text-white">김지은 김동인 이송하 윤희영 황민재</p></div>
        </footer>
        
    </body>
</html>
