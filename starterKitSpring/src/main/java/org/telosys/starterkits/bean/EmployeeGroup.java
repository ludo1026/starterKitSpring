
package org.telosys.starterkits.bean;

import java.io.Serializable;

import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import org.telosys.starterkits.bean.EmployeeGroupId;

import javax.persistence.*;
import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Persistent class for entity stored in table "employee_group"
 */
@XmlRootElement  // JAXB annotation for REST Web Services
@XmlAccessorType(XmlAccessType.PROPERTY) // JAXB accessor = getter/setter pair
@Entity
@Table(name="employee_group", schema="public" )
// Define named queries here
// @NamedQueries ( {
//  @NamedQuery ( name="EmployeeGroup.query1", query="SELECT x FROM EmployeeGroup x WHERE  " ),
//  @NamedQuery ( name="EmployeeGroup.query2", query="SELECT x FROM EmployeeGroup x WHERE  " )
// } )
public class EmployeeGroup implements Serializable
{
    private static final long serialVersionUID = 1L;

	@EmbeddedId
	@Valid
    private EmployeeGroupId id = new EmployeeGroupId();

	public EmployeeGroupId getId() {
        return id;
    }
 
    public void setId(EmployeeGroupId id) {
        this.id = id;
    }

    public void setEmployeeCode( String employeeCode )
    {
        this.id.setEmployeeCode(employeeCode);
    }

    public String getEmployeeCode()
    {
        return this.id.getEmployeeCode();
    }

    public void setGroupId( Short groupId )
    {
        this.id.setGroupId(groupId);
    }

    public Short getGroupId()
    {
        return this.id.getGroupId();
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
