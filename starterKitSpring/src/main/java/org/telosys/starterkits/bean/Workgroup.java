package org.telosys.starterkits.bean;

import java.io.Serializable;

import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.Date;

import javax.persistence.*;
import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Persistent class for entity stored in table "workgroup"
 */
@XmlRootElement  // JAXB annotation for REST Web Services
@XmlAccessorType(XmlAccessType.PROPERTY) // JAXB accessor = getter/setter pair
@Entity
@Table(name="workgroup", schema="public" )
// Define named queries here
// @NamedQueries ( {
//  @NamedQuery ( name="Workgroup.query1", query="SELECT x FROM Workgroup x WHERE  " ),
//  @NamedQuery ( name="Workgroup.query2", query="SELECT x FROM Workgroup x WHERE  " )
// } )
public class Workgroup implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id", nullable=false)

    private Short id;

    @Temporal(TemporalType.DATE)
    @Column(name="creation_date", nullable=false)
    @NotNull
    private Date creationDate ;

    @Column(name="description", nullable=false, length=600)
    @NotNull
    @Size( max = 600 )
    private String description ;

    @Column(name="name", nullable=false, length=40)
    @NotNull
    @Size( max = 40 )
    private String name ;

	public Short getId() {
        return id;
    }
 
    public void setId(Short id) {
        this.id = id;
    }

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
