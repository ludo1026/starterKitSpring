/*
 * Created on 22 nov. 2013 ( Time 16:27:47 )
 * Generated by Telosys Tools Generator ( version 2.0.6 )
 */
package org.telosys.starterkits.test.persistence;


import org.telosys.starterkits.bean.Employee ;
import org.telosys.starterkits.mock.EmployeeMock;
import org.telosys.starterkits.persistence.PersistenceServiceProvider;
import org.telosys.starterkits.persistence.services.EmployeePersistence;

import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test case for Employee persistence service
 * 
 * @author Telosys Tools Generator
 *
 */
public class EmployeePersistenceTest 
{
	@Test
	public void test1() {
		
		System.out.println("Test Employee persistence : delete + load ..." );
		
		EmployeePersistence service = PersistenceServiceProvider.getService(EmployeePersistence.class);
		
		EmployeeMock mock = new EmployeeMock();
		
		// TODO : set primary key values here 
		process( service, mock, "A"  );
		// process( service, mock, ... );
	}

	private void process(EmployeePersistence service, EmployeeMock mock, String code ) {
		System.out.println("----- "  );
		System.out.println(" . load : " );
		Employee entity = service.load( code );
		if ( entity != null ) {
			// Found 
			System.out.println("   FOUND : " + entity );
			
			// Save (update) with the same values to avoid database integrity errors  
			System.out.println(" . save : " + entity );
			service.save(entity);
			System.out.println("   saved : " + entity );
		}
		else {
			// Not found 
			System.out.println("   NOT FOUND" );
			// Create a new instance 
			entity = mock.createInstance( code ) ;
			Assert.assertNotNull(entity);

			// This entity references the following entities : 
			// . Shop
			// . Badge
			/* Insert only if references are OK
			// Try to insert the new instance
			System.out.println(" . insert : " + entity );
			service.insert(entity);
			System.out.println("   inserted : " + entity );
			*/

			System.out.println(" . delete : " );
			boolean deleted = service.delete( code );
			System.out.println("   deleted = " + deleted);
		}		
	}
}
