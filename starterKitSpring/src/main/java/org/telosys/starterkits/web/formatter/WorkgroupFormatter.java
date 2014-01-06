package org.telosys.starterkits.web.formatter;

import org.springframework.stereotype.Component;
import org.telosys.starterkits.bean.Workgroup;

@Component
public class WorkgroupFormatter implements Formatter<Workgroup> {

	@Override
	public String display(Workgroup workgroup) {
		return workgroup.toString();
	}

}
