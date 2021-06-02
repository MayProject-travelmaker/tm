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
        <script type="text/javascript" charset="utf8" src="https:////cdn.datatables.net/plug-ins/1.10.24/dataRender/datetime.js"></script>
        
        <!-- 테이블 정렬 기능 및 css -->
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.js"></script>
      	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.css">
        
     
     	<script>
     		window.onload = function() {
     			$('.member_list_tableDiv').css('display','block');
     			$('#member_list_page').DataTable({
      				destroy:true,
   			    	searching:true,
   			    	ordering: true,
   			    	serverSide:false,
   			    	paging:false,
   			    	scrollY:"500px",
   			        scrollCollapse: true,
			    	ajax:{
		        		   url:"memberlist",
		        		   method:"post",
		        		   /* dataType:"JSON", */
		        		   dataSrc:''
			    	},
			    	columns:[
			    		{data:"id"},
			    		{data:"name"},
			    		{data:"phone"},
			    		{data:"gender"},
			    		{data:"registerDate"}
			    		
			    	],
			    	columnDefs: [ 
			    		{	targets: [4],
			    	      	render: function (data,type,row){
			    	    	  return moment(row.updatedDate).format('YYYY년 MM월 DD일');
			    	      }
			    		},
			    	 {	targets:[5],
			    		 data:null,
			    		 render:function(data,type,row){
			    			 return '<input type="button" class="blockBtn" value="차단"/>'
			    		 },
			    		 orderable:false
			    	 }]
      		 	});
      		 
     		}
     	
     	</script>
     
     
     
     
     
     
      	<!-- 회원조회 -->
      	<script type="text/javascript">
      	
      		 $(document).on("click","button[name='list']",function() {
      			$('.black_list_tableDiv').css('display','none');
      			$('.report_list_tableDiv').css('display','none');
      			$('.member_list_tableDiv').css('display','block');
      			$('#member_list_page').DataTable({
      				destroy:true,
   			    	searching:true,
   			    	ordering: true,
   			    	serverSide:false,
   			    	paging:false,
   			    	scrollY:"500px",
   			        scrollCollapse: true,
			    	ajax:{
			    		
		        		   url:"memberlist",
		        		   method:"post",
		        		   /* dataType:"JSON", */
		        		   dataSrc:''
			    	},
			    	columns:[
			    		{data:"id"},
			    		{data:"name"},
			    		{data:"phone"},
			    		{data:"gender"},
			    		{data:"registerDate"}
			    		
			    	],
			    	columnDefs: [ 
			    		{	targets: [4],
			    	      	render: function (data,type,row){
			    	    	  return moment(row.updatedDate).format('YYYY년 MM월 DD일');
			    	      }
			    		},
			    	 {	targets:[5],
			    		 data:null,
			    		 render:function(data,type,row){
			    			 return '<input type="button" class="blockBtn" value="차단"/>'
			    		 },
			    		 orderable:false
			    	 }]
      		 	});
      		 });
		        		   
		</script>
    	
       
        
        
        <!-- 신고 리스트  -->
        <script type="text/javascript">
      	
      		 $(document).on("click","button[name='reported']",function() {
      			$('.black_list_tableDiv').css('display','none');
      			$('.member_list_tableDiv').css('display','none');
      			$('.report_list_tableDiv').css('display','block');
      			 $('#report_list_page').DataTable({
      				destroy:true,
   			    	searching:true,
   			    	ordering: true,
   			    	serverSide:false,
   			    	paging:false,
   			    	scrollY:"500px",
   			        scrollCollapse: true,
			    	ajax:{
			    			
		        		   url:"reportlist",
		        		   method:"post",
		        		   /* dataType:"JSON", */
		        		   dataSrc:''
			    	},
			    	
			    	columns:[
			    		{data:"postNo",
			    		 render:function(data,type,row, meta) {
			    			 if (type === 'display') {
			    				 data = '<a href="' + "/project/board/read.do?boardNo="+data+'">' + data + '</a>';
			    			 }
			    			 return data;
			    		 }},
			    		{data:"reportType"},
			    		{data:"REPLY_NO"},
			    		{data:"ID"},
			    		{data:"reportMemberId"},
			    		{data:"reportCTName"},
			    		{data:"reportDate"}
			    		
			    		
			    	],
			    	columnDefs: [
			    		 
			    			{
			    	      targets: [6],
			    	      render: function (data,type,row){
			    	    	  return moment(row.reportDate).format('YYYY년 MM월 DD일');
			    	      }
			    	    } ,/* 1 */
			    	    
			    	   	{
			    	    	targets:[2],
			    	    	render:function(data,type,row,meta) {
			    	    		console.log(row.title)
					    		if(data==0) {
					    			data=row.title;
					    		} else{
					    			data=row.CONTENT;
					    		}
					    		return data;
					     	}
			    	    },/* 2 */
			    	    
			    	    {
			    	    	targets:[1],
			    	    	render : function (data,type,row) {
			    	    		if(data==1) {
			    	    			data="게시글";
			    	    		} else if(data==2) {
			    	    			data="댓글";
			    	    		}
			    	    		return data;
			    	    	}
			    	    }
			    	    ] /* columnDefs */ 
			    	
      		 	});
      		 });
		        		   
		</script> 
        
        
        
        
        <!-- 차단 리스트  -->
        <script>
 		 $(document).on("click","button[name='blacklist']",function() {
 			$('.member_list_tableDiv').css('display','none');
 			$('.report_list_tableDiv').css('display','none');
 			$('.black_list_tableDiv').css('display','block');
 			 $('#black_list_page').DataTable({
 				destroy:true,
			    	searching:true,
			    	ordering: true,
			    	serverSide:false,
			    	paging:false,
			    	scrollY:"500px",
			        scrollCollapse: true,
		    	ajax:{
	        		   url:"blacklist",
	        		   method:"post",
	        		   /* dataType:"JSON", */
	        		   dataSrc:''
		    	},
		    	columns:[
		    		{data:"id"},
		    		{data:"name"},
		    		{data:"phone"},
		    		{data:"gender"},
		    		{data:"email"}
		    		
		    		/* {data:("email" + "domain")} */
		    		]
		    	
 		 	});
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
						location.reload();
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
        			<button name="list" class="btn btn-primary" style="margin-right : 5px; margin-left:5px;" >회원 정보 조회 </button>
        		</span>
        		
        		<span>
        			<button name="reported" class="btn btn-primary" style="margin-right : 5px;">신고 리스트</button>
        		</span>
        		
        		<span>
        			<button name="blacklist" class="btn btn-primary" style="margin-right : 5px;">블랙 리스트 </button>
        		</span>
        		
        		<!-- <span>
        			<input type="text" id="keyword" onkeyup="searchFunction();" 
        			class="searchText" size="20" placeholder="검색...">
        			<button class="searchBtn" onclick="searchFunction();" type="button"> 검색 </button>
        		</span> -->
        		
        		
        </div>
        
        <div class="member_list_tableDiv" align="center" style="display:none">
        	<table id="member_list_page" style="text-align: center;">
        		<thead id="list_head">
        			<tr style= "text-align : center; font-size:20px;">
						<th>ID</th>
						<th>이름</th>
						<th>전화번호</th>
						<th>성별</th>
						<th>가입날짜</th>
						<th>BLACK!!!</th>
						        			
        			</tr>
        		</thead>
        		<tbody id="list_body"></tbody>
        	</table>
        </div>
        
        <div class="report_list_tableDiv" align="center" style="display:none">
        	<table id="report_list_page" style="text-align: center;">
        		<thead id="list_head">
        			<tr style= "text-align : center; font-size:20px;">
						<th>게시글 번호</th>
						<th>유형</th>
						<th>제목 및 내용</th>
						<th>신고한 ID</th>
						<th>신고대상 ID</th>
						<th>신고 사유</th>
						<th>신고 날짜</th>
        			</tr>
        		</thead>
        		<tbody id="list_body"></tbody>
        	</table>
        </div>
        
        <div class="black_list_tableDiv" align="center" style="display:none">
        	<table id="black_list_page" style="text-align: center;">
        		<thead id="list_head">
        			<tr style= "text-align : center; font-size:20px;">
						<th>ID</th>
						<th>이름</th>
						<th>전화번호</th>
						<th>성별</th>
						<th>이메일</th>       			
        			</tr>
        		</thead>
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
