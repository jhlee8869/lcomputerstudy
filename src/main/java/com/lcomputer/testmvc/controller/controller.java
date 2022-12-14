package com.lcomputer.testmvc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lcomputer.testmvc.service.BoardService;
import com.lcomputer.testmvc.service.CommentService;
import com.lcomputer.testmvc.service.UserService;
import com.lcomputer.testmvc.vo.Pagination;
import com.lcomputer.testmvc.vo.Search;
import com.lcomputer.testmvc.vo.User;
import com.lcomputer.testmvc.vo.Board;
import com.lcomputer.testmvc.vo.Comment;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("*.do")
public class controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");

		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		String view = null;
		String pw = null;
		String idx = null;
		
		/*
		String saveDir = "C:\\Users\\l5-morning\\Documents\\work11\\lcomputerstudy\\src\\main\\webapp\\img";
		String enctype = "utf-8";
		System.out.println("saveDir" + saveDir);
		int size = 10 * 1024 * 1024;
		MultipartRequest multi = new MultipartRequest(request, saveDir, size, enctype, new DefaultFileRenamePolicy());
		
		String b_title = multi.getParameter("b_title");
		String b_content = multi.getParameter("b_content");
		String b_filename = multi.getFilesystemName("b_filename");
		String origin = multi.getOriginalFileName("b_filename");
		//long fileSize = multi.getFile("b_filename").length();
		
		System.out.println(b_title);
		System.out.println(b_content);
		System.out.println(origin);
		*/
		HttpSession session = null;
		UserService userService = null;
		BoardService boardService = null;
		CommentService commentService = null;
		//Comment comment = null;
		//List<Comment> commentList = null;
		//ArrayList<Board> boardList = null;
		ArrayList<User> userList = null;
		ArrayList<Comment> commentList = null;
		command = checkSession(request, response, command);
		
		int usercount = 0;	//??????
		int boardcount = 0;
		//int boardviewcount = 0;
		int page = 1;	//??????
		
		boolean isRedirected = false;
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		switch (command) {
			
			case "/user-list.do":
				String reqPage = request.getParameter("page");

				if (reqPage != null)
					page = Integer.parseInt(reqPage);

				userService = UserService.getInstance();
				usercount = userService.getUsersCount();
				
				Pagination pagination = new Pagination();
				pagination.setPage(page);
				pagination.setCount(usercount);
				pagination.init();
				
				ArrayList<User> list = userService.getUsers(pagination);
				
				view = "user/list";
				
				request.setAttribute("list", list);
				request.setAttribute("pagination", pagination);	//??????
				
				break;
				
				//?????? ??????
			case "/user-list-process.do":
				
				String reqPage3 = request.getParameter("page");
				
				if (reqPage3 != null)
					page = Integer.parseInt(reqPage3);
				
				User user21 = new User();
				user21.setU_type(Integer.parseInt(request.getParameter("u_type")));
				user21.setU_idx(Integer.parseInt(request.getParameter("u_idx")));
				
				userService = UserService.getInstance();
				userService.changeTypeUser(user21);
				usercount = userService.getUsersCount();
				
				
				Pagination pagination215 = new Pagination();
				//userList = userService.getUsers(pagination215);
						
				pagination215.setPage(page);
				pagination215.setCount(usercount);
				pagination215.init();
				
				userList = userService.getUsers(pagination215);
				
				view = "user/aj-user-list";
				request.setAttribute("list", userList);
				request.setAttribute("pagination", pagination215);
				
				break;
				
			case "/user-insert.do":
				view = "user/insert";
				break;
				
			// ?????? ??????	
			case "/user-insert-process.do":
				User user = new User();
				user.setU_id(request.getParameter("id"));
				user.setU_pw(request.getParameter("password"));
				user.setU_name(request.getParameter("name"));
				user.setU_tel(request.getParameter("tel1") + "-" + request.getParameter("tel2") + "-" + request.getParameter("tel3"));
				user.setU_age(request.getParameter("age"));
				//user.setU_age(request.getParameter("age"));
				
				userService = UserService.getInstance();
				userService.insertUser(user);
						
				view = "user/insert-result";
				break;
				
			case "/user-login.do":
				
				view = "user/login";
				break;
				
			case "/user-login-process.do":
				
				idx = request.getParameter("login_id");
				pw = request.getParameter("login_password");
				
				userService = UserService.getInstance();
				user = userService.loginUser(idx,pw);
							
				if(user != null) {
					session = request.getSession();
					session.setAttribute("user", user);

					view = "user/login-result";
				} else {
					view = "user/login-fail";
				}	
				
				break;
				
			case "/logout.do":
				session = request.getSession();
				session.invalidate();
				
				view = "user/login";				
				break;
				
			case "/access-denied.do":
				
				view = "user/access-denied";
				break;
				
			case "/user-detail.do":
				
				User user2 = new User();
				user2.setU_idx(Integer.parseInt(request.getParameter("u_idx")));
				
				userService = UserService.getInstance();
				user2 = userService.detailUser(user2);
						
				view = "user/detail";
				request.setAttribute("user", user2);
				
				break;				
				
			case "/user-edit.do":

				User user1 = new User();
				//user1.setU_idx(request.getParameter("id"));
				user1.setU_idx(Integer.parseInt(request.getParameter("u_idx")));
				
				userService = UserService.getInstance();
				user1 = userService.editUser(user1);
						
				view = "user/edit";
				request.setAttribute("user", user1);
						
				break;
				
			case "/user-edit-process.do":
				
				User user4 = new User();
				user4.setU_id(request.getParameter("id"));
				user4.setU_pw(request.getParameter("password"));
				user4.setU_name(request.getParameter("name"));
				user4.setU_tel(request.getParameter("tel1") + "-" + request.getParameter("tel2") + "-" + request.getParameter("tel3"));
				user4.setU_age(request.getParameter("age"));
				user4.setU_idx(Integer.parseInt(request.getParameter("u_idx")));
				
				userService = UserService.getInstance();
				userService.editprocessUser(user4);
				
				view = "user/edit-result";				
				request.setAttribute("user", user4);
				
				break;
				
			case "/user-delete.do":
				
				User user3 = new User();
				user3.setU_idx(Integer.parseInt(request.getParameter("u_idx")));
				//user3.setU_idx(Integer.parseInt(request.getParameter("u_idx")));
				
				userService = UserService.getInstance();
				userService.deleteUser(user3);

				view = "user/delete";				
				request.setAttribute("user", user3);
				
				break;
				
			case "/board-home.do":
				
				view = "board/home";				
				break;
			
			// ?????????
				/*
			case "/board-list.do":
				boardService = BoardService.getInstance();
				ArrayList<Board> list2 = boardService.getBoards();
				view = "board/board";
				request.setAttribute("list", list2);
					
				break;
				*/
				
			// ????????? ??????
			case "/board-list.do":

				String reqPage2 = request.getParameter("page");
				
				if (reqPage2 != null)
					page = Integer.parseInt(reqPage2);
				
				Board board31 = new Board();
				board31.setB_title(request.getParameter("b_content"));
				boardService = BoardService.getInstance();
				
				Pagination pagination2 = new Pagination();
				Search search2 = new Search();
				search2.setSearchType(request.getParameter("searchType"));
				search2.setSearchName(request.getParameter("searchName"));
				
				boardcount = boardService.getBoardCount(pagination2, search2);
				
				pagination2.setPage(page);
				pagination2.setCount(boardcount);
				pagination2.init();
				
				ArrayList<Board> list2 = boardService.getBoards(pagination2, board31, search2);
				request.setAttribute("list", list2);
				request.setAttribute("pagination", pagination2);	//??????
				request.setAttribute("board", board31);
				request.setAttribute("search", search2);
				
				view = "board/board-list";
				
				break;				
				
			case "/board-insert.do":
				
				view = "board/board-insert";
				
				break;
				
			case "/board-insert-process.do":
				
				session = request.getSession();
				user = (User)session.getAttribute("user");
				
				String saveDir = "C:\\Users\\l5-morning\\Documents\\work11\\lcomputerstudy\\src\\main\\webapp\\img";
				String enctype = "utf-8";
				System.out.println("saveDir" + saveDir);
				int size = 10 * 1024 * 1024;
				MultipartRequest multi = new MultipartRequest(request, saveDir, size, enctype, new DefaultFileRenamePolicy());
				
				String b_title = multi.getParameter("b_title");
				String b_content = multi.getParameter("b_content");
				String b_filename = multi.getFilesystemName("b_filename");
				String origin = multi.getOriginalFileName("b_filename");
				//long fileSize = multi.getFile("b_filename").length();
				
				System.out.println(b_title);
				System.out.println(b_content);
				System.out.println(origin);
				
				Board board = new Board();
				board.setB_title(b_title);
				board.setB_content(b_content);
				board.setB_filename(b_filename);
				//board.setB_title(request.getParameter(b_title));
				//board.setB_content(request.getParameter(b_content));
				//board.setB_filename(request.getParameter(b_filename));
				//board.setB_title(request.getParameter("b_title"));
				//board.setB_content(request.getParameter("b_content"));
				//board.setB_filename(request.getParameter("b_filename"));
				board.setUser(user);
				
				boardService = BoardService.getInstance();
				boardService.insertBoard(board);
				
				view = "board/board-insert-result";
				
				request.setAttribute("board", board);
				
				break;
				
				// ?????? ?????????					
			case "/board-fileUpload.do":
							
				session = request.getSession();
				user = (User)session.getAttribute("user");
								
				Board board300 = new Board();
				//board300.setB_filename(request.getParameter("b_filename"));
				board300.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
				boardService = BoardService.getInstance();
								
				boardService.fileUpload(board300);
								
				view = "board/board-fileUpload";
								
				request.setAttribute("board", board300);
								
				break;				
				
				
			// ?????? ??????					
			case "/board-detail.do":
				
				session = request.getSession();
				user = (User)session.getAttribute("user");

				Board board3 = new Board();
				board3.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
				board3.setUser(user);
				boardService = BoardService.getInstance();
				Pagination pagination3 = new Pagination();
				
				boardService.getBoardviewcount(board3);
				board3 = boardService.detailBoard(board3);
				
				commentService = CommentService.getInstance();
				Comment comment1 = new Comment();
				comment1.setB_idx(board3.getB_idx());
				
				commentList = commentService.getComment(pagination3, board3);
				
				view = "board/board-detail";
				
				request.setAttribute("board", board3);
				request.setAttribute("commentList", commentList);
				
				break;				
				
			// ?????? ??????					
			case "/board-edit.do":
				
				session = request.getSession();
				user = (User)session.getAttribute("user");

				Board board1 = new Board();
				//user1.setU_idx(request.getParameter("id"));
				board1.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
				board1.setUser(user);
				
				boardService = BoardService.getInstance();
				board1 = boardService.editBoard(board1);
						
				view = "board/board-edit";
				request.setAttribute("board", board1);
						
				break;
				
			// ?????? ??????				
			case "/board-edit-process.do":
				
				Board board2 = new Board();
				board2.setB_title(request.getParameter("b_title"));
				board2.setB_content(request.getParameter("b_content"));
				board2.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
				
				boardService = BoardService.getInstance();
				boardService.editProcessBoard(board2);
				
				view = "board/board-edit-result";
				
				request.setAttribute("board", board2);
				
				break;
				
			// ?????? ??????
			case "/board-delete.do":
				
				Board board4 = new Board();
				board4.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
				//user3.setU_idx(Integer.parseInt(request.getParameter("u_idx")));
				
				boardService = BoardService.getInstance();
				boardService.deleteBoard(board4);

				view = "board/board-delete";
				
				request.setAttribute("board", board4);
				break;
				
			// ?????? ??????
			case "/board-reply.do":
				
				Board board5 = new Board();
				board5.setB_group(Integer.parseInt(request.getParameter("b_group")));
				board5.setB_order(Integer.parseInt(request.getParameter("b_order")));
				board5.setB_depth(Integer.parseInt(request.getParameter("b_depth")));
				
				boardService = BoardService.getInstance();
				boardService.replyUpBoard(board5);

				view = "board/board-reply";				
				request.setAttribute("board", board5);
				
				break;
				
			case "/board-reply-process.do":
				
				session = request.getSession();
				user = (User)session.getAttribute("user");
				
				Board board6 = new Board();
				board6.setB_title(request.getParameter("b_title"));
				board6.setB_content(request.getParameter("b_content"));
				board6.setB_group(Integer.parseInt(request.getParameter("b_group")));
				board6.setB_order(Integer.parseInt(request.getParameter("b_order")));
				board6.setB_depth(Integer.parseInt(request.getParameter("b_depth")));
				board6.setUser(user);
				
				boardService = BoardService.getInstance();
				boardService.replyinsertBoard(board6);
				
				view = "/board/board-reply-result";				
				request.setAttribute("board", board6);
				
				break;
				
				
			//?????? ?????? (???????????????)
			case "/comment-insert-process.do":
				
				session = request.getSession();
				user = (User)session.getAttribute("user");
				
				Comment comment3 = new Comment();
				
				comment3.setC_content(request.getParameter("c_content"));
				comment3.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
				 
				comment3.setUser(user);
				
				commentService = CommentService.getInstance();
				commentService.insertComment(comment3);
				
				isRedirected = true;
				view = "board-detail.do?b_idx=" + comment3.getB_idx();
			
				request.setAttribute("comment", comment3);		
				
				break;
				
				// ?????? ??????
			case "/comment-delete.do":
				
				Comment comment5 = new Comment();
				comment5.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
				comment5.setC_group(Integer.parseInt(request.getParameter("c_group")));
				comment5.setC_order(Integer.parseInt(request.getParameter("c_order")));
				comment5.setC_depth(Integer.parseInt(request.getParameter("c_depth")));
				
				commentService = CommentService.getInstance();
				commentService.deleteComment(comment5);

				isRedirected = true;
				view = "board-detail.do?b_idx=" + comment5.getB_idx();			
				request.setAttribute("comment", comment5);
				
				break;
				
				// ?????? ??????(ajax)
			case "/comment-edit.do":
				session = request.getSession();
				user = (User)session.getAttribute("user");
				
				Comment comment6 = new Comment();
				
				comment6.setC_content(request.getParameter("c_content"));
				comment6.setC_idx(Integer.parseInt(request.getParameter("c_idx")));				
				comment6.setUser(user);
				
				commentService = CommentService.getInstance();
				commentService.editComment(comment6);
				
				Pagination pagination64 = new Pagination();
				
				Board board68 = new Board();
				board68.setB_idx(Integer.parseInt(request.getParameter("b_idx")));				
				boardService = BoardService.getInstance();
				
				commentList = commentService.getComment(pagination64, board68);
				
				view = "comment/aj-comment-edit";
				request.setAttribute("commentList", commentList);
				
				break;
			
				//?????? ?????? ??????
			case "/comment-reply-process.do":
				
				session = request.getSession();
				user = (User)session.getAttribute("user");
				
				Comment comment8 = new Comment();
				comment8.setC_content(request.getParameter("c_content"));
				comment8.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
				comment8.setC_group(Integer.parseInt(request.getParameter("c_group")));
				comment8.setC_order(Integer.parseInt(request.getParameter("c_order")));
				comment8.setC_depth(Integer.parseInt(request.getParameter("c_depth")));
				comment8.setUser(user);
		
				commentService = CommentService.getInstance();
				commentService.replyinsertComment(comment8);
				
				Pagination pagination84 = new Pagination();
				
				Board board88 = new Board();
				board88.setB_idx(Integer.parseInt(request.getParameter("b_idx")));				
				boardService = BoardService.getInstance();
				
				commentList = commentService.getComment(pagination84, board88);
				
				
				view = "comment/aj-comment-reply";
				request.setAttribute("commentList", commentList);
				
				break;
				
		}
		
		//RequestDispatcher rd = request.getRequestDispatcher(view+".jsp");
		//rd.forward(request, response);
		
		// ???????????????
		if (!isRedirected) {
			RequestDispatcher rd = request.getRequestDispatcher(view+".jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect(view);
		}
	}
	
	String checkSession(HttpServletRequest request, HttpServletResponse response, String command) {
		HttpSession session = request.getSession();
		
		String[] authList = {
				"/user-list.do"
				,"/user-insert.do"
				,"/user-insert-process.do"
				,"/user-detail.do"
				,"/user-edit.do"
				,"/user-edit-process.do"
				,"/logout.do"
			};
		
		for (String item : authList) {
			if (item.equals(command)) {
				if (session.getAttribute("user") == null) {
					command = "/access-denied.do";
				}
			}
		}
		return command;
	}	

}

