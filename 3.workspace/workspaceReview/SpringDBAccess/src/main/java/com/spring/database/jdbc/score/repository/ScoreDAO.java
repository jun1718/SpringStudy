package com.spring.database.jdbc.score.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.database.jdbc.score.model.ScoreVO;

@Repository
public class ScoreDAO implements IScoreDAO {
	@Autowired
	private JdbcTemplate template;
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/spring2?serverTimezone=Asia/Seoul";
	private String uid = "root";
	private String upw = "mysql";

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	@Override
	public void insertScore(ScoreVO score) {
		// TODO Auto-generated method stub
		
		String sql = "INSERT INTO scores "
				+ "(stu_name, kor, eng, math, total, average) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		
		/*
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, score.getStuName());
			pstmt.setInt(2, score.getKor());
			pstmt.setInt(3, score.getEng());
			pstmt.setInt(4, score.getMath());
			pstmt.setInt(5, score.getTotal());
			pstmt.setDouble(6, score.getAverage());
			
			pstmt.executeUpdate();
			System.out.println("insert 완료!@");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		*/
		
		template.update(sql, score.getStuName(), score.getKor(),
				score.getEng(), score.getMath(), score.getTotal(), 
				score.getAverage());

	}

	@Override
	public List<ScoreVO> selectAllScores() {
		// TODO Auto-generated method stub
		List<ScoreVO> list = new ArrayList<>();
		
		String sql = "SELECT * FROM scores";
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ScoreVO score = new ScoreVO();
				
				score.setStuId(rs.getInt("stu_id"));
				score.setStuName(rs.getString("stu_name"));
				score.setKor(rs.getInt("kor"));
				score.setEng(rs.getInt("eng"));
				score.setMath(rs.getInt("math"));
				score.setTotal(rs.getInt("total"));
				score.setAverage(rs.getInt("average"));
				
				list.add(score);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
				rs.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public void deleteScore(int stuNum) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM scores WHERE stu_id = " + stuNum;
		template.update(sql);
	}

	public ScoreVO selectOneScore(int stuNum) {
		
		String sql = "SELECT * FROM scores WHERE stu_id = " + stuNum;
		ScoreVO score = new ScoreVO();

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				score.setStuId(rs.getInt("stu_id"));
				score.setStuName(rs.getString("stu_name"));
				score.setKor(rs.getInt("kor"));
				score.setEng(rs.getInt("eng"));
				score.setMath(rs.getInt("math"));
				score.setTotal(rs.getInt("total"));
				score.setAverage(rs.getInt("average"));				
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				conn.close();
				pstmt.close();
				rs.close();				
			} catch (Exception e2) {
				// TODO: handle exception
			} 
		}
		
		return score;
	}
}
