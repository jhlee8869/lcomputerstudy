package com.lcomputer.testmvc.service;

import java.util.ArrayList;
import com.lcomputer.testmvc.dao.CommentDAO;
import com.lcomputer.testmvc.vo.Board;
import com.lcomputer.testmvc.vo.Comment;

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
	
	public void commentinsert(Comment comment) {
		dao.commentinsert(comment);
	}

}