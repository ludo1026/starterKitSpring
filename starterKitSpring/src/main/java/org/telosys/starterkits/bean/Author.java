package org.telosys.starterkits.bean;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.*;

import java.util.List;

import javax.persistence.*;

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


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
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
