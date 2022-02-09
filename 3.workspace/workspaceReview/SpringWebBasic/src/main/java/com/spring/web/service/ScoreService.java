package com.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.web.model.ScoreVO;
import com.spring.web.repository.IScoreDAO;

@Service
public class ScoreService implements IScoreService {
	@Autowired
	private IScoreDAO dao;
	
	@Override
	public void insertScore(ScoreVO score) {
		// TODO Auto-generated method stub
		dao.insertScore(score);
	}

	@Override
	public List<ScoreVO> selectAllScores() {
		// TODO Auto-generated method stub
		return dao.selectAllScores();
	}

	@Override
	public void deleteScore(int stuNum) {
		// TODO Auto-generated method stub
		dao.deleteScore(stuNum);
	}
	
	
	public ScoreVO selectOneScore(int stuNum) {
		return dao.selectOneScore(stuNum);
	}
}
