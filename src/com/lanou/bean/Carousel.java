package com.lanou.bean;

import java.io.Serializable;

public class Carousel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private int id;
    private String timg;
    
	public Carousel() {
		super();
	}
	
	public Carousel(int id, String timg) {
		super();
		this.id = id;
		this.timg = timg;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTimg() {
		return timg;
	}
	public void setTimg(String timg) {
		this.timg = timg;
	}
    
}
