package org.telosys.starterkits.web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Message implements Serializable {
	
	private static final long serialVersionUID = -6488696546322780424L;
	
	private TypeMessage type;
	
	private String message;
	
	private List<String> messageArguments = new ArrayList<String>();

	public Message() {
	}
	
	public Message(TypeMessage type, String message) {
		this.type = type;
		this.message = message;
	}

	public Message(TypeMessage type, String message, String... messageArguments) {
		this.type = type;
		this.message = message;
		if(messageArguments != null) {
			this.messageArguments.addAll(Arrays.asList(messageArguments));
		}
	}

	public void addMessageArgument(String messageArgument) {
		this.messageArguments.add(messageArgument);
	}

	public TypeMessage getType() {
		return type;
	}

	public void setType(TypeMessage type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getMessageArguments() {
		return messageArguments;
	}

	public void setMessageArguments(List<String> messageArguments) {
		this.messageArguments = messageArguments;
	}

}
