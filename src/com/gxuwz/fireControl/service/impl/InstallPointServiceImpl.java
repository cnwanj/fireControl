package com.gxuwz.fireControl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gxuwz.core.pagination.Result;
import com.gxuwz.fireControl.dao.InstallPointDao;
import com.gxuwz.fireControl.model.entity.InstallPoint;
import com.gxuwz.fireControl.service.InstallPointService;


/**
 * @Description TODO
 * @author 马金典
 * @version v1.0
 * @date 2020 年2月10日
 */
@Transactional
@Service("installPointService")
public class InstallPointServiceImpl implements InstallPointService{
	@Autowired
	private InstallPointDao installpointdao;
	private Class<InstallPoint> ip = InstallPoint.class;
	
	
	@Override
	public boolean save(InstallPoint installPoint) {
		if(installpointdao.getOneByInsName(installPoint.getInsName()) == null) {
			installpointdao.save(installPoint);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean delete(InstallPoint installPoint) {				
		if (installpointdao.get(ip, installPoint.getInsId()) != null) {
			installpointdao.remove(ip, installPoint.getInsId());
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean update(InstallPoint installPoint) {		
		installpointdao.update(installPoint);
		return true;
	}

	@Override
	public Result<InstallPoint> findByPage(InstallPoint installpoint,Integer page, Integer limit) {
		return installpointdao.findByPage(installpoint,page, limit);
	}

	@Override
	public InstallPoint getOne(Long insId) {
		return installpointdao.get(ip, insId);
	}

	@Override
	public List<InstallPoint> findAll(String asId) {
		return installpointdao.findAll(asId);
	}

}
