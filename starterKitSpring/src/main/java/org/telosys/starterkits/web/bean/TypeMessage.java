package org.telosys.starterkits.web.bean;

public enum TypeMessage {
	
	SUCCESS,
	INFO,
	WARNING,
	DANGER;
	
	public String getCss() {
		return name().toLowerCase();
	}
	
}
