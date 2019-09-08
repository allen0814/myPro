package com.lyl.service;

import com.github.pagehelper.PageInfo;
import com.lyl.entity.Question;

public interface QuestionService {

    public void saveQuestion(Question question);

    public PageInfo<Question> queryQuestion(Question question, Integer currentPage, Integer pageSize);

    public Question singleQuestion(Question question);

    public void updateQuestion(Question question);
}
