package com.lcomputer.testmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.ResultSet;
//import java.util.ArrayList;

import com.lcomputer.testmvc.database.DBConnection;
import com.lcomputer.testmvc.vo.Board;
import com.lcomputer.testmvc.vo.Comment;
//import com.lcomputer.testmvc.vo.Board;
//import com.lcomputer.testmvc.vo.User;

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

			String sql = "insert into comment(c_content, c_date, c_group, c_order, c_depth, b_idx) values(?, now(), ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, comment.getC_content());
			pstmt.setInt(2, comment.getC_group());
			pstmt.setInt(3, comment.getC_order()+1);
			pstmt.setInt(4, comment.getC_depth()+1);
			//pstmt.setInt(5, comment.getBoard().getB_group());
			pstmt.setInt(5, comment.getB_idx());
			
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
	
	public Comment detailComment(Comment comment) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		try {
			conn = DBConnection.getConnection();
			String sql = "select * from comment where c_idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comment.getC_idx());
			rs = pstmt.executeQuery();

	        while(rs.next()){     
	        	comment.setC_idx(rs.getInt("c_idx"));
	        	comment.setC_content(rs.getString("c_content"));
	        	comment.setC_date(rs.getString("c_date"));
	        	comment.setC_group(rs.getInt("c_group"));
	        	comment.setC_order(rs.getInt("c_order"));
	        	comment.setC_depth(rs.getInt("c_depth"));
	        	comment.setB_idx(rs.getInt("b_idx"));;
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
		
		return comment;
	}

}