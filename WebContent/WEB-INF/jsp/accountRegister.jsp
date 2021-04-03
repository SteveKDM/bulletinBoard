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
<h1>ユーザー登録</h1>
<c:if test="${not empty errorMsg}">
<p>${errorMsg}</p>
</c:if>
<form action="/bulletinBoard/AccountRegister" method="post">
ユーザーID：<input type="text" name="userId">（半角10字以内）<br>
パスワード：<input type="password" name ="pass">（半角10字以内）<br>
　名　前　：<input type="text" name="name"><br>
<input type="submit" value="確認">
</form>

<p><jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include></p>
</body>
</html>