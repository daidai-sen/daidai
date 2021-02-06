package com.lanou.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.lanou.bean.Product;
import com.lanou.bean.Type;
import com.lanou.bean.User;
import com.lanou.util.JDBCUtil;

public class ProductDaoImpl implements IProductDao{
    private ITypeDao itDao = new TypeDaoImpl();
	@Override
	public List<Product> getByPage(int pagenum,int pagecount) throws Exception {
		List<Product> prolist = new ArrayList<>();
		String sql = "select * from t_product limit "+(pagenum-1)*pagecount+","+pagecount+"";
		ResultSet rs = JDBCUtil.queryUtil(sql, null);
		while(rs.next()) {
			int id = rs.getInt("id");
			String pname = rs.getString("pname");
			String pimg = rs.getString("pimg");
			Double price = rs.getDouble("price");
			String ptitle = rs.getString("ptitle");
			int pv = rs.getInt("pv");
			int typeid = rs.getInt("typeid");
			
			List<Type> a= itDao.getById(typeid);
			String typename1 = a.get(0).getTitle();
			
			
			List<Type> b= itDao.getById(a.get(0).getParentid());
			String typename2 =b.get(0).getTitle();
			
			List<Type> c= itDao.getById(b.get(0).getParentid());
			String typename3=c.get(0).getTitle();
			String typename = typename3+"--"+typename2+"--"+typename1;
			Product product = new Product(id, pname, pimg, price, ptitle, pv,typename);
			prolist.add(product);
		}
		return prolist;
	}

	@Override
	public int getCount() throws Exception {
		String sql = "select count(*) count from t_product";
		ResultSet rs = JDBCUtil.queryUtil(sql, null);
		while(rs.next()) {
			int count = rs.getInt("count");
			return count;
		}
		return 0;
	}

	@Override
	public void add(String pname, String pimg, double price, String ptitle, int pv, String typename) throws Exception {
		String sql = "insert into t_product(pname, pimg, price, ptitle, pv, typeid) values('"+pname+"','"+pimg+"','"+price+"','"+ptitle+"','"+pv+"','"+typename+"')";
		JDBCUtil.ZengShanGai(sql);	
	}

	@Override
	public void edit(String id, String pname, String pimg, double price, String ptitle, int pv, String typename)
			throws Exception {
		String sql = "update t_product set pname='"+pname+"',pimg='"+pimg+"',price='"+price+"',ptitle='"+ptitle+"',pv='"+pv+"',typeid='"+typename+"' where id="+id;
		JDBCUtil.ZengShanGai(sql);	
	}

	@Override
	public void delete(String id) throws Exception {
		String sql = "delete from t_product where id="+id;
		JDBCUtil.ZengShanGai(sql);	
	}

	@Override
	public List<Product> getById(int id) throws Exception {
		List<Product> prolist = new ArrayList<>();
		String sql = "select * from t_product where id = "+id;
		ResultSet rs = JDBCUtil.queryUtil(sql, null);	
		while(rs.next()) {
	    	String pname = rs.getString("pname");
	    	String pimg = rs.getString("pimg");
	    	double price = rs.getDouble("price");
	    	String ptitle = rs.getString("ptitle");
	    	int pv = rs.getInt("pv");
	    	String typename = rs.getString("typeid");
	    	Product product = new Product(id,pname,pimg,price,ptitle,pv,typename);
	    	prolist.add(product);
		}
		return prolist;
	}

	@Override
	public List<Product> getByTypeId(int typeid) throws Exception {
		List<Product> prolist = new ArrayList<>();
		String sql = "select * from t_product where typeid = "+typeid;
		ResultSet rs = JDBCUtil.queryUtil(sql, null);	
		while(rs.next()) {
			int id = rs.getInt("id");
	    	String pname = rs.getString("pname");
	    	String pimg = rs.getString("pimg");
	    	double price = rs.getDouble("price");
	    	String ptitle = rs.getString("ptitle");
	    	int pv = rs.getInt("pv");
	    	String typename = rs.getString("typeid");
	    	Product product = new Product(id,pname,pimg,price,ptitle,pv,typename);
	    	prolist.add(product);
		}
		return prolist;
	}

	@Override
	public List<Product> getByName(String pname) throws Exception {
		List<Product> prolist = new ArrayList<>();
		String sql = "select * from t_product where pname='"+pname+"' ";
		ResultSet rs = JDBCUtil.queryUtil(sql, null);
		while(rs.next()) {
			int id = rs.getInt("id");
			String pimg = rs.getString("pimg");
			Double price = rs.getDouble("price");
			String ptitle = rs.getString("ptitle");
			int pv = rs.getInt("pv");
			int typeid = rs.getInt("typeid");
			
			List<Type> a= itDao.getById(typeid);
			String typename1 = a.get(0).getTitle();
			
			
			List<Type> b= itDao.getById(a.get(0).getParentid());
			String typename2 =b.get(0).getTitle();
			
			List<Type> c= itDao.getById(b.get(0).getParentid());
			String typename3=c.get(0).getTitle();
			String typename = typename3+"--"+typename2+"--"+typename1;
			Product product = new Product(id,pname,pimg,price,ptitle,pv,typename);
			prolist.add(product);
		}
		return prolist;
	}
    
}
