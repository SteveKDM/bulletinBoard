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
<h1>ひとこと掲示板ログイン</h1>
<c:choose>
<c:when test="${account != null}">
	<p>
		ログインに成功しました<br>
		ようこそ<c:out value="${account.name}" />さん<br>
		<a href="/bulletinBoard/Main">投稿・閲覧へ</a>
	</p>
</c:when>
<c:otherwise>
	<p>
		ログインに失敗しました<br>
		<a href="/bulletinBoard/">TOPへ</a>
</c:otherwise>
</c:choose>

<jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include>
</body>
</html>