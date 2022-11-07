package com.lcomputer.testmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//import java.sql.ResultSet;
//import java.util.ArrayList;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.apache.catalina.connector.Response;

import com.lcomputer.testmvc.database.DBConnection;
import com.lcomputer.testmvc.vo.Board;
import com.lcomputer.testmvc.vo.Comment;
import com.lcomputer.testmvc.vo.User;
import com.lcomputer.testmvc.vo.Pagination;

public class CommentDAO {
	private static CommentDAO dao = null;
    
	private CommentDAO () {
		
	}

	public static CommentDAO getInstance() {
		if(dao == null) {
			dao = new CommentDAO ();
		}
		return dao;
	}
	
	public void insertComment(Comment comment) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConnection.getConnection();

			String sql = "insert into comment(c_content, c_date, c_group, c_order, c_depth, b_idx, u_idx) values(?, now(), ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, comment.getC_content());
			pstmt.setInt(2, comment.getC_group());
			pstmt.setInt(3, comment.getC_order());
			pstmt.setInt(4, comment.getC_depth());
			pstmt.setInt(5, comment.getB_idx());
			pstmt.setInt(6, comment.getUser().getU_idx());
			pstmt.executeUpdate();
			
			pstmt.close();
			
			sql = "update comment set c_group = last_insert_id() where c_idx = last_insert_id()";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();

		
		} catch( Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<Comment> getComment(Pagination pagination, Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//List<Comment> list = new ArrayList<>();
		//int b_idx = 0;
		//ArrayList<Comment> list = null;
		ArrayList<Comment> list = new ArrayList<Comment>();
		int pageNum = pagination.getPageNum();
			
		try {
			conn = DBConnection.getConnection();
			//String sql = "select * from comment where b_idx = ?";
			//*
			String query = "SELECT @ROWNUM := @ROWNUM - 1 AS ROWNUM,\n"
					+ "ta.*,\n"
					+ "tc.u_id, tc.u_pw, tc.u_name, tc.u_tel, tc.u_age\n"
					+ "FROM comment ta\n"
					+ "INNER JOIN (SELECT @rownum := (SELECT	COUNT(*)-?+1 FROM comment ta)) tb ON 1=1\n"
					+ "left join user tc ON ta.u_idx = tc.u_idx\n"
					+ "where ta.b_idx = ?\n"
					+ "ORDER BY 	ta.c_group DESC, ta.c_order asc\n"
					+ "LIMIT ?,?\n";
			pstmt = conn.prepareStatement(query);
			//*/
			//pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pageNum);
			pstmt.setInt(2, board.getB_idx());
			pstmt.setInt(3, pageNum);
			pstmt.setInt(4, Pagination.perPage);
			rs = pstmt.executeQuery();
			//list = new ArrayList<Comment>();

	        while(rs.next()){
	        	//comment = new Comment();
	        	Comment comment = new Comment();
	        	comment.setRownum(rs.getInt("ROWNUM"));
	        	comment.setC_idx(rs.getInt("c_idx"));
	        	comment.setC_content(rs.getString("c_content"));
	        	comment.setC_date(rs.getString("c_date"));
	        	comment.setC_group(rs.getInt("c_group"));
	        	comment.setC_order(rs.getInt("c_order"));
	        	comment.setC_depth(rs.getInt("c_depth"));
	        	comment.setB_idx(rs.getInt("b_idx"));
	        	comment.setU_idx(rs.getInt("u_idx"));
	        	
	        	User user = new User();
	        	user.setU_idx(rs.getInt("u_idx"));
		      	user.setU_id(rs.getString("u_id"));
		      	user.setU_name(rs.getString("u_name"));
		      	user.setU_tel(rs.getString("u_tel"));
		      	user.setU_age(rs.getString("u_age"));
		      	comment.setUser(user);
		      	
	        	list.add(comment);
	        }
		} catch( Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public void replyinsertComment(Comment comment) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConnection.getConnection();
			
			String sql = "insert into comment(c_content, c_date, c_group, c_order, c_depth, b_idx, u_idx) values(?, now(), ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, comment.getC_content());
			pstmt.setInt(2, comment.getC_group());
			pstmt.setInt(3, comment.getC_order()+1);
			pstmt.setInt(4, comment.getC_depth()+1);
			pstmt.setInt(5, comment.getB_idx());
			pstmt.setInt(6, comment.getUser().getU_idx());
			pstmt.executeUpdate();
			
		
		} catch( Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void replyUpComment(Comment comment) {
		Connection conn = null;
		PreparedStatement pstmt = null;
			
		try {
			conn = DBConnection.getConnection();
			String sql = "update comment set c_order = c_order+1 where c_group = ? and c_order > ? or c_depth > ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comment.getC_group());
			pstmt.setInt(2, comment.getC_order());
			pstmt.setInt(3, comment.getC_depth());
			pstmt.executeUpdate();
	
			
		} catch( Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deleteComment(Comment comment) {
		Connection conn = null;
		PreparedStatement pstmt = null;
			
		try {
			conn = DBConnection.getConnection();
			String sql = "delete from comment where b_idx = ? and c_group = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comment.getB_idx());
			pstmt.setInt(2, comment.getC_group());

			pstmt.executeUpdate();
		} catch( Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void editComment(Comment comment) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		try {
			conn = DBConnection.getConnection();
			String sql = "select * from comment where b_idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comment.getB_idx());
			rs = pstmt.executeQuery();
			
			while(rs.next()){     	        	
	        	comment.setC_content(rs.getString("c_content"));
	        }

			pstmt.close();
			
			sql = "update comment set c_content = ? where b_idx = ? and c_group = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, comment.getC_content());
			pstmt.setInt(2, comment.getB_idx());
			pstmt.setInt(3, comment.getC_group());

			pstmt.executeUpdate();
			
		} catch( Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}