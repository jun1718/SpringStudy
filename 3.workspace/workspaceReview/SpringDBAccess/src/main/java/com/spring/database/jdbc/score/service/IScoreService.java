package com.spring.database.jdbc.score.service;

import java.util.List;

import com.spring.database.jdbc.score.model.ScoreVO;

public interface IScoreService {
	//점수 등록 기능 
	void insertScore(ScoreVO score);
	
	//점수 전체 조회 기능
	List<ScoreVO> selectAllScores();
	
	//점수 삭제기능
	void deleteScore(int stuNum); // stuNum을 사용
	
	ScoreVO selectOneScore(int stuNum);
}