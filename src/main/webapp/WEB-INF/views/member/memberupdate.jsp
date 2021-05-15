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
        <script src="${root}/resources/js/memberUpdate.js"></script>
         <!-- 회원정보 후, 메시지 출력 -->
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
	                    <c:if test = "${memberLevel==null}">
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
                
		<div>
		<br>
		<br>
		<br>
		<div align="center">
			<div class="col-lg-6">
				<!-- 점보트론 -->
				<div class="jumbotron" style="padding-top: 20px;">
					<h3 style="text-align: center;">회원정보</h3>
					<form method="post" action="${root}/member/updateOk.do" onsubmit="return update_check();">
						<div align="left">
							<span>아이디</span>
							<div class="form-inline">
								<div class="form-group">
									<input type="text" class="form-control" value="${member.id}" id="id" name="id" maxlength="20" disabled="disabled">
								</div>
							</div>
						</div>
						<div class="pwd_class" align="left" style="display: none">
							<span>비밀번호</span>
							<input type="password" class="form-control" id="password" name="password" maxlength="20">
							<span id="pwd_check"></span>
						</div>
						<div class="pwd_class" align="left" style="display: none">
							<span>비밀번호 재확인</span>
							<input type="password" class="form-control" id="passwordCheck" name="passwordCheck" maxlength="20" >
							<span id="pwdck_check"></span>
						</div>
						<div align="left">
							<span>이름</span> <input type="text" class="form-control"
								placeholder="이름" value="${member.name}" id="name" name="name" maxlength="20" readonly="readonly">
						</div>
						<div align="left">
							<span>주민등록번호</span>
							<div class="form-inline">
							<div class="form-group">
								<input type="text" class="form-control" value="${member.jumin1}" id="jumin1" name="jumin1" maxlength="6" readonly="readonly">
								<label>&nbsp;-&nbsp;</label>
								<input type="password" class="form-control" value="${member.jumin2}" id="jumin2" name="jumin2" maxlength="7" readonly="readonly">
							</div>
							</div>
						</div>
						<div align="left">
							<div class="form-inline">
								<div class="form-group">
									<span>성별</span>&nbsp;&nbsp;&nbsp;
									<c:if test="${member.gender == 'M'}">
										<input class="form-control" type="radio" name="gender" autocomplete="off" value="M" checked="checked" disabled="disabled">남자
										<input class="form-control" type="radio" name="gender" autocomplete="off" value="F" disabled="disabled">여자
									</c:if>
									<c:if test="${member.gender == 'F'}">
										<input class="form-control" type="radio" name="gender" autocomplete="off" value="M" disabled="disabled">남자
										<input class="form-control" type="radio" name="gender" autocomplete="off" value="F" checked="checked" disabled="disabled">여자
									</c:if>
								</div>
							</div>
						</div>
						<div align="left">
							<span>전화번호</span>
							<input type="text" class="form-control" value="${member.phone}" id="phone" name="phone" maxlength="20" disabled="disabled">
							<span id="phone_check"></span>
						</div>
						<div align="left">
							<span>e-mail</span>
							<div class="form-inline">
								<div class="form-group">
									<input type="text" class="form-control" value="${member.email}" id="email" name="email" maxlength="20" readonly="readonly"> <label>&nbsp;@&nbsp;</label>
									<div class="checkbox">
											<c:set var="domain" value="${member.domain}"/>
											<select id="domain" size="1" disabled="disabled">
											<c:choose>
												<c:when test="${domain eq '@naver.com'}">
													<option value="@naver.com" selected>naver.com</option>
													<option value="@daum.net">daum.net</option>
													<option value="@gmail.com">gmail.com</option>
												</c:when>
												<c:when test="${domain eq '@daum.net'}">
													<option value="@naver.com">naver.com</option>
													<option value="@daum.net" selected>daum.net</option>
													<option value="@gmail.com">gmail.com</option>
												</c:when>
												<c:when test="${domain eq '@gmail.com'}">
													<option value="@naver.com">naver.com</option>
													<option value="@daum.net">daum.net</option>
													<option value="@gmail.com" selected>gmail.com</option>
												</c:when>
											</c:choose>
										</select>
									</div>
								</div>
							</div>
							<input type="hidden" name="domain" value="${member.domain}">
							<input type="hidden" name="gender" value="${member.gender}">
							<div class="form-inline">
							</div>
						</div>
						<br>
						<button type="button" class="btn btn-primary form-control" id="update_btn" onclick="update();" value="수정">수정</button>
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
