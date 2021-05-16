var idxId = false;	// 아이디 확인 플래그
var idxeMail = false; // 이메일 확인 플래그
var idxPwd = false;
var idxPasswordCheck = false;
// 비밀번호 찾기
function findPwd(){
	if(idxId == false) {
		alert("아이디를 다시 확인해 주세요.");
		$('#id').focus();
		return false;
	}
	if(idxeMail == false){
		alert("이메일을 다시 확인해 주세요.");
		$('#email').focus();
		return false;
	}
	// 아이디, 이메일 존재유무 확인
	var id = $('#id').val();
	var email = $('#email').val();
	var flag = false;
	$.ajax({
				url: "/project/member/checkIdAndEmail.do",
				type: "POST",
				async: false, 	
				data: {
					id: id,
					email: email
				},
				dataType: "JSON",
				success: function(result) {
					if(result == 1){
						// 아이디, 이메일이 존재하는 경우
						alert("인증 메일이 발송되었습니다.");
						flag = true;
					} else {
						// 아이디, 이메일이 존재하지 않거나 회원정보가 일치하지 않는 경우
						alert("존재하지 않는 회원이거나 회원정보가 일치하지 않습니다.");
						$("#id").val('');
						$('#email').val('');
						falg = false;					
					}
				}
			});
	return (flag ? true : false);
}

// 비밀번호 변경
function changePwd(){
	if(!idxPwd || !idxPasswordCheck){
		alert("비밀번호를 다시 확인 해 주세요.");
		return false;
	}
	return true;
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
	// 이메일 유효성 검사
	$('#email').blur(function(){
		idxeMail = false;
		if($('#email').val() == ''){
			$('#email_check').text("이메일을 입력해 주세요.");
			$('#email_check').css('color', 'red');
		} else {
			idxeMail = true;
			$('#email_check').text("");
		}
	});
});

$(function(){
	// 비밀번호 유효성 검사
	$("#password").blur(function() {
		idxPassword = false;
		var getPwdCheck = /^(?=.*[a-zA-Z])(?=.*[!@#$%^+=-])(?=.*[0-9]).{8,25}$/;
		if ($("#password").val() == '' || !getPwdCheck.test($("#password").val())) {
			$('#pwd_check').text("영문자+숫자+특수문자 조합 8~25자리로 입력해주세요.");
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
})