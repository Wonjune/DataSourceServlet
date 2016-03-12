package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MemberDao;
import spms.vo.Member;

@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String no = req.getParameter("no");		
		
		try{
			ServletContext sc = this.getServletContext();
			
			MemberDao dao = (MemberDao)sc.getAttribute("memberDao");
			Member member = dao.selectOne(Integer.parseInt(no));
			
			req.setAttribute("member", member);
			resp.setContentType("text/html; charset=UTF-8");
			
			RequestDispatcher rd = req.getRequestDispatcher("/member/MemberUpdateForm.jsp");
			rd.include(req, resp);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		try{
			ServletContext sc = this.getServletContext();
			MemberDao dao = (MemberDao)sc.getAttribute("memberDao");
			int result = dao.update(new Member()
				.setName(req.getParameter("name"))
				.setEmail(req.getParameter("email"))
				.setPassword(req.getParameter("password"))
				.setNo(Integer.parseInt(req.getParameter("no"))));
			resp.setContentType("text/html; charset=UTF-8");
			
			resp.sendRedirect("list");
			
		}catch(Exception e){
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("Error.jsp");
			rd.forward(req, resp);
		}
	}

}
