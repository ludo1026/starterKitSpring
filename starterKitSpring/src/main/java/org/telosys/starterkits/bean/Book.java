package org.telosys.starterkits.bean;

import java.io.Serializable;

import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.math.BigDecimal;

import javax.persistence.*;
import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Persistent class for entity stored in table "book"
 */
@XmlRootElement  // JAXB annotation for REST Web Services
@XmlAccessorType(XmlAccessType.PROPERTY) // JAXB accessor = getter/setter pair
@Entity
@Table(name="book", schema="public" )
// Define named queries here
// @NamedQueries ( {
//  @NamedQuery ( name="Book.query1", query="SELECT x FROM Book x WHERE  " ),
//  @NamedQuery ( name="Book.query2", query="SELECT x FROM Book x WHERE  " )
// } )
public class Book implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id", nullable=false)

    private Integer id;

    @Column(name="availability")

    private Short availability ;

    @Column(name="best_seller")

    private Short bestSeller ;

    @Column(name="discount")

    private Integer discount ;

    @Column(name="isbn", nullable=false, length=13)
    @NotNull
    @Size( max = 13 )
    private String isbn ;

    @Column(name="price")

    private BigDecimal price ;

    @Column(name="quantity")

    private Integer quantity ;

    @Column(name="title", length=160)
    @Size( max = 160 )
    private String title ;

    @ManyToOne
    @JoinColumn(name="publisher_id", referencedColumnName="code")
    private Publisher publisher;

    @ManyToOne
    @JoinColumn(name="author_id", referencedColumnName="id")
    private Author author;

	public Integer getId() {
        return id;
    }
 
    public void setId(Integer id) {
        this.id = id;
    }

    public void setAvailability( Short availability )
    {
        this.availability = availability;
    }

    public Short getAvailability()
    {
        return this.availability;
    }

    public void setBestSeller( Short bestSeller )
    {
        this.bestSeller = bestSeller;
    }

    public Short getBestSeller()
    {
        return this.bestSeller;
    }

    public void setDiscount( Integer discount )
    {
        this.discount = discount;
    }

    public Integer getDiscount()
    {
        return this.discount;
    }

    public void setIsbn( String isbn )
    {
        this.isbn = isbn;
    }

    public String getIsbn()
    {
        return this.isbn;
    }

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

    public void setTitle( String title )
    {
        this.title = title;
    }

    public String getTitle()
    {
        return this.title;
    }

    public void setPublisher( Publisher publisher )
    {
        this.publisher = publisher;
    }

    public Publisher getPublisher()
    {
        return this.publisher;
    }

    public void setAuthor( Author author )
    {
        this.author = author;
    }

    public Author getAuthor()
    {
        return this.author;
    }

    public String toString()
    {
        StringBuffer sb = new StringBuffer(); 
        sb.append(id); 
        sb.append("|"); 
        sb.append(availability); 
        sb.append( "|" ); 
        sb.append(bestSeller); 
        sb.append( "|" ); 
        sb.append(discount); 
        sb.append( "|" ); 
        sb.append(isbn); 
        sb.append( "|" ); 
        sb.append(price); 
        sb.append( "|" ); 
        sb.append(quantity); 
        sb.append( "|" ); 
        sb.append(title); 
        return sb.toString(); 
    }

}
