<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<fmt:setLocale value="vi_VN"/>

<%@ include file="../_layout/header.jspf" %>

<div class="d-flex justify-content-between align-items-center mb-3">
  <h2>Quản lý sản phẩm</h2>
  <a class="btn btn-primary" href="${pageContext.request.contextPath}/admin/product/new">Thêm sản phẩm</a>
</div>

<table class="table table-striped align-middle">
  <thead>
    <tr>
      <th>ID</th><th>Tên</th><th>Giá</th><th>Tồn</th><th style="width:180px"></th>
    </tr>
  </thead>
  <tbody>
    <c:forEach items="${products}" var="p">
      <tr>
        <td>${p.id}</td>
        <td>${p.name}</td>
        <td><fmt:formatNumber value="${p.price}" type="number" groupingUsed="true"/> đ</td>
        <td>${p.stock}</td>
        <td class="text-end">
          <a class="btn btn-sm btn-outline-secondary"
             href="${pageContext.request.contextPath}/admin/product/edit?id=${p.id}">Sửa</a>
          <form class="d-inline" method="post" action="${pageContext.request.contextPath}/admin/product/delete">
            <input type="hidden" name="id" value="${p.id}">
            <%@ include file="../_layout/csrf.jspf" %>
            <button class="btn btn-sm btn-outline-danger" onclick="return confirm('Xoá sản phẩm này?')">Xoá</button>
          </form>
        </td>
      </tr>
    </c:forEach>
  </tbody>
</table>

<%@ include file="../_layout/footer.jspf" %>
