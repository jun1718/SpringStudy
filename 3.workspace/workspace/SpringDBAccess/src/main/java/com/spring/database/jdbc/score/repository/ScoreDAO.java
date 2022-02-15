package com.spring.database.jdbc.score.repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

//import com.spring.database.jdbc.score.commons.ScoreMapper;
import com.spring.database.jdbc.score.model.ScoreVO;
@Repository
public class ScoreDAO implements IScoreDAO {
	class ScoreMapper implements RowMapper<ScoreVO>{
		@Override
		public ScoreVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			ScoreVO score = new ScoreVO();
			score.setStuId(rs.getInt("stu_id"));
			score.setStuName(rs.getString("stu_name"));
			score.setKor(rs.getInt("kor"));
			score.setEng(rs.getInt("eng"));
			score.setMath(rs.getInt("math"));
			score.setTotal(rs.getInt("total"));
			score.setAverage(rs.getDouble("average"));
			
			return score;
		}
	}

	
	/*
	//#전통적 방식의 JDBC
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/spring?serverTimezone=Asia/Seoul";
		//8버전 커넥터에서만 서버시간과 장소를알려줘야함
	private String uid = "root";
	private String upw = "mysql";
	 */
	
	/*
	 * 	
	@Override
	public void insertScore(ScoreVO scores) {
		// TODO Auto-generated method stub
		System.out.println("Repository param: " + scores);
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO scores "
				+ "(stu_name, kor, eng, math, total, average) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, scores.getStuName());
			pstmt.setInt(2,  scores.getKor());
			pstmt.setInt(3,  scores.getEng());
			pstmt.setInt(4,  scores.getMath());
			pstmt.setInt(5,  scores.getTotal());
			pstmt.setDouble(6,  scores.getAverage());
			
			pstmt.executeUpdate();
			System.out.println("점수 등록 성공!");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
			} catch (Exception e) {}
		}
	}
	*/
	
	//#Spring-JDBC 방식의 처리 : JdbcTemplate 사용!
	@Autowired
	private JdbcTemplate template; // 이거는 빈등록직접했다. 그래서 자동스캔됨
	
	
	@Override
	public void insertScore(ScoreVO scores) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO scores "
				+ "(stu_name, kor, eng, math, total, average) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		template.update(sql, scores.getStuName(), scores.getKor(), 
				scores.getEng(), scores.getMath(), scores.getTotal(),
				scores.getAverage());
		
		System.out.println("점수 등록 성공!");
	}
	
	/*
	@Override
	public List<ScoreVO> selectAllScores() {
		// TODO Auto-generated method stub
		List<ScoreVO> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM scores";
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ScoreVO vo = new ScoreVO();
				vo.setStuId(rs.getInt("stu_id"));
				vo.setStuName(rs.getString("stu_name"));
				vo.setKor(rs.getInt("kor"));
				vo.setEng(rs.getInt("eng"));
				vo.setMath(rs.getInt("math"));
				vo.setTotal(rs.getInt("total"));
				vo.setAverage(rs.getDouble("average"));
				
				
				list.add(vo);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				conn.close();
				pstmt.close();
				rs.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		return list;
	}
	*/
	/*
	@Override
	public List<ScoreVO> selectAllScores() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM scores";
		
		//인터페이스를 구현한 클래스를 만들어서 template.query 사용하는법
//		List<ScoreVO> list = template.query(sql, new ScoreMapper());
		
		//익명클래스
		
		List<ScoreVO> list = template.query(sql, new RowMapper<ScoreVO>() {
			@Override
			public ScoreVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				ScoreVO score = new ScoreVO();
				score.setStuId(rs.getInt("stu_id"));
				score.setStuName(rs.getString("stu_name"));
				score.setKor(rs.getInt("kor"));
				score.setEng(rs.getInt("eng"));
				score.setMath(rs.getInt("math"));
				score.setTotal(rs.getInt("total"));
				score.setAverage(rs.getDouble("average"));
				
				return score;
			}
		});
		
		

		return list;
	}
	*/

	
	//람다를 이용한 익명클래스
	@Override
	public List<ScoreVO> selectAllScores() {
		String sql = "SELECT * FROM scores";
		
		return template.query(sql, (rs, rowNum) -> {
			{
				ScoreVO score = new ScoreVO();
				score.setStuId(rs.getInt("stu_id"));
				score.setStuName(rs.getString("stu_name"));
				score.setKor(rs.getInt("kor"));
				score.setEng(rs.getInt("eng"));
				score.setMath(rs.getInt("math"));
				score.setTotal(rs.getInt("total"));
				score.setAverage(rs.getDouble("average"));
				
				return score;
			}
		});
	}
	
	
	@Override
	public void deleteScore(int stuNum) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM scores WHERE stu_id=?";
		template.update(sql, stuNum);
		System.out.println("삭제성공!");
	}

	/*
	public ScoreVO selectOneScore(int stuNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM scores WHERE stu_id = ?";
		
		
		ScoreVO vo = new ScoreVO();
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, stuNum);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				vo.setStuId(rs.getInt("stu_id"));
				vo.setStuName(rs.getString("stu_name"));
				vo.setKor(rs.getInt("kor"));
				vo.setEng(rs.getInt("eng"));
				vo.setMath(rs.getInt("math"));
				vo.setTotal(rs.getInt("total"));
				vo.setAverage(rs.getDouble("average"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				conn.close();
				pstmt.close();
				rs.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return vo;
	} */
	
	@Override
	public ScoreVO selectOneScore(int stuNum) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM scores WHERE stu_id = ?";
		
		
		//아래 두가지방법으로 ?를 채운 뒤 ScoreMapper에서 정의한 제너릭타입을 제너릭으로갖는 list 반환받기
		//1)sql, 오브젝트배열, RowMapper구현한 클래스 형식
		//2)sql, RowMapper구현한 클래스, 가변인수 형식
//		template.query(sql,  new Object[] {stuNum}, new ScoreMapper());
//		template.query(sql, new ScoreMapper(), stuNum);
		
		//queryForObject는 Single row를 리턴할때 사용
				//조회결과가 한줄인걸 single row라함
		//query는 multi row를 리턴할때 사용
				//조회결과가 두개 이상인경우 multi row라하고 이때 list에 담는거임
		template.queryForObject(sql, new ScoreMapper(), stuNum);
				//RowMapper를 구현한 클래스 ScoreMapper의 제너릭이 queryForObject의 반환값을 결정함
		return null;
	}
}
