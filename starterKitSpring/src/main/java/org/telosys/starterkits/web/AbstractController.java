package org.telosys.starterkits.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.telosys.starterkits.web.helper.ControllerHelper;
import org.telosys.starterkits.web.helper.MessageHelper;

public abstract class AbstractController {
	
	private static final String DATE_FORMAT_PATTERN = "date_format_pattern";

	@Resource
	protected ControllerHelper controllerHelper;
	@Resource
	protected MessageHelper messageHelper;
	@Resource
	private MessageSource messageSource;
	
	private Map<Locale, CustomDateEditor> customDateEditorByLocales = new HashMap<Locale, CustomDateEditor>();
	
	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request) {
		Locale locale = RequestContextUtils.getLocale(request);
		binder.registerCustomEditor(Date.class, getCustomDateEditor(locale));
	}
	
	private CustomDateEditor getCustomDateEditor(Locale locale) {
		CustomDateEditor customDateEditor = customDateEditorByLocales.get(locale);
		if(customDateEditor == null) {
			String dateFormatPattern = messageSource.getMessage(DATE_FORMAT_PATTERN, null, locale);
			SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatPattern);
			customDateEditor = new CustomDateEditor(dateFormat, false);
			customDateEditorByLocales.put(locale, customDateEditor);
		}
		return customDateEditor;
	}

}