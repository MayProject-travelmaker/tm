/*!
* Start Bootstrap - Shop Homepage v4.3.0 (https://startbootstrap.com/template/shop-homepage)
* Copyright 2013-2021 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-shop-homepage/blob/master/LICENSE)
*/
// This file is intentionally blank
// Use this file to add JavaScript to your project

$(function() {
	//여행일지 삭제
	$(document).on('click', '#del', function(){
		if(confirm("삭제하시겠습니까?") == true) {
			var diaryNo = $(this).attr('alt');
			$.ajax({
				url: "diaryDel.do",
				type: "POST",
				data: {diaryNo : diaryNo},
				success: function(data) {
					if(data == 1) {
						alert("삭제되었습니다.");
						location.reload();
					}
				}
			})
		}
	});

	//여행일지 수정 완료
	$(document).on('click', '#upd', function(){
		if(confirm("수정하시겠습니까?") == true) {
			var diaryNo = $(this).attr('value');
			var diContent = document.getElementById("diContent").innerHTML;
			$.ajax({
				url: "diaryUpdOk.do",
				type: "POST",
				data: {diaryNo : diaryNo,
						diContent : diContent},
				success: function(data) {
					if(data == 1) {
						alert("수정되었습니다.");
						location.href="mydiary.do";
					}
				}
			})
		}
	});
})
//여행일지 수정
function diaryUpd(root, diaryNo) {
	var url = root + "/board/diaryUpd.do?diaryNo=" + diaryNo;
	location.href = url;
}