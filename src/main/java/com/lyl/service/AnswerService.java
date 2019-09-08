package com.lyl.service;

import com.lyl.entity.Answer;

import java.util.List;

public interface AnswerService {

    public void saveAnswer(Answer answer);

    public List<Answer> queryAnswer(Answer answer);
}
