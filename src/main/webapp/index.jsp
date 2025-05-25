<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Начнем</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Special+Gothic&display=swap">
    </head>
    <body>
        <div class="text-center mb-3">
            <h1>Добро пожаловать в тест Айзенка</h1>
        </div>

        <div class="container text-center mt-2 mb-4" style="max-width: 800px;">
            <div class="py-4 px-4 border border-1 rounded text-start" style="background-color: #f8f9fa;">
                <p class="fs-5"> Это психологическое исследование основано на методике <strong>Ганса Айзенка</strong> — одного из ведущих психологов XX века.</p>
                <p class="fs-5"> В ходе теста вы ответите на серию вопросов, которые помогут определить особенности вашей личности по трем шкалам:</p>
                <ul class="fs-5">
                    <li><strong>Экстраверсия</strong> — насколько вы общительны, активны и ориентированы на внешний мир;</li>
                    <li><strong>Невротизм</strong> — уровень вашей эмоциональной устойчивости и склонности к тревожности;</li>
                    <li><strong>Искренность</strong> — степень откровенности и честности в ваших ответах.</li>
                </ul>
                <p class="fs-5"> Результаты помогут вам лучше понять свои черты характера, а также получить краткую психологическую интерпретацию.</p>
            </div>
        </div>

        <div class="container" style="max-width: 800px;">
            <div>
                <form action="sessionManagement" method="get">
                    <input type="hidden" name="resetAnswerResults" value="true">
                    <button class="btn btn-primary w-100 fw-bold" type="submit">Начать тест</button>
                </form>

                <c:choose>
                    <c:when test="${true}">
                        <form action="/results.jsp" method="get">
                            <button class="btn btn-primary w-100 fw-bold" type="submit">Перейти к результатам</button>
                        </form>
                    </c:when>
                </c:choose>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
