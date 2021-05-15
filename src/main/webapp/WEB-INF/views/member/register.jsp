<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <script src="https://code.jquery.com/jquery-3.5.1.js" ></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="${root}/resources/js/scripts.js"></script>
        
        <!-- 회원가입 유효성검사 JS  -->
        <script src="${root}/resources/js/register.js"></script>
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
		<br>
		<br>
		<br>
		<div align="center">
			<div class="col-lg-6">
				<!-- 점보트론 -->
				<div class="jumbotron" style="padding-top: 20px;">
					<!-- 로그인 정보를 숨기면서 전송post -->
					<h3 style="text-align: center;">회원가입</h3>
					<form method="post" action="${root}/member/registerOk.do" onsubmit="return register();">
						<div align="left">
							<span>아이디</span>
							<div class="form-inline">
								<div class="form-group">
									<input type="text" class="form-control" placeholder="ID" id="id" name="id" maxlength="20">
								</div>
							</div>
							<span id="id_check"></span>
							<span id="id_check2"></span>
						</div>
						<div align="left">
							<span>비밀번호</span>
							<input type="password" class="form-control" placeholder="비밀번호" id="password" name="password" maxlength="20">
							<span id="pwd_check"></span>
						</div>
						<div align="left">
							<span>비밀번호 재확인</span>
							<input type="password" class="form-control" placeholder="비밀번호재확인" id="passwordCheck" name="passwordCheck" maxlength="20">
							<span id="pwdck_check"></span>
						</div>
						<div align="left">
							<span>이름</span> <input type="text" class="form-control"
								placeholder="이름" id="name" name="name" maxlength="20" >
								<span id="name_check"></span>
						</div>
						<div align="left">
							<span>주민등록번호</span>
							<div class="form-inline">
							<div class="form-group">
								<input type="text" class="form-control" id="jumin1" name="jumin1" maxlength="6">
								<label>&nbsp;-&nbsp;</label>
								<input type="password" class="form-control" id="jumin2" name="jumin2" maxlength="7">
							</div>
							</div>
							<span id="jumin_check"></span>
						</div>
						<div align="left">
							<div class="form-inline">
							<div class="form-group">
								<span>성별</span>&nbsp;&nbsp;&nbsp;
								<input class="form-control" type="radio" name="gender" autocomplete="off" value="M">남자 
								<input class="form-control" type="radio" name="gender" autocomplete="off" value="F">여자
							</div>
							</div>
							<span id="gender_check"></span>
						</div>
						<div align="left">
							<span>전화번호</span>
							<input type="text" class="form-control" placeholder="010-1234-5678" id="phone" name="phone" maxlength="20">
							<span id="phone_check"></span>
						</div>
						<div align="left">
							<span>e-mail</span>
							<div class="form-inline">
								<div class="form-group">
									<input type="text" class="form-control" placeholder="이메일 주소" id="email" name="email" maxlength="20"> <label>&nbsp;@&nbsp;</label>
									<div class="checkbox">
										<select id="domain" name="domain" size="1">
											<option value="@naver.com" selected>naver.com</option>
											<option value="@daum.net">daum.net</option>
											<!-- 우선설정됨 -->
											<option value="@gmail.com">gmail.com</option>
										</select>
									</div>
									&nbsp;&nbsp;
									<button id="sendEmail" class="btn btn-primary" type="button">인증</button>
								</div>
							</div>
							<div class="form-inline">
								<span id="email_check"></span>							
							</div>
						</div>
						<br>
						<button type="submit" class="btn btn-primary form-control" value="회원가입">회원가입</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<br><br>

	<!-- Footer-->
        <footer class="py-5 bg-dark">
            <div class="container"><p class="m-0 text-center text-white">김지은 김동인 이송하 윤희영 황민재</p></div>
        </footer>
        
    </body>
</html>