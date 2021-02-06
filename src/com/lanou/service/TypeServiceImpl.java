package com.lanou.service;

import java.util.List;

import com.lanou.bean.Type;
import com.lanou.dao.ITypeDao;
import com.lanou.dao.TypeDaoImpl;

public class TypeServiceImpl implements ITypeService{
    private ITypeDao itDao = new TypeDaoImpl();

	@Override
	public List<Type> getAll() throws Exception {
		 List<Type> typelist1 =  itDao.getByParentId(0);  //写死得到  手机 电脑
		 for(Type c : typelist1) {
			 List<Type> typelist2 =  itDao.getByParentId(c.getId());
			 c.setChildren(typelist2);
			   for(Type s : typelist2) {
				   List<Type> typelist3 =  itDao.getByParentId(s.getId());
				   s.setChildren(typelist3);
			   }
		 }
		return typelist1;
	}

	@Override
	public void add(String title, int parentid) throws Exception {
		itDao.add(title, parentid);
	}

	@Override
	public void edit(String id, String title) throws Exception {
        itDao.edit(id, title);		
	}

	@Override
	public void delete(String id) throws Exception {
        itDao.delete(id);		
	}
}
