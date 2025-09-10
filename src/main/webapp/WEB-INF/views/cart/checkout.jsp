<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="../_layout/header.jspf" %>

<h2>Thanh toán</h2>

<c:if test='${not empty error}'>
  <div class="alert alert-danger">${error}</div>
</c:if>

<form method="post">
  <%@ include file="../_layout/csrf.jspf" %>
  <div class="row g-3">
    <div class="col-md-6">
      <label class="form-label">Họ tên</label>
      <input class="form-control" name="shipping_name" required>
    </div>
    <div class="col-md-6">
      <label class="form-label">Điện thoại</label>
      <input class="form-control" name="shipping_phone" required>
    </div>
    <div class="col-12">
      <label class="form-label">Địa chỉ</label>
      <input class="form-control" name="shipping_address" required>
    </div>
    <div class="col-12">
      <button class="btn btn-primary">Đặt hàng</button>
    </div>
  </div>
</form>

<%@ include file="../_layout/footer.jspf" %>
