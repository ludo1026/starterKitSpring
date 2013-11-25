/*
 * Created on 22 nov. 2013 ( Time 17:59:38 )
 * Generated by Telosys Tools Generator ( version 2.0.6 )
 */
// This Bean has a basic Primary Key (not composite) 
package org.telosys.starterkits.bean;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;


import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Persistent class for entity stored in table "PUBLISHER"
 */
@XmlRootElement  // JAXB annotation for REST Web Services
@XmlAccessorType(XmlAccessType.PROPERTY) // JAXB accessor = getter/setter pair
@Entity
@Table(name="PUBLISHER", schema="public" )
// Define named queries here
// @NamedQueries ( {
//  @NamedQuery ( name="Publisher.query1", query="SELECT x FROM Publisher x WHERE  " ),
//  @NamedQuery ( name="Publisher.query2", query="SELECT x FROM Publisher x WHERE  " )
// } )
public class Publisher implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="CODE", nullable=false)
    private Integer code;

    @Column(name="NAME", length=45)
    private String name ;

    @Column(name="EMAIL", length=45)
    private String email ;

    @Column(name="CONTACT", length=45)
    private String contact ;

    @Column(name="CITY", length=45)
    private String city ;

    @Column(name="ZIP_CODE")
    private Integer zipCode ;

    @Column(name="PHONE", length=14)
    private String phone ;

	// "countryCode" (column "COUNTRY_CODE") is not defined by itself because used as FK in a link 

    @OneToMany(mappedBy="publisher", targetEntity=Book.class)
    private List<Book> listOfBook;

    @ManyToOne
    @JoinColumn(name="COUNTRY_CODE", referencedColumnName="CODE", insertable=false, updatable=false)
    private Country country;

	public Integer getCode() {
        return code;
    }
 
    public void setCode(Integer code) {
        this.code = code;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setContact( String contact )
    {
        this.contact = contact;
    }

    public String getContact()
    {
        return this.contact;
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

    @XmlTransient
    public void setListOfBook( List<Book> listOfBook )
    {
        this.listOfBook = listOfBook;
    }

    public List<Book> getListOfBook()
    {
        return this.listOfBook;
    }

    public void setCountry( Country country )
    {
        this.country = country;
    }

    public Country getCountry()
    {
        return this.country;
    }

    public String toString()
    {
        StringBuffer sb = new StringBuffer(); 
        sb.append(code); 
        sb.append("|"); 
        sb.append(name); 
        sb.append( "|" ); 
        sb.append(email); 
        sb.append( "|" ); 
        sb.append(contact); 
        sb.append( "|" ); 
        sb.append(city); 
        sb.append( "|" ); 
        sb.append(zipCode); 
        sb.append( "|" ); 
        sb.append(phone); 
        return sb.toString(); 
    }

}
