package org.telosys.starterkits.bean;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.*;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="employee", schema="public" )
public class Employee implements Serializable
{
    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @Column(name="code", nullable=false, length=4)
    @NotNull
    @Size( min = 1, max = 4 )
    private String code;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="email", length=320)
    @Size( max = 320 )
    private String email;

    @Column(name="first_name", length=40)
    @Size( max = 40 )
    private String firstName;

    @Column(name="last_name", nullable=false, length=40)
    @NotNull
    @Size( min = 1, max = 40 )
    private String lastName;

    @Column(name="manager")

    private Short manager;


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @ManyToOne
    @JoinColumn(name="shop_code", referencedColumnName="code")
    private Shop shop;

    @OneToMany(mappedBy="employee", targetEntity=BookOrder.class)
    private List<BookOrder> listOfBookOrder;

    @ManyToOne
    @JoinColumn(name="badge_number", referencedColumnName="badge_number")
    private Badge badge;

    @OneToMany(mappedBy="employee", targetEntity=Shop.class)
    private List<Shop> listOfShop;


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
    public void setEmail( String email )
    {
        this.email = email;
    }
    public String getEmail()
    {
        return this.email;
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


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
    public void setShop( Shop shop )
    {
        this.shop = shop;
    }
    public Shop getShop()
    {
        return this.shop;
    }

    public void setListOfBookOrder( List<BookOrder> listOfBookOrder )
    {
        this.listOfBookOrder = listOfBookOrder;
    }
    public List<BookOrder> getListOfBookOrder()
    {
        return this.listOfBookOrder;
    }

    public void setBadge( Badge badge )
    {
        this.badge = badge;
    }
    public Badge getBadge()
    {
        return this.badge;
    }

    public void setListOfShop( List<Shop> listOfShop )
    {
        this.listOfShop = listOfShop;
    }
    public List<Shop> getListOfShop()
    {
        return this.listOfShop;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString()
    {
        StringBuffer sb = new StringBuffer(); 
        sb.append(code); 
        sb.append("|"); 
        sb.append(email); 
        sb.append( "|" ); 
        sb.append(firstName); 
        sb.append( "|" ); 
        sb.append(lastName); 
        sb.append( "|" ); 
        sb.append(manager); 
        return sb.toString(); 
    }

}
