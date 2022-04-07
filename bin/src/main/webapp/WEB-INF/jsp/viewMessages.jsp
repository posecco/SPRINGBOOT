<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<jsp:include page="layout/default.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p />
	<div class="container">

		<c:forEach var="workMessage" items="${page.content}">

			<div class="row justify-content-center">
				<div class="col-9">
					<div class="card">
						<div class="card-header">
							新增訊息的時間 <span> <fmt:formatDate
									pattern="yyyy-MM-dd HH:mm:ss EEEE" value="${workMessage.added}" />
							</span>
						</div>
						<div class="card-body">
							<c:out value="${workMessage.text}" />

							<div class="edit-link">
								<a
									href="${contextRoot}/message/editMessage?id=${workMessage.id}">編輯</a>
								| <a onclick="return confirm('確認刪除')"
									href="${contextRoot}/message/deleteMessage?id=${workMessage.id}">刪除</a>
							</div>
						</div>
					</div>
				</div>
				</div>
		</c:forEach>
		<div class="row justify-content-center">
			<!-- 加我框框才能置中喔 -->
			<div class="col-9">
				<!-- 總共會分割成12份，我要佔據12份中的9份 -->
				<c:forEach var="pageNumber" begin="1" end="${page.totalPages}">
					<!-- 在頁面下方產生關連的頁數  -->
					<a href="${contextRoot}/message/viewMessages?p=${pageNumber}"><c:out
							value="${pageNumber}" /> </a>
					<c:if test="${pageNumber != page.totalPages}">
						<!-- 我是判斷是不是到最後一頁，是的話不加分隔線 -->
		    | <!-- 我是分隔線 -->
					</c:if>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>