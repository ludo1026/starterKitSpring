package org.telosys.starterkits.bean;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.*;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="badge", schema="public" )
public class Badge implements Serializable
{
    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @Column(name="badge_number", nullable=false)
	@GeneratedValue

    private Integer badgeNumber;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="authorization_level", nullable=false)

    private Short authorizationLevel;

    @Temporal(TemporalType.DATE)
    @Column(name="end_of_validity")

    private Date endOfValidity;


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @OneToMany(mappedBy="badge", targetEntity=Employee.class)
    private List<Employee> listOfEmployee;


    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setBadgeNumber( Integer badgeNumber )
    {
        this.badgeNumber = badgeNumber ;
    }

    public Integer getBadgeNumber()
    {
        return this.badgeNumber;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    public void setAuthorizationLevel( Short authorizationLevel )
    {
        this.authorizationLevel = authorizationLevel;
    }
    public Short getAuthorizationLevel()
    {
        return this.authorizationLevel;
    }

    public void setEndOfValidity( Date endOfValidity )
    {
        this.endOfValidity = endOfValidity;
    }
    public Date getEndOfValidity()
    {
        return this.endOfValidity;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
    public void setListOfEmployee( List<Employee> listOfEmployee )
    {
        this.listOfEmployee = listOfEmployee;
    }
    public List<Employee> getListOfEmployee()
    {
        return this.listOfEmployee;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString()
    {
        StringBuffer sb = new StringBuffer(); 
        sb.append(badgeNumber); 
        sb.append("|"); 
        sb.append(authorizationLevel); 
        sb.append( "|" ); 
        sb.append(endOfValidity); 
        return sb.toString(); 
    }

}
