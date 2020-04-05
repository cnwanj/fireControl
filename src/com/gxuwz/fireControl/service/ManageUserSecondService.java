package com.gxuwz.fireControl.service;

import java.util.List;

import com.gxuwz.core.pagination.Result;
import com.gxuwz.fireControl.model.entity.ManageUserSecond;

/**
 * 
 * @Description TODO
 * @author 王海明
 * @version v1.0
 * @date 2020年2月4日
 */
public interface ManageUserSecondService {

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
	 * 保存二级管辖员信息
	 * 
	 * @Description TODO
	 * @author 王海明
	 * @date 2020年2月4日
	 * @param userSecond
	 * @return
	 */
	boolean save(ManageUserSecond userSecond);

	/**
	 * 删除二级管辖员信息
	 * 
	 * @Description TODO
	 * @author 王海明
	 * @date 2020年2月4日
	 * @param userSecond
	 * @return
	 */
	boolean delete(ManageUserSecond userSecond);

	/**
	 * 更新二级管辖员信息
	 * 
	 * @Description TODO
	 * @author 王海明
	 * @date 2020年2月4日
	 * @param userSecond
	 * @return
	 */
	boolean update(ManageUserSecond userSecond);

	/**
	 * 根据二级管辖员工号获取对应的二级管辖员信息。
	 * 
	 * @Description TODO
	 * @author 王海明
	 * @date 2020年2月5日
	 * @param usId 二级管辖员工号
	 * @return
	 */
	ManageUserSecond getOne(String usId);

	/**
	 * 根据一级管辖员工号获取相应的二级管辖员信息
	 * 
	 * @Description TODO
	 * @author 王海明
	 * @date 2020年2月11日
	 * @param ufId 一级管辖员工号
	 * @return
	 */
	List<ManageUserSecond> getManageUserSecondByUfId(String ufId);

}
