<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="rus">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

		<title>admin_profile</title>
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
		
		<form method="get" action="Controller">
		 	<input type="hidden" name="command" value="sign_out" /> 
			<input type="submit" name="submit" value=" выйти "/> 
		</form>
		
		<c:if test="${not empty requestScope.message }">
			<c:out value="${requestScope.message}"></c:out>
		</c:if>
		
		<c:if test="${not empty requestScope.errorMessage }">
			<c:out value="${requestScope.errorMessage}"></c:out>
		</c:if>
		
		<form method="get" action="Controller">
		 	<input type="hidden" name="command" value="adding_page" /> 
			<input type="submit" name="submit" value=" добавить книгу "/> 
		</form>
		
		<c:if test="${not empty requestScope.books }">
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
						 	    <input type="hidden" name="user_id" value="${book.getUser_id()}" /> 
						 	    <input type="hidden" name="title" value="${book.getTitle()}" /> 
						 	    <input type="hidden" name="author" value="${book.getAuthor()}" /> 
						 	    <input type="hidden" name="content" value="${book.getContent()}" /> 
						 	    <input type="hidden" name="genre" value="${book.getGenre()}" /> 
							    <input type="submit" name="submit" value=" читать "/> 
						    </form>
				        </td> 
				        <td>
				           <form method="get" action="Controller">
					 	        <input type="hidden" name="command" value="delete_book" /> 
					 	        <input type="hidden" name="user_id" value="${book.getUser_id()}" /> 
					 	        <input type="hidden" name="title" value="${book.getTitle()}" /> 
						 	    <input type="hidden" name="author" value="${book.getAuthor()}" /> 
						 	    <input type="hidden" name="content" value="${book.getContent()}" /> 
						 	    <input type="hidden" name="genre" value="${book.getGenre()}" /> 
						        <input type="submit" name="submit" value=" удалить "/> 
		   					</form>
				        </td> 
			        </tr>
			    </c:forEach>
			</table>
		</c:if>
		
		<c:if test="${empty requestScope.books }">
		    <h2>Мои книги</h2>
			<form method="get" action="Controller">
		 	     <input type="hidden" name="command" value="search_book_user_id" /> 
			     <input type="submit" name="submit" value=" смотреть "/> 
		    </form>
		</c:if>
		
		
		<h2>Все пользователи</h2>
	    <form method="get" action="Controller">
		 	 <input type="hidden" name="command" value="get_all_user" /> 
			 <input type="submit" name="submit" value=" смотреть "/> 
		</form>
		
		<c:if test="${not empty requestScope.users }">
	        <table>
				<tr>
					<th>никнейме</th>
					<th>почта</th>
				</tr>
				<c:forEach var="user" items="${requestScope.users}" >
					<tr> 
					    <td>${user.getNickname()}</td> 
					    <td>${user.getEmail()}</td> 
					    <td>
						    <form method="get" action="Controller">
						 	    <input type="hidden" name="command" value="search_book_user_id" /> 
						 	    <input type="hidden" name="user_id" value="${user.getUser_id()}" /> 
							    <input type="submit" name="submit" value=" смотреть книги "/> 
						    </form>
				        </td> 
			        </tr>
			    </c:forEach>
			</table>
		</c:if>
		
		
		<c:if test="${not empty requestScope.book }">
		    <h2>${book.getTitle()}</h2>
		    <p>${book.getContent()}</p>
			<form method="get" action="Controller">
		 	     <input type="hidden" name="command" value="delete_book" /> 
		 	     <input type="hidden" name="user_id" value="${book.getUser_id()}" /> 
		 	     <input type="hidden" name="title" value="${book.getTitle()}" /> 
			 	 <input type="hidden" name="author" value="${book.getAuthor()}" /> 
			 	 <input type="hidden" name="content" value="${book.getContent()}" /> 
			 	 <input type="hidden" name="genre" value="${book.getGenre()}" /> 
			     <input type="submit" name="submit" value=" удалить "/> 
		    </form>
		    
		    <form method="get" action="Controller">
		 	     <input type="hidden" name="command" value="change_book_content" />
		 	     <input type="hidden" name="user_id" value="${book.getUser_id()}" />  
		 	     <input type="hidden" name="title" value="${book.getTitle()}" /> 
			 	 <input type="hidden" name="author" value="${book.getAuthor()}" /> 
			 	 <input type="hidden" name="content" value="${book.getContent()}" /> 
			 	 <input type="hidden" name="genre" value="${book.getGenre()}" /> 

		 	     <p><textarea name="new_content" cols = "50" rows="7">${book.getContent()}</textarea></p>
			     <input type="submit" name="submit" value=" изменить "/> 
		    </form>
		</c:if>
    </body>
</html>