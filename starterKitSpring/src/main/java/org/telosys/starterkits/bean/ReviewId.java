package org.telosys.starterkits.bean;

import java.io.Serializable;

import javax.validation.constraints.* ;

import java.util.Date;
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

    //----------------------------------------------------------------------
    // ENTITY KEY ATTRIBUTES 
    //----------------------------------------------------------------------
    @Column(name="book_id", nullable=false)
    @NotNull
    private Integer bookId;
    
    @Column(name="customer_code", nullable=false, length=5)
    @NotNull
    @Size( min = 1, max = 5 )
    private String customerCode;
    

    //----------------------------------------------------------------------
    // CONSTRUCTORS
    //----------------------------------------------------------------------
    public ReviewId()
    {
        super();
    }

    public ReviewId( Integer bookId, String customerCode )
    {
        super();
        this.bookId = bookId ;
        this.customerCode = customerCode ;
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

    public void setCustomerCode( String value )
    {
        this.customerCode = value;
    }
    public String getCustomerCode()
    {
        return this.customerCode;
    }


    //----------------------------------------------------------------------
    // equals METHOD
    //----------------------------------------------------------------------
	public boolean equals(Object obj) { 
		if ( this == obj ) return true ; 
		if ( obj == null ) return false ;
		if ( this.getClass() != obj.getClass() ) return false ; 
		ReviewId other = (ReviewId) obj; 
		//--- Attribute bookId
		if ( bookId == null ) { 
			if ( other.bookId != null ) 
				return false ; 
		} else if ( ! bookId.equals(other.bookId) ) 
			return false ; 
		//--- Attribute customerCode
		if ( customerCode == null ) { 
			if ( other.customerCode != null ) 
				return false ; 
		} else if ( ! customerCode.equals(other.customerCode) ) 
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
		//--- Attribute customerCode
		result = prime * result + ((customerCode == null) ? 0 : customerCode.hashCode() ) ; 
		
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
		sb.append(customerCode); 
        return sb.toString();
    }
}
