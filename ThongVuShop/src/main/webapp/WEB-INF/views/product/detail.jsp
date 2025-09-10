<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<fmt:setLocale value="vi_VN"/>

<%@ include file="../_layout/header.jspf" %>

<div class="row">
  <div class="col-md-6">
    <img class="img-fluid"
         src="${pageContext.request.contextPath}/${empty p.imageUrl ? 'assets/img/placeholder.png' : p.imageUrl}"
         alt="${p.name}">
  </div>
  <div class="col-md-6">
    <h2>${p.name}</h2>
    <p class="lead text-danger">
      <fmt:formatNumber value="${p.price}" type="number" groupingUsed="true"/> đ
    </p>
    <p>${p.description}</p>

    <form method="post" action="${pageContext.request.contextPath}/cart">
      <input type="hidden" name="action" value="add"/>
      <input type="hidden" name="slug" value="${p.slug}"/>
      <%@ include file="../_layout/csrf.jspf" %>
      <div class="input-group mb-3" style="max-width:260px;">
        <input class="form-control" type="number" name="qty" value="1" min="1"/>
        <button class="btn btn-primary">Thêm giỏ hàng</button>
      </div>
    </form>
  </div>
</div>

<%@ include file="../_layout/footer.jspf" %>
