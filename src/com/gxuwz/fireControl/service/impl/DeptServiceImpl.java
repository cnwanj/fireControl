package com.gxuwz.fireControl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gxuwz.core.pagination.Result;
import com.gxuwz.fireControl.dao.DeptDao;
import com.gxuwz.fireControl.model.entity.Dept;
import com.gxuwz.fireControl.service.DeptService;

/**
 * 单位信息
 * @author 韦永恒
 * @date 2020.02.09
 */

@Transactional
@Service("deptService")
public class DeptServiceImpl implements DeptService {
	@Autowired
	private DeptDao deptDao;
	private Class<Dept> d = Dept.class;
	
	// 分页查询
	public Result<Dept> findByPage(Dept dept, Integer page, Integer limit) {
		return deptDao.findByPage(dept, page, limit);
	}

	// 添加
	public boolean save(Dept dept) {
		if (deptDao.get(d, dept.getDeptId()) == null) {
			deptDao.save(dept);
			return true;
		} else {
			return false;
		}
	}

	// 删除
	public boolean delete(Dept dept) {
		if (deptDao.get(d, dept.getDeptId()) != null) {
			deptDao.remove(d, dept.getDeptId());
			return true;
		} else {
			return false;
		}
	}

	// 修改
	public boolean update(Dept dept) {
		deptDao.update(dept);
		return true;
	}

	// 获取详情信息
	public Dept getOne(String deptId) {
		return deptDao.get(d, deptId);
	}

	// 根据单位管理员编号查询单位信息
	public List<Dept> findByUserId(String userId) {
		return deptDao.findByUserId(userId);
	}

	// 根据编号修改单位管理员编号
	public boolean updateByUserId(String userId, String deptId) {
		deptDao.updateByUserId(userId, deptId);
		return true;
	}
}
