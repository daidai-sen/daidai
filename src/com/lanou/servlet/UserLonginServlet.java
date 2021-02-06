package com.lanou.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.lanou.bean.User;
import com.lanou.service.IUserService;
import com.lanou.service.UserServiceImpl;
import com.lanou.util.JSONBean;
@WebServlet("/loginuser")
public class UserLonginServlet extends HttpServlet{
    private IUserService iuService = new UserServiceImpl();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String op = req.getParameter("op");
    	if(op.equals("dl")) {
    		try {
    			String name = req.getParameter("name");
    	    	String pwd = req.getParameter("pwd");
    	    	User user = iuService.getByNameAndPwd(name, pwd);
    	    	int id = user.getId();
    	    	Cookie ck1 =new Cookie("name",name);//名字和密码存在Cookie里
    			Cookie ck2 =new Cookie("pwd",pwd);
    			Cookie ck3 =new Cookie("id",id+"");
    			
    			ck1.setMaxAge(1000);
    			ck2.setMaxAge(1000);
    			ck3.setMaxAge(1000);
    			resp.addCookie(ck1);
    			resp.addCookie(ck2);
    			resp.addCookie(ck3);
    			
    			JSONBean js = new JSONBean("0", "", iuService.getCount(),user );
    			String jsonstr = JSON.toJSONString(js);
    			resp.setContentType("text/html;charset=UTF-8");
    			PrintWriter pw = resp.getWriter();
    			pw.write(jsonstr);
    			pw.flush();
    			pw.close();
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}else if(op.equals("hq")) {
    		String name="";
    		String pwd="";
    		String id="";
    		Cookie[] ck =req.getCookies();
    		if(ck!=null&&ck.length!=0) {
    			for(Cookie c : ck) {
    		    	String key=c.getName();
    		    	if(key.equals("name")) {
    		    		name=c.getValue();
    		    	}else if(key.equals("pwd")) {
    		    		pwd=c.getValue();
    		    	}else if(key.equals("id")) {
    		    		id =c.getValue();
    		    	}
    		    }
    		}
    		    User user = new User();
    		    user.setName(name);
    		    user.setPwd(pwd);
  		        user.setId(Integer.parseInt(id));
    		    String jsonStr = JSON.toJSONString(user);
	    	    resp.setContentType("text/html;charset=utf-8");
	    	    PrintWriter pw=resp.getWriter();
	    	    pw.write(jsonStr);
	    	    pw.flush();
	    	    pw.close();
    	}
    	
    }
}
