package org.telosys.starterkits.bean;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.*;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="country", schema="public" )
public class Country implements Serializable
{
    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @Column(name="code", nullable=false, length=2)
    @NotNull
    @Size( min = 1, max = 2 )
    private String code;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="name", length=45)
    @Size( max = 45 )
    private String name;


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @OneToMany(mappedBy="country", targetEntity=Customer.class)
    private List<Customer> listOfCustomer;

    @OneToMany(mappedBy="country", targetEntity=Shop.class)
    private List<Shop> listOfShop;

    @OneToMany(mappedBy="country", targetEntity=Publisher.class)
    private List<Publisher> listOfPublisher;


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
    public void setName( String name )
    {
        this.name = name;
    }
    public String getName()
    {
        return this.name;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
    public void setListOfCustomer( List<Customer> listOfCustomer )
    {
        this.listOfCustomer = listOfCustomer;
    }
    public List<Customer> getListOfCustomer()
    {
        return this.listOfCustomer;
    }

    public void setListOfShop( List<Shop> listOfShop )
    {
        this.listOfShop = listOfShop;
    }
    public List<Shop> getListOfShop()
    {
        return this.listOfShop;
    }

    public void setListOfPublisher( List<Publisher> listOfPublisher )
    {
        this.listOfPublisher = listOfPublisher;
    }
    public List<Publisher> getListOfPublisher()
    {
        return this.listOfPublisher;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString()
    {
        StringBuffer sb = new StringBuffer(); 
        sb.append(code); 
        sb.append("|"); 
        sb.append(name); 
        return sb.toString(); 
    }

}
