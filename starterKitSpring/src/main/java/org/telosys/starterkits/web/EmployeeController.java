package org.telosys.starterkits.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;

import javax.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.telosys.starterkits.bean.Employee;
import org.telosys.starterkits.service.EmployeeService;
import org.telosys.starterkits.service.ShopService;
import org.telosys.starterkits.service.BadgeService;
import org.telosys.starterkits.web.bean.Message;
import org.telosys.starterkits.web.bean.TypeMessage;
import org.telosys.starterkits.web.helper.ControllerHelper;
import org.telosys.starterkits.web.helper.MessageHelper;

/**
 * Employee.
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController 
{
	@Resource
    private EmployeeService employeeService;
	@Resource
    private ShopService shopService;
	@Resource
    private BadgeService badgeService;
	@Resource
	private ControllerHelper controllerHelper;
	@Resource
	private MessageHelper messageHelper;

	@RequestMapping()
	public String list(Model uiModel) {
		List<Employee> list = employeeService.loadAll();
		uiModel.addAttribute("listEmployees", list);
		return "employee/list";
	}

	void populateForm(Model uiModel, Employee employee) {
		uiModel.addAttribute("employee", employee);
		// Listes déroulantes des objets liés
    	uiModel.addAttribute("shops", shopService.loadAll());
    	uiModel.addAttribute("badges", badgeService.loadAll());
	}

	@RequestMapping("/create")
	public String displayCreateForm(Model uiModel) {
		this.populateForm(uiModel, new Employee());
		return "employee/create";
	}

	@RequestMapping(value = "/{code}")
	public String displayEditForm(Model uiModel, @PathVariable("code") String code) {
		Employee employee = employeeService.load(code);
		this.populateForm(uiModel, employee);
		return "employee/edit";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid Employee employee, BindingResult result, Model uiModel, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		try {
			if (!result.hasErrors()) {
				if(employeeService.load(employee.getCode()) != null) {
					result.addError(new ObjectError("employee", "already.exists"));
				}
			}
			if (!result.hasErrors()) {
				employee = employeeService.save(employee);
				messageHelper.addMessage(redirectAttributes, new Message(TypeMessage.SUCCESS,"save.ok"));
				return "redirect:/employee/"+controllerHelper.encodeUrlPathSegments(httpServletRequest, employee.getCode());
			} else {
				populateForm(uiModel, employee);
				return "employee/create";
			}
		} catch(Exception e) {
			messageHelper.addException(redirectAttributes, "employee.error.create", e);
			populateForm(uiModel, employee);
			return "employee/create";
		}
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String update(@Valid Employee employee, BindingResult result, Model uiModel, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		try {
			if (!result.hasErrors()) {
				employee = employeeService.save(employee);
				messageHelper.addMessage(redirectAttributes, new Message(TypeMessage.SUCCESS,"save.ok"));
				return "redirect:/employee/"+controllerHelper.encodeUrlPathSegments(httpServletRequest, employee.getCode());
			} else {
				populateForm(uiModel, employee);
				return "employee/edit";
			}
		} catch(Exception e) {
			messageHelper.addException(redirectAttributes, "employee.error.update", e);
			populateForm(uiModel, employee);
			return "employee/edit";
		}
	}

	@RequestMapping(value = "/delete/{code}")
	public String delete(RedirectAttributes redirectAttributes, @PathVariable("code") String code) {
		try {
			employeeService.delete(code);
		} catch(Exception e) {
			messageHelper.addException(redirectAttributes, "employee.error.delete", e);
		}
		return "redirect:/employee";
	}
	
}
