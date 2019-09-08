package com.lyl.dao;

import com.lyl.entity.Answer;

import java.util.List;

public interface IAnswerDao {

    public void saveAnswer(Answer answer);

    public List<Answer> queryAnswer(Answer answer);
}
