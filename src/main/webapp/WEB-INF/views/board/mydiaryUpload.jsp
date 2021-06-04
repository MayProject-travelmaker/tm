<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
	Date nowTime = new Date();
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
%>


<c:set var="root" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html>
<html lang="kr">
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
        <script src="${root}/resources/js/board.js?v=<%=System.currentTimeMillis() %>"></script>
        <script src="${root}/resources/js/html2canvas.js?v=<%=System.currentTimeMillis() %>"></script>
        
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
	                        <li class="nav-item active">
	                        	<a class="nav-link" href="${root}/member/mypage.do">마이페이지</a>
	                        </li>		                    
	                        <li class="nav-item active"><a class="nav-link" href="${root}/chat/chatRoomList.do">채팅</a></li>
	                    </c:if>
                    </ul>
                </div>
            </div>
        </nav>
        
    <form action="${root}/board/mydiaryUploadOk.do" method="post" enctype="multipart/form-data">    
	    <div style="overflow:hidden;">
	        <div style="margin-top: 100px; position: fixed; right: 294px;">
		        <div style="float:right;">
			       	
			       	
			       	<button onclick="location.href='${root}/board/mydiary.do'" type="button">❌</button>
			       	<input type="file" id="image" accept="image/*" onchange="setThumbnail(event);" multiple name="file"/> 
			       	<input type="submit" value="✔" class="btn btn-light">
	       		</div>
	       		<br>
	       		<br>	       	
		       	<div style="overflow-y: scroll; max-height:400px;" id="image_container"></div> 	      
	       	</div> 
	       	<div>       		
	       		<div style="position: fixed; bottom: 392px; margin-left: 32%; ">
	       			<%= sf.format(nowTime) %>
	       		</div>
	       		<textarea rows="10%" cols="100%" style="position: fixed; bottom: 144px; margin-left: 31%;" name="diContent"></textarea>
	       	</div>
	       
	    </div>  
	 </form>     
	 
    <!-- Footer-->
	  <footer class="py-5 bg-dark" style="position: fixed; width: 100%; bottom: 0;">
	      <div class="container"><p class="m-0 text-center text-white">김지은 김동인 이송하 윤희영 황민재</p></div>
	  </footer>    
    </body>
  
</html>
