<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="../_layout/header.jspf" %>

<div class="d-flex justify-content-between align-items-center mb-3">
  <h2>Quản lý danh mục</h2>
  <a class="btn btn-primary" href="${pageContext.request.contextPath}/admin/category/new">Thêm danh mục</a>
</div>

<table class="table table-striped">
  <thead>
    <tr><th>ID</th><th>Tên</th><th>Slug</th><th style="width:180px"></th></tr>
  </thead>
  <tbody>
    <c:forEach items="${categories}" var="c">
      <tr>
        <td>${c.id}</td>
        <td>${c.name}</td>
        <td>${c.slug}</td>
        <td class="text-end">
          <a class="btn btn-sm btn-outline-secondary"
             href="${pageContext.request.contextPath}/admin/category/edit?id=${c.id}">Sửa</a>
          <form class="d-inline" method="post" action="${pageContext.request.contextPath}/admin/category/delete">
            <input type="hidden" name="id" value="${c.id}">
            <%@ include file="../_layout/csrf.jspf" %>
            <button class="btn btn-sm btn-outline-danger" onclick="return confirm('Xoá danh mục này?')">Xoá</button>
          </form>
        </td>
      </tr>
    </c:forEach>
  </tbody>
</table>

<%@ include file="../_layout/footer.jspf" %>
