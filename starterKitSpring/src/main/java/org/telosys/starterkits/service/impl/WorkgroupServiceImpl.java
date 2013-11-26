package org.telosys.starterkits.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.telosys.starterkits.bean.Workgroup;
import org.telosys.starterkits.dao.jpa.WorkgroupDao;
import org.telosys.starterkits.service.WorkgroupService;

/**
 * Service : Workgroup.
 */
@Component
public class WorkgroupServiceImpl implements WorkgroupService {
		
	@Resource
	private WorkgroupDao workgroupDao;
	
	public Workgroup load(final Short id) {
		return workgroupDao.load(id);
	}
	
	public Workgroup save(final Workgroup workgroup) {
		return workgroupDao.save(workgroup);
	}

	public void delete(final Short id) {
		workgroupDao.delete(id);
	}

	public List<Workgroup> search(final Map<String,Object> criteria) {
		return workgroupDao.search(criteria);
	}

	public List<Workgroup> loadAll() {
		return workgroupDao.loadAll();
	}
	
}
