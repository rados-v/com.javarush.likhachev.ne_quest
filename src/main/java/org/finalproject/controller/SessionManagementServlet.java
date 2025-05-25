package org.finalproject.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * SessionManagementServlet - сервлет для управления сессиями пользователей.
 *
 * Этот сервлет отвечает за сброс состояния сессии и навигацию между страницами.
 * Он предоставляет функции для сброса данных о вопросах, ответах и интерпретациях,
 * а также завершает сессию, перенаправляя пользователя на страницу с результатами.
 *
 * Логика:
 * 1. Обрабатывает параметры сброса состояния сессии.
 * 2. Если параметр resetQuestionRange задан, сбрасывает индексы вопросов и перенаправляет на страницу результатов.
 * 3. Если параметр resetAnswerResults задан, сбрасывает данные о ответах и перенаправляет на начало теста.
 */

@WebServlet (name = "SessionManagementServlet", value = "/sessionManagement")
public class SessionManagementServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (req.getParameter("resetQuestionRange") != null) {
            session.setAttribute("startIndex", 0);
            session.setAttribute("nextIndex", 0);

            resp.sendRedirect("/results.jsp");
            return;
        }

        if (req.getParameter("resetAnswerResults") != null) {
            session.setAttribute("answersYes", 0);
            session.setAttribute("answersNo", 0);
            session.setAttribute("countAnswersByCategoryExtroversionAll", 0);
            session.setAttribute("countAnswersByCategoryNeuroticismAll", 0);
            session.setAttribute("countAnswersByCategorySincerityAll", 0);

            resp.sendRedirect("questions");
            return;
        }
    }
}
