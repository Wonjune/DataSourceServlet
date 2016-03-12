package spms.listeners;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import spms.dao.MemberDao;
import spms.util.DBConnectionPool;

@WebListener
public class ContextLoaderListener implements ServletContextListener{

	DBConnectionPool pool = null;
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		try{
			ServletContext sc = event.getServletContext();
			pool = new DBConnectionPool(sc.getInitParameter("driver")
					, sc.getInitParameter("url")
					, sc.getInitParameter("username")
					, sc.getInitParameter("password"));

			MemberDao dao = new MemberDao();
			dao.setDBConnectionPool(pool);
			sc.setAttribute("memberDao", dao);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		try{
			pool.closeAll();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
