<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" errorPage="WEB-INF/jsp/error.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="rus">
	<head>
		<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

		<title>index</title>

	    <!-- Bootstrap -->
	    <link href="css/bootstrap.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
	    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	    <!--[if lt IE 9]>
	      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
	      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	    <![endif]-->
	</head>

	<body>
	    <div class="navbar navbar-inverse">
	        <div class="container">
	        	<div class="row">
	        		<div class="navbar-header">
	        			<a class="navbar-brand" href="index.jsp">КниГи </a>
	        			<ul class="nav navbar-nav">
			        		<li><a href="index.jsp">Главная </a></li>
			        		<li><a href="index.jsp">Новости </a></li>
			        		<li><a href="index.jsp">Контакты </a></li>
			        	</ul>
	        		</div>
				</div>
			</div>
    	</div>

    	<div class="container">
			<div class="row">
				<c:if test="${not empty requestScope.message }">
					<div class="alert alert-success">
						<c:out value="${requestScope.message}"></c:out>
					</div>
				</c:if>

				<c:if test="${not empty requestScope.errorMessage }">
					<div class="alert alert-warning">
						<c:out value="${requestScope.errorMessage}"></c:out>
					</div>
				</c:if>
			</div>
		</div>

		<div class="container">
			<div class="row">
				<div class="col-md-8">

					<c:if test="${empty requestScope.books }">

						<div class="row">
							<div class="col-md-7">
								<p><img src="img/img1.jpg" width="300" height="100"  class="img-thumbnail"></p>
							</div>
							<div class="col-md-5">
							    <h2>Детективы</h2>
								<form method="get" action="Controller">
							 	     <input type="hidden" name="command" value="search_book_genre" /> 
							 	     <input type="hidden" name="search" value="DETECTIVES" /> 
								     <button type="submit" class="btn btn-success btn-lg btn-block">
										Смотреть
									</button>
							    </form>
							</div>
					    </div>

						<hr>

						<div class="row">
							<div class="col-md-7">
								<p><img src="img/img2.jpg" width="300" height="100"  class="img-thumbnail"></p>
							</div>
							<div class="col-md-5">
							    <h2>Фэнтези</h2>
								<form method="get" action="Controller">
							 	     <input type="hidden" name="command" value="search_book_genre" /> 
							 	     <input type="hidden" name="search" value="FANTASY" /> 
								     <button type="submit" class="btn btn-success btn-lg btn-block">
										Смотреть
									</button>
							    </form>
							</div>
					    </div>

						<hr>

						<div class="row">
							<div class="col-md-7">
								<p><img src="img/img3.jpg" width="300" height="100"  class="img-thumbnail"></p>
							</div>
							<div class="col-md-5">
							    <h2>Романы</h2>
								<form method="get" action="Controller">
							 	     <input type="hidden" name="command" value="search_book_genre" /> 
							 	     <input type="hidden" name="search" value="NOVELS" /> 
								     <button type="submit" class="btn btn-success btn-lg btn-block">
										Смотреть
									</button>
							    </form>
							</div>
					    </div>

						<hr>

						<div class="row">
							<div class="col-md-7">
								<p><img src="img/img4.jpg" width="300" height="100"  class="img-thumbnail"></p>
							</div>
							<div class="col-md-5">
							    <h2>Ужасы</h2>
								<form method="get" action="Controller">
							 	     <input type="hidden" name="command" value="search_book_genre" /> 
							 	     <input type="hidden" name="search" value="HORRORS" /> 
								     <button type="submit" class="btn btn-success btn-lg btn-block">
										Смотреть
									</button>
							    </form>
							</div>
					    </div>

						<hr>

						<div class="row">
							<div class="col-md-7">
								<p><img src="img/img5.jpg" width="300" height="100"  class="img-thumbnail"></p>
							</div>
							<div class="col-md-5">
							    <h2>Басни</h2>
								<form method="get" action="Controller">
							 	     <input type="hidden" name="command" value="search_book_genre" /> 
							 	     <input type="hidden" name="search" value="FABLES" /> 
								     <button type="submit" class="btn btn-success btn-lg btn-block">
										Смотреть
									</button>
							    </form>
							</div>
					    </div>
						<hr>
					</c:if>
					<c:if test="${not empty requestScope.books }">

						<table  class="table">
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
									 	    <button type="submit" class="btn btn-info">
												ЧИТАТЬ
											</button>
								        </form>
							        </td> 
							        </tr>
							    </c:forEach>
						</table>

					</c:if>
				</div>

				<div class="col-md-4">
				 	<div class="row">
				 		<div class="btn-toolbar">
							<div class="btn-group">
								<form method="get" action="Controller"  class="navbar-form navbar-right">
									<div class="form-group">
										<select required size = "1" name = "command"  class="form-control">
									        <option value = "search_book_title">Название</option>
									        <option value = "search_book_author">Автор</option>
								     	</select>
										<input type="search" name="search" value="" placeholder="поиск по сайту" class="form-control"/>
										<button type="submit" class="btn btn-success">
											ПОИСК
										</button>
									</div>
								</form>
							</div>
						</div>
				 	</div>

					<hr>

					<div class="row">
						<c:if test="${empty sessionScope.user_id }">
	        				<div class="btn-toolbar">
								<div class="btn-group">
									<form method="post" action="Controller" class="navbar-form navbar-right">
										<div class="form-group">
										 	<input type="hidden" name="command" value="sign_in" /> 
											<input type="email" class="form-control" name="email" value="" placeholder="email" required="required"/> 
											<input type="password" class="form-control" name="password" value="" placeholder="пароль" required="required"/>
										</div>
										<button type="submit" class="btn btn-primary">
											ВОЙТИ
										</button>
									</form>
								 </div>
	         				</div>
							<div class="btn-toolbar">
								<div class="btn-group">
									<form method="get" action="Controller" class="navbar-form navbar-right">
									 	<input type="hidden" name="command" value="registration_page" /> 
										<button type="submit" class="btn btn-link">
											регистрация
										</button>
									</form>
									<form method="get" action="Controller" class="navbar-form navbar-right">
									 	<input type="hidden" name="command" value="recovery_page" /> 
										<button type="submit" class="btn btn-link">
											забыли пароль
										</button>
									</form>
								</div>
			         		</div>
						</c:if>

						<c:if test="${not empty sessionScope.user_id }">
							<div class="btn-toolbar">
								<div class="btn-group">
									<form method="get" action="Controller" class="navbar-form">
										<input type="hidden" name="command" value="profile_page" /> 
										<button type="submit" class="btn btn-success">
											${sessionScope.nickname}
										</button>
									</form>
									<form method="get" action="Controller" class="navbar-form">
										<input type="hidden" name="command" value="sign_out" /> 
											<button type="submit" class="btn btn-danger">
											ВЫЙТИ
										</button>
									</form>
								</div>
			                </div>
		       			</c:if>
					</div>
				</div>
			</div>	
		</div>
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.js"></script>
	</body>
</html>

