package com.gxuwz.fireControl.dao;

import java.util.List;

import com.gxuwz.core.dao.BaseDao;
import com.gxuwz.core.pagination.Result;
import com.gxuwz.fireControl.model.entity.ManageUserSecond;

/**
 * 
 * @Description 二级管辖员dao层
 * @author 王海明
 * @version v1.0
 * @date 2020年2月4日
 */
public interface ManageUserSecondDao extends BaseDao<ManageUserSecond> {

	/**
	 * 分页查询二级管辖员
	 * 
	 * @Description 分页查询二级管辖员
	 * @author 王海明
	 * @date 2020年2月4日
	 * @param userSecond 二级管辖员
	 * @param page 当前页
	 * @param row 每页记录数
	 * @return
	 */
	Result<ManageUserSecond> findByPage(ManageUserSecond userSecond, Integer page, Integer row);

	/**
	 * 根据二级管辖员用户名查询二级管辖员信息
	 * 
	 * @Description TODO
	 * @author 王海明
	 * @date 2020年2月4日
	 * @param loginName 二级管辖员用户名
	 * @return
	 */
	ManageUserSecond getManageUserSecondByLoginName(String loginName);

	/**
	 * 根据一级管辖员工号查询二级管辖员信息
	 * 
	 * @Description TODO
	 * @author 王海明
	 * @date 2020年2月11日
	 * @param ufId 一级管辖员工号
	 * @return
	 */
	List<ManageUserSecond> getManageUserSecondByUfId(String ufId);
}
