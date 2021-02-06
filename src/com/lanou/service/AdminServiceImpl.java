package com.lanou.service;

import java.util.List;

import com.lanou.bean.Admin;
import com.lanou.dao.AdminDaoImpl;
import com.lanou.dao.IAdminDao;
import com.lanou.util.SqlSessionUtil;

public class AdminServiceImpl implements IAdminService{
	
//    private IAdminDao idDao=new AdminDaoImpl();
    private IAdminDao idDao = (IAdminDao) SqlSessionUtil.sqlsession("com.lanou.dao.IAdminDao");
	@Override
	public Admin getByNameAndPwd(String adminname, String adminpwd) throws Exception {
		 Admin admin =idDao.getByNameAndPwd(adminname, adminpwd);
		 return admin;
	}
	
	@Override
	public List<Admin> getByPage(int pagenum, int pagecount) throws Exception {
		return idDao.getByPage(pagenum, pagecount);
	}
	@Override
	public int getCount() throws Exception {
		return idDao.getCount();
	}
	@Override
	public void add(String adminname, String adminpwd) throws Exception {
		idDao.add(adminname, adminpwd);
	}
	@Override
	public void exit(String id, String adminname, String adminpwd) throws Exception {
		idDao.exit(id, adminname, adminpwd);
	}
	@Override
	public void delete(String id) throws Exception {
		idDao.delete(id);
	}

}
