package com.spring.database.mybatis.score.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.database.mybatis.score.model.ScoreVO;
import com.spring.database.mybatis.score.repository.IScoreMapper;

@Service("scoreService2")
public class ScoreService implements IScoreService {
	@Autowired
	private IScoreMapper dao;
	
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
