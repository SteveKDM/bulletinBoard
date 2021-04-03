<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ひとこと掲示板</title>
</head>
<body>
<h1>ひとこと掲示板</h1>
<p>
<c:out value="${account.name}" />さん、ログイン中<br>
<a href="/bulletinBoard/Logout">ログアウト</a>
</p>
<p><a href="/bulletinBoard/Main">更新</a></p>
<form action="/bulletinBoard/Main" method="post">
<input type="text" name="text">
<input type="submit" value="投稿">
</form>
<c:if test="${not empty errorMsg}">
<p>${errorMsg}</p>
</c:if>
<c:forEach var="comment" items="${commentList}">
	<p><c:out value="${comment.name}" />：${comment.dateTime}<br>
	<c:out value="${comment.text}" /></p>
</c:forEach>

<p><jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include></p>
</body>
</html>