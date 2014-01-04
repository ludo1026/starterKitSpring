package org.telosys.starterkits.web.filter;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.util.WebUtils;

public class I18nFilter implements Filter {

	private static final String DEFAULT_LOCALE = "defaultLocale";
	
	private String defaultLocale;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		defaultLocale = filterConfig.getInitParameter(DEFAULT_LOCALE);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
//			Locale locale = RequestContextUtils.getLocale((HttpServletRequest)request);
//	        if (locale != null) {
//	            request.setAttribute("_user_locale", locale);
//	        }
			Cookie cookie = WebUtils.getCookie((HttpServletRequest)request, CookieLocaleResolver.LOCALE_REQUEST_ATTRIBUTE_NAME);
		    Locale locale;
			if (cookie == null) {
				locale = org.springframework.util.StringUtils.parseLocaleString(defaultLocale);
	        } else {
	        	locale = org.springframework.util.StringUtils.parseLocaleString(cookie.getValue());
	        }
            if (locale != null) {
                request.setAttribute(CookieLocaleResolver.LOCALE_REQUEST_ATTRIBUTE_NAME, locale);
                LocaleContextHolder.setLocale(locale, true);
            }
		}
        chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}
