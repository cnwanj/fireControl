package com.gxuwz.fireControl.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.gxuwz.core.dao.impl.BaseDaoImpl;
import com.gxuwz.core.pagination.Result;
import com.gxuwz.fireControl.dao.DeptDao;
import com.gxuwz.fireControl.model.entity.Dept;
import com.gxuwz.fireControl.dao.ManageAreaFirstDao;
import com.gxuwz.fireControl.model.entity.ManageAreaFirst;
import com.gxuwz.fireControl.model.entity.ManageAreaSecond;
/**
 * 单位信息
 * @author 韦永恒  刘家乐
 * @date 2020.02.06
 */
@SuppressWarnings("unchecked")
@Repository("deptDao")
public class DeptDaoImpl extends BaseDaoImpl<Dept> implements DeptDao {

	// 分页查询
	public Result<Dept> findByPage(Dept dept, Integer page, Integer row) {
		String queryString = "from Dept where 1=1";
		if (null != dept.getDeptName()) {
			queryString = queryString + " and deptName like '%"
					+ dept.getDeptName() + "%'";
		}
		int start = (page - 1) * row;
		int limit = row;
		return (Result<Dept>) super.find(queryString, null, null, start, limit);
	}
	
	// 根据单位管理员编号查询单位信息
	public List<Dept> findByUserId(String userId){
		Criteria criteria = getSession().createCriteria(Dept.class);
		List<Dept> list = criteria.add(Restrictions.eq("userId", userId)).list();
		return list;
	}

	// 根据编号修改单位管理员编号
	public void updateByUserId(String userId, String deptId) {
		Query query = getSession().createQuery("update Dept set userId="+userId+" where deptId="+deptId);
		query.executeUpdate();
	}
	
	public Dept getDeptByDeptUserId(String deptId) {
		// 创建离线对象
		Criteria criteria = getSession().createCriteria(Dept.class);
		// 添加查询条件:单位管理员工号
		criteria.add(Restrictions.eq("deptId", deptId));
		// 查询结果
		List<Dept> list = criteria.list();
		return list.size() == 0 ? null : list.get(0);
	}
	
}
