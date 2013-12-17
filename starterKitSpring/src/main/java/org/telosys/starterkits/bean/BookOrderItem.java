
package org.telosys.starterkits.bean;

import java.io.Serializable;

import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.math.BigDecimal;
import org.telosys.starterkits.bean.BookOrderItemId;

import javax.persistence.*;
import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Persistent class for entity stored in table "book_order_item"
 */
@XmlRootElement  // JAXB annotation for REST Web Services
@XmlAccessorType(XmlAccessType.PROPERTY) // JAXB accessor = getter/setter pair
@Entity
@Table(name="book_order_item", schema="public" )
// Define named queries here
// @NamedQueries ( {
//  @NamedQuery ( name="BookOrderItem.query1", query="SELECT x FROM BookOrderItem x WHERE  " ),
//  @NamedQuery ( name="BookOrderItem.query2", query="SELECT x FROM BookOrderItem x WHERE  " )
// } )
public class BookOrderItem implements Serializable
{
    private static final long serialVersionUID = 1L;

	@EmbeddedId
	@Valid
    private BookOrderItemId id = new BookOrderItemId();

    @Column(name="price", nullable=false)
    @NotNull
    private BigDecimal price ;

    @Column(name="quantity", nullable=false)

    private Integer quantity ;

	public BookOrderItemId getId() {
        return id;
    }
 
    public void setId(BookOrderItemId id) {
        this.id = id;
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

    public void setBook( Book book )
    {
        this.id.setBook(book);
    }

    public Book getBook()
    {
        return this.id.getBook();
    }

    public void setBookOrder( BookOrder bookOrder )
    {
        this.id.setBookOrder(bookOrder);
    }

    public BookOrder getBookOrder()
    {
        return this.id.getBookOrder();
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
        sb.append(price); 
        sb.append( "|" ); 
        sb.append(quantity); 
        return sb.toString(); 
    }

}
