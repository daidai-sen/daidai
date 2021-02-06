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
import com.lanou.bean.Type;
import com.lanou.dao.ITypeDao;
import com.lanou.service.ITypeService;
import com.lanou.service.TypeServiceImpl;
import com.lanou.util.JSONBean;
@WebServlet("/userbefore/type")
public class TypeServlet extends HttpServlet{
    private ITypeService itService = new TypeServiceImpl();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String op = req.getParameter("op");
    	if(op ==null || op.equals("")) {
    		try {
    			List<Type> typelist = itService.getAll();
    			JSONBean js = new JSONBean("0", "", null, typelist);
    			String jsonstr = JSON.toJSONString(js);
    			resp.setContentType("text/html;charset=utf-8");
    			PrintWriter pw = resp.getWriter();
    			pw.write(jsonstr);
//    			System.out.println(jsonstr);
    			pw.flush();
    			pw.close();
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}else if (op.equals("action")){
    		String id = req.getParameter("id");
    		String title = req.getParameter("title");
    		String parentid =req.getParameter("parentid");
    		if(id==null || id.equals("")) {
    			try {
					itService.add(title, Integer.parseInt(parentid));
				}  catch (Exception e) {
					e.printStackTrace();
				}
    		}else {
    			    try {
						itService.edit(id, title);
					}  catch (Exception e) {
						e.printStackTrace();
					}
    		}
    		JSONBean js = new JSONBean("200", "", null, null);
			String jsonstr = JSON.toJSONString(js);
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter pw = resp.getWriter();
			pw.write(jsonstr);
			pw.flush();
			pw.close();
    	}else if(op.equals("del")) {
    		String id = req.getParameter("id");
    		try {
				itService.delete(id);
				JSONBean js = new JSONBean("200", "", null, null);
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
