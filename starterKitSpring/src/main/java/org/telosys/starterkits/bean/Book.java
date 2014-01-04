package org.telosys.starterkits.bean;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="book", schema="public" )
public class Book implements Serializable
{
    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @Column(name="id", nullable=false)
	@GeneratedValue

    private Integer id;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="availability")

    private Short availability;

    @Column(name="best_seller")

    private Short bestSeller;

    @Column(name="discount")

    private Integer discount;

    @Column(name="isbn", nullable=false, length=13)
    @NotNull
    @Size( min = 1, max = 13 )
    private String isbn;

    @Column(name="price")

    private BigDecimal price;

    @Column(name="quantity")

    private Integer quantity;

    @Column(name="title", length=160)
    @Size( max = 160 )
    private String title;


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @ManyToOne
    @JoinColumn(name="publisher_id", referencedColumnName="code")
    private Publisher publisher;

    @OneToMany(mappedBy="book", targetEntity=Synopsis.class)
    private List<Synopsis> listOfSynopsis;

    @ManyToOne
    @JoinColumn(name="author_id", referencedColumnName="id")
    private Author author;

    @OneToMany(mappedBy="book", targetEntity=Review.class)
    private List<Review> listOfReview;

    @OneToMany(mappedBy="book", targetEntity=BookOrderItem.class)
    private List<BookOrderItem> listOfBookOrderItem;


    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setId( Integer id )
    {
        this.id = id ;
    }

    public Integer getId()
    {
        return this.id;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
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


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
    public void setPublisher( Publisher publisher )
    {
        this.publisher = publisher;
    }
    public Publisher getPublisher()
    {
        return this.publisher;
    }

    public void setListOfSynopsis( List<Synopsis> listOfSynopsis )
    {
        this.listOfSynopsis = listOfSynopsis;
    }
    public List<Synopsis> getListOfSynopsis()
    {
        return this.listOfSynopsis;
    }

    public void setAuthor( Author author )
    {
        this.author = author;
    }
    public Author getAuthor()
    {
        return this.author;
    }

    public void setListOfReview( List<Review> listOfReview )
    {
        this.listOfReview = listOfReview;
    }
    public List<Review> getListOfReview()
    {
        return this.listOfReview;
    }

    public void setListOfBookOrderItem( List<BookOrderItem> listOfBookOrderItem )
    {
        this.listOfBookOrderItem = listOfBookOrderItem;
    }
    public List<BookOrderItem> getListOfBookOrderItem()
    {
        return this.listOfBookOrderItem;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
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
