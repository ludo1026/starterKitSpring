package org.telosys.starterkits.bean;

import java.io.Serializable;

import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;


import java.util.List;
import javax.persistence.*;
import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Persistent class for entity stored in table "employee"
 */
@XmlRootElement  // JAXB annotation for REST Web Services
@XmlAccessorType(XmlAccessType.PROPERTY) // JAXB accessor = getter/setter pair
@Entity
@Table(name="employee", schema="public" )
// Define named queries here
// @NamedQueries ( {
//  @NamedQuery ( name="Employee.query1", query="SELECT x FROM Employee x WHERE  " ),
//  @NamedQuery ( name="Employee.query2", query="SELECT x FROM Employee x WHERE  " )
// } )
public class Employee implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="code", nullable=false, length=4)
    @NotNull
    @Size( max = 4 )
    private String code;

    @Column(name="email", length=320)
    @Size( max = 320 )
    private String email ;

    @Column(name="first_name", length=40)
    @Size( max = 40 )
    private String firstName ;

    @Column(name="last_name", nullable=false, length=40)
    @NotNull
    @Size( max = 40 )
    private String lastName ;

    @Column(name="manager")

    private Short manager ;

    @ManyToOne
    @JoinColumn(name="shop_code", referencedColumnName="code")
    private Shop shop;

    @ManyToOne
    @JoinColumn(name="badge_number", referencedColumnName="badge_number")
    private Badge badge;

	public String getCode() {
        return code;
    }
 
    public void setCode(String code) {
        this.code = code;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    public String getEmail()
    {
        return this.email;
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

    public void setManager( Short manager )
    {
        this.manager = manager;
    }

    public Short getManager()
    {
        return this.manager;
    }

    public void setShop( Shop shop )
    {
        this.shop = shop;
    }

    public Shop getShop()
    {
        return this.shop;
    }

    public void setBadge( Badge badge )
    {
        this.badge = badge;
    }

    public Badge getBadge()
    {
        return this.badge;
    }

    public String toString()
    {
        StringBuffer sb = new StringBuffer(); 
        sb.append(code); 
        sb.append("|"); 
        sb.append(email); 
        sb.append( "|" ); 
        sb.append(firstName); 
        sb.append( "|" ); 
        sb.append(lastName); 
        sb.append( "|" ); 
        sb.append(manager); 
        return sb.toString(); 
    }

}
