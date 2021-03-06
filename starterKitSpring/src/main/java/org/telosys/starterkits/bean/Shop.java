package org.telosys.starterkits.bean;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.*;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="shop", schema="public" )
public class Shop implements Serializable
{
    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @Column(name="code", nullable=false, length=3)
    @NotNull
    @Size( min = 1, max = 3 )
    private String code;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="address_1", length=80)
    @Size( max = 80 )
    private String address1;

    @Column(name="address_2", length=80)
    @Size( max = 80 )
    private String address2;

    @Column(name="city", length=45)
    @Size( max = 45 )
    private String city;

    @Column(name="email", length=50)
    @Size( max = 50 )
    private String email;

    @Column(name="name", length=80)
    @Size( max = 80 )
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
    @JoinColumn(name="executive", referencedColumnName="code")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name="country_code", referencedColumnName="code")
    private Country country;

    @OneToMany(mappedBy="shop", targetEntity=Employee.class)
    private List<Employee> listOfEmployee;

    @OneToMany(mappedBy="shop", targetEntity=BookOrder.class)
    private List<BookOrder> listOfBookOrder;


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


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
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

    public void setListOfEmployee( List<Employee> listOfEmployee )
    {
        this.listOfEmployee = listOfEmployee;
    }
    public List<Employee> getListOfEmployee()
    {
        return this.listOfEmployee;
    }

    public void setListOfBookOrder( List<BookOrder> listOfBookOrder )
    {
        this.listOfBookOrder = listOfBookOrder;
    }
    public List<BookOrder> getListOfBookOrder()
    {
        return this.listOfBookOrder;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
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
