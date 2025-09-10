<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="../_layout/header.jspf" %>

<h2>${param.id == null ? 'Thêm' : 'Sửa'} sản phẩm</h2>

<form method="post" action="${pageContext.request.contextPath}/admin/product/${param.id == null ? 'new' : 'edit'}">
  <%@ include file="../_layout/csrf.jspf" %>
  <input type="hidden" name="id" value="${param.id}">
  <div class="row g-3">
    <div class="col-md-6">
      <label class="form-label">Tên</label>
      <input class="form-control" name="name" required>
    </div>
    <div class="col-md-3">
      <label class="form-label">Slug</label>
      <input class="form-control" name="slug" required>
    </div>
    <div class="col-md-3">
      <label class="form-label">Danh mục</label>
      <select class="form-select" name="category_id">
        <c:forEach items="${categories}" var="c">
          <option value="${c.id}">${c.name}</option>
        </c:forEach>
      </select>
    </div>

    <div class="col-md-3">
      <label class="form-label">Giá</label>
      <input class="form-control" name="price" type="number" step="0.01" required>
    </div>
    <div class="col-md-3">
      <label class="form-label">Tồn kho</label>
      <input class="form-control" name="stock" type="number" required>
    </div>

    <div class="col-12">
      <label class="form-label">Ảnh (URL trong app)</label>
      <input class="form-control" name="image_url" placeholder="assets/img/ten-anh.png">
      <div class="form-text">Ví dụ: <code>assets/img/intel-core-i5-12400f.png</code></div>
    </div>

    <div class="col-12">
      <label class="form-label">Mô tả</label>
      <textarea class="form-control" name="description" rows="4"></textarea>
    </div>

    <div class="col-12">
      <button class="btn btn-primary">Lưu</button>
    </div>
  </div>
</form>

<%@ include file="../_layout/footer.jspf" %>
