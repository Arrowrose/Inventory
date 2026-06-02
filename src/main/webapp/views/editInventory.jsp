<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Редактирование инвентаря</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body>
<div class="container-fluid">
    <jsp:include page="header.jsp" />
    
    <div class="container-fluid">
        <div class="row justify-content-start">
            <!-- Таблица со списком инвентаря -->
            <div class="col-6 border bg-light px-4">
                <h3>Список инвентаря</h3>
                <table class="table table-bordered table-striped">
                    <thead class="table-dark">
                        <tr>
                            <th>ID</th><th>Инв.номер</th><th>Название</th>
                            <th>Кол-во</th><th>Аудитория</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${inventories}">
                            <tr>
                                <td>${item.id}</td>
                                <td>${item.inventoryNumber}</td>
                                <td>${item.name}</td>
                                <td>${item.quantity}</td>
                                <td>${item.audience.number}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            
            <!-- Форма редактирования -->
            <div class="col-6 border px-4">
                <form method="POST" action="${pageContext.request.contextPath}/editInventory">
                    <h3>Редактирование инвентаря</h3>
                    
                    <div class="mb-3">
                        <label class="form-label">Код</label>
                        <input type="text" class="form-control" readonly 
                               value="${inventoryEdit.id}">
                        <input type="hidden" name="id" value="${inventoryEdit.id}">
                    </div>
                    
                    <div class="mb-3">
                        <label class="form-label">Инвентарный номер</label>
                        <input type="text" name="inventoryNumber" class="form-control" 
                               value="${inventoryEdit.inventoryNumber}" required>
                    </div>
                    
                    <div class="mb-3">
                        <label class="form-label">Название</label>
                        <input type="text" name="name" class="form-control" 
                               value="${inventoryEdit.name}" required>
                    </div>
                    
                    <div class="mb-3">
                        <label class="form-label">Количество</label>
                        <input type="number" name="quantity" class="form-control" 
                               value="${inventoryEdit.quantity}">
                    </div>
                    
                    <div class="mb-3">
                        <label class="form-label">Описание</label>
                        <textarea name="description" class="form-control" rows="3">${inventoryEdit.description}</textarea>
                    </div>
                    
                    <div class="mb-3">
                        <label class="form-label">Аудитория</label>
                        <select name="idAudience" class="form-control">
                            <option value="">-- Выберите аудиторию --</option>
                            <c:forEach var="audience" items="${audiences}">
                                <option value="${audience.id}" 
                                    ${inventoryEdit.idAudience == audience.id ? 'selected' : ''}>
                                    ${audience.number}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    
                    <button type="submit" class="btn btn-primary">Сохранить</button>
                    <a href="${pageContext.request.contextPath}/inventory" 
                       class="btn btn-secondary">Отмена</a>
                </form>
            </div>
        </div>
    </div>
    
    <jsp:include page="footer.jsp" />
</div>
<script src="${pageContext.request.contextPath}/js/jquery-3.6.4.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>