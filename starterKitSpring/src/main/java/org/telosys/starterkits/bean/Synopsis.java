/*
 * Created on 3 janv. 2014 ( Time 22:37:44 )
 * Generated by Telosys Tools Generator ( version 2.1.0 )
 */
// This Bean has a basic Primary Key (not composite) 
/******
keyAttributes size = 1
nonKeyAttributes size = 1
bigList size = 2
. bookId - Integer
. synopsis - String
******/
package org.telosys.starterkits.bean;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Persistent class for entity stored in table "synopsis"
 *
 * @author Telosys Tools Generator
 *
 */
@XmlRootElement  // JAXB annotation for REST Web Services
@XmlAccessorType(XmlAccessType.PROPERTY) // JAXB accessor = getter/setter pair
@Entity
@Table(name="synopsis", schema="public" )
public class Synopsis implements Serializable
{
    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @Column(name="book_id", nullable=false)
    @NotNull
    private Integer bookId;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="synopsis", length=32700)
    @Size( max = 32700 )
    private String synopsis;



    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @ManyToOne
    @JoinColumn(name="book_id", referencedColumnName="id", insertable=false, updatable=false)
    private Book book;

    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setBookId( Integer bookId )
    {
        this.bookId = bookId ;
    }
    public Integer getBookId()
    {
        return this.bookId;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : synopsis ( varchar ) 
    public void setSynopsis( String synopsis )
    {
        this.synopsis = synopsis;
    }
    public String getSynopsis()
    {
        return this.synopsis;
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


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString()
    {
        StringBuffer sb = new StringBuffer(); 
        sb.append(bookId); 
        sb.append("|"); 
        sb.append(synopsis); 
        return sb.toString(); 
    }

}
