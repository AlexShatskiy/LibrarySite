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
	<a href="index.jsp">Главная </a>
	
        <h2>Восстановление пароля</h2>
	    <form method="get" action="Controller">
		 	<input type="hidden" name="command" value="password_recovery" /> 
		 	<input type="email" name="email" value="" placeholder="email" required="required"/> 
			<input type="submit" name="submit" value="забыл пароль"/> 
		</form>
        <c:if test="${not empty requestScope.errorMessage }">
			<c:out value="${requestScope.errorMessage}"></c:out>
		</c:if>
	</body>
</html>

