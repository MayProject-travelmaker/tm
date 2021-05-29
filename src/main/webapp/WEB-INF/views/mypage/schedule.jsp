<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.java.calendar.dto.CalendarDto" %>
<%@ page import ="java.util.ArrayList" %>
<%@ page import ="java.util.List" %>
<%
	List<CalendarDto> list = (ArrayList<CalendarDto>)request.getAttribute("showSchedule");		
%>

<c:set var="root" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


		<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
		<!-- moment.js (날짜 파싱) -->
     	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js" ></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment-with-locales.min.js" ></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/locale/ko.min.js" ></script>
        	
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
       /*  function click_add() {
			var url="schedulePopup";
			var name="schedulePopup";
			var option="width=600, height=600, left=100, top=50, location=no";
			window.open(url,name,option)
			};
         */
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
        				click: function click_add() {
        					
        						var url="schedulePopup";
        						var name="schedulePopup";
        						var option="width=600, height=600, left=100, top=50, location=no";
        						window.open(url,name,option)
        						
        				}
        			}
        		},	
        			
        			
        		/* eventClick:function(calEvent, jsEvent, view) {
        			openModal(calEvent);
        		}, */
        		defaultDate: new Date(),	
        			
        			
        		defaultView: 'dayGridMonth', 
        		locale: 'ko',
        		navLinks: true,		// ex) 5월5일 클릭시, 그 날 일정 목록으로 넘어감
            	eventLimit : true, 	// 일정이 셀을 넘어갈 정도로 많을 때 more로 표시
        		editable : true,
        		events:[
        			<%
        				for (int i=0; i<list.size(); i++) {
        					CalendarDto calendarDto = (CalendarDto)list.get(i);
        			%>
        				{
        					title:'<%=calendarDto.getSubject()%>',
        					start:'<%=calendarDto.getStartDate()%>',
        					end:'<%=calendarDto.getEndDate()%>',
        					url:'readSchedule?'+'num='+'<%=calendarDto.getNum()%>',
        					
        				},
        			<%
        				}
        			%>
        				{
        					title:'default',
        					start:"2019-01-01",
        					end:"2019-01-01"
        				}
        		],
        		eventClick:function (info) {
        			info.jsEvent.preventDefault(); 
        			
        			if (info.event.url) {
        				var name='일정';
            			console.log(name)
            			var option='width=600, height=600, left=100, top=50, location=no';
        			    window.open(info.event.url,name,option);
        			    return true;
        			}
        		}
        		}); 
        		calendar.render(); 
        	});
	    </script>

</head>
<body>
	<div style="float:right; width: 800px; margin:50px;" id='calendar'>
        		
    </div>
</body>
</html>