<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일정 추가</title>
	<!-- bootstrap -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	<!-- moment.js (날짜 파싱) -->
     	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js" ></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment-with-locales.min.js" ></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/locale/ko.min.js" ></script>
	<!-- datetimepicker -->
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js" ></script>
    	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css"/>
    	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>

  
    <!-- 팝업창 css-->
    <link href="${root}/resources/css/fullcalendar/calendarPopup.css" rel="stylesheet" />
    	
    <!--js-->
    <script src="${root}/resources/js/fullcalendar/schedule.js" type="text/javascript"></script>
</head>
<body>
<div class="group" id="popupGroup">
	<div class="group-head">
		<h1 class="zTree-h1" style="text-align : center;">일정 추가</h1>
	</div>
	
	<div class="group-body">
		<form id="scheduleData">
			<div class="top">
				<input class="subject" id="subject" type="text" name="subject" placeholder="제목을 입력해주세요">
			</div>
			
			<div class="domain">
				<h3 class="zTree-h3"> 시작일 </h3>
			</div>
			<div class="domain" id="date_start">
				<input style="align-self: left" class="date" id="startDatetime" type="text" name="startDate"   >
			</div>
			<br>
			<div class="domain">
				<h3 class="zTree-h3"> 종료일 </h3> 
			</div>
			<div class="domain" id="date_end"  >
				<input style="align-self: right" class="date" id="endDatetime" type="text" name="endDate"   >
			</div>
			
			<div class="domain">
				<h3 class="zTree-h3"> 메모 </h3>
			</div>
			<div class="domain">
				<textarea class="memo" id="memo" name="memo" rows="5" cols="20" placeholder="100글자까지 입력 가능합니다..."></textarea>
			</div>
			
		</form>
			<button class="ok-button" type="button" onclick="click_ok();">확인</button>
				
	</div>

</div>

</body>
</html>