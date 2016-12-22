/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import jersey.OperacionJersey;
import rest.ejb.Aviso;
import rest.ejb.Operacion;

/**
 *
 * @author Serg
 */
@ManagedBean
@SessionScoped
public class OperacionesBean {
    
    @ManagedProperty(value="#{usuarioBean}")
    private UsuarioBean usuarioBean;
    @ManagedProperty(value="#{avisosBean}")
    private AvisosBean avisosBean;
    
    private OperacionJersey operacionJersey;
    private String error;
    private Operacion operacionSeleccionada;
    private String fecha;

    /**
     * Creates a new instance of listaOperacionesBean
     */
    public OperacionesBean() {
    }
    
    @PostConstruct
    public void init() {
        operacionJersey = new OperacionJersey();
        error = "";
    }

    public String getError() {
        return error;
    }

    public Operacion getOperacionSeleccionada() {
        return operacionSeleccionada;
    }

    public void setOperacionSeleccionada(Operacion operacionSeleccionada) {
        this.operacionSeleccionada = operacionSeleccionada;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public List<Operacion> getListaOperaciones(Aviso aviso) {
        return findListaOperaciones(aviso.getId());
    }

    public void setUsuarioBean(UsuarioBean usuarioBean) {
        this.usuarioBean = usuarioBean;
    }

    public void setAvisosBean(AvisosBean avisosBean) {
        this.avisosBean = avisosBean;
    }
    
    public String doVer(Operacion operacion) {
        this.operacionSeleccionada = operacion;
        return "verOperacion";
    }
    
    public String doVolver() {
        return "listaOperaciones";
    }
    
    public String doCrear() {
        error = "";
        operacionSeleccionada = new Operacion();
        fecha = null;
        return "editarOperacion";
    }
    
    public String doEditar(Operacion operacion) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        formatter.applyPattern("dd-MM-yyyy");
        
        error = "";
        operacionSeleccionada = operacion;
        
        if(operacionSeleccionada.getFecha() != null) {
            fecha = formatter.format(operacionSeleccionada.getFecha());
        } else {
            fecha = null;
        }
        return "editarOperacion";
    }
    
    public String doGuardar() {
        if(operacionSeleccionada.getDescripcion() == null || operacionSeleccionada.getDescripcion().trim().isEmpty()) {
            error = "La descripción no puede estar vacía";
            return "editarOperacion";
        }
        
        if(fecha != null && !fecha.trim().isEmpty()) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
            formatter.applyPattern("dd-MM-yyyy");
            try {
                Date date;
                date = formatter.parse(fecha);
                operacionSeleccionada.setFecha(date);
            } catch (ParseException ex) {
                Logger.getLogger(OperacionesBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            error = "La fecha no puede estar vacía";
            return "editarOperacion";
        }
        
        if(operacionSeleccionada.getId() == null) {
            operacionSeleccionada.setUsuarioemail(usuarioBean.getUsuario());
            operacionSeleccionada.setAvisoId(avisosBean.getAvisoSeleccionado());
            
            create(operacionSeleccionada);
        } else {
            edit(operacionSeleccionada);
        }
        
        return "listaOperaciones";
    }

    public String doBorrar(Operacion operacion) {
        remove(operacion);
        return "listaOperaciones";
    }
    
    private java.util.List<Operacion> findListaOperaciones(int id) {
        List<Operacion> listaOperaciones = null;
        
        Response r = operacionJersey.findByIdAviso_JSON(Response.class, "" + id);
        if (r.getStatus() == 200) {
            GenericType<List<Operacion>> genericType = new GenericType<List<Operacion>>() {
            };
            listaOperaciones = r.readEntity(genericType);
        }
        
        return listaOperaciones;
    }

    private void create(Operacion entity) {
        operacionJersey.create_JSON(entity);
    }

    private void edit(Operacion entity) {
        operacionJersey.edit_JSON(entity);
    }

    private void remove(Operacion entity) {
        operacionJersey.remove(""+entity.getId());
    }
    
}
