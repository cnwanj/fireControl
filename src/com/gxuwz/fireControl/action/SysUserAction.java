package com.gxuwz.fireControl.action;

import com.gxuwz.core.web.action.BaseAction;
import com.gxuwz.fireControl.model.entity.SysUsers;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @Description 用户登录信息
 * @author 韦永恒
 * @date 2020年01月24日
 */
public class SysUserAction extends BaseAction implements ModelDriven<SysUsers>, Preparable {

	private static final long serialVersionUID = 1L;
	protected static final String LOGIN_JSP = "/login.jsp";
	protected static final String MAIN_JSP = "/pages/other/main.jsp";
	protected static final String TOP_JSP = "/pages/other/top.jsp";
	protected static final String LEFT_JSP = "/pages/other/left.jsp";
	protected static final String INDEX_JSP = "/pages/other/index.jsp";

	private SysUsers user;

//	@Autowired
//	private SysUserService sysUserService;

	/**
	 * 登录
	 * @return
	 */
	public String login() {

		SysUsers login = userService.login(user.getLoginName(), user.getLoginPwd());

		if (null != login) { // 登录成功
			// 获取用户名和工号，并把用户名存入session
			String userCode = login.getUserCode();
			String userName = login.getUserName();
			getRequest().getSession().setAttribute("userCode", userCode);
			getRequest().getSession().setAttribute("userName", userName);
			forwardView = MAIN_JSP;
		} else { // 登录失败
			forwardView = LOGIN_JSP;
		}
		return SUCCESS;
	}

	/**
	 * 退出
	 * @return
	 */
	public String out() {
		getRequest().getSession().invalidate();
		return SUCCESS;
	}

	/**
	 * 添加
	 * @return
	 */
	public String save() {
		user.setUserCode(code.dateRandom());
		targ = userService.save(user);
		res.put("targ", targ);
		user.setUserName(null);
		return SUCCESS;
	}

	/**
	 * 删除
	 * @return
	 */
	public String delete() {
		targ = userService.delete(user);
		res.put("targ", targ);
		return SUCCESS;
	}

	/**
	 * 修改
	 * @return
	 */
	public String update() {
		targ = userService.update(user);
		res.put("targ", targ);
		return SUCCESS;
	}

	/**
	 * 获取详情
	 * @return
	 */
	public String getOne() {
		user = userService.getOne(user.getUserId());
		forwardView = "/pages/sysUser/sysUser/sysUser_update.jsp";
		return SUCCESS;
	}

	/**
	 * 分页查询
	 * @return
	 */
	public String findByPage() {
		pageResult = userService.findByPage(user, getPage(), getRow());
		forwardView = "/pages/sysUser/sysUser/sysUser_list.jsp";
		return SUCCESS;
	}

	/**
	 * 查询全部
	 * @return
	 */
	public String findAll() {
		return SUCCESS;
	}

	/**
	 * 主页跳转
	 * @return
	 */
	public String openMain() {
		forwardView = MAIN_JSP;
		return SUCCESS;
	}

	/**
	 * 登录跳转
	 * @return
	 */
	public String openLogin() {
		forwardView = LOGIN_JSP;
		return SUCCESS;
	}

	/**
	 * 顶部跳转
	 * 
	 * @return
	 */
	public String openTop() {
		forwardView = TOP_JSP;
		return SUCCESS;
	}

	/**
	 * 左部跳转
	 * 
	 * @return
	 */
	public String openLeft() {
		forwardView = LEFT_JSP;
		return SUCCESS;
	}

	/**
	 * 内容跳转
	 * 
	 * @return
	 */
	public String openIndex() {
		forwardView = INDEX_JSP;
		return SUCCESS;
	}

	public SysUsers getModel() {
		return user;
	}

	public void prepare() throws Exception {
		if (user == null) {
			user = new SysUsers();
		}
	}

	public SysUsers getUser() {
		return user;
	}

	public void setUser(SysUsers user) {
		this.user = user;
	}

}
