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
import com.lanou.bean.Cart;
import com.lanou.service.CartServiceImpl;
import com.lanou.service.ICartService;
import com.lanou.util.JSONBean;
@WebServlet("/userbefore/cart")
public class CartServlet extends HttpServlet{
    private ICartService icService = new CartServiceImpl();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String op =req.getParameter("op");
		String pagenum = req.getParameter("page");
		if(pagenum==null) {
			pagenum="1";
		}
		String pagecount = req.getParameter("limit");
		if(pagecount==null) {
			pagecount="10";
		}
		if(op==null ||op.equals("")) {
			try {
				List<Cart>  cartlist = icService.getByPage(Integer.parseInt(pagenum), Integer.parseInt(pagecount));
				JSONBean js = new JSONBean("0", "", icService.getCount(), cartlist);
				String jsonstr = JSON.toJSONString(js);
				System.out.println(jsonstr);
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
				icService.delete(id);
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
		String op =req.getParameter("op");
		if(op.equals("add")) {
			String username = req.getParameter("userid");
			String productname = req.getParameter("productid");
			try {
				icService.add(username, productname);
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
			String username = req.getParameter("username");
			String productname = req.getParameter("productname");
			try {
				icService.update(id, username, productname);
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
		}else if(op.equals("itss")){
			String userid = req.getParameter("userid");
			String pagenum = req.getParameter("page");
			if(pagenum==null) {
				pagenum="1";
			}
			String pagecount = req.getParameter("limit");
			if(pagecount==null) {
				pagecount="10";
			}
			try {
				List<Cart> cartlist = icService.getByDistinct(Integer.parseInt(userid), Integer.parseInt(pagenum), Integer.parseInt(pagecount));
				String jsonstr = JSON.toJSONString(cartlist);
				resp.setContentType("text/html;charset=utf-8");
				PrintWriter pw = resp.getWriter();
				pw.write(jsonstr);
				pw.flush();
			}  catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
