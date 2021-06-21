<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<c:set var="root" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html>
<html lang="kr">
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
        <script src="https://code.jquery.com/jquery-3.5.1.js" ></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"></script>
        <!-- 구글 캡챠 -->
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
        <!-- Core theme JS-->
        <script src="${root}/resources/js/scripts.js"></script>
        <!-- 구글 캡챠 javascript -->
        <script src="${root}/resources/js/member/reCaptcha.js"></script>
        <script type="text/javascript">
           $(function(){
              var loginMessage = "<c:out value="${message}"/>";
              var loginFailCount = "<c:out value="${loginFailCount}"/>";
              if(loginMessage != ""){
                 alert(loginMessage)
              }
              if(loginFailCount!=null && loginFailCount >= 5){
            	  $(".g-recaptcha").css("display", "block");
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
      <div class="col-lg-4"></div>
      <div class="col-lg-4">
         <div class="jumbotron" style="padding-top: 20px;">
            <form method="post" action="loginOk.do" onsubmit="return reCaptcha();">
               <h3 style="text-align: center;">로그인</h3>
               <div class="form-group">
               <div align ="left">아이디</div>
                  <input type="text" class="form-control" placeholder="ID" name="id" maxlength="20">
               </div>
               <div class="form-group">
               <div align ="left">비밀번호</div>
                  <input type="password" class="form-control" placeholder="비밀번호" name="password" maxlength="20">
               </div>
               <input type="submit" class="btn btn-primary form-control" value="로그인">
               <div align ="left">
               <span style="float: left;"><a href="findId.do">아이디 찾기</a></span>
               <span style="float: right;"><a href="findPassword.do">비밀번호 찾기</a></span>
               </div>
      			<br/>
                <div class="g-recaptcha" data-sitekey="6LfJuj4bAAAAAKVCO1bPW066-ur9Wtzl5O3J3rDM" style="display: none"></div>
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