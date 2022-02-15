package com.spring.database.jdbc.score.commons;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.database.jdbc.score.model.ScoreVO;

public class ScoreMapper implements RowMapper<ScoreVO> {
	@Override
	public ScoreVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		
		ScoreVO vo = new ScoreVO();
		
		vo.setStuId(rs.getInt("stu_id"));
		vo.setStuName(rs.getString("stu_name"));
		vo.setKor(rs.getInt("kor"));
		vo.setEng(rs.getInt("eng"));
		vo.setMath(rs.getInt("math"));
		vo.setTotal(rs.getInt("total"));
		vo.setAverage(rs.getDouble("average"));
		
		return vo;
	}
}
