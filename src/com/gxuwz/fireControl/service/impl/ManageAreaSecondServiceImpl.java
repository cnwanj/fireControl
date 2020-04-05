package com.gxuwz.fireControl.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gxuwz.core.pagination.Result;
import com.gxuwz.fireControl.dao.ManageAreaFirstDao;
import com.gxuwz.fireControl.dao.ManageAreaSecondDao;
import com.gxuwz.fireControl.dao.ManageUserSecondDao;
import com.gxuwz.fireControl.model.entity.ManageAreaFirst;
import com.gxuwz.fireControl.model.entity.ManageAreaSecond;
import com.gxuwz.fireControl.model.entity.ManageUserSecond;
import com.gxuwz.fireControl.service.ManageAreaSecondService;

/**
 * 
 * @Description TODO
 * @author 王海明
 * @version v1.0
 * @date 2020年2月5日
 */
@Transactional
@Service("manageAreaSecondService")
public class ManageAreaSecondServiceImpl implements ManageAreaSecondService {

	@Autowired
	private ManageAreaSecondDao manageAreaSecondDao;
	@Autowired
	private ManageAreaFirstDao manageAreaFirstDao;
	
	private Class<ManageAreaSecond> mas = ManageAreaSecond.class;
	@Autowired
	private ManageUserSecondDao manageUserSecondDao;

	@Override
	public List<ManageAreaSecond> getManageAreaSecondByUfId(String ufId) {
		List<ManageAreaSecond> manageAreaSecondByAfId = new ArrayList<ManageAreaSecond>();

		ManageAreaFirst manageAreaFirst = manageAreaFirstDao.getManageAreaFirstByUfId(ufId);

		if (manageAreaFirst == null) {
			return manageAreaSecondByAfId;
		}
		String afId = manageAreaFirst.getAfId();
		manageAreaSecondByAfId = manageAreaSecondDao.getManageAreaSecondByAfId(afId);
		return manageAreaSecondByAfId;
	}

	@Override
	public List<ManageAreaSecond> findAll(String asId) {
		return manageAreaSecondDao.findAll(asId);
	}

	@Override
	public Result<ManageAreaSecond> findByPage(String ufId, ManageAreaSecond manageAreaSecond, Integer page, Integer row) {
		ManageAreaFirst manageAreaFirst = manageAreaFirstDao.getManageAreaFirstByUfId(ufId);

		if (manageAreaFirst != null) {
			manageAreaSecond.setAf(manageAreaFirst);
		}

		return manageAreaSecondDao.findByPage(manageAreaSecond, page, row);
	}

	@Override
	public boolean save(ManageAreaSecond manageAreaSecond) {
		if (manageAreaSecondDao.get(ManageAreaSecond.class, manageAreaSecond.getAsId()) == null) {
			manageAreaSecondDao.save(manageAreaSecond);
			if (manageAreaSecond.getUsId() != null) {
				ManageUserSecond manageUserSecond = manageUserSecondDao.get(ManageUserSecond.class, manageAreaSecond.getUsId());
				manageUserSecond.setAs(manageAreaSecond);
				manageUserSecondDao.update(manageUserSecond);
			}
			return true;
		}
		return false;
	}

	@Override
	public ManageAreaSecond getOne(String asId) {

		return manageAreaSecondDao.get(ManageAreaSecond.class, asId);
	}

	@Override
	public boolean update(ManageAreaSecond manageAreaSecond) {
		manageAreaSecondDao.update(manageAreaSecond);
		ManageUserSecond manageUserSecond = manageUserSecondDao.get(ManageUserSecond.class, manageAreaSecond.getUsId());
		manageUserSecond.setAs(manageAreaSecond);
		manageUserSecondDao.update(manageUserSecond);
		return true;
	}

	@Override
	public boolean delete(ManageAreaSecond manageAreaSecond) {
		if (manageAreaSecondDao.get(ManageAreaSecond.class, manageAreaSecond.getAsId()) != null) {
			manageAreaSecondDao.remove(ManageAreaSecond.class, manageAreaSecond.getAsId());
			return true;
		}
		return false;
	}

}
