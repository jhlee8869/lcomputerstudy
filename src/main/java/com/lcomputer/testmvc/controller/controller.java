package com.lcomputer.testmvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lcomputer.testmvc.service.BoardService;
import com.lcomputer.testmvc.service.UserService;
import com.lcomputer.testmvc.vo.Pagination;
import com.lcomputer.testmvc.vo.User;
import com.lcomputer.testmvc.vo.Board;

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
		
		HttpSession session = null;
		BoardService boardService = null;
		command = checkSession(request, response, command);
		
		int usercount = 0;	//추가
		int boardcount = 0;
		int boardviewcount = 0;
		int page = 1;	//추가
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		switch (command) {
			case "/user-list.do":
				String reqPage = request.getParameter("page");
				/*
				if (reqPage != null) { 
					page = Integer.parseInt(reqPage);
					page = (page-1)*3;
				}
				*/
				if (reqPage != null)
					page = Integer.parseInt(reqPage);

				UserService userService = UserService.getInstance();
				usercount = userService.getUsersCount();
				
				Pagination pagination = new Pagination();
				pagination.setPage(page);
				pagination.setCount(usercount);
				pagination.init();
				
				ArrayList<User> list = userService.getUsers(pagination);
		
				//usercount = userService.getUsersCount();	//추가
				request.setAttribute("list", list);
				//request.setAttribute("usercount", usercount);	//추가
				request.setAttribute("pagination", pagination);	//추가
				
				view = "user/list";
				break;
				
			case "/user-insert.do":
				view = "user/insert";
				break;
				
			// 코드 추가	
			case "/user-insert-process.do":
				User user = new User();
				user.setU_id(request.getParameter("id"));
				user.setU_pw(request.getParameter("password"));
				user.setU_name(request.getParameter("name"));
				user.setU_tel(request.getParameter("tel1") + "-" + request.getParameter("tel2") + "-" + request.getParameter("tel3"));
				user.setU_age(request.getParameter("age"));
				
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
//					session.setAttribute("u_idx", user.getU_idx());
//					session.setAttribute("u_id", user.getU_id());
//					session.setAttribute("u_pw", user.getU_pw());
//					session.setAttribute("u_name", user.getU_name());
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
			
			// 게시판
				/*
			case "/board-list.do":
				boardService = BoardService.getInstance();
				ArrayList<Board> list2 = boardService.getBoards();
				view = "board/board";
				request.setAttribute("list", list2);
					
				break;
				*/
				
			case "/board-list.do":

				String reqPage2 = request.getParameter("page");
				
				if (reqPage2 != null)
					page = Integer.parseInt(reqPage2);
				
				boardService = BoardService.getInstance();
				boardcount = boardService.getBoardCount();
				//boardviewcount = boardService.getBoardviewcount();
				
				Pagination pagination2 = new Pagination();
				pagination2.setPage(page);
				pagination2.setCount(boardcount);
				//pagination2.setCount(boardviewcount);
				pagination2.init();
				
				ArrayList<Board> list2 = boardService.getBoards(pagination2);
				request.setAttribute("list", list2);
				request.setAttribute("pagination", pagination2);	//추가
				
				view = "board/board";
				
				break;

			case "/board-insert.do":
				
				view = "board/board-insert";
				
				break;
				
			case "/board-insert-process.do":
				session = request.getSession();
				user = (User)session.getAttribute("user");
				/*User user5 = new User();
				user5.setU_idx(user.getU_idx());*/
				
				Board board = new Board();
				board.setB_title(request.getParameter("b_title"));
				board.setB_content(request.getParameter("b_content"));
				//board.setB_viewcount(Integer.parseInt(request.getParameter("b_viewcount")));
				board.setUser(user);
				
				boardService = BoardService.getInstance();
				boardService.insertBoard(board);
				
				view = "board/board-insert-result";
				
				break;
			// 상세 화면					
			case "/board-detail.do":
				session = request.getSession();
				user = (User)session.getAttribute("user");
				
				//boardviewcount = boardService.getBoardviewcount();
				Board board3 = new Board();
				board3.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
				board3.setUser(user);
				
				boardService = BoardService.getInstance();
				boardService.getBoardviewcount(board3);
				board3 = boardService.detailBoard(board3);
						
				view = "board/board-detail";
				request.setAttribute("board", board3);
				break;
			// 수정 화면					
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
			// 수정 동작				
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
			// 삭제 화면
			case "/board-delete.do":
				
				Board board4 = new Board();
				board4.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
				//user3.setU_idx(Integer.parseInt(request.getParameter("u_idx")));
				
				boardService = BoardService.getInstance();
				boardService.deleteBoard(board4);

				view = "board/board-delete";
				
				request.setAttribute("board", board4);
				break;
			// 답글 화면
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
				
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(view+".jsp");
		rd.forward(request, response);
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

