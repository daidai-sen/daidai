package com.lanou.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {
	
	
	private static Connection con = null;
	private static Statement st = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://10.10.12.59:3306/lvyou?useUnicode=true&characterEncoding=utf-8";
			String user = "root";
			String password = "123456";
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
	}
	
	public static void ZengShanGai(String sql) throws Exception {

		st = con.createStatement();
		
		st.execute(sql);
	}
	
	public static ResultSet queryUtil(String sql,Object[] os) throws Exception {
		ps = con.prepareStatement(sql);
		
		if(os!=null&&os.length>0) {
			for(int i=0;i<os.length;i++) {
				ps.setObject(i+1, os[i]);
			}
		}
		
		rs = ps.executeQuery();
		
		return rs;
	}
	
	public static void closeRes() throws Exception {
		if(con!=null) {
			con.close();
		}
		if(rs!=null) {
			rs.close();
		}
		if(ps!=null) {
			ps.close();
		}
		if(st!=null) {
			st.close();
		}
		
	}
}
