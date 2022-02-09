package com.spring.web.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.web.model.ScoreVO;

@Repository
public class ScoreDAO implements IScoreDAO {
	//학생들의 점수정보를 저장할 리스트 생성(DB대용)_ DB대용 : scoreList
	private List<ScoreVO> scoreList = new ArrayList<>();
	
	@Override
	public void insertScore(ScoreVO scores) {
		// TODO Auto-generated method stub
		System.out.println("Repository param: " + scores);
		scoreList.add(scores);
	}

	@Override
	public List<ScoreVO> selectAllScores() {
		// TODO Auto-generated method stub
		return scoreList;
	}

	@Override
	public void deleteScore(int stuNum) {
		// TODO Auto-generated method stub
		scoreList.remove(stuNum - 1);
	}

	public ScoreVO selectOneScore(int stuNum) {
		return scoreList.get(stuNum - 1);
	}
}
