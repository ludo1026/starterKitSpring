package org.telosys.starterkits.test.common;

import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

public class HibernateDbSetupTestListener extends AbstractTestExecutionListener {

	@Override
	public void beforeTestClass(TestContext testContext) throws Exception {
		super.beforeTestMethod(testContext);
		Map<String, LocalSessionFactoryBean> sessionFactoryMap = testContext.getApplicationContext().getBeansOfType(
				LocalSessionFactoryBean.class);
		for (Entry<String, LocalSessionFactoryBean> sessionFactoryEntry : sessionFactoryMap.entrySet()) {
			LocalSessionFactoryBean sessionFactory = sessionFactoryEntry.getValue();
			SchemaExport schemaExport = new SchemaExport(sessionFactory.getConfiguration());
			schemaExport.execute(false, true, false, false);
		}
	}

}