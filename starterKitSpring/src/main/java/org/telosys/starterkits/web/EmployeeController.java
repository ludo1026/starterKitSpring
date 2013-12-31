package org.telosys.starterkits.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.telosys.starterkits.bean.Employee;
import org.telosys.starterkits.service.EmployeeService;
import org.telosys.starterkits.service.ShopService;
import org.telosys.starterkits.service.BadgeService;
import org.telosys.starterkits.web.bean.Message;
import org.telosys.starterkits.web.bean.TypeMessage;
import org.telosys.starterkits.web.helper.ControllerHelper;

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
	private ControllerHelper controllerHelper;
	@Resource
    private ShopService shopService;
	@Resource
    private BadgeService badgeService;

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
		if (!result.hasErrors()) {
			employee = employeeService.save(employee);
			redirectAttributes.addFlashAttribute("message", new Message(TypeMessage.SUCCESS,"save.ok"));
			return "redirect:/employee/"+controllerHelper.encodeUrlPathSegments(httpServletRequest, employee.getCode());
		} else {
			populateForm(uiModel, employee);
			return "employee/create";
		}
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String update(@Valid Employee employee, BindingResult result, Model uiModel, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		if (!result.hasErrors()) {
			employee = employeeService.save(employee);
			redirectAttributes.addFlashAttribute("message", new Message(TypeMessage.SUCCESS,"save.ok"));
			return "redirect:/employee/"+controllerHelper.encodeUrlPathSegments(httpServletRequest, employee.getCode());
		} else {
			populateForm(uiModel, employee);
			return "employee/edit";
		}
	}

	@RequestMapping(value = "/delete/{code}")
	public String delete(Model uiModel, @PathVariable("code") String code) {
		employeeService.delete(code);
		return "redirect:/employee";
	}
	
}
