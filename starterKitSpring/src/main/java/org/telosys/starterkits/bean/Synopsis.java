package org.telosys.starterkits.bean;

import java.io.Serializable;

import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;


import javax.persistence.*;
import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Persistent class for entity stored in table "synopsis"
 */
@XmlRootElement  // JAXB annotation for REST Web Services
@XmlAccessorType(XmlAccessType.PROPERTY) // JAXB accessor = getter/setter pair
@Entity
@Table(name="synopsis", schema="public" )
// Define named queries here
// @NamedQueries ( {
//  @NamedQuery ( name="Synopsis.query1", query="SELECT x FROM Synopsis x WHERE  " ),
//  @NamedQuery ( name="Synopsis.query2", query="SELECT x FROM Synopsis x WHERE  " )
// } )
public class Synopsis implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="book_id", nullable=false)

    private Integer bookId;

    @Column(name="synopsis", length=32700)
    @Size( max = 32700 )
    private String synopsis ;

	public Integer getBookId() {
        return bookId;
    }
 
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public void setSynopsis( String synopsis )
    {
        this.synopsis = synopsis;
    }

    public String getSynopsis()
    {
        return this.synopsis;
    }

    public String toString()
    {
        StringBuffer sb = new StringBuffer(); 
        sb.append(bookId); 
        sb.append("|"); 
        sb.append(synopsis); 
        return sb.toString(); 
    }

}
