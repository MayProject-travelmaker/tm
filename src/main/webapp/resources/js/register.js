/*회원가입 유효성 검사*/
var idxId = false; // 아이디 확인 플래그
var idxPassword = false; // 비밀번호 확인 플래그
var idxPasswordCheck = false; // 비밀번호 재 확인 플래그
var idxName = false;
var idxJumin = false;
var idxGender = false;
var idxPhone = false;
var idxeMail = false; // 이메일 확인 플래그
var randomNumber; // 이메일 인증번호
var getJuminCheck = RegExp(/^[0-9]*$/);
// 회원가입 제출완료 전
function register(){
	if(!idxId){
		alert("아이디를 확인 부탁드립니다.");
		$("#id").focus();
		return false;
	}
	if(!idxPassword || !idxPasswordCheck){
		alert("비밀번호를 확인 부탁드립니다.");
		$("#password").focus();
		$("#password").val('');
		$("#passwordCheck").val('');
		return false;
	}
	if(!idxName){
		alert("이름을 확인 부탁드립니다.");
		$("#name").focus();
		return false;
	}
	if($('#jumin1').val() == '' || $('#jumin2').val() == ''){
		alert("주민등록번호를 입력해 주세요.");
		$("#jumin1").focus();
		return false;
	}
	if(!getJuminCheck.test($('#jumin1').val()) ||!getJuminCheck.test($('#jumin2').val())){
		alert("주민등록번호를 올바르게 입력해 주세요.");
		$("#jumin1").focus();
		return false;
	}
	if(!$('input:radio[name=gender]').is(':checked')){
		alert("성별을 선택해 주세요.");
		$('#gender_check').text("성별을 선택해 주세요.");
		$('#gender_check').css('color', 'red');
		return false;
	}
	if(!idxPhone){
		alert("전화번호를 확인 부탁드립니다.");
		$("#phone").focus();
		return false;
	}
	if(!idxeMail){
		alert("이메일 인증을 부탁드립니다.");
		$("#email").focus();
		return false;
	}
	return true;
}



$(function() {
	// id 유효성 검사 및 중복체크
	$('#id').blur(function() {
		idxId = false
		var getIdCheck = /^[a-zA-Z0-9]{4,12}$/;
		if ($("#id").val() == '' || !getIdCheck.test($("#id").val())) {
			$('#id_check').text("영문자,숫자 4~12자리의 아이디를 입력해주세요.");
			$('#id_check').css('color','red');
		} else {
			$('#id_check').text("");
			$.ajax({
				url: "idCheck.do",
				cache: false,
				type: "POST",
				data: {
					id: $("#id").val()
				},
				dataType: "JSON",
				success: function(result) {
					if (result == 1) {
						$('#id_check').text("사용불가능한 아이디 입니다.");
						$('#id_check').css('color','red');
					} else {
						$('#id_check').text("사용가능한 아이디 입니다.");
						$('#id_check').css('color','green');
						idxId = true;
					}
				}
			})
		}
	});
	
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
	// 주민등록번호1 유효성 검사
	$("#jumin1").blur(function(){
		idxJumin = false;
		var getJuminCheck = RegExp(/^[0-9]*$/);
		if($('#jumin1').val() == '' || !getJuminCheck.test($('#jumin1').val())){
			$('#jumin_check').text("주민등록번호를 입력해 주세요.");
			$('#jumin_check').css('color', 'red');
		} else if ($('#jumin2').val() != '' && getJuminCheck.test($('#jumin2').val())){
			$('#jumin_check').text("주민등록번호 입력완료.");
			$('#jumin_check').css('color', 'green');
			idxJumin = true;
		}
	});
	// 주민등록번호2 유효성 검사
	$("#jumin2").blur(function(){
		idxJumin = false;
		var getJuminCheck = RegExp(/^[0-9]*$/);
		if($('#jumin2').val() == '' || !getJuminCheck.test($('#jumin2').val())){
			$('#jumin_check').text("주민등록번호를 입력해 주세요.");
			$('#jumin_check').css('color', 'red');
		} else if ($('#jumin1').val() != '' && getJuminCheck.test($('#jumin2').val())){
			$('#jumin_check').text("주민등록번호 입력완료.");
			$('#jumin_check').css('color', 'green');
			idxJumin = true;
		}
	})
	// 성별 유효성 검사
	$('input:radio[name=gender]').blur(function(){
		$('#gender_check').text('');
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
	// 이메일 유효성 검사
	$('#email').blur(function(){
		if($('#email').val() == ''){
			$('#email_check').text("이메일을 입력해 주세요.");
			$('#email_check').css('color', 'red');
		}
	});
	
	// 이메일 인증 버튼 클릭
	$('#sendEmail').click(function(){
		idxeMail = false;
		if ($('#email').val() == '') {
			$('#email_check').text("이메일을 입력해 주세요.");
			$('#email_check').css('color', 'red');
		} else{
			$('#email').attr('readonly', true);
			$('#sendEmail').attr('disabled', true);
			$('#email_check').parent().prepend($('<input>',{
				type: 'text',
				id: 'emailCertification',
				placeholder: '이메일 인증번호',
				class: 'form-control'
			}));
			$('#email_check').text("이메일 인증을 완료해 주세요.");
			$('#email_check').css('color', 'red');
			var email = $('#email').val() + $('#domain option:selected').val();
			console.log("email " + email);
			
			alert("인증 이메일이 발송되었습니다. 이메일을 확인 해 주세요.");
			
			$.ajax({
				url: "sendEmail.do",
				type: "POST",
				data: {
					email: email
				},
				dataType: "JSON",
				success: function(result) {
					randomNumber = result;
				}
			})
		}
	})
	
	// 이메일 인증번호 확인
	$(document).on('blur','#emailCertification', function(){
		idxeMail = false;
		console.log("emailCertification"+$('#emailCertification').val())
		if($('#emailCertification').val() == randomNumber){
			$('#email_check').text("이메일 인증이 완료됐습니다.");
			$('#email_check').css('color', 'green');
			idxeMail = true;
		} else{
			$('#email_check').text("이메일 인증이 실패했습니다.");
			$('#email_check').css('color', 'red');
			idxeMail = false;
		}
	});
});


