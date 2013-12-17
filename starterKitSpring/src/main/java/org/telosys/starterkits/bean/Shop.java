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
 * Persistent class for entity stored in table "shop"
 */
@XmlRootElement  // JAXB annotation for REST Web Services
@XmlAccessorType(XmlAccessType.PROPERTY) // JAXB accessor = getter/setter pair
@Entity
@Table(name="shop", schema="public" )
// Define named queries here
// @NamedQueries ( {
//  @NamedQuery ( name="Shop.query1", query="SELECT x FROM Shop x WHERE  " ),
//  @NamedQuery ( name="Shop.query2", query="SELECT x FROM Shop x WHERE  " )
// } )
public class Shop implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="code", nullable=false, length=3)
    @NotNull
    @Size( max = 3 )
    private String code;

    @Column(name="address_1", length=80)
    @Size( max = 80 )
    private String address1 ;

    @Column(name="address_2", length=80)
    @Size( max = 80 )
    private String address2 ;

    @Column(name="city", length=45)
    @Size( max = 45 )
    private String city ;

    @Column(name="email", length=50)
    @Size( max = 50 )
    private String email ;

    @Column(name="name", length=80)
    @Size( max = 80 )
    private String name ;

    @Column(name="phone", length=14)
    @Size( max = 14 )
    private String phone ;

    @Column(name="zip_code")

    private Integer zipCode ;

    @ManyToOne
    @JoinColumn(name="executive", referencedColumnName="code")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name="country_code", referencedColumnName="code")
    private Country country;

	public String getCode() {
        return code;
    }
 
    public void setCode(String code) {
        this.code = code;
    }

    public void setAddress1( String address1 )
    {
        this.address1 = address1;
    }

    public String getAddress1()
    {
        return this.address1;
    }

    public void setAddress2( String address2 )
    {
        this.address2 = address2;
    }

    public String getAddress2()
    {
        return this.address2;
    }

    public void setCity( String city )
    {
        this.city = city;
    }

    public String getCity()
    {
        return this.city;
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

    public void setEmployee( Employee employee )
    {
        this.employee = employee;
    }

    public Employee getEmployee()
    {
        return this.employee;
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
        sb.append(address1); 
        sb.append( "|" ); 
        sb.append(address2); 
        sb.append( "|" ); 
        sb.append(city); 
        sb.append( "|" ); 
        sb.append(email); 
        sb.append( "|" ); 
        sb.append(name); 
        sb.append( "|" ); 
        sb.append(phone); 
        sb.append( "|" ); 
        sb.append(zipCode); 
        return sb.toString(); 
    }

}
