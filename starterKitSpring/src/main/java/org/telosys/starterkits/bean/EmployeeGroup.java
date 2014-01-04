package org.telosys.starterkits.bean;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.*;


import javax.persistence.*;

@Entity
@Table(name="employee_group", schema="public" )
public class EmployeeGroup implements Serializable
{
    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( EMBEDDED IN AN EXTERNAL CLASS )  
    //----------------------------------------------------------------------
	@EmbeddedId
	@Valid
    private EmployeeGroupId id = new EmployeeGroupId();

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    

    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE COMPOSITE KEY 
    //----------------------------------------------------------------------
	public EmployeeGroupId getId() {
        return id;
    }
 
    public void setId(EmployeeGroupId id) {
        this.id = id;
    }

    public void setEmployeeCode( String employeeCode )
    {
        this.id.setEmployeeCode( employeeCode ) ;
    }

    public String getEmployeeCode()
    {
        return this.id.getEmployeeCode() ;
    }

    public void setGroupId( Short groupId )
    {
        this.id.setGroupId( groupId ) ;
    }

    public Short getGroupId()
    {
        return this.id.getGroupId() ;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
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
