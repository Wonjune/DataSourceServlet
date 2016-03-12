package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import spms.vo.*;

public class MemberDao{
	
	DataSource ds;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public void setDataSource(DataSource ds){
		this.ds = ds;
	}
	
	public ArrayList<Member> selectList() throws Exception{
		ArrayList<Member> members = new ArrayList<Member>();
		
		try{
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select mno, email, mname, cre_date from members order by mno asc");
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				members.add(new Member().setNo(rs.getInt(1))
						.setEmail(rs.getString(2))
						.setName(rs.getString(3))
						.setCreateDate(rs.getString(4))
						);
			}
		}catch(Exception e){
			throw e;
		}finally{
			try{ if(rs != null){ rs.close(); }}catch(Exception e){}
			try{ if(pstmt != null){ pstmt.close(); }}catch(Exception e){}
			try{ if(conn != null){ conn.close(); }}catch(Exception e){}
		}
		
		return members;
	}
	
	public int insert(Member member) throws Exception {
		try{
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("insert into members(mname, email, pwd, cre_date, mod_date) values (?,?,?,now(),now())");
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getEmail());
			pstmt.setString(3, member.getPassword());
			return pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println("insert 에러");
			throw e;
		}finally{
			try{ if(pstmt != null){ pstmt.close(); }}catch(Exception e){}
			try{ if(conn != null){ conn.close(); }}catch(Exception e){}
		}
	}
	
	public Member selectOne(int no) throws Exception {
		try{
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select mname, email, pwd, cre_date, mod_date from members where mno = ?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				return new Member()
				.setNo(no)
				.setName(rs.getString("mname"))
				.setEmail(rs.getString("email"))
				.setPassword(rs.getString("pwd"))
				.setCreateDate(rs.getString("cre_date"))
				.setModifiedDate(rs.getString("mod_date"));
			}else{
				return new Member();
			}
		}catch(Exception e){
			System.out.println("selectone 에러");
			throw e;
		}finally{
			try{ if(rs != null){ rs.close(); }}catch(Exception e){}
			try{ if(pstmt != null){ pstmt.close(); }}catch(Exception e){}
			try{ if(conn != null){ conn.close(); }}catch(Exception e){}
		}
	}
	
	public int update(Member member) throws Exception {
		try{
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("update members set mname = ?, email = ?, pwd = ?, mod_date = now() where mno = ?");
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getEmail());
			pstmt.setString(3, member.getPassword());
			pstmt.setInt(4, member.getNo());
			return pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println("update 에러");
			throw e;
		}finally{
			try{ if(pstmt != null){ pstmt.close(); }}catch(Exception e){}
			try{ if(conn != null){ conn.close(); }}catch(Exception e){}
		}
	}
	
	public int delete(int no) throws Exception {
		try{
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("delete from members where mno = ?");
			pstmt.setInt(1, no);
			return pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println("delete 에러");
			throw e;
		}finally{
			try{ if(pstmt != null){ pstmt.close(); }}catch(Exception e){}
			try{ if(conn != null){ conn.close(); }}catch(Exception e){}
		}
	}
	
	public Member exist(String email, String password) throws Exception {
		try{
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select email, mname from members where email = ? and pwd = ?");
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				return new Member().setEmail(rs.getString("email")).setName(rs.getString("mname"));
			}else{
				return null;
			}
		}catch(Exception e){
			System.out.println("exist 에러");
			throw e;
		}finally{
			try{ if(rs != null){ rs.close(); }}catch(Exception e){}
			try{ if(pstmt != null){ pstmt.close(); }}catch(Exception e){}
			try{ if(conn != null){ conn.close(); }}catch(Exception e){}
		}
	}
}
