<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="rus">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

	<title>Registration</title>
</head>
	<body>
	<a href="index.jsp">Главная </a>
	
        <h2>Регистрация</h2>
		<form method="post" action="Controller">
		 	<input type="hidden" name="command" value="check_in" /> 
		 	<input type="email" name="email" value="" placeholder="email" required="required"/> 
			<input type="password" name="password" value="" placeholder="пароль" required="required"/>
			<input type="text" name="nickname" value="" placeholder="никнейм" required="required" />
			<input type="submit" name="submit" value="регистрация"/> 
		</form>
        <c:if test="${not empty requestScope.errorMessage }">
			<c:out value="${requestScope.errorMessage}"></c:out>
		</c:if>
	</body>
</html>

