package com.lanou.Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest	req=(HttpServletRequest) request;	
		HttpServletResponse resp=(HttpServletResponse) response;
		
		//·ÅÐÐlogin.htmlºÍlogin
		String uri=req.getRequestURI();
		//System.out.println(uri);
		if(uri.endsWith("loginuser") ||uri.endsWith("login.html") || uri.endsWith("loginServlet") || uri.contains("/js/") ||uri.contains("/userbefore/")){
			chain.doFilter(req, resp);
		}else {
			HttpSession session =req.getSession();
			Object obj=session.getAttribute("admin");
			if(obj!=null) {
				chain.doFilter(req, resp);
			}else {
				resp.sendRedirect("/XXXManage/admin/login.html");
			}
		}
	}
}
