package com.gxuwz.fireControl.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gxuwz.core.web.action.BaseAction;
import com.gxuwz.fireControl.model.entity.ManageAreaSecond;
import com.gxuwz.fireControl.model.entity.ManageUserSecond;
import com.gxuwz.fireControl.model.entity.SysUsers;
import com.gxuwz.fireControl.service.ManageAreaSecondService;
import com.gxuwz.fireControl.service.ManageUserSecondService;
import com.gxuwz.fireControl.service.SysUserService;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * 
 * @Description TODO
 * @author 王海明
 * @version v1.0
 * @date 2020年2月4日
 */
public class ManageUserSecondAction extends BaseAction implements ModelDriven<ManageUserSecond>, Preparable {

	private static final long serialVersionUID = 1L;

	private ManageUserSecond userSecond;
	// 一级管辖员编号
	private String ufId;
	// 二级管辖域集合
	private List<ManageAreaSecond> list;

	@Autowired
	private ManageUserSecondService manageUserSecondService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private ManageAreaSecondService manageAreaSecondService;

	/**
	 * 分页显示二级管辖员信息
	 * 
	 * @Description TODO
	 * @author 王海明
	 * @date 2020年2月4日
	 * @return
	 */
	public String findByPage() {
		pageResult = manageUserSecondService.findByPage(userSecond, getPage(), getRow());
		forwardView = "/pages/manageUserFirst/manageUserSecond/manageUserSecond_list.jsp";
		return SUCCESS;

	}

	/**
	 * 1、显示二级管辖员信息添加页面。 2、根据一级管辖员工号获取二级管辖域。
	 * 
	 * @Description TODO
	 * @author 王海明
	 * @date 2020年2月4日
	 * @return
	 */
	public String toManageUserSecondAddPage() {
		forwardView = "/pages/manageUserFirst/manageUserSecond/manageUserSecond_save.jsp";
		list = manageAreaSecondService.getManageAreaSecondByUfId(ufId);
		return SUCCESS;
	}

	/**
	 * 1、显示二级管辖员信息修改页面。 2、根据一级管辖员工号获取二级管辖域。3、根据二级管辖员工号获取对应的二级管辖员信息。
	 * 
	 * @Description TODO
	 * @author 王海明
	 * @date 2020年2月4日
	 * @return
	 */
	public String toManageUserSecondUpdatePage() {
		forwardView = "/pages/manageUserFirst/manageUserSecond/manageUserSecond_update.jsp";
		list = manageAreaSecondService.getManageAreaSecondByUfId(ufId);
		userSecond = manageUserSecondService.getOne(userSecond.getUsId());
		return SUCCESS;

	}

	/**
	 * 保存二级管辖员信息和用户登录信息
	 * 
	 * @Description TODO
	 * @author 王海明
	 * @date 2020年2月4日
	 * @return
	 */
	public String save() {
		userSecond.setUsId(code.dateRandom());
		if ("".equals(userSecond.getAs().getAsId())) {
			userSecond.getAs().setAsId(null);
		}
		// 保存二级管辖员信息
		targ = manageUserSecondService.save(userSecond);
		// 如果二级管辖员信息保存成功
		if (targ == true) {
			// 1、实例化SysUsers对象
			SysUsers users = new SysUsers();
			// 2、设置各个属性
			users.setLoginName(userSecond.getLoginName());
			users.setLoginPwd(userSecond.getLoginPwd());
			users.setUserCode(userSecond.getUsId());
			users.setUserName(userSecond.getUsName());
			users.setUserPhone(userSecond.getUsPhone());
			// 3、用户类型设置为5: 二级管辖员
			users.setUserType(5);
			// 4、保存用户登录信息到sys_users表
			sysUserService.save(users);
		}
		res.put("targ", targ);
		return SUCCESS;

	}

	/**
	 * 更新二级管辖员信息（注：同时也要更新用户登录信息）
	 * 
	 * @Description TODO
	 * @author 王海明
	 * @date 2020年2月5日
	 * @return
	 */
	public String update() {
		if ("".equals(userSecond.getAs().getAsId())) {
			userSecond.getAs().setAsId(null);
		}
		targ = manageUserSecondService.update(userSecond);
		res.put("targ", targ);
		return SUCCESS;
	}

	/**
	 * 删除二级管辖员信息（注：同时也要删除用户登录信息）
	 * 
	 * @Description TODO
	 * @author 王海明
	 * @date 2020年2月5日
	 * @return
	 */
	public String delete() {
		targ = manageUserSecondService.delete(userSecond);
		res.put("targ", targ);
		return SUCCESS;
	}

	public void prepare() throws Exception {
		if (userSecond == null) {
			userSecond = new ManageUserSecond();
		}

	}

	public ManageUserSecond getModel() {

		return userSecond == null ? new ManageUserSecond() : userSecond;
	}

	public ManageUserSecond getUserSecond() {
		return userSecond;
	}

	public void setUserSecond(ManageUserSecond userSecond) {
		this.userSecond = userSecond;
	}

	public String getUfId() {
		return ufId;
	}

	public void setUfId(String ufId) {
		this.ufId = ufId;
	}

	public List<ManageAreaSecond> getList() {
		return list;
	}

	public void setList(List<ManageAreaSecond> list) {
		this.list = list;
	}

}
