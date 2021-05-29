var idxName = false;
var idxPhone = false;
function findId(){
	if(idxName == false){
		return false;
	}
	if(idxPhone == false){
		return false;
	}
	return true;
}

$(function() {
	// 이름 유효성 검사
	$("#name").blur(function() {
		idxName = false;
		var getNameCheck = RegExp(/^[가-힣]+$/);
		if ($('#name').val() == '' || !getNameCheck.test($('#name').val())) {
			$('#name_check').text("이름을 한글로 입력해주세요.");
			$('#name_check').css('color', 'red');
		} else {
			$('#name_check').text("");
			idxName = true;
		}
	});
	// 전화번호 유효성 검사
	$('#phone').blur(function(){
		idxPhone = false;
		var getPhoneCheck = /^01([0|1|6|7|8|9]?)*-([0-9]{3,4})*-([0-9]{4})$/;
		if($('#phone').val()=='' || !getPhoneCheck.test($('#phone').val())){
			$('#phone_check').text("전화번호를 하이픈(-)을 포함 해 알맞게 입력해주세요.");
			$('#phone_check').css('color','red');
		} else {
			$('#phone_check').text('');
			idxPhone = true;
		}
	});
	
});