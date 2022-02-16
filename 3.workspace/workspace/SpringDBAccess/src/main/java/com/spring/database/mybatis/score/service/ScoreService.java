package com.spring.database.mybatis.score.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.database.mybatis.score.repository.IScoreMapper;
import com.spring.database.mybatis.score.model.ScoreVO;


@Service("scoreService2")
public class ScoreService implements IScoreService {

	@Autowired
	private IScoreMapper dao;
	
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
