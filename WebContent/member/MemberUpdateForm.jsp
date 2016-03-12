<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 수정</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<h1>회원 수정</h1>
<form action='update' method='post'>
번호 : <input type='text' name='no' value=${member.no } readonly /><br>
이름 : <input type='text' name='name' value=${member.name } %> /><br>
이메일 : <input type='text' name='email' value=${member.email } %>/><br>
비밀번호 : <input type='password' name='password' value=${member.password }/><br>
가입일 : ${member.createDate }<br>
수정일 : ${member.modifiedDate }<br>
<input type='submit' value='수정'/> <input type='button' value='삭제' onclick='location.href="delete?no=${member.no }"'/> <input type='button' value='취소' onclick='location.href="list"'/>
</form>
<jsp:include page="/tail.jsp"/>
</body>
</html>