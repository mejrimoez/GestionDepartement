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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author moez
 */
@Entity
@Table(name = "Enseignant")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Enseignant.findAll", query = "SELECT e FROM Enseignant e"),
    @NamedQuery(name = "Enseignant.findByCin", query = "SELECT e FROM Enseignant e WHERE e.cin = :cin"),
    @NamedQuery(name = "Enseignant.findByNom", query = "SELECT e FROM Enseignant e WHERE e.nom = :nom"),
    @NamedQuery(name = "Enseignant.findByPrenom", query = "SELECT e FROM Enseignant e WHERE e.prenom = :prenom"),
    @NamedQuery(name = "Enseignant.findByCivilite", query = "SELECT e FROM Enseignant e WHERE e.civilite = :civilite"),
    @NamedQuery(name = "Enseignant.findByEmail", query = "SELECT e FROM Enseignant e WHERE e.email = :email"),
    @NamedQuery(name = "Enseignant.findByTel", query = "SELECT e FROM Enseignant e WHERE e.tel = :tel")})
public class Enseignant implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "cin")
    private String cin;
    @Size(max = 254)
    @Column(name = "nom")
    private String nom;
    @Size(max = 254)
    @Column(name = "prenom")
    private String prenom;
    @Size(max = 254)
    @Column(name = "civilite")
    private String civilite;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 254)
    @Column(name = "email")
    private String email;
    @Size(max = 254)
    @Column(name = "tel")
    private String tel;
    @JoinTable(name = "specialiteenseignant", joinColumns = {
        @JoinColumn(name = "cin", referencedColumnName = "cin")}, inverseJoinColumns = {
        @JoinColumn(name = "id", referencedColumnName = "id")})
    @ManyToMany
    private List<Specialite> specialiteList;
    @OneToMany(mappedBy = "enscin")
    private List<Projet> projetList;
    @OneToMany(mappedBy = "enscin2")
    private List<Projet> projetList1;
    @OneToMany(mappedBy = "cin")
    private List<Projet> projetList2;
    @JoinColumn(name = "id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Grade id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "enseignant")
    private List<Choixenseignant> choixenseignantList;
    @OneToMany(mappedBy = "cin")
    private List<Proposeprojet> proposeprojetList;

    public Enseignant() {
    }

    public Enseignant(String cin) {
        this.cin = cin;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @XmlTransient
    public List<Specialite> getSpecialiteList() {
        return specialiteList;
    }

    public void setSpecialiteList(List<Specialite> specialiteList) {
        this.specialiteList = specialiteList;
    }

    @XmlTransient
    public List<Projet> getProjetList() {
        return projetList;
    }

    public void setProjetList(List<Projet> projetList) {
        this.projetList = projetList;
    }

    @XmlTransient
    public List<Projet> getProjetList1() {
        return projetList1;
    }

    public void setProjetList1(List<Projet> projetList1) {
        this.projetList1 = projetList1;
    }

    @XmlTransient
    public List<Projet> getProjetList2() {
        return projetList2;
    }

    public void setProjetList2(List<Projet> projetList2) {
        this.projetList2 = projetList2;
    }

    public Grade getId() {
        return id;
    }

    public void setId(Grade id) {
        this.id = id;
    }

    @XmlTransient
    public List<Choixenseignant> getChoixenseignantList() {
        return choixenseignantList;
    }

    public void setChoixenseignantList(List<Choixenseignant> choixenseignantList) {
        this.choixenseignantList = choixenseignantList;
    }

    @XmlTransient
    public List<Proposeprojet> getProposeprojetList() {
        return proposeprojetList;
    }

    public void setProposeprojetList(List<Proposeprojet> proposeprojetList) {
        this.proposeprojetList = proposeprojetList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cin != null ? cin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Enseignant)) {
            return false;
        }
        Enseignant other = (Enseignant) object;
        if ((this.cin == null && other.cin != null) || (this.cin != null && !this.cin.equals(other.cin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nom + " " + prenom;
    }
    
}
