package com.lcomputer.testmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.lcomputer.testmvc.database.DBConnection;
import com.lcomputer.testmvc.vo.Board;
import com.lcomputer.testmvc.vo.Comment;
import com.lcomputer.testmvc.vo.User;

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
	
	public void commentinsert(Comment comment) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConnection.getConnection();

			String sql = "insert into board(c_content, c_date, c_group, c_order, c_depth, b_idx) values(?, now(), ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, comment.getC_content());
			pstmt.setInt(2, comment.getC_group());
			pstmt.setInt(3, comment.getC_order());
			pstmt.setInt(4, comment.getC_depth());
			pstmt.setInt(5, comment.getBoard().getB_idx());
			
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