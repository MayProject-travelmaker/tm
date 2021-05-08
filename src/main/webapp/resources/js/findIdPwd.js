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
function findPwd(){
	
}
$(function() {
	// 아이디 유효성 검사
	$('#id').blur(function() {
		idxId = false
		var getIdCheck = /^[a-zA-Z0-9]{4,12}$/;
		if ($("#id").val() == '' || !getIdCheck.test($("#id").val())) {
			$('#id_check').text("영문자,숫자 4~12자리의 아이디를 입력해주세요.");
			$('#id_check').css('color','red');
		} else{
			$('#id_check').text("");
			idxId = true;
		}
	});
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