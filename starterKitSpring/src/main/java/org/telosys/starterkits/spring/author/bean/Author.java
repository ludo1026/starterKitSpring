/*
 * Dto class 
 * Created on 22 mai 2013 ( Time 15:26:18 )
 */
// This Bean has a basic Primary Key (not composite) 

package org.telosys.starterkits.spring.author.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="AUTHOR", schema="public" )
public class Author implements Serializable
{
    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID", nullable=false)
    private Integer        id           ;


    //----------------------------------------------------------------------
    // ENTITY FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="FIRST_NAME", length=40)
    private String     firstName    ;
    @Column(name="LAST_NAME", length=40)
    private String     lastName     ;

    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public Author()
    {
        super();
    }
    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setId( Integer value )
    {
        this.id = value;
    }
    public Integer getId()
    {
        return this.id;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABSE MAPPING : FIRST_NAME ( VARCHAR ) 
    public void setFirstName( String value )
    {
        this.firstName = value;
    }
    public String getFirstName()
    {
        return this.firstName;
    }

    //--- DATABSE MAPPING : LAST_NAME ( VARCHAR ) 
    public void setLastName( String value )
    {
        this.lastName = value;
    }
    public String getLastName()
    {
        return this.lastName;
    }


    
    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------


}
