package org.finalproject.controller;

import org.finalproject.repository.QuestionRepository;
import org.finalproject.entity.Questions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * QuestionServlet - сервлет для отображения вопросов и навигации по ним.
 *
 * Этот сервлет управляет отображением вопросов на основе текущего индекса.
 * Он также обрабатывает навигацию между вопросами (переход к следующему вопросу)
 * и передает управление на следующий вопрос или завершает тест.
 *
 * Логика:
 * 1. Извлекает из сессии индекс текущего вопроса (startIndex).
 * 2. Загружает вопросы из базы данных с учетом индекса и размера пакета.
 * 3. Обновляет данные о текущем индексе и следующем вопросе.
 * 4. Отображает вопросы на странице (questionnaire.jsp).
 */

@WebServlet(name = "QuestionsServlet", value = "/questions")
public class QuestionServlet extends HttpServlet {

    private final QuestionRepository repository = new QuestionRepository();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);

        if (session.getAttribute("startIndex") == null) {
            session.setAttribute("startIndex", 0);
        }

        int startIndex = (int) session.getAttribute("startIndex");

        if (req.getParameter("start") != null) {
            startIndex = Integer.parseInt(req.getParameter("start"));
            session.setAttribute("startIndex", startIndex);
            resp.sendRedirect("questions");
            return;
        }

        int batchSize = 10;
        int nextIndex = startIndex + batchSize;
        int getQuestionsCount = repository.getQuestionsCount();

        List<Questions> currentQuestions = repository.getQuestionsForRange(startIndex, batchSize);

        session.setAttribute("startIndex", startIndex);
        session.setAttribute("getQuestionsCount", getQuestionsCount);
        session.setAttribute("currentQuestions", currentQuestions);
        session.setAttribute("nextIndex", nextIndex);

       getServletContext().getRequestDispatcher("/questionnaire.jsp").forward(req, resp);
    }
}