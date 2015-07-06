<%@ page import="com.tw.core.User" %>
<%@ page import="javax.validation.constraints.Null" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<% String currentUserName = (String) session.getAttribute("currentUserName"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>List of Users</title>
  <meta name="description" content="">
  <meta name="viewport" content="width=device-width">
  <link rel="stylesheet" href="../lib/css/bootstrap.min.css" />
</head>
<body>

<div class="container">
  <small>${message}</small>
  <form action="${pageContext.request.contextPath}/user/login" class="navbar-form navbar-right" method="post">
    <div class="form-group">
      <label for="name">Name:</label>
      <input name="name" class="form-control"/>
    </div>
    <div class="form-group">
      <label for="password">Password:</label>
      <input name="password" class="form-control" />
    </div>
    <div class="form-group">
      <input class="btn btn-primary" type="submit" value="登录" />
    </div>
  </form>
<div class="container">

</div>

<script src="../lib/js/jquery-1.11.1.min.js"></script>
<script src="../lib/js/bootstrap.min.js"></script>
<script src="../lib/js/underscore.min.js"></script>
<script src="../js/jquery_webmvc/select_all_users.js"></script>


</body>
</html>