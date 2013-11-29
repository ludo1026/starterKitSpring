/*
 * Created on 29 nov. 2013 ( Time 17:32:13 )
 * Generated by Telosys Tools Generator ( version 2.0.7 )
 */
package org.telosys.starterkits.bean;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Composite primary key for entity "EmployeeGroup" ( stored in table "EMPLOYEE_GROUP" )
 *
 * @author Telosys Tools Generator
 *
 */
@Embeddable
public class EmployeeGroupId implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Column(name="EMPLOYEE_CODE", nullable=false, length=4)
    private String employeeCode ;
    
    @Column(name="GROUP_ID", nullable=false)
    private short groupId ;
    
    public EmployeeGroupId()
    {
        super();
    }

    public EmployeeGroupId( String employeeCode, short groupId )
    {
        super();
        this.employeeCode = employeeCode ;
        this.groupId = groupId ;
    }
    
    public void setEmployeeCode( String value )
    {
        this.employeeCode = value;
    }

    public String getEmployeeCode()
    {
        return this.employeeCode;
    }

    public void setGroupId( short value )
    {
        this.groupId = value;
    }

    public short getGroupId()
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

}
