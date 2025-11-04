<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="_csrf"content="${_csrf.token}">
<meta name="_csrf_header"content="${_csrf.headerName}">
<title>카네스블랙 카페</title>
<link rel="stylesheet"type="text/css"href="${pageContext.request.contextPath}/resources/css/style.css">

<%-- 🔽 [추가] 페이지네이션 버튼을 위한 간단한 스타일 추가 --%>
<style>
	#pagination-container {
		text-align: center;
		margin-top: 20px;
	}
	.pagination-btn {
		border: 1px solid #ddd;
		padding: 5px 10px;
		margin: 0 2px;
		cursor: pointer;
		background-color: white;
		border-radius: 4px;
	}
	.pagination-btn:hover {
		background-color: #f0f0f0;
	}
	.pagination-btn.active {
		background-color: #333;
		color: white;
		border-color: #333;
		cursor: default;
	}
</style>
<%-- 🔼 [추가] 스타일 종료 --%>

</head>
<body>

<%@include file="/WEB-INF/views/common/header.jsp" %>

<div id="container">
	<div id="menuAdmin">
		<h2 id="menuAdminH2">공지사항</h2>
		
		<!-- 특별한기능(jstl이라는 라이브러리를 이용해서,세션에 있는 변수를 셋팅 조건을 겁니다. -->
		<!-- 세션공간에 저장되어있는"MANAGER"의 값이 true일때 작성이라는 버튼이 보이게끔 할것임 -->
		<c:if test="${MANAGER==true}">
			<button type="button" onclick="location.href=`${pageContext.request.contextPath}/noticeAddPage`">작성</button>
			<!-- location.href=`localhost:8080/noticeAdd` -->
		</c:if>
	<div id="menuList">
	<%-- (이곳은 script.js가 채울 것입니다) --%>
	</div>
	
	<%-- 🔽 [추가] 페이지네이션 버튼이 생성될 컨테이너 --%>
	<div id="pagination-container">
		<%-- (이곳은 script.js가 채울 것입니다) --%>
	</div>
	<%-- 🔼 [추가] 컨테이너 종료 --%>
	
	</div>
</div>

<%@include file="/WEB-INF/views/common/footer.jsp" %>

<script type="text/javascript"src="${pageContext.request.contextPath}/resources/js/script.js"></script>
</body>
</html>
