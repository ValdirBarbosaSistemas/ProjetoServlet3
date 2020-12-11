<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<c:choose>
		<c:when test="${sessaoUsuario != null}">
			Usuario: ${sessaoUsuario}
		</c:when>
		<c:otherwise>
			Usuario
		</c:otherwise>
	</c:choose>
</body>
</html>