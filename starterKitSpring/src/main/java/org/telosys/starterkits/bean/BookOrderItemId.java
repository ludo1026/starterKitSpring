package org.telosys.starterkits.bean;

import java.io.Serializable;

import javax.validation.constraints.* ;

import java.math.BigDecimal;
import javax.persistence.*;

/**
 * Composite primary key for entity "BookOrderItem" ( stored in table "book_order_item" )
 *
 * @author Telosys Tools Generator
 *
 */
@Embeddable
public class BookOrderItemId implements Serializable
{
    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY KEY ATTRIBUTES 
    //----------------------------------------------------------------------
    @Column(name="book_id", nullable=false)
    @NotNull
    private Integer bookId;
    
    @Column(name="book_order_id", nullable=false)
    @NotNull
    private Integer bookOrderId;
    

    //----------------------------------------------------------------------
    // CONSTRUCTORS
    //----------------------------------------------------------------------
    public BookOrderItemId()
    {
        super();
    }

    public BookOrderItemId( Integer bookId, Integer bookOrderId )
    {
        super();
        this.bookId = bookId ;
        this.bookOrderId = bookOrderId ;
    }
    
    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR KEY FIELDS
    //----------------------------------------------------------------------
    public void setBookId( Integer value )
    {
        this.bookId = value;
    }
    public Integer getBookId()
    {
        return this.bookId;
    }

    public void setBookOrderId( Integer value )
    {
        this.bookOrderId = value;
    }
    public Integer getBookOrderId()
    {
        return this.bookOrderId;
    }


    //----------------------------------------------------------------------
    // equals METHOD
    //----------------------------------------------------------------------
	public boolean equals(Object obj) { 
		if ( this == obj ) return true ; 
		if ( obj == null ) return false ;
		if ( this.getClass() != obj.getClass() ) return false ; 
		BookOrderItemId other = (BookOrderItemId) obj; 
		//--- Attribute bookId
		if ( bookId == null ) { 
			if ( other.bookId != null ) 
				return false ; 
		} else if ( ! bookId.equals(other.bookId) ) 
			return false ; 
		//--- Attribute bookOrderId
		if ( bookOrderId == null ) { 
			if ( other.bookOrderId != null ) 
				return false ; 
		} else if ( ! bookOrderId.equals(other.bookOrderId) ) 
			return false ; 
		return true; 
	} 


    //----------------------------------------------------------------------
    // hashCode METHOD
    //----------------------------------------------------------------------
	public int hashCode() { 
		final int prime = 31; 
		int result = 1; 
		
		//--- Attribute bookId
		result = prime * result + ((bookId == null) ? 0 : bookId.hashCode() ) ; 
		//--- Attribute bookOrderId
		result = prime * result + ((bookOrderId == null) ? 0 : bookOrderId.hashCode() ) ; 
		
		return result; 
	} 


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString()
    {
		StringBuffer sb = new StringBuffer(); 
		sb.append(bookId); 
		sb.append("|"); 
		sb.append(bookOrderId); 
        return sb.toString();
    }
}
