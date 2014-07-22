/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rnu.fst.gestiondedepatement.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author moez
 */
@Embeddable
public class ChoixprojetproposePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "Etu_id")
    private int etuid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private int id;

    public ChoixprojetproposePK() {
    }

    public ChoixprojetproposePK(int etuid, int id) {
        this.etuid = etuid;
        this.id = id;
    }

    public int getEtuid() {
        return etuid;
    }

    public void setEtuid(int etuid) {
        this.etuid = etuid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) etuid;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChoixprojetproposePK)) {
            return false;
        }
        ChoixprojetproposePK other = (ChoixprojetproposePK) object;
        if (this.etuid != other.etuid) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rnu.fst.gestiondedepatement.entity.ChoixprojetproposePK[ etuid=" + etuid + ", id=" + id + " ]";
    }
    
}
