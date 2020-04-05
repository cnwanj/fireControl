package com.gxuwz.fireControl.service;

import com.gxuwz.core.pagination.Result;
import com.gxuwz.fireControl.model.entity.DeptUser;

public interface DeptUserService {

	Result<DeptUser> findByPage(DeptUser deptUser, int page, int row);

	boolean save(DeptUser deptUser);

	boolean delete(DeptUser deptUser);

	DeptUser getOne(String userId);

	boolean update(DeptUser deptUser);

	DeptUser login(String loginName, String loginPwd);

}
