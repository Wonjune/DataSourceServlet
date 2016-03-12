package spms.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.commons.dbcp.BasicDataSource;

import spms.dao.MemberDao;

@WebListener
public class ContextLoaderListener implements ServletContextListener{

	BasicDataSource ds;
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		try{
			ServletContext sc = event.getServletContext();
			
			ds = new BasicDataSource();
			ds.setDriverClassName(sc.getInitParameter("driver"));
			ds.setUrl(sc.getInitParameter("url"));
			ds.setUsername(sc.getInitParameter("username"));
			ds.setPassword(sc.getInitParameter("password"));

			MemberDao dao = new MemberDao();
			dao.setDataSource(ds);
			sc.setAttribute("memberDao", dao);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		try{
			if(ds != null) ds.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
