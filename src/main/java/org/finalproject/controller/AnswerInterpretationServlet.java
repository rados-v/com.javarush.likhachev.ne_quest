package org.finalproject.controller;

import org.finalproject.repository.AnswerInterpretationRepository;
import org.finalproject.repository.QuestionRepository;
import org.finalproject.service.AnswerAnalysisService;
import org.finalproject.service.AnswerInterpretationService;
import org.finalproject.service.AnswerProcessingService;
import org.finalproject.service.QuestionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * AnswerInterpretationServlet - сервлет для интерпретации результатов ответов.
 *
 * Этот сервлет получает подсчитанные в AnswerAnalysisServlet данные о количестве
 * ответов по категориям (экстраверсия, нейротизм, искренность), и на основе этих
 * данных генерирует интерпретации для каждой категории. Интерпретации сохраняются
 * в сессии, после чего происходит перенаправление на страницу управления сессиями
 * (SessionManagementServlet).
 *
 * Логика:
 * 1. Извлекает подсчитанные значения из сессии.
 * 2. Получает интерпретацию для каждой категории (экстраверсия, нейротизм, искренность).
 * 3. Сохраняет интерпретации в сессии.
 * 4. Перенаправляет на /sessionManagement для дальнейшей обработки.
 */

@WebServlet(name = "AnswerInterpretationServlet", value = "/answerInterpretationServlet")
public class AnswerInterpretationServlet extends HttpServlet {

    public final AnswerInterpretationRepository answerInterpretationRepository = new AnswerInterpretationRepository();
    public final AnswerInterpretationService answerInterpretationService = new AnswerInterpretationService(answerInterpretationRepository);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String interpretationExtroversion = answerInterpretationService.getInterpretationExtroversion( (int) session.getAttribute("countAnswersByCategoryExtroversionAll"));
        String interpretationNeuroticism = answerInterpretationService.getInterpretationNeuroticism( (int) session.getAttribute("countAnswersByCategoryNeuroticismAll"));
        String interpretationSincerity = answerInterpretationService.getInterpretationSincerity( (int) session.getAttribute("countAnswersByCategorySincerityAll"));

        session.setAttribute("interpretationExtroversion", interpretationExtroversion);
        session.setAttribute("interpretationNeuroticism", interpretationNeuroticism);
        session.setAttribute("interpretationSincerity", interpretationSincerity);

        resp.sendRedirect("sessionManagement?resetQuestionRange=true");
    }
}
