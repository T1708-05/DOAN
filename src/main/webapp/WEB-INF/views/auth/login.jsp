<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="../_layout/header.jspf" %>

<h2>Đăng nhập</h2>

<c:if test='${not empty error}'>
  <div class="alert alert-danger">${error}</div>
</c:if>

<form method="post">
  <%@ include file="../_layout/csrf.jspf" %>
  <div class="mb-3">
    <label class="form-label">Email</label>
    <input class="form-control" type="email" name="email" required>
  </div>
  <div class="mb-3">
    <label class="form-label">Mật khẩu</label>
    <input class="form-control" type="password" name="password" required>
  </div>
  <button class="btn btn-primary">Đăng nhập</button>
</form>

<%@ include file="../_layout/footer.jspf" %>
