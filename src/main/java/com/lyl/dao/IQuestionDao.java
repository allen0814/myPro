package com.lyl.dao;

import com.lyl.entity.Question;

import java.util.List;

public interface IQuestionDao {

    public void saveQuestion(Question question);

    public List<Question> queryQuestion(Question question);

    public Question singleQuestion(Question question);

    public void updateQuestion(Question question);

}
