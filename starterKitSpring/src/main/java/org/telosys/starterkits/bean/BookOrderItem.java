package org.telosys.starterkits.bean;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.*;

import java.math.BigDecimal;

import javax.persistence.*;

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
    public void setPrice( BigDecimal price )
    {
        this.price = price;
    }
    public BigDecimal getPrice()
    {
        return this.price;
    }

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
