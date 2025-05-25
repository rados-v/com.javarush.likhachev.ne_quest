package org.finalproject.controller;

import org.finalproject.repository.QuestionRepository;
import org.finalproject.service.AnswerProcessingService;
import org.finalproject.service.QuestionService;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * AnswerProcessingServlet - сервлет для обработки и подсчета ответов пользователей.
 *
 * Этот сервлет обрабатывает ответы пользователя на вопросы, сохраняет их в сессии
 * и обновляет статистику о количестве положительных и отрицательных ответов.
 * Далее, он перенаправляет управление на AnswerAnalysisServlet для анализа ответов
 * или на следующий вопрос в зависимости от текущего состояния.
 *
 * Логика:
 * 1. Обрабатывает параметры запроса, получая ответы пользователя.
 * 2. Обновляет количество положительных и отрицательных ответов.
 * 3. Обновляет карту ответов в сессии.
 * 4. Перенаправляет на /answerAnalysisServlet для анализа ответов.
 */

@WebServlet (name = "ProcessingResponsesServlet", value = "/processingResponses")
public class AnswerProcessingServlet extends HttpServlet {

    private final QuestionRepository questionRepository = new QuestionRepository();
    private final QuestionService questionService = new QuestionService(questionRepository);
    private final AnswerProcessingService answerProcessingService = new AnswerProcessingService(questionService);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        Integer answersYes = (Integer) session.getAttribute("answersYes");
        Integer answersNo = (Integer) session.getAttribute("answersNo");

        if (answersYes == null) {answersYes = 0;}
        if (answersNo == null) {answersNo = 0;}

        @SuppressWarnings("unchecked")
        Map<String, String> answersMap = (Map<String, String>) session.getAttribute("answersMap");
        if (answersMap == null) {answersMap = new HashMap<>();}

        Map<String, String> extractAnswers = answerProcessingService.extractAnswers(req.getParameterMap());
        answersMap.putAll(extractAnswers);

        Map<String, Integer> answerCounts = answerProcessingService.countAnswers(req.getParameterMap());
        answersYes += answerCounts.get("answersYes");
        answersNo += answerCounts.get("answersNo");

        String startParam = req.getParameter("start");
        int nextIndex = (startParam != null) ? Integer.parseInt(startParam) : 0;
        int getQuestionsCount = (int) session.getAttribute("getQuestionsCount");

        session.setAttribute("answersYes", answersYes);
        session.setAttribute("answersNo", answersNo);
        session.setAttribute("answersMap", answersMap);
        session.setAttribute("nextIndex", nextIndex);
        session.setAttribute("getQuestionsCount", getQuestionsCount);

        RequestDispatcher dispatcher = req.getRequestDispatcher("answerAnalysisServlet");
        dispatcher.forward(req, resp);
    }
}