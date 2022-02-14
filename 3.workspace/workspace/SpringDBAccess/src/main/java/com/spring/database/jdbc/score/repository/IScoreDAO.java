package com.spring.database.jdbc.score.repository;

import java.util.List;

import com.spring.database.jdbc.score.model.ScoreVO;

public interface IScoreDAO {
	//점수 등록 기능
	void insertScore(ScoreVO scores);
	
	//점수 전체 조회 기능
	List<ScoreVO> selectAllScores();
	
	//점수 삭제기능
	void deleteScore(int stuNum);
	
	ScoreVO selectOneScore(int stuNum);
}
