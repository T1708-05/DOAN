<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<fmt:setLocale value="vi_VN"/>

<%@ include file="../_layout/header.jspf" %>

<h2>Giỏ hàng</h2>

<c:choose>
  <c:when test="${empty sessionScope.cart}">
    <div class="alert alert-info">Giỏ hàng trống.</div>
  </c:when>
  <c:otherwise>
    <c:set var="total" value="0"/>
    <table class="table align-middle">
      <thead>
        <tr>
          <th>Sản phẩm</th>
          <th style="width:160px">Số lượng</th>
          <th>Đơn giá</th>
          <th>Tạm tính</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
      <c:forEach items="${sessionScope.cart}" var="e">
        <c:set var="item" value="${e.value}"/>
        <tr>
          <td>${item.product.name}</td>
          <td>
            <form method="post" action="${pageContext.request.contextPath}/cart" class="d-flex">
              <input type="hidden" name="action" value="update"/>
              <%@ include file="../_layout/csrf.jspf" %>
              <input class="form-control" style="width:90px" type="number" min="1"
                     name="qty_${item.product.id}" value="${item.quantity}"/>
              <button class="btn btn-sm btn-outline-primary ms-2">Cập nhật</button>
            </form>
          </td>
          <td><fmt:formatNumber value="${item.product.price}" type="number" groupingUsed="true"/> đ</td>
          <td><fmt:formatNumber value="${item.subTotal}" type="number" groupingUsed="true"/> đ</td>
          <td class="text-end">
            <form method="post" action="${pageContext.request.contextPath}/cart">
              <input type="hidden" name="action" value="remove"/>
              <input type="hidden" name="id" value="${item.product.id}"/>
              <%@ include file="../_layout/csrf.jspf" %>
              <button class="btn btn-sm btn-outline-danger">Xoá</button>
            </form>
          </td>
        </tr>
        <c:set var="total" value="${total + item.subTotal}"/>
      </c:forEach>
      </tbody>
    </table>

    <div class="text-end">
      <h4>Tổng: <span class="text-danger">
        <fmt:formatNumber value="${total}" type="number" groupingUsed="true"/> đ
      </span></h4>
      <a class="btn btn-success" href="${pageContext.request.contextPath}/checkout">Thanh toán</a>
    </div>
  </c:otherwise>
</c:choose>

<%@ include file="../_layout/footer.jspf" %>
