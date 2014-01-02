/*
 * Created on 2 janv. 2014 ( Time 18:16:50 )
 * Generated by Telosys Tools Generator ( version 2.1.0 )
 */
// This Bean has a composite Primary Key  

/******
keyAttributes size = 2
nonKeyAttributes size = 4
bigList size = 6
. bookId - Integer
. customerCode - String
. creation - Date
. lastUpdate - Date
. reviewNote - Integer
. reviewText - String
******/
package org.telosys.starterkits.bean;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Persistent class for entity stored in table "review"
 *
 * @author Telosys Tools Generator
 *
 */
@XmlRootElement  // JAXB annotation for REST Web Services
@XmlAccessorType(XmlAccessType.PROPERTY) // JAXB accessor = getter/setter pair
@Entity
@Table(name="review", schema="public" )
public class Review implements Serializable
{
    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( EMBEDDED IN AN EXTERNAL CLASS )  
    //----------------------------------------------------------------------
	@EmbeddedId
	@Valid
    private ReviewId id = new ReviewId();

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="creation")

    private Date creation;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="last_update")

    private Date lastUpdate;

    @Column(name="review_note")

    private Integer reviewNote;

    @Column(name="review_text", length=32700)

    private String reviewText;



    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @ManyToOne
    @JoinColumn(name="book_id", referencedColumnName="id", insertable=false, updatable=false)
    private Book book;

    @ManyToOne
    @JoinColumn(name="customer_code", referencedColumnName="code", insertable=false, updatable=false)
    private Customer customer;

    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE COMPOSITE KEY 
    //----------------------------------------------------------------------
	public ReviewId getId() {
        return id;
    }
 
    public void setId(ReviewId id) {
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
    public void setCustomerCode( String customerCode )
    {
        this.id.setCustomerCode( customerCode ) ;
    }
    public String getCustomerCode()
    {
        return this.id.getCustomerCode() ;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : creation ( timestamp ) 
    public void setCreation( Date creation )
    {
        this.creation = creation;
    }
    public Date getCreation()
    {
        return this.creation;
    }

    //--- DATABASE MAPPING : last_update ( timestamp ) 
    public void setLastUpdate( Date lastUpdate )
    {
        this.lastUpdate = lastUpdate;
    }
    public Date getLastUpdate()
    {
        return this.lastUpdate;
    }

    //--- DATABASE MAPPING : review_note ( int4 ) 
    public void setReviewNote( Integer reviewNote )
    {
        this.reviewNote = reviewNote;
    }
    public Integer getReviewNote()
    {
        return this.reviewNote;
    }

    //--- DATABASE MAPPING : review_text ( varchar ) 
    public void setReviewText( String reviewText )
    {
        this.reviewText = reviewText;
    }
    public String getReviewText()
    {
        return this.reviewText;
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

    public void setCustomer( Customer customer )
    {
		if(customer != null && this.getCustomerCode() == null) {
			this.setCustomerCode(customer.getCode());
		}
        this.customer = customer;
    }
    public Customer getCustomer()
    {
        return this.customer;
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
        sb.append(creation); 
        sb.append( "|" ); 
        sb.append(lastUpdate); 
        sb.append( "|" ); 
        sb.append(reviewNote); 
        return sb.toString(); 
		// reviewText is not in toString because it's a "long text" field
    }

}
