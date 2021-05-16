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
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="${root}/resources/js/scripts.js"></script>
        
        <!-- fullCalendar CSS -->
        <link href="${root }/resources/css/fullcalendar/fullcalendar.css" rel="stylesheet" />
        <link href="${root }/resources/css/fullcalendar/daygrid.css" rel="stylesheet" />
        <link href="${root }/resources/css/fullcalendar/timegrid.css" rel="stylesheet" />
        <link href="${root }/resources/css/fullcalendar/list.css" rel="stylesheet" />
        
        
        <!-- fullCalendar JS -->
        <script src="${root }/resources/js/fullcalendar/fullcalendar.js"></script>
        <script src="${root }/resources/js/fullcalendar/daygrid.js"></script>
        <script src="${root }/resources/js/fullcalendar/timegrid.js"></script>
        <script src="${root }/resources/js/fullcalendar/interaction.js"></script>
        <script src="${root }/resources/js/fullcalendar/locales-all.js"></script>
        <script src="${root }/resources/js/fullcalendar/list.js"></script>
        
        <!-- fullCalendar -->
        <script>
        document.addEventListener('DOMContentLoaded', function() { 
        	var calendarEl = document.getElementById('calendar'); 
        	
        	var calendar = new FullCalendar.Calendar(calendarEl, { 
        		plugins: [ 'interaction', 'dayGrid', 'timeGrid' ], 
        		header: { 
        			left: 'prevYear,prev,next,nextYear, today', 
        			center: 'title', 
        			right: 'addSchedule, ,dayGridMonth,dayGridWeek,dayGridDay'
        			},
        		customButtons:{
        			addSchedule: {
        				text:'일정입력',
        				click:function(event) {
        					onSelectEvent(event);
        				}
        			}
        		},	
        			
        			
        		eventClick:function(calEvent, jsEvent, view) {
        			openModal(calEvent);
        		},
        		defaultDate: new Date(),	
        			
        			
        		defaultView: 'dayGridMonth', 
        		locale: 'ko',
        		navLinks: true,		// ex) 5월5일 클릭시, 그 날 일정 목록으로 넘어감
            	eventLimit : true, 	// 일정이 셀을 넘어갈 정도로 많을 때 more로 표시
        		editable : true,
        		events:[
        			
        		]
        		/*droppable: true, // this allows things to be dropped onto the calendar
        		 drop: function(info) {
        		    // is the "remove after drop" checkbox checked?
        		    if (checkbox.checked) {
        		      // if so, remove the element from the "Draggable Events" list
        		      info.draggedEl.parentNode.removeChild(info.draggedEl);
        		    }
        		  } */
        		}); 
        		calendar.render(); 
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
	                    <c:if test = "${sessionScope.memberLevel==null}">
	                        <li class="nav-item active">
		                    	<a class="nav-link" href="${root}/member/logout.do">
		                                로그아웃
		                        	<span class="sr-only">(current)</span>
		                   		</a>
	                        </li>
	                        <li class="nav-item active"><a class="nav-link" href="${root}/member/memberProfile.do">회원정보수정</a></li>
	                        <li class="nav-item active"><a class="nav-link" href="${root}/member/memberdelete.do">회원탈퇴</a></li>
	                    </c:if>   
                    </ul>
                </div>
            </div>
        </nav>
        
        
       <!-- 마이페이지 -->
        <div>
        	<div style="float:left; margin-top:200px; margin-left:300px;">
	        	<a href="${root}/member/myboard.do">내가 쓴 글</a>
	        	<br><br>
	        	<a href="${root}/member/myreply.do">내가 쓴 댓글</a>
	        	<br><br>
	        	<a href="${root}/member/mylike.do">내가 좋아요 누른 글</a>
	        	<br><br>
	        	<a href="${root}/member/mypick.do">즐겨찾기</a>
	        	<br><br>
	        	<a href="${root}/member/">나의 여행기록</a> 
	        	
        	</div>
        	<div style="float:right; width: 800px; margin:50px;" id='calendar'>
        	</div>
        </div>
        
        <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
        <!-- Footer-->
        <footer class="py-5 bg-dark">
            <div class="container"><p class="m-0 text-center text-white">김지은 김동인 이송하 윤희영 황민재</p></div>
        </footer>
        
    </body>
</html>