package com.lanou.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.lanou.bean.Admin;
import com.lanou.service.AdminServiceImpl;
import com.lanou.service.IAdminService;
import com.lanou.util.JSONBean;
@WebServlet("/admin")
public class AdminServlet extends HttpServlet{
     private IAdminService idService=new AdminServiceImpl();
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
    			List<Admin> admin = idService.getByPage(Integer.parseInt(pagenum), Integer.parseInt(pagecount));
    			JSONBean js = new JSONBean("0", "", idService.getCount(), admin);
    			String jsonstr = JSON.toJSONString(js);
    			resp.setContentType("text/html;charset=utf-8");
    			PrintWriter pw = resp.getWriter();
    			pw.write(jsonstr);
    			pw.flush();
    			pw.close();
    		}  catch (Exception e) {
    			e.printStackTrace();
    		}
        }else if(op.equals("delete")) {
    		String id = req.getParameter("id");
            try {
				idService.delete(id);
				JSONBean js = new JSONBean("0", "", null, null);
    			String jsonstr = JSON.toJSONString(js);
    			resp.setContentType("text/html;charset=utf-8");
    			PrintWriter pw = resp.getWriter();
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
    	if(op.equals("add")) {
    		String adminname = req.getParameter("adminname");
        	String adminpwd = req.getParameter("pwd");
    		try {
				idService.add(adminname, adminpwd);
				JSONBean js = new JSONBean("0", "", null, null);
				String jsonstr = JSON.toJSONString(js);
				resp.setContentType("text/html;charset=utf-8");
				PrintWriter pw = resp.getWriter();
				pw.write(jsonstr);
				pw.flush();
				pw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}else if(op.equals("edit")) {
    		String id = req.getParameter("id");
    		String adminname = req.getParameter("adminname");
        	String adminpwd = req.getParameter("pwd");
        	try {
				idService.exit(id, adminname, adminpwd);
				JSONBean js = new JSONBean("0", "", null, null);
				String jsonstr = JSON.toJSONString(js);
				resp.setContentType("text/html;charset=utf-8");
				PrintWriter pw = resp.getWriter();
				pw.write(jsonstr);
				pw.flush();
				pw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    	
    }
	
}
