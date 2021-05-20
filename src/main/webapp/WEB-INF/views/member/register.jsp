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
        <link rel="icon" type="image/x-icon" href="/webapp/resources/assets/favicon.ico" />
        
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
	                    <c:if test = "${memberLevel==null}">
	                        <li class="nav-item active">
		                    	<a class="nav-link" href="${root}/member/login.do">
		                                로그인
		                        	<span class="sr-only">(current)</span>
		                   		</a>
	                        </li>
	                    </c:if>   
                    </ul>
                </div>
            </div>
        </nav>
        
        
        <div>
        <br><br><br><br><br><br>
        <div align ="center">
         <div class="col-lg-6"></div>
            <div
                class="col-lg-6">
                <!-- 점보트론 -->
                <div
                    class="jumbotron" style="padding-top: 20px;">
                    <!-- 로그인 정보를 숨기면서 전송post -->
                     <h3 style="text-align: center;">회원가입</h3>
                     <div align ="left">아이디</div>
                    <form method="post" action="loginOk.jsp" class ="form-inline">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="ID" name="id" maxlength="20">&nbsp;&nbsp;
                            <button class="btn btn-primary" onclick="registerCheckFuntion();" type="button">중복체크</button>
                        </div>
                    </form>
                    <form method="post" action="loginOk.jsp">
                    	<div align ="left">비밀번호</div>
                        <div class="form-group">
                            <input type="password" class="form-control" placeholder="비밀번호" name="password" maxlength="20">                   
                        <div align ="left">비밀번호재확인</div>
                            <input type="password" class="form-control" placeholder="비밀번호재확인" name="password" maxlength="20">
                     	<div align ="left">이름</div>
                            <input type="text" class="form-control" placeholder="이름" name="name" maxlength="20">
                        </div>
                     <div align ="left">주민등록번호</div>
                     </form>
                     <form method="post" action="loginOk.jsp" class ="form-inline">
                        <div class="form-group">
                            <input type="text" class="form-control" name="jumin1" maxlength="6">
                        	<label>-</label>
                            <input type="password" class="form-control" name="jumin2" maxlength="7">
                        </div>
                    </form>
                    <form method="post" action="loginOk.jsp" class ="form-inline">
                        <label class="plabel">성별</label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <div class="form-group">
                            <div class="btn-group" data-toggle="buttons">
                                <label class="btn btn-default">
                                    <input type="radio" name="gender" autocomplete="off" value="남자">남자
                                </label>
                                <label class="btn btn-default">
                                    <input type="radio" name="gender" autocomplete="off" value="여자">여자
                                </label>
                            </div>
                        </div>
                    </form>
                    <form method="post" action="loginOk.jsp">
                    <div align ="left">전화번호</div>
                       	<input type="text" class="form-control" placeholder="010-1234-5678" name="phone" maxlength="20">
                    </form>
                    <div align ="left">e-mail</div>
                    <form method="post" action="loginOk.jsp" class ="form-inline">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="이메일 주소" name="email" maxlength="20">
                            <label>&nbsp;@&nbsp;</label>
                            <div class="checkbox">
                            <select id="domain" name="domain" size="1"> 
								<option value="@naver.com" selected>naver.com</option>
								<option value="@daum.net">daum.net</option>
								<!-- 우선설정됨 -->
								<option value="@gmail.com" >gmail.com</option>
							</select>
							</div>
							&nbsp;&nbsp;
                            <button class="btn btn-primary" onclick="registerCheckFuntion();" type="button">인증</button>
                        </div>
                    </form>
                    <br>
                    <input type="submit" class="btn btn-primary form-control" value="회원가입">
                </div>
            </div>
        </div>
        <br><br><br><br><br><br>
        </div>
        
        
        
        
        <!-- Footer-->
        <footer class="py-5 bg-dark">
            <div class="container"><p class="m-0 text-center text-white">김지은 김동인 이송하 윤희영 황민재</p></div>
        </footer>
        
    </body>
</html>