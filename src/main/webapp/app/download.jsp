<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>

</head>
<body>
	<h1>DOWNLOAD</h1>
	<br><br>

	<c:forEach var="file" items="${requestScope.files}">
		fileName:${file.fileName}<br>
		desc:${file.fileDesc}<br>
		<a href="downloadServlet?id=${file.id}">download</a>
		<<hr>
	</c:forEach>



</body>
</html>
