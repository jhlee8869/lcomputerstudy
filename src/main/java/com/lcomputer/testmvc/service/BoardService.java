package com.lcomputer.testmvc.service;

import java.util.ArrayList;
import com.lcomputer.testmvc.dao.BoardDao;
import com.lcomputer.testmvc.vo.Pagination;
import com.lcomputer.testmvc.vo.Search;
import com.lcomputer.testmvc.vo.Board;

public class BoardService {

	private static BoardService service = null;
	private static BoardDao dao = null;
    
	private BoardService() {
		
	}

	public static BoardService getInstance() {
		if(service == null) {
			service = new BoardService();
			dao = BoardDao.getInstance();
		}
		return service;
	}
	
	/*
	public ArrayList<Board> getBoards() {
		return dao.getBoards();
	}
	*/
	public int getBoardCount() {
		return dao.getBoardCount();
	}
	
	public void insertBoard(Board board) {
		dao.insertBoard(board);
	}
	
	public ArrayList<Board> getBoards(Pagination pagination, Board board, Search search) {
		return dao.getBoards(pagination, board, search);
	}
	
	public Board editBoard(Board board) {
		return dao.editBoard(board);
	}
	
	public void editProcessBoard(Board board) {
		dao.editProcessBoard(board);
	}
	
	public Board detailBoard(Board board) {
		return dao.detailBoard(board);
	}
	
	public void deleteBoard(Board board) {
		dao.deleteBoard(board);
	}
	
	public void getBoardviewcount(Board board) {
		dao.getBoardviewcount(board);
	}
	/*
	public Board replyBoard(Board board) {
		return dao.replyBoard(board);
	}
	*/
	public void replyUpBoard(Board board) {
		dao.replyUpBoard(board);
	}
	
	public void replyinsertBoard(Board board) {
		dao.replyinsertBoard(board);
	}
	
	
}