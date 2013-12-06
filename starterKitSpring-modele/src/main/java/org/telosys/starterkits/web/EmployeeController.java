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

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
	}

	void populateEditForm(Model uiModel, Employee employee) {
		uiModel.addAttribute("employee", employee);
		// Listes déroulantes des objets liés
    	uiModel.addAttribute("shops", shopService.loadAll());
    	uiModel.addAttribute("badges", badgeService.loadAll());
	}

	@RequestMapping("/create")
	public String create(Model uiModel) {
		this.populateEditForm(uiModel, new Employee());
		return "employee/edit";
	}

	@RequestMapping()
	public String list(Model uiModel) {
		List<Employee> list = employeeService.loadAll();
		uiModel.addAttribute("listEmployees", list);
		return "employee/list";
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String save(@Valid Employee employee, BindingResult result, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		if (!result.hasErrors()) {
			employee = employeeService.save(employee);
			redirectAttributes.addFlashAttribute("message", new Message(TypeMessage.SUCCESS,"save.ok"));
			return "redirect:/employee/"+controllerHelper.encodeUrlPathSegments(httpServletRequest, employee.getCode());
		} else {
			return "employee/edit";
		}
	}

	@RequestMapping(value = "/{code}")
	public String edit(Model uiModel, @PathVariable("code") String code) {
		Employee employee = employeeService.load(code);
		this.populateEditForm(uiModel, employee);
		return "employee/edit";
	}

	@RequestMapping(value = "/delete/{code}")
	public String delete(Model uiModel, @PathVariable("code") String code) {
		employeeService.delete(code);
		return "redirect:/employee";
	}
	
}
