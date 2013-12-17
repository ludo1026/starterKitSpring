package org.telosys.starterkits.bean;

import java.io.Serializable;

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


    @ManyToOne
    @JoinColumn(name="book_id", referencedColumnName="id")
    private Book book;

    @ManyToOne
    @JoinColumn(name="book_order_id", referencedColumnName="id")
    private BookOrder bookOrder;


    public BookOrderItemId()
    {
        super();
    }

    public void setBook( Book book )
    {
        this.book = book;
    }

    public Book getBook()
    {
        return this.book;
    }

    public void setBookOrder( BookOrder bookOrder )
    {
        this.bookOrder = bookOrder;
    }

    public BookOrder getBookOrder()
    {
        return this.bookOrder;
    }


    public String toString()
    {
		StringBuffer sb = new StringBuffer(); 
		if(this.book != null && this.book.getId() != null) {
			sb.append(this.book.getId());
		}
		sb.append("|"); 
		if(this.bookOrder != null && this.bookOrder.getId() != null) {
			sb.append(this.bookOrder.getId());
		}
        return sb.toString();
    }

//	public boolean equals(Object obj) { 
//		if ( this == obj ) return true ; 
//		if ( obj == null ) return false ;
//		if ( this.getClass() != obj.getClass() ) return false ; 
//		BookOrderItemId other = (BookOrderItemId) obj;
//		if(this.book == null) {
//			if(other.book != null) {
//				return false;
//			}
//		} else if(this.book.getId() == null) {
//			if(other.book.getId() != null) {
//				return false;
//			}
//		} else {
//			if(!this.book.getId().equals(other.book.getId())) {
//				return false;
//			}
//		}
//		if(this.bookOrder == null) {
//			if(other.bookOrder != null) {
//				return false;
//			}
//		} else if(this.bookOrder.getId() == null) {
//			if(other.bookOrder.getId() != null) {
//				return false;
//			}
//		} else {
//			if(!this.bookOrder.getId().equals(other.bookOrder.getId())) {
//				return false;
//			}
//		}
//		return true; 
//	} 
//
//	public int hashCode() { 
//		final int prime = 31; 
//		int result = 1; 
//		result = prime * result + ((book == null) ? 0 : ((book.getId() == null) ? 0 : book.getId().hashCode() ) );
//		result = prime * result + ((bookOrder == null) ? 0 : ((bookOrder.getId() == null) ? 0 : bookOrder.getId().hashCode() ) );
//		return result; 
//	} 

}
