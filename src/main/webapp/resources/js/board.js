/**
 * 
 */
$(function() {
	var url = document.URL;
	var new_url = new URL(url);
	var boardNo = new_url.searchParams.get("boardNo");
	var boardCode = $('.panel-heading').attr('id');
	var postId = document.getElementById('postId').innerHTML;
	
	//즐겨찾기
	$("#bm").click(function() {
		if (confirm("즐겨찾기에 추가하시겠습니까?") == true) {
			// 즐겨찾기 중복체크
			$.ajax({
				url: "bmCheck.do",
				type: "POST",
				data: {boardNo : boardNo},
				dataType: "JSON",
				success: function(data) {
					if (data == 1) {
						alert("이미 즐겨찾기에 추가되어 있습니다.");
					} else {
						$.ajax({
							url: "bookmark.do",
							type: "POST",
							data: {	boardNo	  : boardNo,
									boardCode : boardCode,
									postId	  : postId
									},
							dataType: "JSON",
							success: function(data) {
								if (data == 1) {
									alert("즐겨찾기에 추가되었습니다.");
								}
							}
						})
					}
				}
			})
		} 
	});
	
	//댓글입력
	replyList();
	$(document).on('click', '#replyBtn', function(){
		var content = $("#replyContent").val();
		var sequenceNo = $(this).attr('value');
		var groupNo = $(this).prev().val();
		$.ajax({
			url: "replyWrite.do",
			type: "POST",
			data: {	content : content,
					boardNo : boardNo,
					boardCode : boardCode,
					postId	: postId,
					sequenceNo : sequenceNo,
					groupNo : groupNo
					},
			success: function(data){
				if(data == 1) {
					alert("댓글이 등록되었습니다.");
					$("#replyContent").val("");
					replyList();
				}
			}
		})
	});
	
	//댓글삭제
	$(document).on('click', '#replyDel', function(){
		if(confirm("삭제하시겠습니까?") == true) {
			var replyNo = $(this).attr('value');
			$.ajax({
				url: "replyDel.do",
				type: "POST",
				data: {replyNo : replyNo},
				success: function(data) {
					if(data == 1) {
						alert("댓글이 삭제되었습니다.");
						replyList();
					}
				}
			})
		}
	});
	
	//댓글수정완료
	$(document).on('click', '#replyUpd', function(){
		var replyNo = $(this).attr('value');
		var content = $(this).prev().val();
		$.ajax({
			url: "replyUpd.do",
			type: "POST",
			data: {	replyNo : replyNo,
					content : content},
			success: function(data) {
				if(data == 1) {
					alert("댓글이 수정되었습니다.");
					replyList();
				}
			}
		})
	});
	
});

//댓글리스트
function replyList() {
	var url = document.URL;
	var new_url = new URL(url);
	var boardNo = new_url.searchParams.get("boardNo");

	$.ajax({
		url: "replyList.do",
		type: "POST",
		data: {boardNo : boardNo},
		success: function(data) {
			var out = "<table class='table'>";
			
			for (var i in data) {
				var replyId = data[i].id;
				var replyNo = data[i].replyNo;
				var content = data[i].content;
				var writeDate = data[i].writeDate;
				var sequenceNo = data[i].sequenceNo;
				var groupNo = data[i].groupNo;
				var sessionId = document.getElementById("sid").innerHTML;
				
				out += "<tr id='replyNo" + replyNo + "'>";
				out += "<td width='100px' style='font-weight: bolder'>" + replyId + "</td>";
				if(sequenceNo > 1) {	//대댓글
					out += "<td id='reReplyNo" + replyNo + "'>↳ " + content + "<br>";
				} else {	//댓글
					out += "<td id='reReplyNo" + replyNo + "'>" + content + "<br>";
				}
				out += "<span style='color: #bec4cd'> " + changeDate(writeDate) + "</span>";
				out += "<button class='btn btn-sm report' value='" + replyNo + "' style='color: red'>신고</button>";
				if(sessionId == replyId) {	//세션id랑 댓글id 같을때만 댓글수정삭제버튼 활성화
					out += "<button class='btn btn-sm' style='color: blue'";
					out += " onclick='replyUpdView(" + replyNo + ", \"" + replyId + "\", \"" + content + "\")'>수정</button>";
					out += "<button id='replyDel' class='btn btn-sm' value='" + replyNo + "' style='color: blue'>삭제</button>";
				}
				out += "<input type='hidden' value='" + groupNo + "'>";
				out += "<button class='btn btn-sm' style='color: blue'";
				if(sequenceNo == 1) {
					out += " onclick='reReply(" + replyNo + ", \"" + sessionId + "\", \"" + sequenceNo + "\", \"" + groupNo + "\")'>답글</button>"
				} 
				out += "</td>";
				out += "</tr>";
			}
			out += "</table>";
			$("#replyList").html(out);
		}
	});
};

