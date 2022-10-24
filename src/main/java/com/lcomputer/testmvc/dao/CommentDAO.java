package com.lcomputer.testmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.lcomputer.testmvc.database.DBConnection;
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
}