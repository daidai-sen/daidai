package com.lanou.servlet;

import java.io.IOException;
import java.io.PrintWriter;

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
@WebServlet("/loginServlet")
public class AdminLoginServlet extends HttpServlet{
     private IAdminService iaService = new AdminServiceImpl();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String adminname = req.getParameter("adminname");
    	String adminpwd = req.getParameter("password");
    	try {
			Admin admin =iaService.getByNameAndPwd(adminname, adminpwd);
			req.getSession().setAttribute("admin", admin);
			resp.setContentType("text/html;charset=UTF-8");
			JSONBean js = new JSONBean("0", "", null, null);
			String jsonstr = JSON.toJSONString(js);
			PrintWriter pw = resp.getWriter();
			pw.write(jsonstr);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
}
