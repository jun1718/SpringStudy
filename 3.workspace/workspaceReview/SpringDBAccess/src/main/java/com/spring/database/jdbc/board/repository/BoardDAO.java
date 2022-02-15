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
	@Autowired
	private JdbcTemplate template;
	
	class BoardMapper implements RowMapper<BoardVO>{
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
	
	@Override
	public List<BoardVO> getArticles() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM jdbc_board "
				+ "ORDER BY board_no DESC";
		return template.query(sql, new BoardMapper());
	}

	@Override
	public void insertArticle(BoardVO article) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO jdbc_board "
				+ "(writer, title, content) "
				+ "VALUES (?, ?, ?)";
		
		template.update(sql, article.getWriter(), article.getTitle(),
								article.getContent());
		
		System.out.println("게시글 등록 완료!");
	}

	@Override
	public void deleteArticle(int boardNo) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM jdbc_board "
				+ "WHERE board_no = ?";
		
		template.update(sql, boardNo);
		System.out.println(boardNo + "번 게시물 삭제 완료!!");
	}

	@Override
	public BoardVO getContent(int boardNo) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM jdbc_board "
				+ "WHERE board_no = ?";
		
		return template.queryForObject(sql, new BoardMapper(), boardNo);
	}

	@Override
	public void modifyArticle(BoardVO article) {
		// TODO Auto-generated method stub
		String sql = "UPDATE jdbc_board "
				+ "SET writer = ?, title = ?, content = ? "
				+ "WHERE board_no = ? "
				+ "ORDER BY board_no DESC";
		
		template.update(sql, article.getWriter(), article.getTitle(),
								article.getContent(), article.getBoardNo());
		System.out.println(article.getBoardNo() + "번 게시물 수정 완료!");
	}
	
	@Override
	public List<BoardVO> getSearchList(String keyword) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM jdbc_board "
				+ "WHERE writer LIKE ?";
		
		return template.query(sql, new BoardMapper(), keyword);
	}

}
