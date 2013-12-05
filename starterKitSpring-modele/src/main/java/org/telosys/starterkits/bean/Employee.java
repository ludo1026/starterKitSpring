/*
 * Created on 2 d�c. 2013 ( Time 16:58:24 )
 * Generated by Telosys Tools Generator ( version 2.0.7 )
 */
// This Bean has a basic Primary Key (not composite) 
package org.telosys.starterkits.bean;

import java.io.Serializable;

import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;


import java.util.List;
import javax.persistence.*;
import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Persistent class for entity stored in table "EMPLOYEE"
 */
@XmlRootElement  // JAXB annotation for REST Web Services
@XmlAccessorType(XmlAccessType.PROPERTY) // JAXB accessor = getter/setter pair
@Entity
@Table(name="EMPLOYEE", schema="public" )
// Define named queries here
// @NamedQueries ( {
//  @NamedQuery ( name="Employee.query1", query="SELECT x FROM Employee x WHERE  " ),
//  @NamedQuery ( name="Employee.query2", query="SELECT x FROM Employee x WHERE  " )
// } )
public class Employee implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="CODE", nullable=false, length=4)
    @NotNull
    @Size( max = 4 )
    private String code;

    @Column(name="FIRST_NAME", length=40)
    @Size( max = 40 )
    private String firstName ;

    @Column(name="LAST_NAME", nullable=false, length=40)
    @NotNull
    @Size( max = 40 )
    private String lastName ;

    @Column(name="MANAGER")

    private Short manager ;

    @Column(name="EMAIL", length=320)
    @Size( max = 320 )
    private String email ;

    @ManyToOne
    @JoinColumn(name="SHOP_CODE", referencedColumnName="CODE", insertable=false, updatable=false)
    private Shop shop;

    @ManyToOne
    @JoinColumn(name="BADGE_NUMBER", referencedColumnName="BADGE_NUMBER", insertable=false, updatable=false)
    private Badge badge;

	public String getCode() {
        return code;
    }
 
    public void setCode(String code) {
        this.code = code;
    }

    public void setFirstName( String firstName )
    {
        this.firstName = firstName;
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public void setLastName( String lastName )
    {
        this.lastName = lastName;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public void setManager( Short manager )
    {
        this.manager = manager;
    }

    public Short getManager()
    {
        return this.manager;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setShop( Shop shop )
    {
        this.shop = shop;
    }

    public Shop getShop()
    {
        return this.shop;
    }

    public void setBadge( Badge badge )
    {
        this.badge = badge;
    }

    public Badge getBadge()
    {
        return this.badge;
    }

    public String toString()
    {
        StringBuffer sb = new StringBuffer(); 
        sb.append(code); 
        sb.append("|"); 
        sb.append(firstName); 
        sb.append( "|" ); 
        sb.append(lastName); 
        sb.append( "|" ); 
        sb.append(manager); 
        sb.append( "|" ); 
        sb.append(email); 
        return sb.toString(); 
    }

}