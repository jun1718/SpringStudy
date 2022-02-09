package com.spring.web.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.web.model.ScoreVO;

@Repository
public class ScoreDAO implements IScoreDAO {
	private List<ScoreVO> list = new ArrayList<>();
	
	@Override
	public void insertScore(ScoreVO score) {
		// TODO Auto-generated method stub
		list.add(score);
	}

	@Override
	public List<ScoreVO> selectAllScores() {
		// TODO Auto-generated method stub
		return list;
	}

	@Override
	public void deleteScore(int stuNum) {
		// TODO Auto-generated method stub
		list.remove(stuNum - 1);
	}

	public ScoreVO selectOneScore(int stuNum) {
		return list.get(stuNum - 1);
	}
}
