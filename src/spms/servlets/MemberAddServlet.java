package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MemberDao;
import spms.vo.Member;

@WebServlet(urlPatterns = {"/member/add"})
public class MemberAddServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setContentType("text/html; charset=UTF-8");
		RequestDispatcher rd = req.getRequestDispatcher("/member/MemberForm.jsp");
		rd.include(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		Connection conn = null;
		
		try{
			ServletContext sc = this.getServletContext();
			MemberDao dao = (MemberDao)sc.getAttribute("memberDao");
			dao.insert(new Member().setName(req.getParameter("name")).setEmail(req.getParameter("email")).setPassword(req.getParameter("password")));
			resp.sendRedirect("list");
		}catch(Exception e){
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("Error.jsp");
			rd.forward(req, resp);
		}
		
	}

	

}
