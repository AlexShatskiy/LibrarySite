<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="rus">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

	<title>Library</title>
</head>
	<body>

	    <h2>Поиск</h2>
		<form method="get" action="Controller">
		 	<input type="hidden" name="command" value="search" /> 
			<input type="search" name="search" value="" placeholder="поиск по сайту"/>
			<input type="submit" name="submit" value="поиск"/> 
		</form>


		<h2>Вход</h2>
		<c:if test="${not empty requestScope.name }">
			<c:out value="${requestScope.name}"></c:out>
		</c:if>
		<form method="post" action="Controller">
		 	<input type="hidden" name="command" value="sign_in" /> 
			<input type="email" name="email" value="" placeholder="email" required="required"/> 
			<input type="password" name="password" value="" placeholder="пароль" required="required"/>
			<input type="submit" name="submit" value="вход"/> 
		</form>
		<c:if test="${not empty requestScope.errorMessage }">
			<c:out value="${requestScope.errorMessage}"></c:out>
		</c:if>
		<form method="get" action="Controller">
		 	<input type="hidden" name="command" value="check_in" /> 
			<input type="submit" name="submit" value="регистрация"/> 
		</form>
	    <form method="get" action="Controller">
		 	<input type="hidden" name="command" value="password_recovery" /> 
			<input type="submit" name="submit" value="забыл пароль"/> 
		</form>



	</body>
</html>

