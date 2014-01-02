package org.telosys.starterkits.web.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.telosys.starterkits.web.bean.Message;
import org.telosys.starterkits.web.bean.TypeMessage;

@Component
public class MessageHelper {
	
	public void addException(Model uiModel, Exception e) {
		List<Message> messages = getMessages(uiModel);
		addException(messages, e);
	}
	
	public void addException(RedirectAttributes redirectAttributes, String messageKey, Exception e) {
		List<Message> messages = getMessages(redirectAttributes);
		addException(messages, messageKey, e);
	}

	public void addException(List<Message> messages, Exception e) {
		if(e.getCause() == null) {
			messages.add(new Message(TypeMessage.DANGER, "error", e.getMessage()));
		} else {
			messages.add(new Message(TypeMessage.DANGER, "error.with.cause", e.getMessage(), e.getCause().getMessage()));
		}
	}

	public void addException(List<Message> messages, String messageKey, Exception e) {
		if(e.getCause() == null) {
			messages.add(new Message(TypeMessage.DANGER, messageKey, e.getMessage()));
		} else {
			messages.add(new Message(TypeMessage.DANGER, messageKey, e.getMessage(), e.getCause().getMessage()));
		}
	}
	
	public void addMessage(RedirectAttributes redirectAttributes, Message message) {
		List<Message> messages = getMessages(redirectAttributes);
		messages.add(message);
	}
	
	public void addMessage(Model uiModel, Message message) {
		List<Message> messages = getMessages(uiModel);
		messages.add(message);
	}

	@SuppressWarnings("unchecked")
	private List<Message> getMessages(RedirectAttributes redirectAttributes) {
		Map<String,Object> map = (Map<String, Object>) redirectAttributes.getFlashAttributes();
		List<Message> messages = (List<Message>) map.get("messages");
		if(messages == null) {
			messages = new ArrayList<Message>();
			map.put("messages", messages);
		}
		return (List<Message>) map.get("messages");
	}
	
	@SuppressWarnings("unchecked")
	private List<Message> getMessages(Model uiModel) {
		Map<String,Object> map = uiModel.asMap();
		List<Message> messages = (List<Message>) map.get("messages");
		if(messages == null) {
			messages = new ArrayList<Message>();
			map.put("messages", messages);
		}
		return (List<Message>) map.get("messages");
	}
}