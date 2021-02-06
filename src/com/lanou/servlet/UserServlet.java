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
import com.lanou.bean.User;
import com.lanou.service.IUserService;
import com.lanou.service.UserServiceImpl;
import com.lanou.util.JSONBean;
@WebServlet("/userbefore/user")
public class UserServlet extends HttpServlet{
    private IUserService iuServlet = new UserServiceImpl();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String op = req.getParameter("op");
    	if(op==null || op.equals("")) {
    		String pagenum = req.getParameter("page");
        	//System.out.println("page");
        	String pagecount = req.getParameter("limit");
        	try {
    			List<User> userlist = iuServlet.getByPage(Integer.parseInt(pagenum), Integer.parseInt(pagecount));
    			JSONBean js = new JSONBean("0", "", iuServlet.getCount(), userlist);
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
					iuServlet.delete(id);
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
    		String name = req.getParameter("name");
    		String pwd = req.getParameter("pwd");
    		String phone = req.getParameter("phone");
    		String mail = req.getParameter("mail");
    		String address = req.getParameter("address");
    		try {
				iuServlet.add(name, pwd, phone, mail, address);
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
    		String name = req.getParameter("name");
    		System.out.println(name);
    		String pwd = req.getParameter("pwd");
    		String phone = req.getParameter("phone");
    		String mail = req.getParameter("mail");
    		String address = req.getParameter("address");   
			try {
				iuServlet.exit(id, name, pwd, phone, mail, address);
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
