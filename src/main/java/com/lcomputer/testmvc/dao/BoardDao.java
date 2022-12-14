package com.lcomputer.testmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.lcomputer.testmvc.database.DBConnection;
import com.lcomputer.testmvc.vo.Board;
import com.lcomputer.testmvc.vo.Comment;
import com.lcomputer.testmvc.vo.Pagination;
import com.lcomputer.testmvc.vo.Search;
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
	
	public int getBoardCount(Pagination pagination, Search search) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		int pageNum = pagination.getPageNum();
		
		String where = "";
		//Search search = new Search();
		
		if (search.getSearchType() != null && !search.getSearchType().equals("")) {
		
			switch(search.getSearchType()) {
	
				case "title":
					where += "and b_title like ? ";
					break;
				case "content":
					where += "and b_content like ? ";
					break;
				case "write":
					where += "and u_name like ? ";
					break;
			}
		}

		try {
			conn = DBConnection.getConnection();
			//String query = "SELECT COUNT(*) count FROM board ";	// 검색 페이징 적용X

			String query = "SELECT COUNT(*) count\n"
					+ "FROM board ta\n"
					+ "left join user tc ON ta.u_idx = tc.u_idx\n"
					+ "where 1=1\n"
					+ where
					+ "ORDER BY ta.b_group DESC, ta.b_order asc\n"
					+ "LIMIT ?,?\n";
		
			pstmt = conn.prepareStatement(query);
			
			if (search.getSearchType() != null && !search.getSearchType().equals("")) {
	       		pstmt.setString(1, "%"+search.getSearchName()+"%");
	       		pstmt.setInt(2, pageNum);
       			pstmt.setInt(3, Pagination.perPage);
	       	}
	       	
	       	else {
	       		pstmt.setInt(1, pageNum);
       			pstmt.setInt(2, Pagination.perPage);
	       	}	
	       	
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
			String sql = "insert into board(b_title, b_content, b_date, b_viewcount, u_idx, b_group, b_order, b_depth, b_filename) values(?, ?, now(), ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, board.getB_title());
			pstmt.setString(2, board.getB_content());
			pstmt.setInt(3, board.getB_viewcount());
			pstmt.setInt(4, board.getUser().getU_idx());
			pstmt.setInt(5, board.getB_group());
			pstmt.setInt(6, board.getB_order());
			pstmt.setInt(7, board.getB_depth());
			pstmt.setString(8, board.getB_filename());
			
			pstmt.executeUpdate();
			
			pstmt.close();
			
			sql = "update board set b_group = last_insert_id() where b_idx = last_insert_id()";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			
			pstmt.close();
			
			sql = "update board set b_filename = ? where b_idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getB_filename());
			pstmt.setInt(2, board.getB_idx());
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
			
			//String sql = "select * from board where b_idx = ?";
			String sql = "select * from board ta\n"
					+ "LEFT JOIN user tb ON ta.u_idx = tb.u_idx\n"
					+ "where ta.b_idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getB_idx());

			rs = pstmt.executeQuery();

	        while(rs.next()){
	        	
	        	//board.setRownum(rs.getInt("ROWNUM"));
	        	board.setB_idx(rs.getInt("b_idx"));
	        	board.setB_title(rs.getString("b_title"));
	        	board.setB_content(rs.getString("b_content"));
	        	board.setB_date(rs.getString("b_date"));
	        	board.setB_viewcount(rs.getInt("b_viewcount"));
	        	board.setB_group(rs.getInt("b_group"));
	        	board.setB_order(rs.getInt("b_order"));
	        	board.setB_depth(rs.getInt("b_depth"));
	        	board.setU_idx(rs.getInt("u_idx"));
	        	board.setB_filename(rs.getString("b_filename"));
	        	
	        	User user = new User();
	        	user.setU_id(rs.getString("u_id"));
	        	user.setU_pw(rs.getString("u_pw"));
	        	user.setU_name(rs.getString("u_name"));
	        	user.setU_tel(rs.getString("u_tel"));
	        	user.setU_age(rs.getString("u_age"));
	        	user.setU_type(rs.getInt("u_type"));
	        	board.setUser(user);
	        	
	        	
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
	
	public ArrayList<Board> getBoards(Pagination pagination, Board board, Search search) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Board> list = null;
		int pageNum = pagination.getPageNum();
		
		String where = "";
		
		if (search.getSearchType() != null && !search.getSearchType().equals("")) {
			
			switch(search.getSearchType()) {
	
				case "title":
					where += "and b_title like ? ";
					break;
				case "content":
					where += "and b_content like ? ";
					break;
				case "write":
					where += "and u_name like ? ";
					break;
			}
		}
		
		try {
			conn = DBConnection.getConnection();

			String query = "SELECT @ROWNUM := @ROWNUM - 1 AS ROWNUM,\n"
					+ "ta.*,\n"
					+ "tc.u_id, tc.u_pw, tc.u_name, tc.u_tel, tc.u_age\n"
					+ "FROM board ta\n"
					+ "INNER JOIN (SELECT @rownum := (SELECT	COUNT(*)-?+1 FROM board ta)) tb ON 1=1\n"
					+ "left join user tc ON ta.u_idx = tc.u_idx\n"
					+ "where 1=1\n"
					+ where
					+ "ORDER BY 	ta.b_group DESC, ta.b_order asc\n"
					+ "LIMIT ?,?\n";
			//System.out.println(query);

	       	pstmt = conn.prepareStatement(query);

   			
   			if (search.getSearchType() != null && !search.getSearchType().equals("")) {
	       		pstmt.setInt(1, pageNum);
	       		pstmt.setString(2, "%"+search.getSearchName()+"%");
	       		pstmt.setInt(3, pageNum);
       			pstmt.setInt(4, Pagination.perPage);
	       	}
	       	
	       	else {
	       		pstmt.setInt(1, pageNum);
	       		pstmt.setInt(2, pageNum);
       			pstmt.setInt(3, Pagination.perPage);
	       	}
	    	
	        rs = pstmt.executeQuery();
	        list = new ArrayList<Board>();
		        
		     while(rs.next()){
		    	Board board1 = new Board();
			    board1.setRownum(rs.getInt("ROWNUM"));
			    board1.setB_idx(rs.getInt("b_idx"));
			    board1.setB_title(rs.getString("b_title"));
			    board1.setB_content(rs.getString("b_content"));
			    board1.setB_date(rs.getString("b_date"));
			    board1.setB_viewcount(rs.getInt("b_viewcount"));
			    board1.setB_group(rs.getInt("b_group"));
			    board1.setB_order(rs.getInt("b_order"));
			    board1.setB_depth(rs.getInt("b_depth"));
		
		      	User user = new User();	        	
		      	user.setU_idx(rs.getInt("u_idx"));
		      	user.setU_id(rs.getString("u_id"));
		      	user.setU_name(rs.getString("u_name"));
		      	user.setU_tel(rs.getString("u_tel"));
		      	user.setU_age(rs.getString("u_age"));
		      
	        	board1.setUser(user);
	           	
	           	list.add(board1);
		      	
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
	
	public void replyinsertBoard(Board board) {
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

	public void replyUpBoard(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
			
		try {
			conn = DBConnection.getConnection();
			//String sql = "update board set b_order = b_order+1 where b_group = ? and b_order > ?";
			String sql = "update board set b_order = b_order+1 where b_group = ? and b_order > ? or b_depth > ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getB_group());
			pstmt.setInt(2, board.getB_order());
			pstmt.setInt(3, board.getB_depth());
			//pstmt.setInt(2, board.getB_depth());
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
	
	public void fileUpload(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
			
		try {
			conn = DBConnection.getConnection();
			String sql = "update board set b_filename = ? where b_idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getB_filename());
			pstmt.setInt(2, board.getB_idx());
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
