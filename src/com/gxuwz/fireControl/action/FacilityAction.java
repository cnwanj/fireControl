package com.gxuwz.fireControl.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.gxuwz.core.web.action.BaseAction;
import com.gxuwz.fireControl.model.entity.Facility;
import com.gxuwz.fireControl.model.entity.InstallPoint;
import com.gxuwz.fireControl.model.entity.ManageAreaSecond;
import com.gxuwz.fireControl.model.entity.ManageUserSecond;
import com.gxuwz.fireControl.model.entity.SysUsers;
import com.gxuwz.fireControl.model.entity.WarnUser;
import com.gxuwz.fireControl.service.FacilityService;
import com.gxuwz.fireControl.service.InstallPointService;
import com.gxuwz.fireControl.service.ManageAreaSecondService;
import com.gxuwz.fireControl.service.ManageUserSecondService;
import com.gxuwz.fireControl.service.SysUserService;
import com.gxuwz.fireControl.service.WarnUserService;
import com.gxuwz.fireControl.util.DateAndRandom;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @Description TODO
 * @author 马金典
 * @version v1.0
 * @date 2020 年2月10日
 */
public class FacilityAction extends BaseAction implements ModelDriven<Facility>, Preparable {

	private static final long serialVersionUID = 1L;
	protected static final String LIST_JSP = "/pages/manageUserSecond/facility/facility_list.jsp";
	protected static final String SAVE_JSP = "/pages/manageUserSecond/facility/facility_save.jsp";
	protected static final String UPDATE_JSP = "/pages/manageUserSecond/facility/facility_update.jsp";
	protected static final String LIST2_JSP = "/pages/manageUserFirst/facility/facility_list.jsp";
	protected static final String ALLLIST_JSP = "/pages/manageUserFirst/facility/facility_allList.jsp";

	private Facility facility;
	private String userType = "2";
	List<SysUsers> sysUsers;
	List<WarnUser> warnUser;
	List<InstallPoint> installPoint;
	private String usId;// 二级管辖员工号
	// 安装点编号
	private String insId;
	// 一级管辖员工号
	private String ufId;
	protected DateAndRandom time = new DateAndRandom();// 生成安装时间
	private ManageUserSecond manageUserSecond;

	@Autowired
	private FacilityService facilityservice;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private WarnUserService warnUserService;
	@Autowired
	private InstallPointService installpointservice;
	@Autowired
	private ManageUserSecondService manageUserSecondService;
	@Autowired
	private ManageAreaSecondService manageAreaSecondService;

	/**
	 * 打开内容添加页面
	 * 
	 * @return
	 */
	public String openSave() {
		usId = (String) getRequest().getSession().getAttribute("userCode");
		manageUserSecond = manageUserSecondService.getOne(usId);
		String asId = manageUserSecond.getAs().getAsId();// 二级管辖域编号
		sysUsers = sysUserService.findOneUserType(userType);
		installPoint = installpointservice.findAll(asId);
		warnUser = warnUserService.findAll(asId);
		getRequest().setAttribute("sysUsers", sysUsers);
		getRequest().setAttribute("installPoint", installPoint);
		getRequest().setAttribute("warnUser", warnUser);
		forwardView = SAVE_JSP;
		return SUCCESS;
	}

	/**
	 * 添加
	 * 
	 * @return
	 */
	public String save() {
		facility.setFacId(code.dateRandom());
		facility.setFacTime(time.Time());
		targ = facilityservice.save(facility);
		res.put("targ", targ);
		facility.setFacName(null);
		return SUCCESS;
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	public String delete() {
		String facId = getRequest().getParameter("facId");
		facility.setFacId(facId);
		targ = facilityservice.delete(facility);
		res.put("targ", targ);
		return SUCCESS;
	}

	/**
	 * 打开编辑页面
	 * 
	 * @return
	 */
	public String openUpdate() {
		usId = (String) getRequest().getSession().getAttribute("userCode");
		manageUserSecond = manageUserSecondService.getOne(usId);
		String asId = manageUserSecond.getAs().getAsId();
		String facId = getRequest().getParameter("facId");
		facility = facilityservice.getOne(facId);
		sysUsers = sysUserService.findOneUserType(userType);
		installPoint = installpointservice.findAll(asId);
		warnUser = warnUserService.findAll(asId);
		getRequest().setAttribute("sysUsers", sysUsers);
		getRequest().setAttribute("installPoint", installPoint);
		getRequest().setAttribute("warnUser", warnUser);
		getRequest().setAttribute("facility", facility);
		forwardView = UPDATE_JSP;
		return SUCCESS;
	}

	/**
	 * 修改
	 * 
	 * @return
	 */
	public String update() {
		facility.setFacTime(time.Time());
		targ = facilityservice.update(facility);
		res.put("targ", targ);
		facility.setFacName(null);
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
		manageUserSecond = manageUserSecondService.getOne(usId);
		String asId = manageUserSecond.getAs().getAsId();

		sysUsers = sysUserService.findOneUserType(userType);
		getRequest().setAttribute("sysUsers", sysUsers);

		installPoint = installpointservice.findAll(asId);
		getRequest().setAttribute("installPoint", installPoint);

		warnUser = warnUserService.findAll(asId);
		getRequest().setAttribute("warnUser", warnUser);

		InstallPoint point = new InstallPoint();
		ManageAreaSecond as = new ManageAreaSecond();
		facility.setPoint(point);
		point.setAs(as);
		as.setAsId(asId);

		pageResult = facilityservice.findByPage(facility, getPage(), getRow());
		facility.setFacName(null);
		forwardView = LIST_JSP;
		return SUCCESS;
	}

	/**
	 * 
	 * @Description TODO
	 * @author 王海明
	 * @date 2020年2月18日
	 * @return
	 */
	public String findByPage2() {
		List<InstallPoint> installPointList = new ArrayList<InstallPoint>();
		if (ufId != null && insId == null) {
			// 通过一级管辖员工号查询一些二级管辖域
			List<ManageAreaSecond> asList = manageAreaSecondService.getManageAreaSecondByUfId(ufId);
			if (asList != null && asList.size() > 0) {
				for (ManageAreaSecond areaSecond : asList) {
					// 通过二级管辖域编号查询一些安装点
					installPointList.addAll(installpointservice.findAll(areaSecond.getAsId()));
				}
			}
			pageResult = facilityservice.findByInsIdByPage(installPointList, facility, getPage(), getRow());
			// 设置为空
			ufId = null;
			forwardView = ALLLIST_JSP;
		} else if (ufId == null && StringUtils.isNotBlank(insId)) {
			InstallPoint point = new InstallPoint();
			// 把String类型转为long型
			long parseLong = Long.parseLong(insId);
			point.setInsId(parseLong);
			facility.setPoint(point);
			pageResult = facilityservice.findByInsIdByPage(installPointList, facility, getPage(), getRow());
			// 设置为空
			insId = null;
			forwardView = LIST2_JSP;
		}
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

	public void prepare() throws Exception {
		if (facility == null) {
			facility = new Facility();
		}

	}

	public Facility getModel() {
		return facility;
	}

	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

	public String getUsId() {
		return usId;
	}

	public void setUsId(String usId) {
		this.usId = usId;
	}

	public String getInsId() {
		return insId;
	}

	public void setInsId(String insId) {
		this.insId = insId;
	}

	public String getUfId() {
		return ufId;
	}

	public void setUfId(String ufId) {
		this.ufId = ufId;
	}

}
