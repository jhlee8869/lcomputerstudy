package com.lcomputer.testmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.lcomputer.testmvc.database.DBConnection;
import com.lcomputer.testmvc.vo.Board;
import com.lcomputer.testmvc.vo.Pagination;
import com.lcomputer.testmvc.vo.User;

public class BoardDao {
	private static BoardDao dao = null;
    
	private BoardDao() {
		
	}

	public static BoardDao getInstance() {
		if(dao == null) {
			dao = new BoardDao();
		}
		return dao;
	}
	
	public int getBoardCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;

		try {
			conn = DBConnection.getConnection();
			String query = "SELECT COUNT(*) count FROM board ";
	       	pstmt = conn.prepareStatement(query);
	        rs = pstmt.executeQuery();
	        
	        while(rs.next()){     
	        	count = rs.getInt("count");
	        }
		} catch (Exception e) {
			
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	public void insertBoard(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
			
		try {
			conn = DBConnection.getConnection();
			String sql = "insert into board(b_title, b_content, b_date, b_viewcount, u_idx, b_group, b_order, b_depth) values(?, ?, now(), ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, board.getB_title());
			pstmt.setString(2, board.getB_content());
			pstmt.setInt(3, board.getB_viewcount());
			pstmt.setInt(4, board.getUser().getU_idx());
			pstmt.setInt(5, board.getB_group());
			pstmt.setInt(6, board.getB_order());
			pstmt.setInt(7, board.getB_depth());
			
			pstmt.executeUpdate();
			
			pstmt.close();
			
			sql = "update board set b_group = last_insert_id() where b_idx = last_insert_id()";
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
	
	public Board editBoard(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		try {
			conn = DBConnection.getConnection();
			String sql = "select * from board where b_idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getB_idx());
			rs = pstmt.executeQuery();

	        while(rs.next()){     	        	
	        	board.setB_idx(rs.getInt("b_idx"));
	        	board.setB_title(rs.getString("b_title"));
	        	board.setB_content(rs.getString("b_content"));
	        	board.setB_date(rs.getString("b_date"));
	        	//board.setB_count(rs.getInt("b_count"));
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
		
		return board;
	}
	
	public void editProcessBoard(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
			
		try {
			conn = DBConnection.getConnection();
			String sql = "update board set b_title = ?, b_content = ? where b_idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getB_title());
			pstmt.setString(2, board.getB_content());
			//pstmt.setString(3, board.getB_writer());
			//pstmt.setInt(4, board.getB_count());
			//pstmt.setString(3, board.getB_date());
			pstmt.setInt(3, board.getB_idx());
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
	
	public Board detailBoard(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		try {
			conn = DBConnection.getConnection();
			String sql = "select * from board where b_idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getB_idx());
			rs = pstmt.executeQuery();

	        while(rs.next()){     
	        	board.setB_idx(rs.getInt("b_idx"));
	        	board.setB_title(rs.getString("b_title"));
	        	board.setB_content(rs.getString("b_content"));
	        	//board.setB_writer(rs.getString("b_writer"));
	        	board.setB_date(rs.getString("b_date"));
	        	board.setB_viewcount(rs.getInt("b_viewcount"));
	        	board.getUser().getU_idx();
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
		
		return board;
	}
	
	public void deleteBoard(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
			
		try {
			conn = DBConnection.getConnection();
			String sql = "delete from board where b_idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getB_idx());
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
	
	public ArrayList<Board> getBoards(Pagination pagination) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Board> list = null;
		int pageNum = pagination.getPageNum();
		
		try {
			conn = DBConnection.getConnection();

			String query = "SELECT @ROWNUM := @ROWNUM - 1 AS ROWNUM,\n"
					+ "ta.*,\n"
					+ "tc.u_id, tc.u_pw, tc.u_name, tc.u_tel, tc.u_age\n"
					+ "FROM board ta\n"
					+ "INNER JOIN (SELECT @rownum := (SELECT	COUNT(*)-?+1 FROM board ta)) tb ON 1=1\n"
					+ "left join user tc ON ta.u_idx = tc.u_idx\n"
					+ "LIMIT ?,?\n";
	       	pstmt = conn.prepareStatement(query);
	       	pstmt.setInt(1, pageNum);
	    	pstmt.setInt(2, pageNum);
	    	//pstmt.setInt(2, pageNum);
	    	pstmt.setInt(3, Pagination.perPage);
	        rs = pstmt.executeQuery();
	        list = new ArrayList<Board>();
		        
		     while(rs.next()){     
		      	Board board = new Board();
		      	board.setRownum(rs.getInt("ROWNUM"));
		      	board.setB_idx(rs.getInt("b_idx"));
		      	board.setB_title(rs.getString("b_title"));
		      	board.setB_content(rs.getString("b_content"));
		      	board.setB_date(rs.getString("b_date"));
		      	board.setB_viewcount(rs.getInt("b_viewcount"));
		      	
		      	User user = new User();	        	
		      	user.setU_idx(rs.getInt("u_idx"));
		      	user.setU_id(rs.getString("u_id"));
		      	user.setU_name(rs.getString("u_name"));
		      	user.setU_tel(rs.getString("u_tel"));
		      	user.setU_age(rs.getString("u_age"));
		      	
	        	board.setUser(user);
	           	
	           	list.add(board);
	        }
		} catch (Exception e) {
			e.printStackTrace();
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

	public void getBoardviewcount(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		//ResultSet rs = null;
		//int count = 0;
			
		try {
			conn = DBConnection.getConnection();
			String sql = "update board set b_viewcount = b_viewcount+1 where b_idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getB_idx());
			pstmt.executeUpdate();
		} catch( Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				//if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public Board replyBoard(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		try {
			conn = DBConnection.getConnection();
			String sql = "select * from board where b_idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getB_idx());
			rs = pstmt.executeQuery();
		
			while(rs.next()){     	        	
	        	board.setB_idx(rs.getInt("b_idx"));
	        	board.setB_title(rs.getString("b_title"));
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
		
		return board;
	}
	
	public void replyProcessBoard(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
			
		try {
			conn = DBConnection.getConnection();
			String sql = "update board set b_order=b_order+1 where b_group=? and b_order>?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getB_group());
			pstmt.setInt(2, board.getB_order());
			//pstmt.setInt(3, board.getB_depth());
			//pstmt.setInt(3, board.getB_idx());
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
	
	public void replyinsertBoard(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
			
		try {
			conn = DBConnection.getConnection();
			String sql = "insert into board(b_title, b_content, b_date, b_viewcount, u_idx, b_group, b_order, b_depth) values(?, ?, now(), ?, ?, ?, ?, ?)";
			//pstmt.close();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getB_title());
			pstmt.setString(2, board.getB_content());
			pstmt.setInt(3, board.getB_viewcount());
			pstmt.setInt(4, board.getUser().getU_idx());
			pstmt.setInt(5, board.getB_group());
			pstmt.setInt(6, board.getB_order()+1);
			pstmt.setInt(7, board.getB_depth()+1);
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
