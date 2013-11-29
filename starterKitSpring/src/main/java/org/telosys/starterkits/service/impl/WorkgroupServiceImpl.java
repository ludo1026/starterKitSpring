package org.telosys.starterkits.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.telosys.starterkits.bean.Workgroup;
import org.telosys.starterkits.dao.jpa.WorkgroupDao;
import org.telosys.starterkits.dao.repository.WorkgroupRepository;
import org.telosys.starterkits.service.WorkgroupService;

/**
 * Service : Workgroup.
 */
@Component
@Transactional
public class WorkgroupServiceImpl implements WorkgroupService {
		
	@Resource
	private WorkgroupDao workgroupDao;
	@Resource
	private WorkgroupRepository workgroupRepository;
	
	public Workgroup load(final Short id) {
		return workgroupRepository.findOne(id);
	}
	
	public Workgroup save(final Workgroup workgroup) {
		return workgroupRepository.save(workgroup);
	}

	public void delete(final Short id) {
		workgroupRepository.delete(id);
	}

	public List<Workgroup> search(final Map<String,Object> criteria) {
		return workgroupDao.search(criteria);
	}

	public List<Workgroup> loadAll() {
		List<Workgroup> workgroups = new ArrayList<Workgroup>();
		for (Workgroup workgroup : workgroupRepository.findAll()) {
			workgroups.add(workgroup);
		}
		return workgroups;
	}
	
	@Transactional(readOnly=true)
	public Page<Workgroup> findAllByPage(Pageable pageable) {
		return workgroupRepository.findAll(pageable);
	}
	
}
