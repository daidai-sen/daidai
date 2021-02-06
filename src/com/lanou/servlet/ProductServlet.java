package com.lanou.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.alibaba.fastjson.JSON;
import com.lanou.bean.Product;
import com.lanou.bean.Type;
import com.lanou.bean.User;
import com.lanou.dao.IProductDao;
import com.lanou.dao.ITypeDao;
import com.lanou.dao.ProductDaoImpl;
import com.lanou.dao.TypeDaoImpl;
import com.lanou.service.IProductService;
import com.lanou.service.ProductServiceImpl;
import com.lanou.util.JSONBean;

public class ProductServlet extends HttpServlet{
    private IProductService ipService = new ProductServiceImpl();
    private IProductDao ipDao = new ProductDaoImpl();
    private ITypeDao itDao = new TypeDaoImpl();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String op = req.getParameter("op");
    	if(op==null || op.equals("")) {
    		String pagenum = req.getParameter("page");
        	String pagecount = req.getParameter("limit");
        	try {
    			List<Product> prolist = ipDao.getByPage(Integer.parseInt(pagenum), Integer.parseInt(pagecount));
    			JSONBean js = new JSONBean("0", "", ipService.getCount(), prolist);
    			String jsonstr = JSON.toJSONString(js);
    			resp.setContentType("text/html;charset=utf-8");
    			PrintWriter pw = resp.getWriter();
    			pw.write(jsonstr);
    			pw.flush();
    			pw.close();
    		}  catch (Exception e) {
    			e.printStackTrace();
    		}
    	}else if(op.equals("cxtp")) {
    		String typeid = req.getParameter("typeid");
    		try {
				List<Product> prolist = ipService.getByTypeId(Integer.parseInt(typeid));
				JSONBean js = new JSONBean("0", "", ipService.getCount(), prolist);
    			String jsonstr = JSON.toJSONString(js);
    			resp.setContentType("text/html;charset=utf-8");
    			PrintWriter pw = resp.getWriter();
    			pw.write(jsonstr);
    			pw.flush();
    			pw.close();
    		}  catch (Exception e) {
				e.printStackTrace();
			}
    	    
    	}else if(op.equals("ss")) {
    		String pname = req.getParameter("pname");
    		try {
				List<Product> prolist = ipService.getByName(pname);
				JSONBean js = new JSONBean("0", "", ipService.getCount(), prolist);
    			String jsonstr = JSON.toJSONString(js);
    			resp.setContentType("text/html;charset=utf-8");
    			PrintWriter pw = resp.getWriter();
    			pw.write(jsonstr);
    			pw.flush();
    			pw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
    	
    	}else if (op.equals("delete")){
    		String id =req.getParameter("id");
    		  try {
				ipService.delete(id);
				JSONBean js = new JSONBean("0", "", null, null);
			    String jsonstr = JSON.toJSONString(js);
			    resp.setContentType("text/html;charset=utf-8");
			    PrintWriter pw =resp.getWriter();
			    pw.write(jsonstr);
			    pw.flush();
			    pw.close();
			} catch (Exception e) {
				e.printStackTrace();
			} 
    	}else if(op.equals("edit")) {
    		String id =req.getParameter("id");
    		String pimg=req.getParameter("nfile");
        	String pname = req.getParameter("pname");
        	String price = req.getParameter("price");
        	String quiz1=req.getParameter("quiz1");
        	String quiz2 = req.getParameter("quiz2");
        	String quiz3 = req.getParameter("quiz3");
        	String ptitle =quiz1+"-"+quiz2+"-"+quiz3;
        	String pv = req.getParameter("pv");
        	String typeid = req.getParameter("typeid");
    		try {
				ipService.edit(id, pname, pimg, Double.parseDouble(price), ptitle, Integer.parseInt(pv), typeid);
				JSONBean js = new JSONBean("0", "", null, null);
			    String jsonstr = JSON.toJSONString(js);
			    resp.setContentType("text/html;charset=utf-8");
			    PrintWriter pw =resp.getWriter();
			    pw.write(jsonstr);
			    pw.flush();
			    pw.close();
    		}  catch (Exception e) {
				e.printStackTrace();
			}
    	}else if(op.equals("add")) {
    		String pimg=req.getParameter("nfile");
        	String pname = req.getParameter("pname");
        	String price = req.getParameter("price");
        	String quiz1=req.getParameter("quiz1");
        	String quiz2 = req.getParameter("quiz2");
        	String quiz3 = req.getParameter("quiz3");
        	String ptitle =quiz1+"-"+quiz2+"-"+quiz3;
        	String pv = req.getParameter("pv");
        	String typeid = req.getParameter("typeid");
    		try {
				ipService.add(pname, pimg,Double.parseDouble(price), ptitle,Integer.parseInt(pv), typeid);
			    JSONBean js = new JSONBean("0", "", null, null);
			    String jsonstr = JSON.toJSONString(js);
			    resp.setContentType("text/html;charset=utf-8");
			    PrintWriter pw =resp.getWriter();
			    pw.write(jsonstr);
			    pw.flush();
			    pw.close();
    		} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String op = req.getParameter("op");
    	System.out.println(op);
    	if(op == null||op.equals("")) {
    		Part part = req.getPart("formFile");
        	String name= part.getSubmittedFileName();
        	String path = req.getServletContext().getRealPath("");
        	path =path.substring(0, path.length()-1);
        	path =path.substring(0, path.lastIndexOf("\\"));
        	
        	path =path+File.separator+"pimgfile"+File.separator;
        	File file =new File(path);
        	if(!file.exists()) {
        		file.mkdir();
        	}else {
        		
        	}
        	part.write(path+name);
        	PrintWriter pw =resp.getWriter();
        	pw.write(name);
        	pw.flush();
        	pw.close();
    	}else if(op.equals("one")) {
    		try {
				List<Type> typelist = itDao.getByParentId(0);
			    String jsonstr = JSON.toJSONString(typelist);
			    resp.setContentType("text/html;charset=utf-8");
			    PrintWriter pw =resp.getWriter();
			    pw.write(jsonstr);
			    pw.flush();
			    pw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}else if(op.equals("two")) {
		    String parentid = req.getParameter("parentid");
		    try {
				List<Type> typelist = itDao.getByParentId(Integer.parseInt(parentid));
			    String jsonstr = JSON.toJSONString(typelist);
			    resp.setContentType("text/html;charset=utf-8");
			    PrintWriter pw =resp.getWriter();
			    pw.write(jsonstr);
			    pw.flush();
			    pw.close();
		    }  catch (Exception e) {
				e.printStackTrace();
			}
    	}else if(op.equals("three")) {
		   String parentid = req.getParameter("parentid");  
		   try {
				List<Type> typelist = itDao.getByParentId(Integer.parseInt(parentid));
				resp.setContentType("text/html;charset=utf-8");
				String jsonstr = JSON.toJSONString(typelist);
				System.out.println(jsonstr);
			    PrintWriter pw =resp.getWriter();
			    pw.write(jsonstr);
			    pw.flush();
			    pw.close();
	    }  catch (Exception e) {
				e.printStackTrace();
			}
    	}
    }
}
