/*
 * Created on 26 nov. 2013 ( Time 16:05:59 )
 * Generated by Telosys Tools Generator ( version 2.0.6 )
 */
// This Bean has a basic Primary Key (not composite) 
package org.telosys.starterkits.bean;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;


import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Persistent class for entity stored in table "AUTHOR"
 */
@XmlRootElement  // JAXB annotation for REST Web Services
@XmlAccessorType(XmlAccessType.PROPERTY) // JAXB accessor = getter/setter pair
@Entity
@Table(name="AUTHOR", schema="public" )
// Define named queries here
// @NamedQueries ( {
//  @NamedQuery ( name="Author.query1", query="SELECT x FROM Author x WHERE  " ),
//  @NamedQuery ( name="Author.query2", query="SELECT x FROM Author x WHERE  " )
// } )
public class Author implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID", nullable=false)
    private Integer id;

    @Column(name="FIRST_NAME", length=40)
    private String firstName ;

    @Column(name="LAST_NAME", length=40)
    private String lastName ;

    @OneToMany(mappedBy="author", targetEntity=Book.class)
    private List<Book> listOfBook;

	public Integer getId() {
        return id;
    }
 
    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstName( String firstName )
    {
        this.firstName = firstName;
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public void setLastName( String lastName )
    {
        this.lastName = lastName;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    @XmlTransient
    public void setListOfBook( List<Book> listOfBook )
    {
        this.listOfBook = listOfBook;
    }

    public List<Book> getListOfBook()
    {
        return this.listOfBook;
    }

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
