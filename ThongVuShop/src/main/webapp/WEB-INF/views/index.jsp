<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<fmt:setLocale value="vi_VN"/>

<%@ include file="_layout/header.jspf" %>

<h1 class="mb-4">Sản phẩm mới</h1>

<div class="row g-3">
  <c:forEach items="${products}" var="p">
    <div class="col-6 col-md-3">
      <div class="card h-100">
        <img class="card-img-top"
             src="${pageContext.request.contextPath}/${empty p.imageUrl ? 'assets/img/placeholder.png' : p.imageUrl}"
             alt="${p.name}">
        <div class="card-body d-flex flex-column">
          <h6 class="card-title">${p.name}</h6>
          <p class="mt-auto fw-bold text-danger">
            <fmt:formatNumber value="${p.price}" type="number" groupingUsed="true"/> đ
          </p>
          <a class="btn btn-primary w-100" href="${pageContext.request.contextPath}/product?slug=${p.slug}">Xem</a>
        </div>
      </div>
    </div>
  </c:forEach>
</div>

<%@ include file="_layout/footer.jspf" %>
