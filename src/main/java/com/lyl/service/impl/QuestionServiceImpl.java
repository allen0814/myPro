package com.lyl.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyl.dao.IQuestionDao;
import com.lyl.entity.Question;
import com.lyl.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("questionService")
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private IQuestionDao questionDao;

    @Override
    public void saveQuestion(Question question) {
        questionDao.saveQuestion(question);
    }

    @Override
    public PageInfo<Question> queryQuestion(Question question, Integer currentPage, Integer pageSize) {

        PageHelper.startPage(currentPage,pageSize);

        List<Question> list = questionDao.queryQuestion(question);

        PageInfo<Question> pageList = new PageInfo<>(list);
        return pageList;
    }

    @Override
    public Question singleQuestion(Question question) {
        return questionDao.singleQuestion(question);
    }

    @Override
    public void updateQuestion(Question question) {
        questionDao.updateQuestion(question);
    }
}
