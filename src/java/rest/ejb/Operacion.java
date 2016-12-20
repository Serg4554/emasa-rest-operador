/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.ejb;

import java.util.Date;

/**
 *
 * @author nacho
 */
public class Operacion {
    private Integer id;
    private String descripcion;
    private Date fecha;
    private Aviso avisoId;
    private Usuario usuarioemail;

    public Operacion() {
    }

    public Operacion(Integer id) {
        this.id = id;
    }

    public Operacion(Integer id, String descripcion, Date fecha) {
        this.id = id;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Aviso getAvisoId() {
        return avisoId;
    }

    public void setAvisoId(Aviso avisoId) {
        this.avisoId = avisoId;
    }

    public Usuario getUsuarioemail() {
        return usuarioemail;
    }

    public void setUsuarioemail(Usuario usuarioemail) {
        this.usuarioemail = usuarioemail;
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
        if (!(object instanceof Operacion)) {
            return false;
        }
        Operacion other = (Operacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rest.Operacion[ id=" + id + " ]";
    }
    
}
