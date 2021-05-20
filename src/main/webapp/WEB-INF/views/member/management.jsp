<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:formatDate value="${registerDate}" pattern="yyyy/MM/dd"/>
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
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="${root}/resources/js/scripts.js"></script>
        
        <!-- moment.js (날짜 파싱) -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js" ></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment-with-locales.min.js" ></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/locale/ko.min.js" ></script>
        
        <!-- 테이블 정렬 기능 // 테이블 스크롤 -->
        <script src="${root }/resources/js/listtable/jquery.tablesorter.min.js"></script>  
       	<script src="${root }/resources/js/listtable/widget-scroller.js"></script>  
        
        <!-- 리스트 페이징처리 x , 스크롤 형식 -->
        
       
        <!-- 회원조회 -->
         <script type="text/javascript">
         $(document).ready(function(){
        	 $("#member_list_page").tablesorter();
        	 $("#member_list_page").trigger("update");
		     $(document).on("click","button[name='list']",function() {
		        	$.ajax({
	        		   url:"memberlist",
	        		   method:"post",
	        		   dataType:"JSON",
	        		   success:function(data) {
	        			   
	        			   $('#list_head').empty();// 클릭 시 초기화, (초기화 안하면 계속 추가된다고 하니 시도해보자)
	        			   $('#list_body').empty();//			상동
	        			   
	        			   var head = '';
	        			   head += '<tr style= "text-align : center; font-size:10px;">';
	        			   head += '<th>ID</th>' ;
	        			   head += '<th>이름</th>' ;
	        			   head += '<th>휴대폰 번호</th>' ;
	        			   head += '<th>성별</th>' ;
	        			   head += '<th>가입날짜</th>' ;
	        			   $('#list_head').append(head);
	        			   
	        			   $.each(data, function(index) {
	        				   
	        				   var body = '' ;
	        				   if(data[0].name == null) {
	        					   body += '<tr style="text-align: center;">';
	        					   body += '<td>정보가 없습니다</td>';
	        					   body += '</tr>'
	        				   } else {
	        					   /* for(var i=0; i<data.length; i++) { */
		        					   body += '<tr style="text-align: center;">';
		        					   body += '<td>' + data[index].id + '</td>' ;
		        					   body += '<td>' + data[index].name + '</td>' ;
		        					   body += '<td>' + data[index].phone + '</td>' ;
		        					   body += '<td>' + data[index].gender + '</td>' ;
		        					   body += '<td>' + moment(data[index].registerDate).format('YYYY-MM-DD') + '</td>' ;
		        					   body += '<td> <input type="button" class="blockBtn" value="차단"/></td>';
		        					   body += '</tr>';
	        					  /*  } */
	        				   }	//else문
	        				   $('#list_body').append(body);
	        			   });		//$.each문 닫기 
	        			   
	        			   
	        		   },		//success문 닫기
	        		   error : function() {
	        			   alert("에러");
	        		   }
	        	   }); 		//ajax 괄호 닫기  
	           }); 
	         });
         
         
        </script>
        
         
        
        
        <!-- 신고 리스트  -->
        <script>
        	$(document).on("click", "button[name='reported']",function(){
        		$.ajax({
        			url: "reportlist",
        			method: "post",
        			dataType:"JSON",
        			
        			success : function(data) {
        				alert("신고리스트 조회")
        				console.log(data)
        				$('#list_head').empty();// 클릭 시 초기화, (초기화 안하면 계속 추가된다고 하니 시도해보자)
        			 	$('#list_body').empty();//			상동
        			   
        			   var head = '';
        			   head += '<tr style= "text-align : center; font-size:10px;">';
        			   head += '<th>게시글번호</th>' ;
        			   head += '<th>신고 당한 ID</th>' ;
        			   head += '<th>신고한 ID</th>' ;
        			   head += '<th>게시글 URL</th>' ;
        			   $('#list_head').append(head);
        			   
        			   $.each(data, function(index) {
        				   
        				   var body = '' ;
        				   if(data[0].name == null) {
        					   body += '<tr style="text-align: center;">';
        					   body += '<td>정보가 없습니다</td>';
        					   body += '</tr>'
        				   } else {
        					   /* for(var i=0; i<data.length; i++) { */
	        					   body += '<tr style="text-align: center;">';
	        					   body += '<td>' + data[index].게시글번호 + '</td>' ;
	        					   body += '<td>' + data[index].신고당한 id + '</td>' ;
	        					   body += '<td>' + data[index].신고한 id + '</td>' ;
	        					   body += '<td>' + data[index].게시글url + '</td>' ;
	        					   body += '</tr>';
        					  /*  } */
        				   }	//else문
        				   $('#list_body').append(body);
        			   });		//$.each문 닫기 
        			},//success
        			
        			error : function(){
        				alert("신고리스트 조회 실패")
        			}//error
        		})
        	})
        </script>
        
        
        
        
        <!-- 차단 리스트  -->
        <script>
        	$(document).on("click","button[name='black']",function(){
        		$.ajax({
        			url:"blacklist",
        			dataType:"JSON",
        			method:"POST",
        			
        			success : function(data){
        				console.log(data)
        				$('#list_head').empty();// 클릭 시 초기화, (초기화 안하면 계속 추가된다고 하니 시도해보자)
        			 	$('#list_body').empty();//			상동
        			   
        			   var head = '';
        			   head += '<tr style= "text-align : center; font-size:10px;">';
        			   head += '<th>ID</th>' ;
        			   head += '<th>이름</th>' ;
        			   head += '<th>휴대폰 번호</th>' ;
        			   head += '<th>성별</th>' ;
        			   head += '<th>이메일</th>' ;
        			   $('#list_head').append(head);
        			   
        			   $.each(data, function(index) {
        				   
        				   var body = '' ;
        				   if(data[0].name == null) {
        					   body += '<tr style="text-align: center;">';
        					   body += '<td>정보가 없습니다</td>';
        					   body += '</tr>'
        				   } else {
        					   /* for(var i=0; i<data.length; i++) { */
	        					   body += '<tr style="text-align: center;">';
	        					   body += '<td>' + data[index].id + '</td>' ;
	        					   body += '<td>' + data[index].name + '</td>' ;
	        					   body += '<td>' + data[index].phone + '</td>' ;
	        					   body += '<td>' + data[index].gender + '</td>' ;
	        					   body += '<td>' + data[index].email + '@' +data[index].domain +'</td>' ;
	        					   body += '</tr>';
        					  /*  } */
        				   }	//else문
        				   $('#list_body').append(body);
        			   });		//$.each문 닫기 
        			},//success
        			
        			error : function(){
        				alert("차단리스트 조회 실패")
        			}//error
        		});//ajax
        	});
        </script>
        
         
        <!-- 차단 기능 구현 -->
        <script>
	    	$(document).on("click","input[class='blockBtn']",function() {
		       	var tdArr=new Array();
		       	var blockBtn=$(this);
		       	var tr = blockBtn.parent().parent();
		       	var td = tr.children();
		       	
		       	td.each(function(i) {
	        		tdArr.push(td.eq(i).text());	 
	        	 });
		       	
		       	var id = tdArr[0];
	       		var name = tdArr[1];
	    		
	       		$.ajax({
					url:"addblack",
	     		    method:"POST",
	     		    data:"id="+id,
	     		    
					success:function(data) {
	     		   },
	       			error:function(){
	       				alert("블랙리스트 연결 실패")
	       			}
		         });
	    	});
    	</script>
        
       
        <!-- 검색  -->
        <script>
			$(document).ready(function(){
			  $("#keyword").on("keyup", function() {
			    var value = $(this).val().toLowerCase();
			    $("#list_body tr").filter(function() {
			      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			    });
			  });
			});
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
	                    <c:if test = "${memberLevel!=null}">
	                        <li class="nav-item active">
		                    	<a class="nav-link" href="${root}/member/logout.do">
		                                로그아웃
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
        <div class="mldiv">
        		<span>
        			<button name="list">회원 정보 조회 </button>
        		</span>
        		
        		<span>
        			<button name="reported">신고 리스트</button>
        		</span>
        		
        		<span>
        			<button name="black">블랙 리스트 </button>
        		</span>
        		
        		<span>
        			<input type="text" id="keyword" onkeyup="searchFunction();" 
        			class="searchText" size="20" placeholder="검색...">
        			<button class="searchBtn" onclick="searchFunction();" type="button"> 검색 </button>
        		</span>
        		
        		
        </div>
        
        <div class="member_list_tableDiv" align="center">
        	<table id="member_list_page" class="tablesorter">
        		<thead id="list_head"></thead>
        		<tbody id="list_body"></tbody>
        	</table>
        </div>
        <br><br><br><br><br><br>
        </div>
        
        
        
        
        <!-- Footer-->
        <footer class="py-5 bg-dark" >
            <div class="container"><p class="m-0 text-center text-white">김지은 김동인 이송하 윤희영 황민재</p></div>
        </footer>
        
    </body>
</html>
