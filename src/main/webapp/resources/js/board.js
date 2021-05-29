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

function setThumbnail(event) {
	for (var image of event.target.files) {
		var reader = new FileReader();
		reader.onload = function(event) {
			var img = document.createElement("img");
			img.setAttribute("src", event.target.result);
			document.querySelector("div#image_container").appendChild(img);
		};
		console.log(image);
		reader.readAsDataURL(image);
	}
}	 

