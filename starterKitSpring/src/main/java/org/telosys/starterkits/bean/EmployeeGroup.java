/*
 * Created on 28 nov. 2013 ( Time 10:04:33 )
 * Generated by Telosys Tools Generator ( version 2.0.7 )
 */
// This Bean has a composite Primary Key  

package org.telosys.starterkits.bean;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import org.telosys.starterkits.bean.EmployeeGroupId;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Persistent class for entity stored in table "EMPLOYEE_GROUP"
 */
@XmlRootElement  // JAXB annotation for REST Web Services
@XmlAccessorType(XmlAccessType.PROPERTY) // JAXB accessor = getter/setter pair
@Entity
@Table(name="EMPLOYEE_GROUP", schema="public" )
// Define named queries here
// @NamedQueries ( {
//  @NamedQuery ( name="EmployeeGroup.query1", query="SELECT x FROM EmployeeGroup x WHERE  " ),
//  @NamedQuery ( name="EmployeeGroup.query2", query="SELECT x FROM EmployeeGroup x WHERE  " )
// } )
public class EmployeeGroup implements Serializable
{
    private static final long serialVersionUID = 1L;

	@EmbeddedId
    private EmployeeGroupId id = new EmployeeGroupId();

	public EmployeeGroupId getId() {
        return id;
    }
 
    public void setId(EmployeeGroupId id) {
        this.id = id;
    }

    public String toString()
    {
        StringBuffer sb = new StringBuffer(); 
        if ( id != null ) {  
            sb.append(id.toString());  
        }  
        else {  
            sb.append( "(null-key)" );  
        }  
        sb.append("|"); 
        return sb.toString(); 
    }

}
