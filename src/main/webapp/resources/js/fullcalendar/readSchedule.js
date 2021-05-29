
$(function(){
	

	$('#startDatetime').datetimepicker({
		format:"YYYY-MM-DD HH:mm"
	});
	    
	$('#endDatetime').datetimepicker({
		format:"YYYY-MM-DD HH:mm"
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
		
		
function click_update() {
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
			url : "updateSchedule",
			type : "POST",
			dataType : "json",
			contentType : "application/json; charset=UTF-8",
			success : function(data) {
						alert("수정 완료");
						opener.parent.location.reload();
						window.close();
					}
				});
			};
			
function click_delete() {
	
	var scheduleData=JSON.stringify($('form#scheduleData').serializeObject());
	console.log(scheduleData);
	$.ajax({
			
			data : scheduleData,
			url : "deleteSchedule",
			type : "POST",
			dataType : "json",
			contentType : "application/json; charset=UTF-8",
			success : function(data) {
						alert("삭제 완료");
						opener.parent.location.reload();
						window.close();
					}
				});
			};
			
function click_exit() {
						opener.parent.location.reload();
						self.close();
			};		