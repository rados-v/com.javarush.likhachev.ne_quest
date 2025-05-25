<%@ page import="java.util.List" %>
<%@ page import="org.finalproject.entity.Questions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ru">
    <head>
        <title>Список вопросов</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Special+Gothic&display=swap">
    </head>
    <body>

        <div class="text-center mb-3">
            <h1>Список вопросов</h1>
        </div>

        <form action="processingResponses" method="get">
            <div class="d-flex justify-content-center align-items-center min-vh-100" >
                <div class="container" style="max-width: 800px;">

                        <c:forEach var="q" items="${currentQuestions}">
                            <div class="row mb-3">

                                <div class="col-12 mb-2">
                                    <div class="py-3 px-3 rounded border border-1 text-center fw-bolder">
                                        ${q.questionFormulation}
                                    </div>
                                </div>

                                <div class="col-6">
                                    <input type="radio" class="btn-check" name="answer_${q.id}" id="yesOption_${q.id}" value="yes" autocomplete="off">
                                    <label class="btn btn-outline-primary w-100" for="yesOption_${q.id}">Да</label>
                                </div>

                                <div class="col-6 mb-3">
                                    <input type="radio" class="btn-check" name="answer_${q.id}" id="noOption_${q.id}" value="no" autocomplete="off">
                                    <label class="btn btn-outline-primary w-100" for="noOption_${q.id}">Нет</label>
                                </div>
                            </div>
                        </c:forEach>


                    <div class="text-center" style="max-width: 800px;">
                        <input type="hidden" name="start" value="${nextIndex}">
                        <c:choose>
                            <c:when test="${nextIndex < getQuestionsCount}">
                                <button type="submit" class="btn btn-primary w-100 fw-bold">Следующие вопросы</button>
                            </c:when>
                            <c:otherwise>
                                <button type="submit" class="btn btn-success w-100 ">Перейти к результатам</button>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </form>

        <div class="fw-lighter">Было отвечено ${nextIndex - 10}</div>
        <div class="fw-lighter">Всего вопросов ${getQuestionsCount}</div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