//댓글수정버튼 클릭 후 해당댓글정보 출력
function replyUpdView(replyNo, id, content) {
	var out = "";
	out += "<tr>";
	out += "<td style='font-weight: bolder'>" + id + "</td>";
	out += "<td>";
	out += "<textarea id='replyContent' name='comment' class='form-control'>" + content + "</textarea>";
	out += "<button class='btn btn-sm' value='" + replyNo + "' style='color: blue' id='replyUpd'>수정완료</button>";
	out += "<button class='btn btn-sm' style='color: blue' onclick='replyList()'>취소</button>";
	out += "</td>";
	out += "</tr>";
	
	$("#replyNo" + replyNo).replaceWith(out);
	//불러온댓글 마지막으로 커서위치지정
	var len = $('#replyContent').val().length;
	$('#replyContent').focus();
	$('#replyContent')[0].setSelectionRange(len, len);
};

//대댓글폼
function reReply(replyNo, sessionId, sequenceNo, groupNo) {
	var out = "";
	out += "<tr>";
	out += "<td style='font-weight: bolder'>" + sessionId + "</td>";
	out += "<td>";
	out += "<textarea id='replyContent' name='comment' cols='100' class='form-control' placeholder='댓글을 작성해주세요'></textarea>";
	out += "<input type='hidden' value='" + groupNo + "'>";
	out += "<button class='btn btn-sm' value='" + sequenceNo + "' style='color: blue' id='replyBtn'>등록</button>";
	out += "<button class='btn btn-sm' style='color: blue' onclick='replyList()'>취소</button>";
	out += "</td>";
	out += "</tr>";
	
	$("#reReplyNo" + replyNo).append(out);
	$('#replyContent').focus();
};

//날짜변환
function changeDate(date) {
	date = new Date(parseInt(date));
	year = date.getFullYear();
	month = date.getMonth() + 1;
	day = date.getDate();
	hour = date.getHours();
	minute = date.getMinutes();
	second = date.getSeconds();
	strDate = year+"."+month+"."+day+". "+hour+":"+minute;
	return strDate;
};

//신고
$(function(){
	/* 신고 모달창 표시 */
	$(document).on('click', '.report', function(){
		/* 비회원인 경우, 신고 모달창 표시 X */
		if($('input[name=id]').val() == ""){
			alert("로그인이 필요한 항목입니다.");
		}else{
		/* 회원인 경우, 신고 모달창 표시 O */	
			$("input:radio[name='rp_ct_no']").prop('checked', false);	// 라디오버튼 체크상태 일괄 해제
			
			if($(this).attr('id') == 'board_report'){
				/* 게시판 신고버튼인 경우 */
				$('input[name=rp_type]').val("1");	
				$('input[name=reply_no]').val("0");				// 신고유형 : 1(게시판)
			} else {
				/* 댓글 신고버튼인 경우 */
				var rp_mem_id = $(this).parent().prev().text();		// 댓글 작성자 아이디
				var reply_no = $(this).val();						// 댓글 번호
				
				$('input[name=rp_type]').val("2");					// 신고유형 : 2(댓글)
				$('input[name=rp_mem_id]').val(rp_mem_id);	
				$('input[name=reply_no]').val(reply_no);	
			}
			
			$('.modal').fadeIn();
		}
	});
	
	/* 신고 모달창 닫기 - X버튼 */
	$('.close').click(function(){
		$('.modal').fadeOut();
	});
	
	/* 신고 모달창 닫기 - 취소버튼*/
	$('#report_close_btn').click(function(){
		$('.modal').fadeOut();
	});
	
	$('#report_submit_btn').click(function(){
		if(!$("input:radio[name='rp_ct_no']").is(':checked')){
			/* 신고유형 입력 안 된 경우 */
			alert("신고유형을 체크 부탁드립니다.");
		} else{
			/* 신고유형 입력 한 경우 */
			var formData = $('#report_form').serialize();		// form(id=report_form)에 입력된 값들을 담는다
			
			$.ajax({
				cache: false,
				url: "/project/report/reportOk.do",
				type: "POST",
				data: formData,
				dataType: "JSON",
				success: function(result){
					if(result == 1){
						alert("신고가 완료 되었습니다.");
						$('.modal').fadeOut();
					} else {
						alert("신고접수가 실패했습니다.");
						$('.modal').fadeOut();
					}
				},
				error : function(error){
					console.log('fail');				
				}	
			});
		}
		
	})
});

//게시글 검색
$(document).on('click', '#btnSearch', function(e){
//	alert("검색");
	e.preventDefault();
	var boardCode = $('.panel-heading').attr('id');
	var url = "";
	if(boardCode == 11){
		url = "accompanylist.do";
	}else if(boardCode == 12){
		url = "accompanyreview.do";
	}else if(boardCode == 13){
		url = "recommendpath.do";
	}else if(boardCode == 14){
		url = "travelreview.do";
	}
	url = url + "?searchType=" + $('#searchType').val();
	url = url + "&keyword=" + $('#keyword').val();

	location.href = url;
});






