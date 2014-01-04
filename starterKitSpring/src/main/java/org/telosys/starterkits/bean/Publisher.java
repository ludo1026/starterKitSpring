package org.telosys.starterkits.bean;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.*;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="publisher", schema="public" )
public class Publisher implements Serializable
{
    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @Column(name="code", nullable=false)
	@GeneratedValue

    private Integer code;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="city", length=45)
    @Size( max = 45 )
    private String city;

    @Column(name="contact", length=45)
    @Size( max = 45 )
    private String contact;

    @Column(name="email", length=45)
    @Size( max = 45 )
    private String email;

    @Column(name="name", length=45)
    @Size( max = 45 )
    private String name;

    @Column(name="phone", length=14)
    @Size( max = 14 )
    private String phone;

    @Column(name="zip_code")

    private Integer zipCode;


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @ManyToOne
    @JoinColumn(name="country_code", referencedColumnName="code")
    private Country country;

    @OneToMany(mappedBy="publisher", targetEntity=Book.class)
    private List<Book> listOfBook;


    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setCode( Integer code )
    {
        this.code = code ;
    }

    public Integer getCode()
    {
        return this.code;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
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


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
    public void setCountry( Country country )
    {
        this.country = country;
    }
    public Country getCountry()
    {
        return this.country;
    }

    public void setListOfBook( List<Book> listOfBook )
    {
        this.listOfBook = listOfBook;
    }
    public List<Book> getListOfBook()
    {
        return this.listOfBook;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
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
        return sb.toString(); 
    }

}
