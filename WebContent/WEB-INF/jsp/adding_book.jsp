<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="rus">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>книга</title>
	</head>
	<body>
		<a href="index.jsp">Главная </a>
	
		<c:if test="${not empty requestScope.message }">
			<c:out value="${requestScope.message}"></c:out>
		</c:if>
		
		<c:if test="${not empty requestScope.errorMessage }">
			<c:out value="${requestScope.errorMessage}"></c:out>
		</c:if>
	
        <h2>Книга</h2>
	    <form method="get" action="Controller">
		 	<input type="hidden" name="command" value="add_book" /> 
			 	<h3>Название</h3>
			 	<p><textarea name="title" cols = "50" rows="1"></textarea></p>
			 	<h3>Автор</h3>
			 	<p><textarea name="author" cols = "50" rows="1"></textarea></p>
			 	<h3>Жанр</h3>
			 	<select required size = "1" name = "genre">
			        <option value = "DETECTIVES">Детективы</option>
			        <option value = "FANTASY">Фэнтези</option>
			        <option value = "NOVELS">Романы</option>
			        <option value = "HORRORS">Ужасы</option>
			        <option value = "FABLES">Басни</option>
		     	</select>
			 	<h3>Текст</h3>
			 	<p><textarea name="content" cols = "50" rows="7"></textarea></p>
			<input type="submit" name="submit" value="добавить"/> 
		</form>
	</body>
</html>

