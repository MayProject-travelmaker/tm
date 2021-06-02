<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
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
        <link rel="icon" type="image/x-icon" href="/webapp/resources/assets/favicon.ico" />
        
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="${root}/resources/css/styles.css" rel="stylesheet" />
        
        <!-- Bootstrap core JS -->
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
	                    <c:if test = "${sessionScope.memberLevel!=null}">	                    	
	                        <li class="nav-item active">
		                    	<a class="nav-link" href="${root}/member/logout.do">
		                                로그아웃
		                        	<span class="sr-only">(current)</span>
		                   		</a>		                        
	                        </li>
	                        <li class="nav-item">
	                        	<a class="nav-link" href="${root}/member/mypage.do">마이페이지</a>
	                        </li>		                    
	                    </c:if>
                    </ul>
                </div>
            </div>
        </nav>
        <div style="margin-top: 100px; margin-bottom: 30px;">
	        <c:if test = "${sessionScope.memberLevel != null}">
	        	<sp class="my-id" style="font-size:25px;">${sessionScope.id}님</sp>
	       	</c:if>
	       	<button style="float:right;margin-right: 515px;" onclick="location.href='${root}/board/mydiaryUpload.do'">➕</button>
       	</div>
        <hr style="width: 1200px;">
        <div style="overflow:hidden;">
	        <div style="margin-top: 100px; position: fixed; left: 110px;">
		        	<div style="overflow-y: scroll; max-height:400px;" id="image_container">
						             	
						           			<c:if test="${diaryDto.diaryNo == null}">업로드된 이미지가 없습니다.</c:if>
						             	<c:forEach var="diaryDto" items="${diaryList}">
						             	
							             	<c:if test="${diaryDto.diaryNo != null}">
							             		
											
							             			<img style="max-height: 75%; max-width: 75%" alt="이미지" src="<c:url value='/img/${diaryDto.imgName}'/>">
							             		
							           		</c:if>
						           		</c:forEach>
					 </div>
			 </div>
		</div>
        
        
        
        
        <!-- Footer-->
        <footer class="py-5 bg-dark" style="position: fixed; width: 100%; bottom: 0;">
	      <div class="container"><p class="m-0 text-center text-white">김지은 김동인 이송하 윤희영 황민재</p></div>
	  </footer>
        
    </body>
</html>
