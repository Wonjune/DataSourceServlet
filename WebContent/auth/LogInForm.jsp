<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그 인</title>
</head>
<body>
	<jsp:include page="/header.jsp"/>
	<h2>사용자 로그인</h2>
	<form action="login" method="post">
		이메일 : <input type="text" name="email"/><br>
		비밀번호 : <input type="password" name="password"/><br>
		<input type="submit" value="로그인"/>
		<input type="button" value="리스트로 돌아가기" onclick='location.href="../member/list"'/>
	</form>
	<jsp:include page="/tail.jsp"/>
</body>
</html>