<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ひとこと掲示板</title>
</head>
<body>
<h1>ようこそ「ひとこと掲示板」へ</h1>
<form action="/bulletinBoard/Login" method="post">
ユーザーID：<input type="text" name="userId"><br>
パスワード：<input type="password" name="pass"><br>
<input type="submit" value="ログイン">
</form>
<a href="/bulletinBoard/AccountRegister">初めての方</a>
<jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include>
</body>
</html>