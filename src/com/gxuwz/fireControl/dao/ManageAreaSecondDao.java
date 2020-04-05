package com.gxuwz.fireControl.dao;

import java.util.List;

import com.gxuwz.core.dao.BaseDao;
import com.gxuwz.core.pagination.Result;
import com.gxuwz.fireControl.model.entity.ManageAreaSecond;

/**
 * 
 * @Description TODO
 * @author 王海明
 * @version v1.0
 * @date 2020年2月5日
 */
public interface ManageAreaSecondDao extends BaseDao<ManageAreaSecond> {

	/**
	 * 根据对应的一级管辖域编号查询对应的二级管辖域信息
	 * 
	 * @Description TODO
	 * @author 王海明
	 * @date 2020年2月5日
	 * @param afId 一级管辖域编号
	 * @return
	 */
	List<ManageAreaSecond> getManageAreaSecondByAfId(String afId);

	/**
	 * 分页查询二级管辖域信息
	 * 
	 * @Description TODO
	 * @author 王海明
	 * @date 2020年2月11日
	 * @param manageAreaSecond 二级管辖域
	 * @param page 当前页
	 * @param row 每页记录数
	 * @return
	 */
	Result<ManageAreaSecond> findByPage(ManageAreaSecond manageAreaSecond, Integer page, Integer row);
	
	public List<ManageAreaSecond> findAll(String asId);

}
