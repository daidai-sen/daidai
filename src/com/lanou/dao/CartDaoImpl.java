package com.lanou.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;

import com.lanou.bean.Cart;
import com.lanou.bean.Product;
import com.lanou.bean.User;
import com.lanou.util.JDBCUtil;

public class CartDaoImpl implements ICartDao{
	private IProductDao ipDao = new ProductDaoImpl();
	private IUserDao iuDao = new UserDaoImpl();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<Cart> getByPage(int pagenum, int pagecount) throws Exception {
		List<Cart> cartlist = new ArrayList<Cart>();
		String sql =" select "+ "t_cart.id id,t_user.id userid,t_product.id productid, t_user.name name,t_product.pname pname,t_product.price price from t_cart left join t_user on t_cart.userid = t_user.id "
				      +" left join t_product on t_cart.productid = t_product.id limit "+(pagenum-1)*pagecount+","+pagecount+" ";
	    ResultSet rs = JDBCUtil.queryUtil(sql, null);
	    while(rs.next()) {
	    	int id = rs.getInt("id");
	    	int userid = rs.getInt("userid");
	    	int productid = rs.getInt("productid");
	    	String username = rs.getString("name");
	    	String productname = rs.getString("pname");
	    	double price = rs.getDouble("price");
	    	Cart cart = new Cart(id,userid,productid,username,productname,price);
	    	cartlist.add(cart);
	    	}
		return cartlist;
    }

	@Override
	public int getCount() throws Exception {
		String sql = "select count(*) count from t_cart";
		ResultSet rs = JDBCUtil.queryUtil(sql, null);
		while(rs.next()) {
			int count = rs.getInt("count");
			return count;
		}
		return 0;
	}

	@Override
	public void add(String username, String productname) throws Exception {
		String sql = "insert into t_cart(userid,productid) values('"+username+"','"+productname+"')";
	    JDBCUtil.ZengShanGai(sql);
	}

	@Override
	public void update(String id, String username, String productname) throws Exception {
		String sql = "update t_cart set userid='"+username+"',productid='"+productname+"' where id="+id;
		JDBCUtil.ZengShanGai(sql);
	}

	@Override
	public void delete(String id) throws Exception {
		String sql = "delete from t_cart where id="+id;
		JDBCUtil.ZengShanGai(sql);
	}

	@Override
	public List<Cart> getById(int userid) throws Exception {
		List<Cart> cartlist = new ArrayList<Cart>();
		String sql = " select "+ "t_cart.id id,t_user.id userid,t_product.id productid, t_user.name name,t_product.pname pname,t_product.price price from t_cart left join t_user on t_cart.userid = t_user.id "
			      +" left join t_product on t_cart.productid = t_product.id where t_user.id= "+userid;
		ResultSet rs = JDBCUtil.queryUtil(sql, null);
		while(rs.next()) {
	    	int id = rs.getInt("id");
	    	int productid = rs.getInt("productid");
	    	String username = rs.getString("name");
	    	String productname = rs.getString("pname");
	    	double price = rs.getDouble("price");
	    	Cart cart = new Cart(id,userid,productid,username,productname,price);
	    	cartlist.add(cart);
		}
		return cartlist;
	}

	@Override
	public List<Cart> getByDistinct(int userid, int pagenum, int pagecount) throws Exception {
		List<Cart> cartlist = new ArrayList<Cart>();
		String sql = "select  distinct productid from t_cart where userid = '"+userid+"' limit "+(pagenum-1)*pagecount+","+pagecount+" ";
	    int i = 0;
	    ResultSet rs = JDBCUtil.queryUtil(sql, null);
	    while(rs.next()) {
	    	 i +=1;
	    	 List<User> userlist = iuDao.getById(userid);
	    	 String username = userlist.get(0).getName();
	    	 int productid = rs.getInt("productid");
	    	 List<Product> prolist = ipDao.getById(productid);
	    	 String productname = prolist.get(0).getPname();
	    	 double price = prolist.get(0).getPrice();
	    	 int count =getByUserIdAndProductId(userid, productid);
	    	 Cart cart = new Cart(i,userid,productid,username,productname,price,count);
	    	 cartlist.add(cart);
	    }
		return cartlist;
	}

	@Override
	public int getByUserIdAndProductId(int userid,int productid) throws Exception {
		String sql = "select count(*) count from t_cart where userid='"+userid+"' and productid= "+productid;
		ResultSet rs = JDBCUtil.queryUtil(sql, null);
		while(rs.next()) {
			int count = rs.getInt("count");
			return count;
		}
		return 0;
	}

	

}
