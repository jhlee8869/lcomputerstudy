package com.lcomputer.testmvc.service;

import java.util.ArrayList;
import java.util.List;

import com.lcomputer.testmvc.dao.CommentDAO;
import com.lcomputer.testmvc.vo.Board;
import com.lcomputer.testmvc.vo.Comment;
import com.lcomputer.testmvc.vo.User;
import com.lcomputer.testmvc.vo.Pagination;

public class CommentService {
	private static CommentService service = null;
	private static CommentDAO dao = null;
    
	private CommentService () {
		
	}

	public static CommentService getInstance() {
		if(service == null) {
			service = new CommentService ();
			dao = CommentDAO.getInstance();
		}
		return service;
	}
	
	public void insertComment(Comment comment) {
		dao.insertComment(comment);
	}
	
	public ArrayList<Comment> getComment(Pagination pagination, Board board) {
		return dao.getComment(pagination, board);
	}
	/*
	public ArrayList<Board> getBoards(Pagination pagination) {
		return dao.getBoards(pagination);
	}
	*/
	public void deleteComment(Comment comment) {
		dao.deleteComment(comment);
	}
	
	public void replyinsertComment(Comment comment) {
		dao.replyinsertComment(comment);
	}
	
	public void replyUpComment(Comment comment) {
		dao.replyUpComment(comment);
	}

}