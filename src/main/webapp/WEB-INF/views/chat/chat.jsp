<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Bootstrap core JS-->
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.5/sockjs.min.js"></script>
<!-- Core theme CSS (includes Bootstrap)-->
<link href="${root}/resources/css/styles.css" rel="stylesheet" />

</head>
<body>
<!-- 세션 확인 -->
<c:if test="${sessionScope.memberLevel == null}">
	<script>
		alert("로그인이 필요한 서비스입니다.");
		location.href = "${root}/member/login.do";
	</script>
</c:if>
	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container">
			<a class="navbar-brand" href="${root}">Travel Maker</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<c:if test="${sessionScope.memberLevel!=null}">
						<li class="nav-item active"><a class="nav-link" href="${root}/member/logout.do"> 로그아웃 <span class="sr-only">(current)</span></a></li>
						<li class="nav-item active"><a class="nav-link" href="${root}/member/mypage.do">마이페이지 <span class="sr-only">(current)</span></a></li>
						<li class="nav-item active"><a class="nav-link" href="${root}/chat/chatRoomList.do">채팅 <span class="sr-only">(current)</span></a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container" style="margin-top: 20px;">
		<div class="row">
			<div class="col-8">
				<h6 id="id">${chatRoomDto.postId}님이 개설한 채팅방입니다</h6>
				<input type="hidden" id="chatRoomNo" value="${chatRoomNo }"/>
				<input type="hidden" id="sessionId" value="${sessionScope.id}"/>	
				
				<div class="input-group mb-3" align="center">
					<input type="text" id="message" class="form-control" size="110" onkeypress="if(event.keyCode==13){webSocket.sendChat();}" />
					<input type="button" id="btnSend"  class="btn btn-info" value="메시지 전송" onclick="webSocket.sendChat()" />
				</div>
				<div id="messageArea" style="overflow:scroll; height: 400px;">
					<c:forEach var="msg" items="${msgData}">
						<div>${fn:split(msg,'|')[0]}<span style="margin-left:10px; color: gray; font-size: 10px">${fn:split(msg,'|')[1]}</span></div>
					</c:forEach>
				</div>
				
				<div align="center" style="margin-top: 10px; margin-bottom: 10px;">
					<button class="btn btn-success btn-sm" type="button" onclick="location.href='${root}/chat/chatRoomList.do'">채팅방 목록</button>
					<button class="btn btn-danger btn-sm" type="button" onclick="exitChatRoom();">채팅방 나가기</button>
				</div>
			</div>
			
			<div class="col-4 row align-items-start">
				<div class="col">
					<p align="center" style="margin-bottom: 10px;">대화 상대</p>
					<ul class="ist-group list-group-flush">
						<li class="list-group-item">${chatRoomDto.postId}</li>
						<c:forEach var="user" items="${users}">
							<li class="list-group-item">${user}</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		
	
	</div>
	<!-- Footer-->
     <footer class="py-5 bg-dark">
            <div class="container"><p class="m-0 text-center text-white">김지은 김동인 이송하 윤희영 황민재</p></div>
     </footer>
</body>
<script type="text/javascript">
	function exitChatRoom(){
		var chatRoomNo = document.getElementById("chatRoomNo").value;
		console.log(chatRoomNo);
		// 나가기 버튼 클릭
		if(confirm('채팅방을 나가면 대화내용이 모두 삭제되고 채팅목록에서도 사라집니다. 나가시겠습니까?')){
			location.href='/project/chat/exitChatRoom.do?chatRoomNo='+chatRoomNo;
		}
	}
	/* $("#sendBtn").click(function() {
		sendMessage();
		$('#message').val('')
	}); */
	var webSocket = {
			init: function(param){
				this._url = param.url;
				this._initSocket();
			},
			sendChat: function(){
				this._sendMessage('${param.chatRoomNo}', 'CHAT', $('#message').val());
				$('#message').val('');
			},
			sendEnter: function(){
				this._sendMessage('${param.chatRoomNo}', 'ENTER', $('#message').val());
				$('#message').val('');
			},
			receiveMessage: function(msgData){
				// 정의된 messageType에 따라서 분기 처리
				if(msgData.messageType == 'CHAT') {
					$('#messageArea').append('<div class="msgData">' + msgData.msg + '<span style="margin-left:10px; color: gray; font-size: 10px">'+ msgData.currentTime +'</span></div>');
				
					var msgDataArray = [];
					// 입력된 채팅 내역 저장
					// 채팅내역1 - 아이디: 채팅메시지
					var text = msgData.msg;
					// 채팅내역2 - 날짜
					var date =  msgData.currentTime;
					// 내가 보낸 메시지만 저장되도록
					var id = text.split(":");
					var sessionId = document.getElementById("sessionId").value;
					console.log(id[0].length)
					console.log(sessionId.length)
					if(id[0].trim() == sessionId){
						console.log("컴")
						// 채팅내역(아이디: 메시지 날짜) ArrayList에 담기
						msgDataArray.push(text+"|"+date+"\n");
						// ajax에 담을 데이터
						var objParams = {
								"chatRoomNo" : ${param.chatRoomNo},
								"msgList" : msgDataArray
						}
						$.ajax({
							url: "insertChatMessage.do",
							cache: false,
							type: "POST",
							data: objParams,
							success : function(result){
								console.log("성공");
							},
							error : function(error){
								console.log("에러");
							}
						});
					}
					
				}
				// 입장
				else if(msgData.messageType == 'ENTER'){
					$('#messageArea').append('<div>' + msgData.msg + '</div>');
				}
				// 퇴장
				else if(msgData.messageType == 'EXIT'){
					$('#messageArea').append('<div>' + msgData.msg + '</div>');

					
				}
			},
			closeMessage: function(str){
				console.log("연결끊김 - closeMessage;")
				$('#messageArea').append('<div>' + '연결 끊김' + '</div>');
			},
			disconnect: function(){
				console.log("연결끊김 - _socket.close();")
				this._socket.close();
			},
			_initSocket: function(){
				this._socket = new SockJS(this._url);
				this._socket.onopen = function(evt) {
					webSocket.sendEnter();
				};
				this._socket.onmessage = function(evt) {
					webSocket.receiveMessage(JSON.parse(evt.data));
				};
				this._socket.onclose = function(evt) {
					webSocket.closeMessage(JSON.parse(evt.data));
				}
			},
			_sendMessage: function(chatRoomNo, messageType, msg){
				var msgData = {
						chatRoomNo : chatRoomNo,
						messageType : messageType,
						msg : msg,
				};
				var jsonData = JSON.stringify(msgData);
				this._socket.send(jsonData);
			}
	};
	/* var id;
	let sock = new SockJS("http://localhost/project/chat");
	sock.onopen = onOpen;
	sock.onmessage = onMessage;
	sock.onclose = onClose;
	// onopen, onmessage, onclose ,... 은 내가 정의하고 사용할 수 있다.
	// 입장
	function onOpen(){
		id = $('#id').text();
		sock.send(id+"님이 입장하셨습니다.")
	}
	// 메시지 전송
	function sendMessage() {
		sock.send($("#message").val());
	}
	// 서버로부터 메시지를 받았을 때
	function onMessage(msg) {
		var data = msg.data;
		$("#messageArea").append(data + "<br/>");
	}
	// 서버와 연결을 끊었을 때
	function onClose(evt) {
		$("#messageArea").append("연결 끊김");

	} */
</script>
<script type="text/javascript">
	$(window).on('load', function(){
			webSocket.init({url: '<c:url value="/chat" />'});
	});
</script>
</html>