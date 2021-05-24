<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신고 사유 선택</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"></script>
<style type="text/css">
	.modal{
		position: absolute;
		width: 100%;
		height: 150%;
		top: 0;
		left: 0;
		background: rgba(0,0,0,0.8);
		display: none;
	}
</style>
</head>
<body>
	<div class="modal" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content" title="신고 사유 선택">
				<div class="modal-header">
					<h5 class="modal-title">신고 사유 선택</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="취소">
						<span aria-hidden="true">&times;</span>
        			</button>	
				</div>
				<div class="modal-body">
					<form id="report_form" method="POST">
						<div class="form-group">
							<div class="form-check">
								<input class="form-check-input" type="radio" name="rp_ct_no" id="rp_ct_no1" value="21">
								<label class="form-check-label" for="rp_ct_no1">욕설/비하</label>
							</div>
						</div>
						<div class="form-group">
							<div class="form-check">
								<input class="form-check-input" type="radio" name="rp_ct_no" id="rp_ct_no2" value="22">
								<label class="form-check-label" for="rp_ct_no2">낚시/놀람/도배</label>
							</div>
						</div>
						<div class="form-group">
							<div class="form-check">
								<input class="form-check-input" type="radio" name="rp_ct_no" id="rp_ct_no3" value="23">
								<label class="form-check-label" for="rp_ct_no3">게시판 성격에 부적절함</label>
							</div>
						</div>
						<div class="form-group">
							<div class="form-check">
								<input class="form-check-input" type="radio" name="rp_ct_no" id="rp_ct_no4" value="24">
								<label class="form-check-label" for="rp_ct_no4">상업적 광고 및 판매</label>
							</div>
						</div>
						<div class="form-group">
							<div class="form-check">
								<input class="form-check-input" type="radio" name="rp_ct_no" id="rp_ct_no5" value="25">
								<label class="form-check-label" for="rp_ct_no5">음란물/불건전한 만남 및 대화</label>
							</div>
						</div>
						<div class="form-group">
							<div class="form-check">
								<input class="form-check-input" type="radio" name="rp_ct_no" id="rp_ct_no6" value="26">
								<label class="form-check-label" for="rp_ct_no6">유출/사칭/사기</label>
							</div>
						</div>
						<input type="hidden" name="id" value="${sessionScope.id}"> 			<!-- 신고한 회원 아이디-->	
						<input type="hidden" name="rp_mem_id" value="${boardDto.postId}">	<!-- 신고된 회원 아이디 -->
						<input type="hidden" name="rp_type">								<!-- 신고유형 1: 게시글 2: 댓글-->
						<input type="hidden" name="post_no" value="${boardDto.boardNo}"> 	<!-- 게시판 번호  -->
						<input type="hidden" name="reply_no"> 								<!-- 댓글 번호 -->
						
						<div class="modal-footer">
							<button class="btn btn-secondary" data-dismiss="modal" id="report_close_btn" type="button">취소</button>
							<button class="btn btn-primary" type="button" id="report_submit_btn">신고</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>