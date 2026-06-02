<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="domain.Inventory"%>
<%@ page import="domain.Audience"%>

<%
    // Тестовые данные для аудиторий (для связи)
    Audience a1 = new Audience(1L, "101", "Иванов И.И.");
    Audience a2 = new Audience(2L, "102", "Петрова А.С.");
    
    // Тестовые данные для инвентаря
    Inventory i1 = new Inventory(1L, "INV-001", "Проектор", 2, "Full HD, 3000 люмен", 1L, a1);
    Inventory i2 = new Inventory(2L, "INV-002", "Ноутбук", 5, "Lenovo ThinkPad", 1L, a1);
    Inventory i3 = new Inventory(3L, "INV-003", "Доска маркерная", 1, "120x90 см", 2L, a2);
    Inventory i4 = new Inventory(4L, "INV-004", "Стол ученический", 10, "Однотумбовый", 2L, a2);
    Inventory[] inventories = new Inventory[]{i1, i2, i3, i4};
    pageContext.setAttribute("inventories", inventories);
    
    // Массив аудиторий для выпадающего списка
    Audience[] audiences = new Audience[]{a1, a2};
    pageContext.setAttribute("audiences", audiences);
%>

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
            <!-- Таблица с списком инвентаря -->
            <div class="col-8 border bg-light px-4">
                <h3>Список инвентаря</h3>
                <table class="table table-bordered table-striped">
                    <thead class="table-dark">
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Инв. номер</th>
                            <th scope="col">Название</th>
                            <th scope="col">Количество</th>
                            <th scope="col">Аудитория</th>
                            <th scope="col">Редактировать</th>
                            <th scope="col">Удалить</th>
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
                                <td width="20">
                                    <a href="#" class="btn btn-outline-primary btn-sm">✏️</a>
                                </td>
                                <td width="20">
                                    <a href="#" class="btn btn-outline-danger btn-sm">🗑️</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            
            <!-- Форма для добавления нового инвентаря -->
            <div class="col-4 border px-4">
                <form method="POST" action="">
                    <h3>Новый инвентарь</h3>
                    
                    <div class="mb-3">
                        <label class="form-label">Инвентарный номер</label>
                        <input type="text" name="inventoryNumber" class="form-control" />
                    </div>
                    
                    <div class="mb-3">
                        <label class="form-label">Название</label>
                        <input type="text" name="name" class="form-control" />
                    </div>
                    
                    <div class="mb-3">
                        <label class="form-label">Количество</label>
                        <input type="number" name="quantity" class="form-control" />
                    </div>
                    
                    <div class="mb-3">
                        <label class="form-label">Описание</label>
                        <textarea name="description" class="form-control" rows="3"></textarea>
                    </div>
                    
                    <div class="mb-3">
                        <label class="form-label">Аудитория</label>
                        <select name="idAudience" class="form-control">
                            <option value="">Выберите аудиторию</option>
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