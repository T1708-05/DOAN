<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<fmt:setLocale value="vi_VN"/>

<%@ include file="../_layout/header.jspf" %>

<div class="row">
  <aside class="col-md-3">
    <h5>Danh mục</h5>
    <ul class="list-group">
      <c:forEach items="${categories}" var="c">
        <li class="list-group-item">
          <a href="${pageContext.request.contextPath}/products?cat=${c.id}">${c.name}</a>
        </li>
      </c:forEach>
    </ul>
  </aside>

  <main class="col-md-9">
    <h3>Kết quả</h3>
    <div class="row g-3">
      <c:forEach items="${products}" var="p">
        <div class="col-6 col-lg-4">
          <div class="card h-100">
            <img class="card-img-top"
                 src="${pageContext.request.contextPath}/${empty p.imageUrl ? 'assets/img/placeholder.png' : p.imageUrl}"
                 alt="${p.name}">
            <div class="card-body d-flex flex-column">
              <h6 class="card-title">${p.name}</h6>
              <p class="mt-auto fw-bold text-danger">
                <fmt:formatNumber value="${p.price}" type="number" groupingUsed="true"/> đ
              </p>
              <a class="btn btn-outline-primary"
                 href="${pageContext.request.contextPath}/product?slug=${p.slug}">Chi tiết</a>
            </div>
          </div>
        </div>
      </c:forEach>
    </div>
  </main>
</div>

<%@ include file="../_layout/footer.jspf" %>
