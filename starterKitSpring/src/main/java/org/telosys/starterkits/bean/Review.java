/*
 * Created on 2 d�c. 2013 ( Time 16:58:24 )
 * Generated by Telosys Tools Generator ( version 2.0.7 )
 */
// This Bean has a composite Primary Key  

package org.telosys.starterkits.bean;

import java.io.Serializable;

import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.Date;
import org.telosys.starterkits.bean.ReviewId;

import javax.persistence.*;
import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Persistent class for entity stored in table "REVIEW"
 */
@XmlRootElement  // JAXB annotation for REST Web Services
@XmlAccessorType(XmlAccessType.PROPERTY) // JAXB accessor = getter/setter pair
@Entity
@Table(name="REVIEW", schema="public" )
// Define named queries here
// @NamedQueries ( {
//  @NamedQuery ( name="Review.query1", query="SELECT x FROM Review x WHERE  " ),
//  @NamedQuery ( name="Review.query2", query="SELECT x FROM Review x WHERE  " )
// } )
public class Review implements Serializable
{
    private static final long serialVersionUID = 1L;

	@EmbeddedId
	@Valid
    private ReviewId id = new ReviewId();

    @Column(name="REVIEW_TEXT")
    @Size( max = 32700 )
    private String reviewText ;

    @Column(name="REVIEW_NOTE")

    private Integer reviewNote ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATION")

    private Date creation ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="LAST_UPDATE")

    private Date lastUpdate ;

    @ManyToOne
    @JoinColumn(name="BOOK_ID", referencedColumnName="ID", insertable=false, updatable=false)
    private Book book;

    @ManyToOne
    @JoinColumn(name="CUSTOMER_CODE", referencedColumnName="CODE", insertable=false, updatable=false)
    private Customer customer;

	public ReviewId getId() {
        return id;
    }
 
    public void setId(ReviewId id) {
        this.id = id;
    }

    public void setReviewText( String reviewText )
    {
        this.reviewText = reviewText;
    }

    public String getReviewText()
    {
        return this.reviewText;
    }

    public void setReviewNote( Integer reviewNote )
    {
        this.reviewNote = reviewNote;
    }

    public Integer getReviewNote()
    {
        return this.reviewNote;
    }

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
        if ( id != null ) {  
            sb.append(id.toString());  
        }  
        else {  
            sb.append( "(null-key)" );  
        }  
        sb.append("|"); 
        sb.append(reviewNote); 
        sb.append( "|" ); 
        sb.append(creation); 
        sb.append( "|" ); 
        sb.append(lastUpdate); 
        return sb.toString(); 
		// reviewText is not in toString because it's a "long text" field
    }

}
