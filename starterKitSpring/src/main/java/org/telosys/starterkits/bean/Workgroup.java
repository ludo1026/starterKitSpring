package org.telosys.starterkits.bean;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.*;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="workgroup", schema="public" )
public class Workgroup implements Serializable
{
    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @Column(name="id", nullable=false)
	@GeneratedValue

    private Short id;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Temporal(TemporalType.DATE)
    @Column(name="creation_date", nullable=false)
    @NotNull
    private Date creationDate;

    @Column(name="description", nullable=false, length=600)
    @NotNull
    @Size( min = 1, max = 600 )
    private String description;

    @Column(name="name", nullable=false, length=40)
    @NotNull
    @Size( min = 1, max = 40 )
    private String name;


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setId( Short id )
    {
        this.id = id ;
    }

    public Short getId()
    {
        return this.id;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    public void setCreationDate( Date creationDate )
    {
        this.creationDate = creationDate;
    }
    public Date getCreationDate()
    {
        return this.creationDate;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }
    public String getDescription()
    {
        return this.description;
    }

    public void setName( String name )
    {
        this.name = name;
    }
    public String getName()
    {
        return this.name;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString()
    {
        StringBuffer sb = new StringBuffer(); 
        sb.append(id); 
        sb.append("|"); 
        sb.append(creationDate); 
        sb.append( "|" ); 
        sb.append(description); 
        sb.append( "|" ); 
        sb.append(name); 
        return sb.toString(); 
    }

}
