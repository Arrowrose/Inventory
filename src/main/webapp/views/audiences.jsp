<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Аудитории</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body>
<div class="container-fluid">
    <jsp:include page="header.jsp" />
    
    <div class="container-fluid">
        <div class="row justify-content-start">
            <!-- Таблица списка аудиторий -->
            <div class="col-8 border bg-light px-4">
                <h3>Список аудиторий</h3>
                
                <c:if test="${not empty error}">
                    <div class="alert alert-danger">${error}</div>
                </c:if>
                
                <table class="table table-bordered table-striped">
                    <thead class="table-dark">
                        <tr>
                            <th>Код</th>
                            <th>Номер аудитории</th>
                            <th>Ответственный</th>
                            <th>✏️</th>
                            <th>🗑️</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="audience" items="${audiences}">
                            <tr>
                                <td>${audience.id}</td>
                                <td>${audience.number}</td>
                                <td>${audience.responsible}</td>
                                <td width="20">
                                    <a href="${pageContext.request.contextPath}/editAudience?id=${audience.id}" 
								       class="btn btn-outline-primary btn-sm">
								        ✏️
								    </a>
                                </td>
                                <td>
                                    <a href="?delete=${audience.id}" 
                                       class="btn btn-outline-danger btn-sm"
                                       onclick="return confirm('Удалить аудиторию ${audience.number}?')">
                                        🗑️
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        <c:if test="${empty audiences}">
                            <tr><td colspan="5" class="text-center">Нет данных</td></tr>
                        </c:if>
                    </tbody>
                </table>
            </div>
            
            <!-- Форма добавления новой аудитории -->
            <div class="col-4 border px-4">
                <form method="POST" action="${pageContext.request.contextPath}/audience">
                    <h3>Новая аудитория</h3>
                    <div class="mb-3">
                        <label class="form-label">Номер аудитории</label>
                        <input type="text" name="number" class="form-control" required/>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Ответственный</label>
                        <input type="text" name="responsible" class="form-control" />
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