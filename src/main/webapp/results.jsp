<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Результаты</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Special+Gothic&display=swap">
    </head>
    <body>

        <div class="text-center mb-3">
            <h1>Результаты</h1>
        </div>

        <div class="container" style="max-width: 800px;">
            <div class="py-2 px-4 mb-3 rounded border border-1 text-center" style="background-color: #f8f9fa;">

                <div class="py-2 mb-3">
                    <h4>Экстраверсия/Интроверсия</h4>
                    <div class="py-2 mb-3">
                        <div style="height: 15px; display: flex; margin-top: 10px;">
                            <c:forEach var="i" begin="1" end="24">
                                <div style="
                                    flex: 1; margin-right: 1px; border-radius: 5px;
                                    background-color: ${i <= countAnswersByCategoryExtroversionAll ? '#0d6efd' : '#e0e0e0'};">
                                </div>
                            </c:forEach>
                        </div>
                        <div style="display: flex; justify-content: space-between; font-size: 12px; margin-top: 5px;">
                            <span>0</span>
                            <span>5</span>
                            <span>10</span>
                            <span>15</span>
                            <span>20</span>
                            <span>24</span>
                        </div>
                    </div>
                    <div class="py-2 px-4 rounded border border-primary text-center text-dark fw-bold font-monospace">${interpretationExtroversion}</div>
                </div>


                <div class="py-2 mb-3">
                    <h4>Нейротизм</h4>
                    <div class="py-2 mb-3">
                        <div style="height: 15px; display: flex; margin-top: 10px;">
                            <c:forEach var="i" begin="1" end="24">
                                <div style="
                                    flex: 1; margin-right: 1px; border-radius: 5px;
                                    background-color: ${i <= countAnswersByCategoryNeuroticismAll ? '#0d6efd' : '#e0e0e0'};">
                                </div>
                            </c:forEach>
                        </div>
                        <div style="display: flex; justify-content: space-between; font-size: 12px; margin-top: 5px;">
                            <span>0</span>
                            <span>5</span>
                            <span>10</span>
                            <span>15</span>
                            <span>20</span>
                            <span>24</span>
                        </div>
                    </div>
                    <div class="py-2 px-4 rounded border border-primary text-center text-dark fw-bold font-monospace">${interpretationNeuroticism}</div>
                </div>


                <div class="py-2">
                    <h4>Контрольная шкала</h4>
                    <div class="py-2 mb-3">
                        <div style="height: 15px; display: flex; margin-top: 10px;">
                            <c:forEach var="i" begin="1" end="9">
                                <div style="
                                    flex: 1; margin-right: 1px; border-radius: 5px;
                                    background-color: ${i <= countAnswersByCategorySincerityAll ? '#0d6efd' : '#e0e0e0'};">
                                </div>
                            </c:forEach>
                        </div>
                        <div style="display: flex; justify-content: space-between; font-size: 12px; margin-top: 5px;">
                            <span>0</span>
                            <span>3</span>
                            <span>6</span>
                            <span>9</span>
                        </div>
                    </div>
                    <div class="py-2 px-4 rounded border border-primary text-center text-dark fw-bold font-monospace">${interpretationSincerity}</div>
                </div>
            </div>

            <form action="/interpretation.jsp" method="get">
                <button class="btn btn-primary w-100 fw-bold" type="submit">Интерпретации</button>
            </form>

            <form action="/index.jsp" method="get">
                <button class="btn btn-primary w-100 fw-bold" type="submit">Главное меню</button>
            </form>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
