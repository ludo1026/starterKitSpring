/*
 * Created on 26 nov. 2013 ( Time 16:06:09 )
 * Generated by Telosys Tools Generator ( version 2.0.6 )
 */
// This Bean has a basic Primary Key (not composite) 
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
 * Persistent class for entity stored in table "BADGE"
 */
@XmlRootElement  // JAXB annotation for REST Web Services
@XmlAccessorType(XmlAccessType.PROPERTY) // JAXB accessor = getter/setter pair
@Entity
@Table(name="BADGE", schema="public" )
// Define named queries here
// @NamedQueries ( {
//  @NamedQuery ( name="Badge.query1", query="SELECT x FROM Badge x WHERE  " ),
//  @NamedQuery ( name="Badge.query2", query="SELECT x FROM Badge x WHERE  " )
// } )
public class Badge implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="BADGE_NUMBER", nullable=false)
    private Integer badgeNumber;

    @Column(name="AUTHORIZATION_LEVEL", nullable=false)
    private Short authorizationLevel ;

    @Temporal(TemporalType.DATE)
    @Column(name="END_OF_VALIDITY")
    private Date endOfValidity ;

    @OneToMany(mappedBy="badge", targetEntity=Employee.class)
    private List<Employee> listOfEmployee;

	public Integer getBadgeNumber() {
        return badgeNumber;
    }
 
    public void setBadgeNumber(Integer badgeNumber) {
        this.badgeNumber = badgeNumber;
    }

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

    @XmlTransient
    public void setListOfEmployee( List<Employee> listOfEmployee )
    {
        this.listOfEmployee = listOfEmployee;
    }

    public List<Employee> getListOfEmployee()
    {
        return this.listOfEmployee;
    }

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
