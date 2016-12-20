/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.ejb;

import java.util.Collection;
import java.util.Date;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author nacho
 */
public class Aviso {
    private Integer id;
    private Date fechacreacion;
    private String ubicacion;
    private String estado;
    private String observaciones;
    private String ubicacionTecnica;
    private Integer prioridad;
    private Date inicioReparacion;
    private Date finReparacion;
    private String posGPS;
    private String tipo;
    private Collection<Operacion> operacionCollection;
    private Collection<Aviso> avisoCollection;
    private Aviso avisoIdDuplicado;
    private Usuario usuarioemail;

    public Aviso() {
    }

    public Aviso(Integer id) {
        this.id = id;
    }

    public Aviso(Integer id, Date fechacreacion, String ubicacion, String estado, String observaciones) {
        this.id = id;
        this.fechacreacion = fechacreacion;
        this.ubicacion = ubicacion;
        this.estado = estado;
        this.observaciones = observaciones;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getUbicacionTecnica() {
        return ubicacionTecnica;
    }

    public void setUbicacionTecnica(String ubicacionTecnica) {
        this.ubicacionTecnica = ubicacionTecnica;
    }

    public Integer getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }

    public Date getInicioReparacion() {
        return inicioReparacion;
    }

    public void setInicioReparacion(Date inicioReparacion) {
        this.inicioReparacion = inicioReparacion;
    }

    public Date getFinReparacion() {
        return finReparacion;
    }

    public void setFinReparacion(Date finReparacion) {
        this.finReparacion = finReparacion;
    }

    public String getPosGPS() {
        return posGPS;
    }

    public void setPosGPS(String posGPS) {
        this.posGPS = posGPS;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public Aviso getAvisoIdDuplicado() {
        return avisoIdDuplicado;
    }

    public void setAvisoIdDuplicado(Aviso avisoIdDuplicado) {
        this.avisoIdDuplicado = avisoIdDuplicado;
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
        if (!(object instanceof Aviso)) {
            return false;
        }
        Aviso other = (Aviso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rest.Aviso[ id=" + id + " ]";
    }
    
}
