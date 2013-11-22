/*
 * Service class 
 * Created on 22 nov. 2013 ( Time 16:27:45 )
 */

package org.telosys.starterkits.service;

import java.util.List;
import java.util.Map;

import org.telosys.starterkits.bean.Employee;
import javax.persistence.PersistenceException;
import org.telosys.starterkits.persistence.PersistenceServiceProvider;
import org.telosys.starterkits.service.IService;
import org.telosys.starterkits.persistence.services.EmployeePersistence;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;


/**
 * Service : Employee.
 */
public class EmployeeService implements IService<Employee, String> {

	protected final Logger LOG = LoggerFactory.getLogger(EmployeeService.class);
	
	private EmployeePersistence getEmployeePersistence() {
		return PersistenceServiceProvider.getService(EmployeePersistence.class);
	}

	public Employee load(final String code) {
		if (LOG.isDebugEnabled()) LOG.debug("load");
		Employee employee;
		try {
			EmployeePersistence employeePersistence = getEmployeePersistence();
			employee = employeePersistence.load(code);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return employee ;
	}

	public Employee save(final Employee employee) {
		if (LOG.isDebugEnabled()) LOG.debug("save");
		Employee employeeSaved;
		try {
			EmployeePersistence employeePersistence = getEmployeePersistence();
			employeeSaved = employeePersistence.save(employee);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return employeeSaved;
	}

	public void delete(final String code) {
		if (LOG.isDebugEnabled()) LOG.debug("delete");
		try {
			EmployeePersistence employeePersistence = getEmployeePersistence();
			employeePersistence.delete(code);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
	}

	public List<Employee> search(final Map<String,Object> criteria) {
		if (LOG.isDebugEnabled()) LOG.debug("search");
		List<Employee> employees;
		try {
			EmployeePersistence employeePersistence = getEmployeePersistence();
			employees = employeePersistence.search(criteria);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return employees;
	}

	public List<Employee> loadAll() {
		if (LOG.isDebugEnabled()) LOG.debug("loadAll");
		List<Employee> employees;
		try {
			EmployeePersistence employeePersistence = getEmployeePersistence();
			employees = employeePersistence.loadAll();
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return employees;
	}

}
