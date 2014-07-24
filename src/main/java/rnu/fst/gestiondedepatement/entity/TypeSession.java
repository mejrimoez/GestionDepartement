/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rnu.fst.gestiondedepatement.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author moez
 */
@Entity
@Table(name = "TypeSession")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeSession.findAll", query = "SELECT t FROM TypeSession t"),
    @NamedQuery(name = "TypeSession.findById", query = "SELECT t FROM TypeSession t WHERE t.id = :id"),
    @NamedQuery(name = "TypeSession.findByType", query = "SELECT t FROM TypeSession t WHERE t.type = :type")})
public class TypeSession implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 254)
    @Column(name = "type")
    private String type;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "typid")
    private List<SessionSoutenance> sessionSoutenanceList;

    public TypeSession() {
    }

    public TypeSession(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    public List<SessionSoutenance> getSessionSoutenanceList() {
        return sessionSoutenanceList;
    }

    public void setSessionSoutenanceList(List<SessionSoutenance> sessionSoutenanceList) {
        this.sessionSoutenanceList = sessionSoutenanceList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeSession)) {
            return false;
        }
        TypeSession other = (TypeSession) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return type;
    }
    
}
