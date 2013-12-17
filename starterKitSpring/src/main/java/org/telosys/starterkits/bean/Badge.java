package org.telosys.starterkits.bean;

import java.io.Serializable;

import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.Date;

import java.util.List;
import javax.persistence.*;
import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Persistent class for entity stored in table "badge"
 */
@XmlRootElement  // JAXB annotation for REST Web Services
@XmlAccessorType(XmlAccessType.PROPERTY) // JAXB accessor = getter/setter pair
@Entity
@Table(name="badge", schema="public" )
// Define named queries here
// @NamedQueries ( {
//  @NamedQuery ( name="Badge.query1", query="SELECT x FROM Badge x WHERE  " ),
//  @NamedQuery ( name="Badge.query2", query="SELECT x FROM Badge x WHERE  " )
// } )
public class Badge implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="badge_number", nullable=false)

    private Integer badgeNumber;

    @Column(name="authorization_level", nullable=false)

    private Short authorizationLevel ;

    @Temporal(TemporalType.DATE)
    @Column(name="end_of_validity")

    private Date endOfValidity ;

	public Integer getBadgeNumber() {
        return badgeNumber;
    }
 
    public void setBadgeNumber(Integer badgeNumber) {
        this.badgeNumber = badgeNumber;
    }

    public void setAuthorizationLevel( Short authorizationLevel )
    {
        this.authorizationLevel = authorizationLevel;
    }

    public Short getAuthorizationLevel()
    {
        return this.authorizationLevel;
    }

    public void setEndOfValidity( Date endOfValidity )
    {
        this.endOfValidity = endOfValidity;
    }

    public Date getEndOfValidity()
    {
        return this.endOfValidity;
    }

    public String toString()
    {
        StringBuffer sb = new StringBuffer(); 
        sb.append(badgeNumber); 
        sb.append("|"); 
        sb.append(authorizationLevel); 
        sb.append( "|" ); 
        sb.append(endOfValidity); 
        return sb.toString(); 
    }

}
