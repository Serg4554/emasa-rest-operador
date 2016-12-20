/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.ejb;

import java.util.Collection;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author nacho
 */
public class Usuario {
    private String email;
    private boolean operador;
    private Collection<Operacion> operacionCollection;
    private Collection<Aviso> avisoCollection;

    public Usuario() {
    }

    public Usuario(String email) {
        this.email = email;
    }

    public Usuario(String email, boolean operador) {
        this.email = email;
        this.operador = operador;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getOperador() {
        return operador;
    }

    public void setOperador(boolean operador) {
        this.operador = operador;
    }

    @XmlTransient
    public Collection<Operacion> getOperacionCollection() {
        return operacionCollection;
    }

    public void setOperacionCollection(Collection<Operacion> operacionCollection) {
        this.operacionCollection = operacionCollection;
    }

    @XmlTransient
    public Collection<Aviso> getAvisoCollection() {
        return avisoCollection;
    }

    public void setAvisoCollection(Collection<Aviso> avisoCollection) {
        this.avisoCollection = avisoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (email != null ? email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rest.Usuario[ email=" + email + " ]";
    }
    
}
