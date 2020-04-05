package com.gxuwz.fireControl.service;

import java.util.List;

import com.gxuwz.core.pagination.Result;
import com.gxuwz.fireControl.model.entity.Dept;

public interface DeptService {

	Result<Dept> findByPage(Dept dept, Integer page, Integer limit);

	boolean save(Dept dept);

	boolean delete(Dept dept);

	boolean update(Dept dept);

	Dept getOne(String deptId);
	
	List<Dept> findByUserId(String userId);

	boolean updateByUserId(String string, String deptId);
	

}
