//게시글 검색
$(document).on('click', '#btnSearch', function(e){
//	alert("검색");
	e.preventDefault();
	var myPageType = $('.panel-heading').attr('id');
	var url = "";
	if(myPageType == 1){
		url = "/project/mypage/myBoard.do";
	}else if(myPageType == 2){
		url = "/project/mypage/myReply.do";
	}else if(myPageType == 3){
		url = "/project/mypage/myLike.do";
	}else if(myPageType == 4){
		url = "/project/mypage/myBookmark.do";
	}
	url = url + "?searchType=" + $('#searchType').val();
	url = url + "&keyword=" + $('#keyword').val();

	console.log(url);
	location.href = url;
});