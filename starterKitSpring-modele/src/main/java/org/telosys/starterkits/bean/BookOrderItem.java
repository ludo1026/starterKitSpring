/*
 * Created on 2 d�c. 2013 ( Time 16:58:23 )
 * Generated by Telosys Tools Generator ( version 2.0.7 )
 */
// This Bean has a composite Primary Key  

package org.telosys.starterkits.bean;

import java.io.Serializable;

import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.math.BigDecimal;
import org.telosys.starterkits.bean.BookOrderItemId;

import javax.persistence.*;
import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Persistent class for entity stored in table "BOOK_ORDER_ITEM"
 */
@XmlRootElement  // JAXB annotation for REST Web Services
@XmlAccessorType(XmlAccessType.PROPERTY) // JAXB accessor = getter/setter pair
@Entity
@Table(name="BOOK_ORDER_ITEM", schema="public" )
// Define named queries here
// @NamedQueries ( {
//  @NamedQuery ( name="BookOrderItem.query1", query="SELECT x FROM BookOrderItem x WHERE  " ),
//  @NamedQuery ( name="BookOrderItem.query2", query="SELECT x FROM BookOrderItem x WHERE  " )
// } )
public class BookOrderItem implements Serializable
{
    private static final long serialVersionUID = 1L;

	@EmbeddedId
	@Valid
    private BookOrderItemId id = new BookOrderItemId();

    @Column(name="QUANTITY", nullable=false)

    private Integer quantity ;

    @Column(name="PRICE", nullable=false)
    @NotNull
    private BigDecimal price ;

    @ManyToOne
    @JoinColumn(name="BOOK_ORDER_ID", referencedColumnName="ID", insertable=false, updatable=false)
    private BookOrder bookOrder;

    @ManyToOne
    @JoinColumn(name="BOOK_ID", referencedColumnName="ID", insertable=false, updatable=false)
    private Book book;

	public BookOrderItemId getId() {
        return id;
    }
 
    public void setId(BookOrderItemId id) {
        this.id = id;
    }

    public void setQuantity( Integer quantity )
    {
        this.quantity = quantity;
    }

    public Integer getQuantity()
    {
        return this.quantity;
    }

    public void setPrice( BigDecimal price )
    {
        this.price = price;
    }

    public BigDecimal getPrice()
    {
        return this.price;
    }

    public void setBookOrder( BookOrder bookOrder )
    {
        this.bookOrder = bookOrder;
    }

    public BookOrder getBookOrder()
    {
        return this.bookOrder;
    }

    public void setBook( Book book )
    {
        this.book = book;
    }

    public Book getBook()
    {
        return this.book;
    }

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
        sb.append(quantity); 
        sb.append( "|" ); 
        sb.append(price); 
        return sb.toString(); 
    }

}
