
function click_add() {
	
	var url="schedulePopup";  
	var name="일정 추가";
	var option="width=600, height=600, left=100, top=50, location=no";
	window.open(url,name,option)
	};
	
/*
$(function() {
	$.datepicker.setDefaults({
		dateFormat : 'yy-mm-dd',
		showOtherMonths : true,
		showMonthAfterYear : true,
		changeYear : true,
		changeMonth : true,
		yearSuffix:"년",
		monthNamesShort:['1','2','3','4','5','6','7','8','9','10','11','12'],
		monthNames:['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		dayNamesMin:['일','월','화','수','목','금','토'],
		dayNames:['일요일','월요일','화요일','수요일','목요일','금요일','토요일']
		
		})
		*/
$(function(){
	$('#startDatetime').datetimepicker({
		useCurrent:true,
		format:"YYYY-MM-DD HH:mm",
	});
	    
	$('#endDatetime').datetimepicker({
		useCurrent:true,
		format:"YYYY-MM-DD HH:mm",
	});
	
})
	
$.fn.serializeObject = function (){
	var o = {};
	var a = this.serializeArray();
	$.each(a, function(){
		var name = $.trim(this.name),
			value= $.trim(this.value);
			
			if(o[name]) {
				if (!o[name].push) {
					o[name] = [o[name]];
					}
					o[name].push(value || '');
				} else {
					o[name] = value || '';
				}
			});
			
			return o;
		};
		
		
function click_ok() {
		var sub=document.getElementById("subject");
		var sdt=document.getElementById("startDatetime");
		var edt=document.getElementById("endDatetime");
		if($('#subject').val()==null || $('#startDate').val()==null || $('#endDate').val()==null){
			if($('#subject').val() == "" ) {
				alert("제목을 입력해주세요");
				
				sub.focus();
				return false;
			} 
			if($('#startDatetime').val() == "" ) {
				console.log($('#subject').val())
				console.log($('#startDate').val())
				alert("시작일을 입력해주세요");
				sdt.focus();
				return false;
			} 
			if($('#endDatetime').val() == "" ) {
				alert("종료일을 입력해주세요");
				edt.focus();
				return false();
			}
			
		} 
		if($('#startDatetime').val() > $('#endDatetime').val()) {
				alert("시작일과 종료일을 올바르게 입력하세요");
				console.log($('#startDate').val())
				return false();
			}
			
		var scheduleData=JSON.stringify($('form#scheduleData').serializeObject());
		console.log(scheduleData);
		
		$.ajax({
					
				data : scheduleData,
				url : "addSchedule",
				type : "POST",
				dataType : "json",
				contentType : "application/json; charset=UTF-8",
				success : function(data) {
							alert("일정 추가를 진행합니다");
							opener.parent.location.reload();
							window.close();
						}
					});
		}
			