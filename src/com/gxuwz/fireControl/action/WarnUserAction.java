package com.gxuwz.fireControl.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gxuwz.core.web.action.BaseAction;
import com.gxuwz.fireControl.model.entity.InstallPoint;
import com.gxuwz.fireControl.model.entity.ManageAreaSecond;
import com.gxuwz.fireControl.model.entity.ManageUserSecond;
import com.gxuwz.fireControl.model.entity.WarnUser;
import com.gxuwz.fireControl.service.InstallPointService;
import com.gxuwz.fireControl.service.ManageUserSecondService;
import com.gxuwz.fireControl.service.WarnUserService;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
/**
 * @Description TODO
 * @author 马金典
 * @version v1.0
 * @date 2020 年2月10日
 */
public class WarnUserAction extends BaseAction implements ModelDriven<WarnUser>, Preparable{
	
	private static final long serialVersionUID = 1L;
	protected static final String LIST_JSP = "/pages/manageUserSecond/warnUser/warnUser_list.jsp";
	protected static final String SAVE_JSP = "/pages/manageUserSecond/warnUser/warnUser_save.jsp";
	protected static final String UPDATE_JSP = "/pages/manageUserSecond/warnUser/warnUser_update.jsp";
	
	private WarnUser warnUser;
	List<InstallPoint> installPoint;
	private String usId;//二级管辖员工号
	private ManageUserSecond manageUserSecond;

	@Autowired
	private WarnUserService warnUserService;
	@Autowired
	private InstallPointService installpointservice;
	@Autowired
	private ManageUserSecondService manageUserSecondService;

	/**
	 * 打开内容添加页面
	 * 
	 * @return
	 */
	public String openSave(){
		usId = (String)getRequest().getSession().getAttribute("userCode");
		manageUserSecond = manageUserSecondService.getOne(usId);
		String asId=manageUserSecond.getAs().getAsId();
		installPoint=installpointservice.findAll(asId);
		getRequest().setAttribute("installPoint", installPoint);
		forwardView=SAVE_JSP;
		return SUCCESS;
	}
	
	/**
	 * 添加
	 * 
	 * @return
	 */
	public String save() {
		warnUser.setUserId(code.dateRandom());
		targ=warnUserService.save(warnUser);
		res.put("targ", targ);
		warnUser.setUserName(null);
		return SUCCESS;
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	public String delete() {
		String userId=getRequest().getParameter("userId");
		warnUser.setUserId(userId);
		targ = warnUserService.delete(warnUser);
		res.put("targ", targ);
		return SUCCESS;
	}
	
	/**
	 * 打开编辑页面
	 * 
	 * @return
	 */
	public String openUpdate(){
		String userId=getRequest().getParameter("userId");
		warnUser = warnUserService.getOne(userId);
		usId = (String)getRequest().getSession().getAttribute("userCode");
		manageUserSecond = manageUserSecondService.getOne(usId);
		String asId=manageUserSecond.getAs().getAsId();
		installPoint=installpointservice.findAll(asId);
		getRequest().setAttribute("installPoint", installPoint);
		getRequest().setAttribute("warnUser", warnUser);
		forwardView = UPDATE_JSP;
		return SUCCESS;
	}
	
	/**
	 * 修改
	 * 
	 * @return
	 */
	public String update() {
		targ = warnUserService.update(warnUser);
		res.put("targ", targ);
		warnUser.setUserName(null);
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
		usId = (String)getRequest().getSession().getAttribute("userCode");
		manageUserSecond = manageUserSecondService.getOne(usId);
		String asId=manageUserSecond.getAs().getAsId();
		installPoint=installpointservice.findAll(asId);
		getRequest().setAttribute("installPoint", installPoint);
		InstallPoint point = new InstallPoint();
		ManageAreaSecond as=new ManageAreaSecond();
		warnUser.setPoint(point);
		point.setAs(as);
		as.setAsId(asId);
		pageResult = warnUserService.findByPage(warnUser,getPage(), getRow());
		warnUser.setUserName(null);
		forwardView = LIST_JSP;
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
	
	@Override
	public WarnUser getModel() {
		return warnUser;
	}
	
	public void prepare() throws Exception {
		if (warnUser == null) {
			warnUser = new WarnUser();
		}
	}

	public WarnUser getWarnUser() {
		return warnUser;
	}

	public void setWarnUser(WarnUser warnUser) {
		this.warnUser = warnUser;
	}

	public String getUsId() {
		return usId;
	}

	public void setUsId(String usId) {
		this.usId = usId;
	}

	public ManageUserSecond getManageUserSecond() {
		return manageUserSecond;
	}

	public void setManageUserSecond(ManageUserSecond manageUserSecond) {
		this.manageUserSecond = manageUserSecond;
	}

	

}
