package com.lyl.service.impl;

import com.lyl.dao.IAnswerDao;
import com.lyl.entity.Answer;
import com.lyl.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private IAnswerDao answerDao;

    @Override
    public void saveAnswer(Answer answer) {
        answerDao.saveAnswer(answer);
    }

    @Override
    public List<Answer> queryAnswer(Answer answer) {
        return answerDao.queryAnswer(answer);
    }
}
