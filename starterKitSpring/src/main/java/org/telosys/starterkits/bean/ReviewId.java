package org.telosys.starterkits.bean;

import java.io.Serializable;

import javax.persistence.*;


/**
 * Composite primary key for entity "Review" ( stored in table "review" )
 *
 * @author Telosys Tools Generator
 *
 */
@Embeddable
public class ReviewId implements Serializable
{
    private static final long serialVersionUID = 1L;


    @ManyToOne
    @JoinColumn(name="book_id", referencedColumnName="id", insertable=false, updatable=false)
    private Book book;

    @ManyToOne
    @JoinColumn(name="customer_code", referencedColumnName="code", insertable=false, updatable=false)
    private Customer customer;


    public ReviewId()
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

    public void setCustomer( Customer customer )
    {
        this.customer = customer;
    }

    public Customer getCustomer()
    {
        return this.customer;
    }


    public String toString()
    {
		StringBuffer sb = new StringBuffer(); 
		if(this.book != null && this.book.getId() != null) {
			sb.append(this.book.getId());
		}
		sb.append("|"); 
		if(this.customer != null && this.customer.getCode() != null) {
			sb.append(this.customer.getCode());
		}
        return sb.toString();
    }

	public boolean equals(Object obj) { 
		if ( this == obj ) return true ; 
		if ( obj == null ) return false ;
		if ( this.getClass() != obj.getClass() ) return false ; 
		ReviewId other = (ReviewId) obj;
		if(this.book == null) {
			if(other.book != null) {
				return false;
			}
		} else if(this.book.getId() == null) {
			if(other.book.getId() != null) {
				return false;
			}
		} else {
			if(!this.book.getId().equals(other.book.getId())) {
				return false;
			}
		}
		if(this.customer == null) {
			if(other.customer != null) {
				return false;
			}
		} else if(this.customer.getCode() == null) {
			if(other.customer.getCode() != null) {
				return false;
			}
		} else {
			if(!this.customer.getCode().equals(other.customer.getCode())) {
				return false;
			}
		}
		return true; 
	} 

	public int hashCode() { 
		final int prime = 31; 
		int result = 1; 
		result = prime * result + ((book == null) ? 0 : ((book.getId() == null) ? 0 : book.getId().hashCode() ) );
		result = prime * result + ((customer == null) ? 0 : ((customer.getCode() == null) ? 0 : customer.getCode().hashCode() ) );
		return result; 
	} 

}
