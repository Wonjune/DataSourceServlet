package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

import spms.dao.MemberDao;
import spms.vo.Member;

@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		RequestDispatcher rd = req.getRequestDispatcher("/auth/LogInForm.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		Member member = new Member();
		
		try{
			ServletContext sc = this.getServletContext();
			MemberDao dao = (MemberDao)sc.getAttribute("memberDao");
			member = dao.exist(req.getParameter("email"), req.getParameter("password"));
			
			if(member != null){
				HttpSession session = req.getSession();
				session.setAttribute("member", member);
				resp.sendRedirect("../member/list");
			}else{
				RequestDispatcher rd = req.getRequestDispatcher("/auth/LogInFail.jsp");
				rd.forward(req, resp);
			}
			
		}catch(Exception e){
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
			rd.forward(req, resp);
		}
	}
}
