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

