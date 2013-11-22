/*
 * Created on 22 nov. 2013 ( Time 16:55:25 )
 * Generated by Telosys Tools Generator ( version 2.0.6 )
 */
package org.telosys.starterkits.bean;
// This Bean has a basic Primary Key (not composite) 

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;


import javax.persistence.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Persistent class for entity stored in table "CUSTOMER"
 */
@XmlRootElement  // JAXB annotation for REST Web Services
@XmlAccessorType(XmlAccessType.PROPERTY) // JAXB accessor = getter/setter pair
@Entity
@Table(name="CUSTOMER", schema="public" )
// Define named queries here
// @NamedQueries ( {
//  @NamedQuery ( name="Customer.query1", query="SELECT x FROM Customer x WHERE  " ),
//  @NamedQuery ( name="Customer.query2", query="SELECT x FROM Customer x WHERE  " )
// } )
public class Customer implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="CODE", nullable=false, length=5)
    private String code;

    @Column(name="COUNTRY_CODE", nullable=false, length=2)
    private String countryCode ;

    @Column(name="FIRST_NAME", length=40)
    private String firstName ;

    @Column(name="LAST_NAME", length=40)
    private String lastName ;

    @Column(name="LOGIN", nullable=false, length=20)
    private String login ;

    @Column(name="PASSWORD", length=20)
    private String password ;

    @Column(name="AGE")
    private Integer age ;

    @Column(name="CITY", length=45)
    private String city ;

    @Column(name="ZIP_CODE")
    private Integer zipCode ;

    @Column(name="PHONE", length=20)
    private String phone ;

    @Column(name="REVIEWER")
    private Short reviewer ;

	public String getCode() {
        return code;
    }
 
    public void setCode(String code) {
        this.code = code;
    }

    public void setCountryCode( String countryCode )
    {
        this.countryCode = countryCode;
    }

    public String getCountryCode()
    {
        return this.countryCode;
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

    public void setLogin( String login )
    {
        this.login = login;
    }

    public String getLogin()
    {
        return this.login;
    }

    public void setPassword( String password )
    {
        this.password = password;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setAge( Integer age )
    {
        this.age = age;
    }

    public Integer getAge()
    {
        return this.age;
    }

    public void setCity( String city )
    {
        this.city = city;
    }

    public String getCity()
    {
        return this.city;
    }

    public void setZipCode( Integer zipCode )
    {
        this.zipCode = zipCode;
    }

    public Integer getZipCode()
    {
        return this.zipCode;
    }

    public void setPhone( String phone )
    {
        this.phone = phone;
    }

    public String getPhone()
    {
        return this.phone;
    }

    public void setReviewer( Short reviewer )
    {
        this.reviewer = reviewer;
    }

    public Short getReviewer()
    {
        return this.reviewer;
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
        sb.append(login); 
        sb.append( "|" ); 
        sb.append(password); 
        sb.append( "|" ); 
        sb.append(age); 
        sb.append( "|" ); 
        sb.append(city); 
        sb.append( "|" ); 
        sb.append(zipCode); 
        sb.append( "|" ); 
        sb.append(phone); 
        sb.append( "|" ); 
        sb.append(reviewer); 
        return sb.toString(); 
    }

}
