<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<jsp:include page="layout/default.jsp" />
</head>
<body>
<div class="container">
<p/>
<div class="row justify-content-center">
<div class="col-9">
<div class="card">
  <div class="card-header">
    新增訊息
  </div>
  <div class="card-body">
    <h5 class="card-title">Special title treatment</h5>
    <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
    <a href="#" class="btn btn-primary">Go somewhere</a>
  </div>
</div>
<form:form class="form" method="POST" modelAttribute="workMessages">
  
  <!-- form:errors  bindingResult 回傳的物件 -->
  <form:errors path="text" />
  
  <div class="input-group">
    <form:textarea path="text" class="form-control" ></form:textarea>
  </div>
  
   <input type="submit" name="submit" value="新增訊息">
  </form:form>
  
  <div class="row justify-content-center">
<div class="col-9">
<div class="card">
  <div class="card-header">
    最新訊息(時間)
    <span><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss EEEE" value="${lastMessage.added}" /></span> <!-- 日期格式化的標籤，EEEE會出現星期幾 -->
  </div>
  <div class="card-body">
  <c:out value="${lastMessage.text}" />
   
   

  </div>
</div>
</div>
</div>
</div>
</body>
</html>