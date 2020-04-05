package com.gxuwz.fireControl.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gxuwz.core.web.action.BaseAction;
import com.gxuwz.fireControl.model.entity.InstallPoint;
import com.gxuwz.fireControl.model.entity.ManageAreaSecond;
import com.gxuwz.fireControl.model.entity.ManageUserSecond;
import com.gxuwz.fireControl.service.InstallPointService;
import com.gxuwz.fireControl.service.ManageAreaSecondService;
import com.gxuwz.fireControl.service.ManageUserSecondService;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @Description TODO
 * @author 马金典
 * @version v1.0
 * @date 2020 年2月10日
 */
public class InstallPointAction extends BaseAction implements ModelDriven<InstallPoint>, Preparable {

	private static final long serialVersionUID = 1L;
	protected static final String LIST_JSP = "/pages/manageUserSecond/installPoint/installPoint_list.jsp";
	protected static final String SAVE_JSP = "/pages/manageUserSecond/installPoint/installPoint_save.jsp";
	protected static final String UPDATE_JSP = "/pages/manageUserSecond/installPoint/installPoint_update.jsp";
	protected static final String LIST2_JSP = "/pages/manageUserFirst/installPoint/installPoint_list.jsp";

	private InstallPoint installpoint;
	private String usId;
	// 二级管辖域编号
	private String asId;
	List<ManageAreaSecond> manageAreaSecond;
	private ManageUserSecond manageUserSecond;

	@Autowired
	private InstallPointService installpointservice;
	@Autowired
	private ManageAreaSecondService manageAreaSecondService;
	@Autowired
	private ManageUserSecondService manageUserSecondService;

	/**
	 * 打开内容添加页面 1根据二级管辖员编号查询出其负责的二级管辖域编号
	 * 
	 * @return
	 */
	public String openSave() {
		usId = (String) getRequest().getSession().getAttribute("userCode");
		manageUserSecond = manageUserSecondService.getOne(usId);
		String asId = manageUserSecond.getAs().getAsId();
		manageAreaSecond = manageAreaSecondService.findAll(asId);
		getRequest().setAttribute("manageAreaSecond", manageAreaSecond);
		forwardView = SAVE_JSP;
		return SUCCESS;
	}

	/**
	 * 添加
	 * 
	 * @return
	 */
	public String save() {
		installpoint.setInsCode(code.dateRandom());
		targ = installpointservice.save(installpoint);
		res.put("targ", targ);
		return SUCCESS;
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	public String delete() {
		Long insId = Long.parseLong(getRequest().getParameter("insId"));
		installpoint.setInsId(insId);
		targ = installpointservice.delete(installpoint);
		res.put("targ", targ);
		return SUCCESS;
	}

	/**
	 * 打开编辑页面
	 * 
	 * @return
	 */
	public String openUpdate() {
		Long insId = Long.parseLong(getRequest().getParameter("insId"));
		installpoint = installpointservice.getOne(insId);
		usId = (String) getRequest().getSession().getAttribute("userCode");
		manageUserSecond = manageUserSecondService.getOne(usId);
		String asId = manageUserSecond.getAs().getAsId();
		manageAreaSecond = manageAreaSecondService.findAll(asId);
		getRequest().setAttribute("manageAreaSecond", manageAreaSecond);
		getRequest().setAttribute("installpoint", installpoint);
		forwardView = UPDATE_JSP;
		return SUCCESS;
	}

	/**
	 * 修改
	 * 
	 * @return
	 */
	public String update() {
		targ = installpointservice.update(installpoint);
		res.put("targ", targ);
		return SUCCESS;
	}

	/**
	 * 获取详情
	 * 
	 * @return
	 */
	public String getOne() {
		return SUCCESS;
	}

	/**
	 * 分页查询
	 * 
	 * @return
	 */
	public String findByPage() {
		usId = (String) getRequest().getSession().getAttribute("userCode");
		ManageAreaSecond as = new ManageAreaSecond();
		installpoint.setAs(as);
		as.setUsId(usId);
		manageUserSecond = manageUserSecondService.getOne(usId);
		String asId = manageUserSecond.getAs().getAsId();
		manageAreaSecond = manageAreaSecondService.findAll(asId);
		pageResult = installpointservice.findByPage(installpoint, getPage(), getRow());
		forwardView = LIST_JSP;
		return SUCCESS;
	}

	/**
	 * 分页查询
	 * 
	 * @Description TODO
	 * @author 王海明
	 * @date 2020年2月18日
	 * @return
	 */
	public String findByPage2() {
		ManageAreaSecond as = new ManageAreaSecond();
		as.setAsId(asId);
		installpoint.setAs(as);
		pageResult = installpointservice.findByPage(installpoint, getPage(), getRow());
		forwardView = LIST2_JSP;
		return SUCCESS;
	}

	/**
	 * 查询全部
	 * 
	 * @return
	 */
	public String findAll() {
		return SUCCESS;
	}

	/**
	 * 主页跳转
	 * 
	 * @return
	 */
	public String openMain() {
		return SUCCESS;
	}

	/**
	 * 登录跳转
	 * 
	 * @return
	 */
	public String openLogin() {
		return SUCCESS;
	}

	/**
	 * 顶部跳转
	 * 
	 * @return
	 */
	public String openTop() {
		return SUCCESS;
	}

	/**
	 * 左部跳转
	 * 
	 * @return
	 */
	public String openLeft() {
		return SUCCESS;
	}

	/**
	 * 内容跳转
	 * 
	 * @return
	 */
	public String openIndex() {
		return SUCCESS;
	}

	public InstallPoint getModel() {
		return installpoint;
	}

	public void prepare() throws Exception {
		if (installpoint == null) {
			installpoint = new InstallPoint();
		}
	}

	public InstallPoint getInstallpoint() {
		return installpoint;
	}

	public void setInstallpoint(InstallPoint installpoint) {
		this.installpoint = installpoint;
	}

	public String getUsId() {
		return usId;
	}

	public void setUsId(String usId) {
		this.usId = usId;
	}

	public String getAsId() {
		return asId;
	}

	public void setAsId(String asId) {
		this.asId = asId;
	}

}
