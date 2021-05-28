/**
 * 
 */

function delFunc(root, boardNo) {
	var value = confirm("삭제하시겠습니까?");

	if (value == true) {
		var url = root + "/board/deleteOk.do?boardNo=" + boardNo;
		alert(url);
	} else {
		alert("취소되었습니다.");
	}
}
function updFunc(root, boardNo) {
	var url = root + "/board/update.do?boardNo=" + boardNo;

	location.href = url;
}
function bookmarkFunc(root, boardNo) {
	alert("즐겨찾기 되었습니다.");
}

/* 추가 한 부분 */
$(function(){
	/* 신고 모달창 표시 */
	$('.report').click(function(){
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
			alert($('input[name=rp_mem_id]').val(), formData);
			
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
/* 좋아요 버튼 */
	function like_btn_click(boardNo, boardCode, postId){
		// 게시글 번호, 게시판 코드, 작성자 아이디
		if($('input[name=id]').val() == ""){
			alert("로그인이 필요한 항목입니다.");
		} else{
			$.ajax({
				url: "likeOk.do",
				type: "GET",
				data: {
					boardNo: boardNo,
					boardCode: boardCode,
					postId: postId
				},
				dataType: "JSON",
				success: function(result){
					var like_cnt = $('#like_btn').children().text();
					if(result['likeType'] == 'ok'){
						 /* 이미 좋아요 누른 회원이면 좋아요 취소 */
						like_cnt = parseInt(like_cnt)+1;
						$('#like_btn').contents()[0].textContent = "❤";	
						$('#like_btn').contents()[1].textContent = like_cnt;
					} else if(result['likeType'] == 'del'){
						/* 좋아요 안 누른 회원이면 좋아요 */
						like_cnt = parseInt(like_cnt)-1;
						$('#like_btn').contents()[0].textContent = "🤍";
						$('#like_btn').contents()[1].textContent = like_cnt;
				}
			}
			});	// ajax
		} // if ~ else
	}