/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rnu.fst.gestiondedepatement.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author moez
 */
@Entity
@Table(name = "Projet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Projet.findAll", query = "SELECT p FROM Projet p"),
    @NamedQuery(name = "Projet.findById", query = "SELECT p FROM Projet p WHERE p.id = :id"),
    @NamedQuery(name = "Projet.findByNomProjet", query = "SELECT p FROM Projet p WHERE p.nomProjet = :nomProjet"),
    @NamedQuery(name = "Projet.findByDescription", query = "SELECT p FROM Projet p WHERE p.description = :description"),
    @NamedQuery(name = "Projet.findByDatesoutenance", query = "SELECT p FROM Projet p WHERE p.datesoutenance = :datesoutenance"),
    @NamedQuery(name = "Projet.findByDureesoutenance", query = "SELECT p FROM Projet p WHERE p.dureesoutenance = :dureesoutenance"),
    @NamedQuery(name = "Projet.findByRaisonsociale", query = "SELECT p FROM Projet p WHERE p.raisonsociale = :raisonsociale"),
    @NamedQuery(name = "Projet.findByResponsablesociete", query = "SELECT p FROM Projet p WHERE p.responsablesociete = :responsablesociete"),
    @NamedQuery(name = "Projet.findByAdressesociete", query = "SELECT p FROM Projet p WHERE p.adressesociete = :adressesociete"),
    @NamedQuery(name = "Projet.findByTelsociete", query = "SELECT p FROM Projet p WHERE p.telsociete = :telsociete"),
    @NamedQuery(name = "Projet.findByEncadrantsociete", query = "SELECT p FROM Projet p WHERE p.encadrantsociete = :encadrantsociete"),
    @NamedQuery(name = "Projet.findByFonctionecadrant", query = "SELECT p FROM Projet p WHERE p.fonctionecadrant = :fonctionecadrant"),
    @NamedQuery(name = "Projet.findByEmailencadrant", query = "SELECT p FROM Projet p WHERE p.emailencadrant = :emailencadrant"),
    @NamedQuery(name = "Projet.findByTelencadrant", query = "SELECT p FROM Projet p WHERE p.telencadrant = :telencadrant"),
    @NamedQuery(name = "Projet.findByMethodeconception", query = "SELECT p FROM Projet p WHERE p.methodeconception = :methodeconception"),
    @NamedQuery(name = "Projet.findByLangageprog", query = "SELECT p FROM Projet p WHERE p.langageprog = :langageprog"),
    @NamedQuery(name = "Projet.findByOutilprog", query = "SELECT p FROM Projet p WHERE p.outilprog = :outilprog"),
    @NamedQuery(name = "Projet.findByRemuneration", query = "SELECT p FROM Projet p WHERE p.remuneration = :remuneration")})
