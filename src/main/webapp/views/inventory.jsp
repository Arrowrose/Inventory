<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Инвентарь</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body>
<div class="container-fluid">
    <jsp:include page="header.jsp" />
    
    <div class="container-fluid">
        <div class="row justify-content-start">
            <!-- Таблица списка инвентаря -->
            <div class="col-8 border bg-light px-4">
                <h3>Список инвентаря</h3>
                
                <c:if test="${not empty error}">
                    <div class="alert alert-danger">${error}</div>
                </c:if>
                
                <table class="table table-bordered table-striped">
                    <thead class="table-dark">
                        <tr>
                            <th>ID</th><th>Инв. номер</th><th>Название</th>
                            <th>Кол-во</th><th>Аудитория</th><th>✏️</th><th>🗑️</th>
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
                                <td><a href="#" class="btn btn-outline-primary btn-sm">✏️</a></td>
                                <td>
                                    <a href="?delete=${item.id}" 
                                       class="btn btn-outline-danger btn-sm"
                                       onclick="return confirm('Удалить ${item.name}?')">
                                        🗑️
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        <c:if test="${empty inventories}">
                            <tr><td colspan="7" class="text-center">Нет данных</td></tr>
                        </c:if>
                    </tbody>
                </table>
            </div>
            
            <!-- Форма добавления нового инвентаря -->
            <div class="col-4 border px-4">
                <form method="POST" action="${pageContext.request.contextPath}/inventory">
                    <h3>Новый инвентарь</h3>
                    <div class="mb-3">
                        <label class="form-label">Инвентарный номер</label>
                        <input type="text" name="inventoryNumber" class="form-control" required/>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Название</label>
                        <input type="text" name="name" class="form-control" required/>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Количество</label>
                        <input type="number" name="quantity" class="form-control" value="0"/>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Описание</label>
                        <textarea name="description" class="form-control" rows="2"></textarea>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Аудитория</label>
                        <select name="idAudience" class="form-control">
                            <option value="">-- Выберите аудиторию --</option>
                            <c:forEach var="audience" items="${audiences}">
                                <option value="${audience.id}">${audience.number}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Добавить</button>
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