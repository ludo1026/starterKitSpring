package org.telosys.starterkits.bean;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.*;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="book_order", schema="public" )
public class BookOrder implements Serializable
{
    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @Column(name="id", nullable=false)
	@GeneratedValue

    private Integer id;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Temporal(TemporalType.DATE)
    @Column(name="date")

    private Date date;

    @Column(name="state")

    private Integer state;


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @OneToMany(mappedBy="bookOrder", targetEntity=BookOrderItem.class)
    private List<BookOrderItem> listOfBookOrderItem;

    @ManyToOne
    @JoinColumn(name="shop_code", referencedColumnName="code")
    private Shop shop;

    @ManyToOne
    @JoinColumn(name="customer_code", referencedColumnName="code")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="employee_code", referencedColumnName="code")
    private Employee employee;


    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setId( Integer id )
    {
        this.id = id ;
    }

    public Integer getId()
    {
        return this.id;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    public void setDate( Date date )
    {
        this.date = date;
    }
    public Date getDate()
    {
        return this.date;
    }

    public void setState( Integer state )
    {
        this.state = state;
    }
    public Integer getState()
    {
        return this.state;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
    public void setListOfBookOrderItem( List<BookOrderItem> listOfBookOrderItem )
    {
        this.listOfBookOrderItem = listOfBookOrderItem;
    }
    public List<BookOrderItem> getListOfBookOrderItem()
    {
        return this.listOfBookOrderItem;
    }

    public void setShop( Shop shop )
    {
        this.shop = shop;
    }
    public Shop getShop()
    {
        return this.shop;
    }

    public void setCustomer( Customer customer )
    {
        this.customer = customer;
    }
    public Customer getCustomer()
    {
        return this.customer;
    }

    public void setEmployee( Employee employee )
    {
        this.employee = employee;
    }
    public Employee getEmployee()
    {
        return this.employee;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString()
    {
        StringBuffer sb = new StringBuffer(); 
        sb.append(id); 
        sb.append("|"); 
        sb.append(date); 
        sb.append( "|" ); 
        sb.append(state); 
        return sb.toString(); 
    }

}
