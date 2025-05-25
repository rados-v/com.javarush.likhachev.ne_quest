package org.finalproject.controller;

import org.finalproject.repository.QuestionRepository;
import org.finalproject.service.AnswerAnalysisService;
import org.finalproject.service.AnswerProcessingService;
import org.finalproject.service.QuestionService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * AnswerAnalysisServlet - сервлет для анализа ответов пользователей.
 *
 * Этот сервлет отвечает за подсчет ответов пользователя по трем категориям:
 * экстраверсия, нейротизм и искренность. Он обрабатывает параметры, передаваемые
 * в запросе, и обновляет сохраненные значения в сессии. После анализа ответов,
 * в зависимости от того, все ли вопросы были отвечены, сервлет либо перенаправляет
 * пользователя к следующему вопросу (QuestionsServlet), либо к интерпретации ответов
 * (AnswerInterpretationServlet).
 *
 * Логика:
 * 1. Считывает сохраненные в сессии значения подсчета ответов.
 * 2. Обрабатывает ответы, переданные в запросе, и обновляет подсчеты.
 * 3. Если все вопросы пройдены, перенаправляет на AnswerInterpretationServlet.
 * 4. Если не все вопросы пройдены, перенаправляет на следующий вопрос.
 */

@WebServlet(name = "AnswerAnalysisServlet", value = "/answerAnalysisServlet")
public class AnswerAnalysisServlet extends HttpServlet {

    private final QuestionRepository questionRepository = new QuestionRepository();
    private final QuestionService questionService = new QuestionService(questionRepository);
    private final AnswerProcessingService answerProcessingService = new AnswerProcessingService(questionService);
    private final AnswerAnalysisService answerAnalysisService = new AnswerAnalysisService(questionRepository, answerProcessingService);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        Integer prevCountExtroversionAll = (Integer) session.getAttribute("countAnswersByCategoryExtroversionAll");
        Integer prevCountNeuroticismAll = (Integer) session.getAttribute("countAnswersByCategoryNeuroticismAll");
        Integer prevCountSincerityAll = (Integer) session.getAttribute("countAnswersByCategorySincerityAll");

        if (prevCountExtroversionAll == null) { prevCountExtroversionAll = 0; }
        if (prevCountNeuroticismAll == null) { prevCountNeuroticismAll = 0; }
        if (prevCountSincerityAll == null) { prevCountSincerityAll = 0; }

        int currentCountExtroversionAll = answerAnalysisService.countAnswersByCategoryExtroversionAll(req.getParameterMap());
        int currentCountNeuroticismAll = answerAnalysisService.countAnswersByCategoryNeuroticismAll(req.getParameterMap());
        int currentCountSincerityAll = answerAnalysisService.countAnswersByCategorySincerityAll(req.getParameterMap());

        int totalCountExtroversionAll = prevCountExtroversionAll + currentCountExtroversionAll;
        int totalCountNeuroticismAll = prevCountNeuroticismAll + currentCountNeuroticismAll;
        int totalCountSincerityAll = prevCountSincerityAll + currentCountSincerityAll;

        session.setAttribute("countAnswersByCategoryExtroversionAll", totalCountExtroversionAll);
        session.setAttribute("countAnswersByCategoryNeuroticismAll", totalCountNeuroticismAll);
        session.setAttribute("countAnswersByCategorySincerityAll", totalCountSincerityAll);

        int nextIndex = (Integer) session.getAttribute("nextIndex");
        int getQuestionsCount = (Integer) session.getAttribute("getQuestionsCount");
        if (nextIndex >= getQuestionsCount) {
            resp.sendRedirect("answerInterpretationServlet");

        } else {
            resp.sendRedirect("questions?start=" + nextIndex);
        }
    }
}