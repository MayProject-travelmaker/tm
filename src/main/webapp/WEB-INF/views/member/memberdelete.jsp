<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
        <script type="text/javascript" src="../../../resources/js/member/rutil.js"></script>
        
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="${root}/resources/assets/favicon.ico" />
        
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="${root}/resources/css/styles.css" rel="stylesheet" />
        
        <!-- Bootstrap core JS-->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="${root}/resources/js/scripts.js"></script>
         <script type="text/javascript">
        	$(function(){
        		var loginMessage = "<c:out value="${message}"/>";
        		if(loginMessage != ""){
        			alert(loginMessage)
        		}
        	})
        </script>
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
		                    	<a class="nav-link" href="${root}/member/register.do">
		                                회원가입
		                        	<span class="sr-only">(current)</span>
		                   		</a>
	                        </li>
	                    </c:if>   
                    </ul>
                </div>
            </div>
        </nav>
        <br><br><br><br><br><br>
        <div align="center">
		
			<div class="container">
		<div class="col-lg-5"></div>
		<div class="col-lg-5">
			<div class="jumbotron" style="padding-top: 20px;">
				<h3 style="text-align: center;">회원탈퇴</h3>
				<form method="post" action="${root}/member/deleteOk.do">
					<div class="form-group">
						<input type="password" class="form-control" placeholder="비밀번호" id="password" name="password" maxlength="20">
					</div>
					<input type="submit" class="btn btn-primary form-control" value="확인">
				</form>
			</div>
		</div>
		
		</div>
		</div>
        <br><br><br><br><br><br>
        
        
        
        
        
        <!-- Footer-->
        <footer class="py-5 bg-dark">
            <div class="container"><p class="m-0 text-center text-white">김지은 김동인 이송하 윤희영 황민재</p></div>
        </footer>
        
    </body>
</html>