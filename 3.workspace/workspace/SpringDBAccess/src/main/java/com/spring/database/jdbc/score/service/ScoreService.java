package com.spring.database.jdbc.score.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.database.jdbc.score.model.ScoreVO;
import com.spring.database.jdbc.score.repository.IScoreDAO;


@Service
public class ScoreService implements IScoreService {

	@Autowired
	private IScoreDAO dao;
	
	@Override
	public void insertScore(ScoreVO scores) {
		// TODO Auto-generated method stub
		scores.calcData();
		dao.insertScore(scores);
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
