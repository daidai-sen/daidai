package com.lanou.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.alibaba.fastjson.JSON;
import com.lanou.bean.Carousel;
import com.lanou.service.CarouselServiceImpl;
import com.lanou.service.ICarouselService;
import com.lanou.util.JSONBean;

public class CarouselServlet extends HttpServlet{
    private ICarouselService icService = new CarouselServiceImpl();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String op =req.getParameter("op");
		String pagenum = req.getParameter("page");
		String pagecount =req.getParameter("limit");
		if(op==null || op.equals("")) {
			 try {
				List<Carousel> carlist = icService.getByPage(Integer.parseInt(pagenum), Integer.parseInt(pagecount));
				JSONBean js= new JSONBean("0", "", icService.getCount(), carlist);
				String jsonstr = JSON.toJSONString(js);
    			resp.setContentType("text/html;charset=utf-8");
    			PrintWriter pw = resp.getWriter();
    			pw.write(jsonstr);
    			pw.flush();
    			pw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(op.equals("delete")) {
			String id = req.getParameter("id");
			try {
				icService.delete(id);
				JSONBean js= new JSONBean("0", "", null, null);
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
	    	 String timg =req.getParameter("timg");
		    try {
				icService.edit(id, timg);
				JSONBean js= new JSONBean("0", "", null, null);
				String jsonstr = JSON.toJSONString(js);
	 			resp.setContentType("text/html;charset=utf-8");
	 			PrintWriter pw = resp.getWriter();
	 			pw.write(jsonstr);
	 			pw.flush();
	 			pw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	  }else if(op.equals("add")) {
		     String timg =req.getParameter("nfile");
		     System.out.println(timg);
			 try {
	    			icService.add(timg);
	    			resp.sendRedirect("../admin/lunbo/lunboList.html");
	    		} catch (Exception e) {
	    			e.printStackTrace();
	    	}
	  }
	}
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    	 Part part = req.getPart("formFile");
    	 String imgname = part.getSubmittedFileName();//得到上传图片的名字
    	 System.out.println(imgname);
    	 String path = req.getServletContext().getRealPath("");
    	 System.out.println(path);
    	 path = path.substring(0, path.length()-1);
    	 path = path.substring(0, path.lastIndexOf("\\"));
    	 System.out.println(path);
    	 path = path+File.separator+"imgfile"+File.separator;
    	 File file = new File(path);
    	 if(!file.exists()) {
    		 file.mkdir();
    	 }else {
    		 
    	 }
    	 part.write(path+imgname);//把照片放到文件夹下
    	 
	     PrintWriter pw = resp.getWriter();
		 pw.write(imgname);
		 pw.flush();
		 pw.close();
    }
}
