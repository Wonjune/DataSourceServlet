package spms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class DBConnectionPool {
	String url = null;
	String username = null;
	String password = null;
	ArrayList<Connection> connList = new ArrayList<Connection>();
	
	public DBConnectionPool(String driver, String url, String username, String password) throws Exception {
		this.url = url;
		this.username = username;
		this.password = password;
		
		Class.forName(driver);
	}
	
	public Connection getConnection() throws Exception{
		System.out.println("DBConnectionPool length : " + connList.size());
		if(!connList.isEmpty()){
			Connection conn = connList.remove(0);
			if(conn.isValid(10)){
				return conn;
			}
		}
		return DriverManager.getConnection(url, username, password);
	}
	
	public void returnConnection(Connection conn) throws Exception{
		connList.add(conn);
	}
	
	public void closeAll(){
		for(Connection conn : connList){
			try{conn.close();}catch(Exception e){}
		}
	}
}
