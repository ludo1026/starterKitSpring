package org.telosys.starterkits.bean;

import java.io.Serializable;

import javax.persistence.*;


/**
 * Composite primary key for entity "EmployeeGroup" ( stored in table "employee_group" )
 *
 * @author Telosys Tools Generator
 *
 */
@Embeddable
public class EmployeeGroupId implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Column(name="employee_code", nullable=false, length=4)
    private String employeeCode ;

    @Column(name="group_id", nullable=false)
    private Short groupId ;



    public EmployeeGroupId()
    {
        super();
    }

    public void setEmployeeCode( String value )
    {
        this.employeeCode = value;
    }

    public String getEmployeeCode()
    {
        return this.employeeCode;
    }

    public void setGroupId( Short value )
    {
        this.groupId = value;
    }

    public Short getGroupId()
    {
        return this.groupId;
    }


    public String toString()
    {
		StringBuffer sb = new StringBuffer(); 
		sb.append(employeeCode); 
		sb.append("|"); 
		sb.append(groupId); 
        return sb.toString();
    }

	public boolean equals(Object obj) { 
		if ( this == obj ) return true ; 
		if ( obj == null ) return false ;
		if ( this.getClass() != obj.getClass() ) return false ; 
		EmployeeGroupId other = (EmployeeGroupId) obj;
		if(this.employeeCode == null) {
			if(other.employeeCode != null) {
				return false;
			}
		} else {
			if(!this.employeeCode.equals(other.employeeCode)) {
				return false;
			}
		}
		if(this.groupId == null) {
			if(other.groupId != null) {
				return false;
			}
		} else {
			if(!this.groupId.equals(other.groupId)) {
				return false;
			}
		}
		return true; 
	} 

	public int hashCode() { 
		final int prime = 31; 
		int result = 1; 
		result = prime * result + ((employeeCode == null) ? 0 : employeeCode.hashCode() ) ; 
		result = prime * result + ((groupId == null) ? 0 : groupId.hashCode() ) ; 
		return result; 
	} 

}
