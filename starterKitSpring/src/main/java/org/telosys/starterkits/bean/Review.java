
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
 * Persistent class for entity stored in table "review"
 */
@XmlRootElement  // JAXB annotation for REST Web Services
@XmlAccessorType(XmlAccessType.PROPERTY) // JAXB accessor = getter/setter pair
@Entity
@Table(name="review", schema="public" )
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="creation")

    private Date creation ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="last_update")

    private Date lastUpdate ;

    @Column(name="review_note")

    private Integer reviewNote ;

    @Column(name="review_text", length=32700)
    @Size( max = 32700 )
    private String reviewText ;

	public ReviewId getId() {
        return id;
    }
 
    public void setId(ReviewId id) {
        this.id = id;
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

    public void setBook( Book book )
    {
        this.id.setBook(book);
    }

    public Book getBook()
    {
        return this.id.getBook();
    }

    public void setCustomer( Customer customer )
    {
        this.id.setCustomer(customer);
    }

    public Customer getCustomer()
    {
        return this.id.getCustomer();
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
        sb.append(creation); 
        sb.append( "|" ); 
        sb.append(lastUpdate); 
        sb.append( "|" ); 
        sb.append(reviewNote); 
        sb.append( "|" ); 
        sb.append(reviewText); 
        return sb.toString(); 
    }

}
