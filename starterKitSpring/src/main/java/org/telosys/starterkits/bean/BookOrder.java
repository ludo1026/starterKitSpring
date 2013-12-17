package org.telosys.starterkits.bean;

import java.io.Serializable;

import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.Date;

import java.util.List;
import javax.persistence.*;
import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Persistent class for entity stored in table "book_order"
 */
@XmlRootElement  // JAXB annotation for REST Web Services
@XmlAccessorType(XmlAccessType.PROPERTY) // JAXB accessor = getter/setter pair
@Entity
@Table(name="book_order", schema="public" )
// Define named queries here
// @NamedQueries ( {
//  @NamedQuery ( name="BookOrder.query1", query="SELECT x FROM BookOrder x WHERE  " ),
//  @NamedQuery ( name="BookOrder.query2", query="SELECT x FROM BookOrder x WHERE  " )
// } )
public class BookOrder implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id", nullable=false)

    private Integer id;

    @Temporal(TemporalType.DATE)
    @Column(name="date")

    private Date date ;

    @Column(name="state")

    private Integer state ;

    @ManyToOne
    @JoinColumn(name="shop_code", referencedColumnName="code")
    private Shop shop;

    @ManyToOne
    @JoinColumn(name="customer_code", referencedColumnName="code")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="employee_code", referencedColumnName="code")
    private Employee employee;

	public Integer getId() {
        return id;
    }
 
    public void setId(Integer id) {
        this.id = id;
    }

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
