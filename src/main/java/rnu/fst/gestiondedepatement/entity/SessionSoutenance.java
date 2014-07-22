/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rnu.fst.gestiondedepatement.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author moez
 */
@Entity
@Table(name = "SessionSoutenance")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SessionSoutenance.findAll", query = "SELECT s FROM SessionSoutenance s"),
    @NamedQuery(name = "SessionSoutenance.findById", query = "SELECT s FROM SessionSoutenance s WHERE s.id = :id"),
    @NamedQuery(name = "SessionSoutenance.findByDatedebut", query = "SELECT s FROM SessionSoutenance s WHERE s.datedebut = :datedebut"),
    @NamedQuery(name = "SessionSoutenance.findByDuree", query = "SELECT s FROM SessionSoutenance s WHERE s.duree = :duree")})
public class SessionSoutenance implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "datedebut")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datedebut;
    @Column(name = "duree")
    private Integer duree;
    @OneToMany(mappedBy = "sessionid")
    private List<Projet> projetList;
    @JoinColumn(name = "Typ_id2", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Typeprojet typid2;
    @JoinColumn(name = "Sec_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Section secid;
    @JoinColumn(name = "Typ_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TypeSession typid;

    public SessionSoutenance() {
    }

    public SessionSoutenance(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public Integer getDuree() {
        return duree;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    @XmlTransient
    public List<Projet> getProjetList() {
        return projetList;
    }

    public void setProjetList(List<Projet> projetList) {
        this.projetList = projetList;
    }

    public Typeprojet getTypid2() {
        return typid2;
    }

    public void setTypid2(Typeprojet typid2) {
        this.typid2 = typid2;
    }

    public Section getSecid() {
        return secid;
    }

    public void setSecid(Section secid) {
        this.secid = secid;
    }

    public TypeSession getTypid() {
        return typid;
    }

    public void setTypid(TypeSession typid) {
        this.typid = typid;
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
        if (!(object instanceof SessionSoutenance)) {
            return false;
        }
        SessionSoutenance other = (SessionSoutenance) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rnu.fst.gestiondedepatement.entity.SessionSoutenance[ id=" + id + " ]";
    }
    
}
