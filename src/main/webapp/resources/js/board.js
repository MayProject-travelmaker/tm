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
		alert($(this).attr('id'));
		if($(this).attr('id') == 'board_report'){
			/* 게시판 신고버튼인 경우 */
			$('input[name=rp_type]').val("1");
		} else {
			/* 댓글 신고버튼인 경우 */
			var rp_mem_id = $(this).closet("tr").children().first().val();	// 댓글 작성자 아이디
			/* var reply_no = $(this).closet("tr"). */
			$('input[name=rp_type]').val("2");	
			$('input[name=rp_mem_id]').val(rp_mem_id);	
		}
		$('.modal').fadeIn();
	});
	
	/* 신고 모달창 닫기 - X버튼 */
	$('.close').click(function(){
		$('.modal').fadeOut();
	});
	
	/* 신고 모달창 닫기 - 취소버튼*/
	$('#report_close_btn').click(function(){
		$('.modal').fadeOut();
	});
});
