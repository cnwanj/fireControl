package com.gxuwz.fireControl.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gxuwz.core.web.action.BaseAction;
import com.gxuwz.fireControl.model.entity.ManageAreaSecond;
import com.gxuwz.fireControl.model.entity.ManageUserSecond;
import com.gxuwz.fireControl.service.ManageAreaSecondService;
import com.gxuwz.fireControl.service.ManageUserFirstService;
import com.gxuwz.fireControl.service.ManageUserSecondService;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * 
 * @Description TODO
 * @author 王海明
 * @version v1.0
 * @date 2020年2月5日
 */
public class ManageAreaSecondAction extends BaseAction implements ModelDriven<ManageAreaSecond>, Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ManageAreaSecond manageAreaSecond;
	// 一级管辖员工号
	private String ufId;
	// 一级管辖域编号
	private String afId;
	// 二级管辖员信息集合
	List<ManageUserSecond> list;

	@Autowired
	private ManageAreaSecondService manageAreaSecondService;
	@Autowired
	private ManageUserFirstService manageUserFirstService;
	@Autowired
	private ManageUserSecondService manageUserSecondService;

	/**
	 * 分页显示二级管辖域信息
	 * 
	 * @Description TODO
	 * @author 王海明
	 * @date 2020年2月11日
	 * @return
	 */
	public String findByPage() {
		pageResult = manageAreaSecondService.findByPage(ufId, manageAreaSecond, getPage(), getRow());
		forwardView = "/pages/manageUserFirst/manageAreaSecond/manageAreaSecond_list.jsp";
		return SUCCESS;
	}

	/**
	 * 显示二级管辖域信息添加页面
	 * 
	 * @Description TODO
	 * @author 王海明
	 * @date 2020年2月11日
	 * @return
	 */
	public String toManageAreaSecondAddPage() {
		afId = manageUserFirstService.getOne(ufId).getAf().getAfId();
		list = manageUserSecondService.getManageUserSecondByUfId(ufId);
		forwardView = "/pages/manageUserFirst/manageAreaSecond/manageAreaSecond_save.jsp";
		return SUCCESS;
	}

	/**
	 * 显示二级管辖域信息修改页面
	 * 
	 * @Description TODO
	 * @author 王海明
	 * @date 2020年2月11日
	 * @return
	 */
	public String toManageAreaSecondUpdatePage() {
		manageAreaSecond = manageAreaSecondService.getOne(manageAreaSecond.getAsId());
		list = manageUserSecondService.getManageUserSecondByUfId(ufId);
		forwardView = "/pages/manageUserFirst/manageAreaSecond/manageAreaSecond_update.jsp";
		return SUCCESS;
	}

	/**
	 * 保存二级管辖域信息
	 * 
	 * @Description TODO
	 * @author 王海明
	 * @date 2020年2月11日
	 * @return
	 */
	public String save() {
		manageAreaSecond.setAsId(code.dateRandom());
		if ("".equals(manageAreaSecond.getUsId())) {
			manageAreaSecond.setUsId(null);
		}
		// 保存二级管辖域信息
		targ = manageAreaSecondService.save(manageAreaSecond);
		res.put("targ", targ);
		return SUCCESS;
	}

	/**
	 * 修改二级管辖域信息
	 * 
	 * @Description TODO
	 * @author 王海明
	 * @date 2020年2月11日
	 * @return
	 */
	public String update() {
		if ("".equals(manageAreaSecond.getUsId())) {
			manageAreaSecond.setUsId(null);
		}
		// 修改二级管辖域信息
		targ = manageAreaSecondService.update(manageAreaSecond);
		res.put("targ", targ);
		return SUCCESS;
	}

	/**
	 * 删除二级管辖域信息
	 * 
	 * @Description TODO
	 * @author 王海明
	 * @date 2020年2月11日
	 * @return
	 */
	public String delete() {
		System.out.println();
		targ = manageAreaSecondService.delete(manageAreaSecond);
		res.put("targ", targ);
		return SUCCESS;
	}

	public void prepare() throws Exception {
		if (manageAreaSecond == null) {
			manageAreaSecond = new ManageAreaSecond();
		}

	}

	public ManageAreaSecond getModel() {
		return manageAreaSecond == null ? new ManageAreaSecond() : manageAreaSecond;
	}

	public String getUfId() {
		return ufId;
	}

	public void setUfId(String ufId) {
		this.ufId = ufId;
	}

	public String getAfId() {
		return afId;
	}

	public void setAfId(String afId) {
		this.afId = afId;
	}

	public List<ManageUserSecond> getList() {
		return list;
	}

	public void setList(List<ManageUserSecond> list) {
		this.list = list;
	}

	public ManageAreaSecond getManageAreaSecond() {
		return manageAreaSecond;
	}

	public void setManageAreaSecond(ManageAreaSecond manageAreaSecond) {
		this.manageAreaSecond = manageAreaSecond;
	}

}
