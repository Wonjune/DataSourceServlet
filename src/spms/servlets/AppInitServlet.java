package spms.servlets;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class AppInitServlet extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		//웹어플리케이션 시작 시 DB 연결을 위한 JDBC Connection 을 미리 설정하여 Context 저장소에 미리 담아 놓음
		System.out.println("AppInitServlet 준비...");
		super.init(config);
		
		try{
			ServletContext sc = this.getServletContext();
			
			Class.forName(sc.getInitParameter("driver"));
			Connection conn = DriverManager.getConnection(
					sc.getInitParameter("url"), 
					sc.getInitParameter("username"), 
					sc.getInitParameter("password"));
			sc.setAttribute("conn", conn);
			
		}catch(Exception e){
			throw new ServletException(e);
		}
	}
	
	@Override
	public void destroy() {
		//웹어플리케이션 종료 시 기존에 연결된 JDBC 커넥션을 해제
		System.out.println("AppInitServlet 마무리...");
		super.destroy();
		
		try{
			ServletContext sc = this.getServletContext();
			Connection conn = (Connection)sc.getAttribute("conn");
			if(conn != null && conn.isClosed() == false) conn.close();
		}catch(Exception e){
			
		}
	}
}
