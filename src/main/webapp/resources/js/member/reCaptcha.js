function reCaptcha() {
	if ($('.g-recaptcha').is(':visible')) {
		var flag = false;
		$.ajax({
			url: 'VerifyRecaptcha',
			type: 'post',
			async: false,
			data: {
				recaptcha: $("#g-recaptcha-response").val()
			},
			success: function(data) {
				switch (data) {
					case 0:
						alert("자동 가입 방지 봇 통과");
						flag = true;
						break;

					case 1:
						alert("자동 가입 방지 봇을 확인 한뒤 진행 해 주세요.");
						break;

					default:
						alert("자동 가입 방지 봇을 실행 하던 중 오류가 발생 했습니다. [Error bot Code : " + Number(data) + "]");
						break;
				}
			}
		});
		if(flag == true) return true;
		else return false;
	}
	else{
		return true;
	}
}