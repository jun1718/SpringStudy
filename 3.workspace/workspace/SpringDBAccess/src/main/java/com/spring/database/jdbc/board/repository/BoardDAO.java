package com.spring.database.jdbc.board.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.database.jdbc.board.model.BoardVO;

@Repository
public class BoardDAO implements IBoardDAO {
	
	class BoardMapper implements RowMapper<BoardVO> {
		
		@Override
		public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			BoardVO vo = new BoardVO();
			
			vo.setBoardNo(rs.getInt("board_no"));
			vo.setWriter(rs.getString("writer"));
			vo.setTitle(rs.getString("title"));
			vo.setContent(rs.getString("content"));
			
			return vo;
		}
	}
	
	@Autowired
	private JdbcTemplate template;
	
	@Override
	public List<BoardVO> getArticles() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM jdbc_board ORDER BY board_no DESC";

		/*
		List<BoardVO> list = template.query(sql, new RowMapper<BoardVO>() {
			@Override
			public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				BoardVO vo = new BoardVO();
				
				vo.setBoardNo(rs.getInt("board_no"));
				vo.setWriter(rs.getString("writer"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				
				return vo;
			}
		});
		*/
		
		List<BoardVO> list = template.query(sql, (rs, rowNum) -> {

				BoardVO vo = new BoardVO();
				
				vo.setBoardNo(rs.getInt("board_no"));
				vo.setWriter(rs.getString("writer"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				
				return vo;
		});
		
		
		return list;
	}

	@Override
	public void insertArticle(BoardVO article) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO jdbc_board "
				+ "(writer, title, content) "
				+ "VALUES (?, ?, ?)";
		
		template.update(sql, article.getWriter(), article.getTitle(), 
				article.getContent());
		
		System.out.println(article); // 로그찍기
		System.out.println("게시글 저장 완료!!");
	}

	@Override
	public void deleteArticle(int boardNo) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM jdbc_board WHERE board_no = " + boardNo;
		template.update(sql);
		System.out.println(boardNo + "번 삭제 완료!");
	}

	@Override
	public BoardVO getContent(int boardNo) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM jdbc_board WHERE board_no = ?";
//		String sql = "SELECT * FROM jdbc_board WHERE board_no = " + boardNo;
		
		BoardVO vo = template.queryForObject(sql, new Object[] {boardNo}, new BoardMapper());
//		template.queryForObject(sql, new BoardMapper(), boardNo);
				
		return vo;
	}

	@Override
	public void modifyArticle(BoardVO article) {
		// TODO Auto-generated method stub
		String sql = "UPDATE jdbc_board "
				+ "SET writer = ?, title = ?, content = ? "
				+ "WHERE board_no = ?";
		
		template.update(sql, article.getWriter(), article.getTitle(),
								article.getContent(), article.getBoardNo());
		System.out.println(article.getBoardNo() + "번 게시글 수정 완료!");
	}

	
	@Override
	public List<BoardVO> getSearchList(String keyword) {
		// TODO Auto-generated method stub
		System.out.println(keyword);
		String sql = "SELECT * FROM jdbc_board "
				+ "WHERE writer LIKE ? "
				+ "ORDER BY board_no DESC";
		
		return template.query(sql, new BoardMapper(), keyword);
	}
}
