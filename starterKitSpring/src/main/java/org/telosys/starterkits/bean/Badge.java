/*
 * Created on 30 d�c. 2013 ( Time 14:44:47 )
 * Generated by Telosys Tools Generator ( version 2.1.0 )
 */
// This Bean has a basic Primary Key (not composite) 
/******
keyAttributes size = 1
nonKeyAttributes size = 2
bigList size = 3
. badgeNumber - int
. authorizationLevel - short
. endOfValidity - Date
******/
package org.telosys.starterkits.bean;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Persistent class for entity stored in table "badge"
 *
 * @author Telosys Tools Generator
 *
 */
@XmlRootElement  // JAXB annotation for REST Web Services
@XmlAccessorType(XmlAccessType.PROPERTY) // JAXB accessor = getter/setter pair
@Entity
@Table(name="badge", schema="public" )
// Define named queries here
// @NamedQueries ( {
//  @NamedQuery ( name="Badge.query1", query="SELECT x FROM Badge x WHERE  " ),
//  @NamedQuery ( name="Badge.query2", query="SELECT x FROM Badge x WHERE  " )
// } )
public class Badge implements Serializable
{
    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @Column(name="badge_number", nullable=false)
    private int        badgeNumber  ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="authorization_level", nullable=false)
    private short      authorizationLevel ;

    @Temporal(TemporalType.DATE)
    @Column(name="end_of_validity")
    private Date       endOfValidity ;



    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @OneToMany(mappedBy="badge", targetEntity=Employee.class)
    private List<Employee> listOfEmployee;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public Badge()
    {
		super();
    }
    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setBadgeNumber( int badgeNumber )
    {
        this.badgeNumber = badgeNumber ;
    }
    public int getBadgeNumber()
    {
        return this.badgeNumber;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : authorization_level ( int2 ) 
    public void setAuthorizationLevel( short authorizationLevel )
    {
        this.authorizationLevel = authorizationLevel;
    }
    public short getAuthorizationLevel()
    {
        return this.authorizationLevel;
    }

    //--- DATABASE MAPPING : end_of_validity ( date ) 
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
    @XmlTransient
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
