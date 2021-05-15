<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
        <c:if test="${check > 0 }">
		<script type="text/javascript">
			alert("비밀번호 변경이 완료됐습니다.");
			location.replace("${root}/index.jsp");
		</script>		
	</c:if>
	<c:if test="${check == 0 }">
		<script type="text/javascript">
			alert("인증번호가 유효하지 않아 비밀번호 변경에 실패하였습니다.");
			location.href="${root}/member/login.do";
		</script>		
	</c:if>
</body>
</html>