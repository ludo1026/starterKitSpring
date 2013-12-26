package org.telosys.starterkits.bean;

import java.io.Serializable;

import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;


import javax.persistence.*;
import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Persistent class for entity stored in table "author"
 */
@XmlRootElement  // JAXB annotation for REST Web Services
@XmlAccessorType(XmlAccessType.PROPERTY) // JAXB accessor = getter/setter pair
@Entity
@Table(name="author", schema="public" )
// Define named queries here
// @NamedQueries ( {
//  @NamedQuery ( name="Author.query1", query="SELECT x FROM Author x WHERE  " ),
//  @NamedQuery ( name="Author.query2", query="SELECT x FROM Author x WHERE  " )
// } )
public class Author implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id", nullable=false)

    private Integer id;

    @Column(name="first_name", length=40)
    @Size( max = 40 )
    private String firstName ;

    @Column(name="last_name", length=40)
    @Size( max = 40 )
    private String lastName ;

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
