/*
 * Created on 2 janv. 2014 ( Time 18:16:49 )
 * Generated by Telosys Tools Generator ( version 2.1.0 )
 */
// This Bean has a basic Primary Key (not composite) 
/******
keyAttributes size = 1
nonKeyAttributes size = 2
bigList size = 3
. id - Integer
. firstName - String
. lastName - String
******/
package org.telosys.starterkits.bean;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Persistent class for entity stored in table "author"
 *
 * @author Telosys Tools Generator
 *
 */
@XmlRootElement  // JAXB annotation for REST Web Services
@XmlAccessorType(XmlAccessType.PROPERTY) // JAXB accessor = getter/setter pair
@Entity
@Table(name="author", schema="public" )
public class Author implements Serializable
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
    @Column(name="first_name", length=40)
    @Size( max = 40 )
    private String firstName;

    @Column(name="last_name", length=40)
    @Size( max = 40 )
    private String lastName;



    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @OneToMany(mappedBy="author", targetEntity=Book.class)
    private List<Book> listOfBook;

    
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
    //--- DATABASE MAPPING : first_name ( varchar ) 
    public void setFirstName( String firstName )
    {
        this.firstName = firstName;
    }
    public String getFirstName()
    {
        return this.firstName;
    }

    //--- DATABASE MAPPING : last_name ( varchar ) 
    public void setLastName( String lastName )
    {
        this.lastName = lastName;
    }
    public String getLastName()
    {
        return this.lastName;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
    @XmlTransient
    public void setListOfBook( List<Book> listOfBook )
    {
        this.listOfBook = listOfBook;
    }
    public List<Book> getListOfBook()
    {
        return this.listOfBook;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString()
    {
        StringBuffer sb = new StringBuffer(); 
        sb.append(id); 
        sb.append("|"); 
        sb.append(firstName); 
        sb.append( "|" ); 
        sb.append(lastName); 
        return sb.toString(); 
    }

}
