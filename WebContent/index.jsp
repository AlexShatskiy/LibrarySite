<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="rus">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

	<title>index</title>
</head>
	<body>
	<a href="index.jsp">Главная </a>

	    <h2>Поиск</h2>
		<form method="get" action="Controller">
			<select required size = "1" name = "command">
		        <option value = "search_book_title">Название</option>
		        <option value = "search_book_author">Автор</option>
	     	</select>
			<input type="search" name="search" value="" placeholder="поиск по сайту"/>
			<input type="submit" name="submit" value="поиск"/> 
		</form>


		<h2>Вход</h2>

		<form method="post" action="Controller">
		 	<input type="hidden" name="command" value="sign_in" /> 
			<input type="email" name="email" value="" placeholder="email" required="required"/> 
			<input type="password" name="password" value="" placeholder="пароль" required="required"/>
			<input type="submit" name="submit" value="вход"/> 
		</form>

		<c:if test="${not empty requestScope.message }">
			<c:out value="${requestScope.message}"></c:out>
		</c:if>

		<c:if test="${not empty requestScope.errorMessage }">
			<c:out value="${requestScope.errorMessage}"></c:out>
		</c:if>
		
		<form method="get" action="Controller">
		 	<input type="hidden" name="command" value="registration_page" /> 
			<input type="submit" name="submit" value=" регистрация "/> 
		</form>

		<form method="get" action="Controller">
		 	<input type="hidden" name="command" value="recovery_page" /> 
			<input type="submit" name="submit" value=" забыли пароль "/> 
		</form>

		<table>
		<tr>
		<th>Название</th>
		<th>Автор</th>
		<th>Жанр</th>
		</tr>
		    <c:forEach var="book" items="${requestScope.books}" >
		        <tr> 
		        <td>${book.getTitle()}</td> 
		        <td>${book.getAuthor()}</td> 
		        <td>${book.getGenre()}</td> 
		        <td>
			        <form method="get" action="Controller">
			 	    <input type="hidden" name="command" value="read_book" /> 
			 	    <input type="hidden" name="title" value="${book.getTitle()}" /> 
			 	    <input type="hidden" name="author" value="${book.getAuthor()}" /> 
			 	    <input type="hidden" name="content" value="${book.getContent()}" /> 
			 	    <input type="hidden" name="genre" value="${book.getGenre()}" /> 
				    <input type="submit" name="submit" value=" читать "/> 
			        </form>
		        </td> 
		        </tr>
		    </c:forEach>
		</table>
		
		
		
		
		
		<c:if test="${empty requestScope.books }">
		    <h2>Детективы</h2>
			<form method="get" action="Controller">
		 	     <input type="hidden" name="command" value="search_book_genre" /> 
		 	     <input type="hidden" name="search" value="DETECTIVES" /> 
			     <input type="submit" name="submit" value=" смотреть "/> 
		    </form>
		    <h2>Фэнтези</h2>
			<form method="get" action="Controller">
		 	     <input type="hidden" name="command" value="search_book_genre" /> 
		 	     <input type="hidden" name="search" value="FANTASY" /> 
			     <input type="submit" name="submit" value=" смотреть "/> 
		    </form>
		    <h2>Романы</h2>
		    <form method="get" action="Controller">
		 	     <input type="hidden" name="command" value="search_book_genre" /> 
		 	     <input type="hidden" name="search" value="NOVELS" /> 
			     <input type="submit" name="submit" value=" смотреть "/> 
		    </form>
		    <h2>Ужасы</h2>
		    <form method="get" action="Controller">
		 	     <input type="hidden" name="command" value="search_book_genre" /> 
		 	     <input type="hidden" name="search" value="HORRORS" /> 
			     <input type="submit" name="submit" value=" смотреть "/> 
		    </form>
		    <h2>Басни</h2>
		    <form method="get" action="Controller">
		 	     <input type="hidden" name="command" value="search_book_genre" /> 
		 	     <input type="hidden" name="search" value="FABLES" /> 
			     <input type="submit" name="submit" value=" смотреть "/> 
		    </form>
			
		</c:if>
	</body>
</html>

