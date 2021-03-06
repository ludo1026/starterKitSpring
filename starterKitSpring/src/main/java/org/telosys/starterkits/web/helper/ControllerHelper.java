package org.telosys.starterkits.web.helper;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

@Component
public class ControllerHelper {

	public static final String URI_SEPARATOR = "/";

	public String encodeUrlPathSegments(HttpServletRequest httpServletRequest, Object... pathSegments) {
		StringBuffer enc = new StringBuffer();
		boolean isFirst = true;
		for (Object pathSegment : pathSegments) {
			if (isFirst) {
				isFirst = false;
			} else {
				enc.append(URI_SEPARATOR);
			}
			enc.append(this.encodeUrlPathSegment(httpServletRequest, pathSegment.toString()));
		}
		return enc.toString();
	}

	public String encodeUrlPathSegment(HttpServletRequest httpServletRequest, String pathSegment) {
		String enc = httpServletRequest.getCharacterEncoding();
		if (enc == null) {
			enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
		}
		try {
			pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
		} catch (UnsupportedEncodingException uee) {
		}
		return pathSegment;
	}

}
