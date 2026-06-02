<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <title>Главная - Учёт аудиторий</title>
</head>
<body>
<jsp:include page="/views/header.jsp" />

<div class="container text-center mt-5">
    <div class="card shadow-sm p-4">
        <h2>Функции системы</h2>
        <div class="list-group mt-3">
            <a href="/Inventory/audience" class="list-group-item list-group-item-action list-group-item-primary">
                🪑 Аудитории
            </a>
            <a href="/Inventory/inventory" class="list-group-item list-group-item-action list-group-item-info">
                📦 Инвентарь
            </a>
        </div>
    </div>
</div>

<jsp:include page="/views/footer.jsp" />

<script src="js/jquery-3.6.4.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>