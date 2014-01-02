/*
 * Created on 2 janv. 2014 ( Time 18:07:20 )
 * Generated by Telosys Tools Generator ( version 2.1.0 )
 */
// This Bean has a composite Primary Key  

/******
keyAttributes size = 2
nonKeyAttributes size = 2
bigList size = 4
. bookId - Integer
. bookOrderId - Integer
. price - BigDecimal
. quantity - Integer
******/
package org.telosys.starterkits.bean;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Persistent class for entity stored in table "book_order_item"
 *
 * @author Telosys Tools Generator
 *
 */
@XmlRootElement  // JAXB annotation for REST Web Services
@XmlAccessorType(XmlAccessType.PROPERTY) // JAXB accessor = getter/setter pair
@Entity
@Table(name="book_order_item", schema="public" )
public class BookOrderItem implements Serializable
{
    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( EMBEDDED IN AN EXTERNAL CLASS )  
    //----------------------------------------------------------------------
	@EmbeddedId
	@Valid
    private BookOrderItemId id = new BookOrderItemId();

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="price", nullable=false)
    @NotNull
    private BigDecimal price;

    @Column(name="quantity", nullable=false)
    @NotNull
    private Integer quantity;



    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @ManyToOne
    @JoinColumn(name="book_id", referencedColumnName="id", insertable=false, updatable=false)
    private Book book;

    @ManyToOne
    @JoinColumn(name="book_order_id", referencedColumnName="id", insertable=false, updatable=false)
    private BookOrder bookOrder;

    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE COMPOSITE KEY 
    //----------------------------------------------------------------------
	public BookOrderItemId getId() {
        return id;
    }
 
    public void setId(BookOrderItemId id) {
        this.id = id;
    }

    public void setBookId( Integer bookId )
    {
        this.id.setBookId( bookId ) ;
    }
    public Integer getBookId()
    {
        return this.id.getBookId() ;
    }
    public void setBookOrderId( Integer bookOrderId )
    {
        this.id.setBookOrderId( bookOrderId ) ;
    }
    public Integer getBookOrderId()
    {
        return this.id.getBookOrderId() ;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : price ( numeric ) 
    public void setPrice( BigDecimal price )
    {
        this.price = price;
    }
    public BigDecimal getPrice()
    {
        return this.price;
    }

    //--- DATABASE MAPPING : quantity ( int4 ) 
    public void setQuantity( Integer quantity )
    {
        this.quantity = quantity;
    }
    public Integer getQuantity()
    {
        return this.quantity;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
    public void setBook( Book book )
    {
		if(book != null && this.getBookId() == null) {
			this.setBookId(book.getId());
		}
        this.book = book;
    }
    public Book getBook()
    {
        return this.book;
    }

    public void setBookOrder( BookOrder bookOrder )
    {
		if(bookOrder != null && this.getBookOrderId() == null) {
			this.setBookOrderId(bookOrder.getId());
		}
        this.bookOrder = bookOrder;
    }
    public BookOrder getBookOrder()
    {
        return this.bookOrder;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString()
    {
        StringBuffer sb = new StringBuffer(); 
        if ( id != null ) {  
            sb.append(id.toString());  
        }  
        else {  
            sb.append( "(null-key)" );  
        }  
        sb.append("|"); 
        sb.append(price); 
        sb.append( "|" ); 
        sb.append(quantity); 
        return sb.toString(); 
    }

}
