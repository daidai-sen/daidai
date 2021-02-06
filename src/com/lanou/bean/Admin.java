package com.lanou.bean;

import java.io.Serializable;

public class Admin implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private int id;
    private String adminname;
    private String adminpwd;
    private String backup1;
    private String backup2;
    private String backup3;
    
	public Admin() {
		super();
	}
	
	public Admin(int id, String adminname, String adminpwd) {
		super();
		this.id = id;
		this.adminname = adminname;
		this.adminpwd = adminpwd;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAdminname() {
		return adminname;
	}
	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}
	public String getAdminpwd() {
		return adminpwd;
	}
	public void setAdminpwd(String adminpwd) {
		this.adminpwd = adminpwd;
	}
    
}
