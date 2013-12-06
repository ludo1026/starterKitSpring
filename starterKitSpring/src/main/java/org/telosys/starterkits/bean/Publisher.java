/*
 * Created on 6 d�c. 2013 ( Time 15:33:21 )
 * Generated by Telosys Tools Generator ( version 2.0.7 )
 */
// This Bean has a basic Primary Key (not composite) 
package org.telosys.starterkits.bean;

import java.io.Serializable;

import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;


import javax.persistence.*;
import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Persistent class for entity stored in table "publisher"
 */
@XmlRootElement  // JAXB annotation for REST Web Services
@XmlAccessorType(XmlAccessType.PROPERTY) // JAXB accessor = getter/setter pair
@Entity
@Table(name="publisher", schema="public" )
// Define named queries here
// @NamedQueries ( {
//  @NamedQuery ( name="Publisher.query1", query="SELECT x FROM Publisher x WHERE  " ),
//  @NamedQuery ( name="Publisher.query2", query="SELECT x FROM Publisher x WHERE  " )
// } )
public class Publisher implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="code", nullable=false)

    private Integer code;

    @Column(name="city", length=45)
    @Size( max = 45 )
    private String city ;

    @Column(name="contact", length=45)
    @Size( max = 45 )
    private String contact ;

    @Column(name="email", length=45)
    @Size( max = 45 )
    private String email ;

    @Column(name="name", length=45)
    @Size( max = 45 )
    private String name ;

    @Column(name="phone", length=14)
    @Size( max = 14 )
    private String phone ;

    @Column(name="zip_code")

    private Integer zipCode ;

    @Column(name="country_code", length=2)
    @Size( max = 2 )
    private String countryCode ;

	public Integer getCode() {
        return code;
    }
 
    public void setCode(Integer code) {
        this.code = code;
    }

    public void setCity( String city )
    {
        this.city = city;
    }

    public String getCity()
    {
        return this.city;
    }

    public void setContact( String contact )
    {
        this.contact = contact;
    }

    public String getContact()
    {
        return this.contact;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public void setPhone( String phone )
    {
        this.phone = phone;
    }

    public String getPhone()
    {
        return this.phone;
    }

    public void setZipCode( Integer zipCode )
    {
        this.zipCode = zipCode;
    }

    public Integer getZipCode()
    {
        return this.zipCode;
    }

    public void setCountryCode( String countryCode )
    {
        this.countryCode = countryCode;
    }

    public String getCountryCode()
    {
        return this.countryCode;
    }

    public String toString()
    {
        StringBuffer sb = new StringBuffer(); 
        sb.append(code); 
        sb.append("|"); 
        sb.append(city); 
        sb.append( "|" ); 
        sb.append(contact); 
        sb.append( "|" ); 
        sb.append(email); 
        sb.append( "|" ); 
        sb.append(name); 
        sb.append( "|" ); 
        sb.append(phone); 
        sb.append( "|" ); 
        sb.append(zipCode); 
        sb.append( "|" ); 
        sb.append(countryCode); 
        return sb.toString(); 
    }

}
