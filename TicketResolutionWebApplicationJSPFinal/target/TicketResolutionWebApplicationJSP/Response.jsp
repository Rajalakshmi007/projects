<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Success</title>
<style>
body{
background-image: url(img/issues_i.jpg);
background-size: cover;
}
div {
text-align: center;
width: 400px;
height: 100px;
background-color: #D7BDE2;
color: #17202A;
margin: auto;
margin-top: 20%;
border-color: coral;
}
#submit_id{
margin-bottom: 10px;
width: 60px;
height: 25px;
}
</style>
</head>
<body>
<div>
<h2> <%= request.getAttribute("message") %> </h2>
<form id="button_id" action="index.html">
<input id="submit_id" type="submit" value="Submit">
</form>
</div>
</body>
</html>