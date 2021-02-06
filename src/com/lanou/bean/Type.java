package com.lanou.bean;

import java.io.Serializable;
import java.util.List;

public class Type implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private int id;
    private String title;
    private int parentid;
	private List<Type> children;
	public Type() {
		super();
	}
	
	public Type(int id, String title, List<Type> children) {
		super();
		this.id = id;
		this.title = title;
		this.children = children;
	}

	public Type(int id, String title, int parentid, List<Type> children) {
		super();
		this.id = id;
		this.title = title;
		this.parentid = parentid;
		this.children = children;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getParentid() {
		return parentid;
	}

	public void setParentid(int parentid) {
		this.parentid = parentid;
	}

	public List<Type> getChildren() {
		return children;
	}

	public void setChildren(List<Type> children) {
		this.children = children;
	}

}
