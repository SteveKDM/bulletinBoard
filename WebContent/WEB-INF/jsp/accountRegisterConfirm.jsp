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
<h1>ユーザー登録確認</h1>
ユーザーID：<c:out value="${account.userId}" /><br>
パスワード：表示されません<br>
　名　前　：<c:out value="${account.name}" /><br>
よろしいですか？<br>
<a href="/bulletinBoard/AccountRegister">戻る</a>
<a href="/bulletinBoard/AccountRegister?action=register">登録</a>

<p><jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include></p>
</body>
</html>