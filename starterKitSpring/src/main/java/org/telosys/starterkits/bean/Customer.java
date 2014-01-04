package org.telosys.starterkits.bean;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.*;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="customer", schema="public" )
public class Customer implements Serializable
{
    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @Column(name="code", nullable=false, length=5)
    @NotNull
    @Size( min = 1, max = 5 )
    private String code;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="age")

    private Integer age;

    @Column(name="city", length=45)
    @Size( max = 45 )
    private String city;

    @Column(name="first_name", length=40)
    @Size( max = 40 )
    private String firstName;

    @Column(name="last_name", length=40)
    @Size( max = 40 )
    private String lastName;

    @Column(name="login", nullable=false, length=20)
    @NotNull
    @Size( min = 1, max = 20 )
    private String login;

    @Column(name="password", length=20)
    @Size( max = 20 )
    private String password;

    @Column(name="phone", length=20)
    @Size( max = 20 )
    private String phone;

    @Column(name="reviewer")

    private Short reviewer;

    @Column(name="zip_code")

    private Integer zipCode;


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @OneToMany(mappedBy="customer", targetEntity=Review.class)
    private List<Review> listOfReview;

    @OneToMany(mappedBy="customer", targetEntity=BookOrder.class)
    private List<BookOrder> listOfBookOrder;

    @ManyToOne
    @JoinColumn(name="country_code", referencedColumnName="code")
    private Country country;


    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setCode( String code )
    {
        this.code = code ;
    }

    public String getCode()
    {
        return this.code;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
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
    public void setListOfReview( List<Review> listOfReview )
    {
        this.listOfReview = listOfReview;
    }
    public List<Review> getListOfReview()
    {
        return this.listOfReview;
    }

    public void setListOfBookOrder( List<BookOrder> listOfBookOrder )
    {
        this.listOfBookOrder = listOfBookOrder;
    }
    public List<BookOrder> getListOfBookOrder()
    {
        return this.listOfBookOrder;
    }

    public void setCountry( Country country )
    {
        this.country = country;
    }
    public Country getCountry()
    {
        return this.country;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString()
    {
        StringBuffer sb = new StringBuffer(); 
        sb.append(code); 
        sb.append("|"); 
        sb.append(age); 
        sb.append( "|" ); 
        sb.append(city); 
        sb.append( "|" ); 
        sb.append(firstName); 
        sb.append( "|" ); 
        sb.append(lastName); 
        sb.append( "|" ); 
        sb.append(login); 
        sb.append( "|" ); 
        sb.append(password); 
        sb.append( "|" ); 
        sb.append(phone); 
        sb.append( "|" ); 
        sb.append(reviewer); 
        sb.append( "|" ); 
        sb.append(zipCode); 
        return sb.toString(); 
    }

}