public class Projet implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 254)
    @Column(name = "nomProjet")
    private String nomProjet;
    @Size(max = 254)
    @Column(name = "description")
    private String description;
    @Column(name = "datesoutenance")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datesoutenance;
    @Size(max = 254)
    @Column(name = "dureesoutenance")
    private String dureesoutenance;
    @Size(max = 254)
    @Column(name = "raisonsociale")
    private String raisonsociale;
    @Size(max = 254)
    @Column(name = "responsablesociete")
    private String responsablesociete;
    @Size(max = 254)
    @Column(name = "adressesociete")
    private String adressesociete;
    @Size(max = 254)
    @Column(name = "telsociete")
    private String telsociete;
    @Size(max = 254)
    @Column(name = "encadrantsociete")
    private String encadrantsociete;
    @Size(max = 254)
    @Column(name = "fonctionecadrant")
    private String fonctionecadrant;
    @Size(max = 254)
    @Column(name = "emailencadrant")
    private String emailencadrant;
    @Size(max = 254)
    @Column(name = "telencadrant")
    private String telencadrant;
    @Size(max = 254)
    @Column(name = "methodeconception")
    private String methodeconception;
    @Size(max = 254)
    @Column(name = "langageprog")
    private String langageprog;
    @Size(max = 254)
    @Column(name = "outilprog")
    private String outilprog;
    @Column(name = "remuneration")
    private Boolean remuneration;
    @JoinColumn(name = "Section_id", referencedColumnName = "id")
    @ManyToOne
    private Section sectionid;
    @JoinColumn(name = "Specialite_id", referencedColumnName = "id")
    @ManyToOne
    private Specialite specialiteid;
    @JoinColumn(name = "Ann_id", referencedColumnName = "id")
    @ManyToOne
    private Anneeuniversitaire annid;
    @JoinColumn(name = "Session_id", referencedColumnName = "id")
    @ManyToOne
    private SessionSoutenance sessionid;
    @JoinColumn(name = "Etu_id2", referencedColumnName = "id")
    @ManyToOne
    private Etudiant etuid2;
    @JoinColumn(name = "Etu_id", referencedColumnName = "id")
    @ManyToOne
    private Etudiant etuid;
    @JoinColumn(name = "codesalle", referencedColumnName = "codesalle")
    @ManyToOne
    private Salle codesalle;
    @JoinColumn(name = "Ens_cin", referencedColumnName = "cin")
    @ManyToOne
    private Enseignant enscin;
    @JoinColumn(name = "Ens_cin2", referencedColumnName = "cin")
    @ManyToOne
    private Enseignant enscin2;
    @JoinColumn(name = "cin", referencedColumnName = "cin")
    @ManyToOne
    private Enseignant cin;

    public Projet() {
    }

    public Projet(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomProjet() {
        return nomProjet;
    }

    public void setNomProjet(String nomProjet) {
        this.nomProjet = nomProjet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDatesoutenance() {
        return datesoutenance;
    }

    public void setDatesoutenance(Date datesoutenance) {
        this.datesoutenance = datesoutenance;
    }

    public String getDureesoutenance() {
        return dureesoutenance;
    }

    public void setDureesoutenance(String dureesoutenance) {
        this.dureesoutenance = dureesoutenance;
    }

    public String getRaisonsociale() {
        return raisonsociale;
    }

    public void setRaisonsociale(String raisonsociale) {
        this.raisonsociale = raisonsociale;
    }

    public String getResponsablesociete() {
        return responsablesociete;
    }

    public void setResponsablesociete(String responsablesociete) {
        this.responsablesociete = responsablesociete;
    }

    public String getAdressesociete() {
        return adressesociete;
    }

    public void setAdressesociete(String adressesociete) {
        this.adressesociete = adressesociete;
    }

    public String getTelsociete() {
        return telsociete;
    }

    public void setTelsociete(String telsociete) {
        this.telsociete = telsociete;
    }

    public String getEncadrantsociete() {
        return encadrantsociete;
    }

    public void setEncadrantsociete(String encadrantsociete) {
        this.encadrantsociete = encadrantsociete;
    }

    public String getFonctionecadrant() {
        return fonctionecadrant;
    }

    public void setFonctionecadrant(String fonctionecadrant) {
        this.fonctionecadrant = fonctionecadrant;
    }

    public String getEmailencadrant() {
        return emailencadrant;
    }

    public void setEmailencadrant(String emailencadrant) {
        this.emailencadrant = emailencadrant;
    }

    public String getTelencadrant() {
        return telencadrant;
    }

    public void setTelencadrant(String telencadrant) {
        this.telencadrant = telencadrant;
    }

    public String getMethodeconception() {
        return methodeconception;
    }

    public void setMethodeconception(String methodeconception) {
        this.methodeconception = methodeconception;
    }

    public String getLangageprog() {
        return langageprog;
    }

    public void setLangageprog(String langageprog) {
        this.langageprog = langageprog;
    }

    public String getOutilprog() {
        return outilprog;
    }

    public void setOutilprog(String outilprog) {
        this.outilprog = outilprog;
    }

    public Boolean getRemuneration() {
        return remuneration;
    }

    public void setRemuneration(Boolean remuneration) {
        this.remuneration = remuneration;
    }

    public Section getSectionid() {
        return sectionid;
    }

    public void setSectionid(Section sectionid) {
        this.sectionid = sectionid;
    }

    public Specialite getSpecialiteid() {
        return specialiteid;
    }

    public void setSpecialiteid(Specialite specialiteid) {
        this.specialiteid = specialiteid;
    }

    public Anneeuniversitaire getAnnid() {
        return annid;
    }

    public void setAnnid(Anneeuniversitaire annid) {
        this.annid = annid;
    }

    public SessionSoutenance getSessionid() {
        return sessionid;
    }

    public void setSessionid(SessionSoutenance sessionid) {
        this.sessionid = sessionid;
    }

    public Etudiant getEtuid2() {
        return etuid2;
    }

    public void setEtuid2(Etudiant etuid2) {
        this.etuid2 = etuid2;
    }

    public Etudiant getEtuid() {
        return etuid;
    }

    public void setEtuid(Etudiant etuid) {
        this.etuid = etuid;
    }

    public Salle getCodesalle() {
        return codesalle;
    }

    public void setCodesalle(Salle codesalle) {
        this.codesalle = codesalle;
    }

    public Enseignant getEnscin() {
        return enscin;
    }

    public void setEnscin(Enseignant enscin) {
        this.enscin = enscin;
    }

    public Enseignant getEnscin2() {
        return enscin2;
    }

    public void setEnscin2(Enseignant enscin2) {
        this.enscin2 = enscin2;
    }

    public Enseignant getCin() {
        return cin;
    }

    public void setCin(Enseignant cin) {
        this.cin = cin;
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
        if (!(object instanceof Projet)) {
            return false;
        }
        Projet other = (Projet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rnu.fst.gestiondedepatement.entity.Projet[ id=" + id + " ]";
    }
    
}
