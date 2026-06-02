<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Редактирование аудитории</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body>
<div class="container-fluid">
    <jsp:include page="header.jsp" />
    
    <div class="container-fluid">
        <div class="row justify-content-start">
            <!-- Таблица со списком аудиторий -->
            <div class="col-6 border bg-light px-4">
                <h3>Список аудиторий</h3>
                <table class="table table-bordered table-striped">
                    <thead class="table-dark">
                        <tr><th>Код</th><th>Номер</th><th>Ответственный</th></tr>
                    </thead>
                    <tbody>
                        <c:forEach var="audience" items="${audiences}">
                            <tr>
                                <td>${audience.id}</td>
                                <td>${audience.number}</td>
                                <td>${audience.responsible}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            
            <!-- Форма редактирования -->
            <div class="col-6 border px-4">
                <form method="POST" action="${pageContext.request.contextPath}/editAudience">
                    <h3>Редактирование аудитории</h3>
                    
                    <div class="mb-3">
                        <label class="form-label">Код аудитории</label>
                        <input type="text" class="form-control" readonly 
                               value="${audienceEdit.id}">
                        <input type="hidden" name="id" value="${audienceEdit.id}">
                    </div>
                    
                    <div class="mb-3">
                        <label class="form-label">Номер аудитории</label>
                        <input type="text" name="number" class="form-control" 
                               value="${audienceEdit.number}" required>
                    </div>
                    
                    <div class="mb-3">
                        <label class="form-label">Ответственный</label>
                        <input type="text" name="responsible" class="form-control" 
                               value="${audienceEdit.responsible}">
                    </div>
                    
                    <button type="submit" class="btn btn-primary">Сохранить</button>
                    <a href="${pageContext.request.contextPath}/audience" 
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