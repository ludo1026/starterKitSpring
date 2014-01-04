package org.telosys.starterkits.bean;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.*;

import java.util.Date;

import javax.persistence.*;

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
    public void setCreation( Date creation )
    {
        this.creation = creation;
    }
    public Date getCreation()
    {
        return this.creation;
    }

    public void setLastUpdate( Date lastUpdate )
    {
        this.lastUpdate = lastUpdate;
    }
    public Date getLastUpdate()
    {
        return this.lastUpdate;
    }

    public void setReviewNote( Integer reviewNote )
    {
        this.reviewNote = reviewNote;
    }
    public Integer getReviewNote()
    {
        return this.reviewNote;
    }

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
