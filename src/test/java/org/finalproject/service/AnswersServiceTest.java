package org.finalproject.service;

import org.finalproject.entity.AnswersDto;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
class AnswersServiceTest {

    private final AnswersService answersService = new AnswersService();

    @Test
    public void testCalculateTotals_validInput() {
        AnswersDto dto = new AnswersDto(3, 2, 4, 5, 1);

        Map<String, Integer> result = answersService.calculateTotals(dto);

        assertEquals(3, result.get("extroversionYes"));
        assertEquals(2, result.get("extroversionNo"));
        assertEquals(4, result.get("neuroticismYes"));
        assertEquals(5, result.get("sincerityYes"));
        assertEquals(1, result.get("sincerityNo"));
    }

    @Test
    public void testCalculateTotals_allZero() {
        AnswersDto dto = new AnswersDto(0, 0, 0, 0, 0);

        Map<String, Integer> result = answersService.calculateTotals(dto);

        assertEquals(0, result.get("extroversionYes"));
        assertEquals(0, result.get("extroversionNo"));
        assertEquals(0, result.get("neuroticismYes"));
        assertEquals(0, result.get("sincerityYes"));
        assertEquals(0, result.get("sincerityNo"));
    }
}