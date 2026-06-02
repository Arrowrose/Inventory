<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="domain.Audience"%>

<%
    Audience a1 = new Audience(1L, "101", "Иванов И.И.");
    Audience a2 = new Audience(2L, "102", "Петрова А.С.");
    Audience a3 = new Audience(3L, "201", "Сидоров В.В.");
    Audience a4 = new Audience(4L, "202", "Кузнецова Е.М.");
    Audience[] audiences = new Audience[]{a1, a2, a3, a4};
    pageContext.setAttribute("audiences", audiences);
%>

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
            <div class="col-8 border bg-light px-4">
                <h3>Список аудиторий</h3>
                <table class="table table-bordered table-striped">
                    <thead class="table-dark">
                        <tr><th scope="col">Код</th>
                            <th scope="col">Номер аудитории</th>
                            <th scope="col">Ответственный</th>
                            <th scope="col">Редактировать</th>
                            <th scope="col">Удалить</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="audience" items="${audiences}">
                            <tr>
                                <td>${audience.id}</td>
                                <td>${audience.number}</td>
                                <td>${audience.responsible}</td>
                                <td>
                                    <a href="#" class="btn btn-outline-primary btn-sm">✏️</a>
                                </td>
                                <td>
                                    <a href="#" class="btn btn-outline-danger btn-sm">🗑️</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            
            <div class="col-4 border px-4">
                <form method="POST" action="">
                    <h3>Новая аудитория</h3>
                    <div class="mb-3">
                        <label class="form-label">Номер аудитории</label>
                        <input type="text" name="number" class="form-control" />
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