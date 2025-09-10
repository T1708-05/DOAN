<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="../_layout/header.jspf" %>

<h2>${param.id == null ? 'Thêm' : 'Sửa'} danh mục</h2>

<form method="post" action="${pageContext.request.contextPath}/admin/category/${param.id == null ? 'new' : 'edit'}">
  <%@ include file="../_layout/csrf.jspf" %>
  <input type="hidden" name="id" value="${param.id}">
  <div class="row g-3">
    <div class="col-md-6">
      <label class="form-label">Tên danh mục</label>
      <input class="form-control" name="name" required>
    </div>
    <div class="col-md-6">
      <label class="form-label">Slug</label>
      <input class="form-control" name="slug" required>
    </div>
    <div class="col-12">
      <button class="btn btn-primary">Lưu</button>
    </div>
  </div>
</form>

<%@ include file="../_layout/footer.jspf" %>
