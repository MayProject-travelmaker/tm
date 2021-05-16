function update(){
	// 비밀번호, 비밀번호 재 입력 보여주기
	$('.pwd_class').css('display', 'block'); // 비밀번호 입력 창 보여주기
	$('#phone').attr('disabled', false);	// 핸드폰번호 readonly 해제
	
	$('#update_btn').css('display', 'none');// 수정하기 버튼 안보이기
	$('form').append("<input type='submit' class='btn btn-primary form-control' value='수정완료'>"); // 수정완료 버튼 추가하기
}
var idxPassword = false; // 비밀번호 확인 플래그
var idxPasswordCheck = false; // 비밀번호 재 확인 플래그
var idxPhone = true; // 휴대전화 확인 플래그 
function update_check(){
	if(!idxPassword || !idxPasswordCheck){
		alert("비밀번호를 확인 부탁드립니다.");
		$("#password").focus();
		$("#password").val('');
		$("#passwordCheck").val('');
		return false;
	}
	if(!idxPhone){
		alert("전화번호를 확인 부탁드립니다.");
		$("#phone").focus();
		return false;
	}
	
	return true;
}
$(function(){
	// 비밀번호 유효성 검사
	$("#password").blur(function() {
		idxPassword = false;
		var getPwdCheck = /^(?=.*[a-zA-Z])(?=.*[!@#$%^+=-])(?=.*[0-9]).{8,25}$/;
		if ($("#password").val() == '' || !getPwdCheck.test($("#password").val())) {
			$('#pwd_check').text("영문자+숫자+특수문자 조합 8~25자리로 입력해주세요.");
			$('#pwd_check').css('color', 'red');
		} else if ($("#password").val() == $("#id").val()) {
			$('#pwd_check').text("아이디와 비밀번호가 같습니다.");
			$('#pwd_check').css('color', 'red');
		} else {
			$('#pwd_check').text("사용가능한 비밀번호 입니다.");
			$('#pwd_check').css('color', 'green');
			idxPassword = true;
		}
	});
	// 비밀번호 재확인 유효성 검사
	$("#passwordCheck").blur(function() {
		idxPasswordCheck = false;
		if ($("#password").val() == '') {
			$('#pwdck_check').text("비밀번호를 입력해주세요.");
			$('#pwdck_check').css('color', 'red');
		} else if ($("#passwordCheck").val() == '') {
			$('#pwdck_check').text("비밀번호를 다시 입력해주세요.");
			$('#pwdck_check').css('color', 'red');
		} else if ($("#password").val() != $("#passwordCheck").val()) {
			$('#pwdck_check').text("비밀번호가 서로 일치하지 않습니다.");
			$('#pwdck_check').css('color', 'red');
		} else {
			$('#pwdck_check').text("비밀번호가 서로 일치합니다.");
			$('#pwdck_check').css('color', 'green');
			idxPasswordCheck = true;
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