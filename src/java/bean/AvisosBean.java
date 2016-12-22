/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import jersey.AvisoJersey;
import rest.ejb.Aviso;

/**
 *
 * @author Serg
 */
@ManagedBean
@SessionScoped
public class AvisosBean {

    @ManagedProperty(value = "#{usuarioBean}")
    private UsuarioBean usuarioBean;

    private AvisoJersey avisoJersey;
    private Aviso avisoSeleccionado;
    private String prioridad;
    private String inicioReparacion;
    private String finReparacion;
    private String latitudGPS;
    private String longitudGPS;
    private String error;
    public static final double R = 6372.8;
    private List<Aviso> avisosEnRadio;
    private String latitudGPSOperador;
    private String longitudGPSOperador;

    public String getLatitudGPSOperador() {
        return latitudGPSOperador;
    }

    public void setLatitudGPSOperador(String latitudGPSOperador) {
        this.latitudGPSOperador = latitudGPSOperador;
    }

    public String getLongitudGPSOperador() {
        return longitudGPSOperador;
    }

    public void setLongitudGPSOperador(String longitudGPSOperador) {
        this.longitudGPSOperador = longitudGPSOperador;
    }

    public List<Aviso> getAvisosEnRadio() {
        return avisosEnRadio;
    }

    public void setAvisosEnRadio(List<Aviso> avisosEnRadio) {
        this.avisosEnRadio = avisosEnRadio;
    }

    /**
     * Creates a new instance of listaAvisosBean
     */
    public AvisosBean() {
    }

    @PostConstruct
    public void init() {
        
        avisoJersey = new AvisoJersey();
        error = "";
    }

    public List<Aviso> getListaAvisos() {
        return findAll();
    }

    public Aviso getAvisoSeleccionado() {
        return avisoSeleccionado;
    }

    public void setAvisoSeleccionado(Aviso avisoSeleccionado) {
        this.avisoSeleccionado = avisoSeleccionado;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getInicioReparacion() {
        return inicioReparacion;
    }

    public void setInicioReparacion(String inicioReparacion) {
        this.inicioReparacion = inicioReparacion;
    }

    public String getFinReparacion() {
        return finReparacion;
    }

    public void setFinReparacion(String finReparacion) {
        this.finReparacion = finReparacion;
    }

    public String getLatitudGPS() {
        return latitudGPS;
    }

    public void setLatitudGPS(String latitudGPS) {
        this.latitudGPS = latitudGPS;
    }

    public String getLongitudGPS() {
        return longitudGPS;
    }

    public void setLongitudGPS(String longitudGPS) {
        this.longitudGPS = longitudGPS;
    }

    public String getError() {
        return error;
    }

    public void setUsuarioBean(UsuarioBean usuarioBean) {
        this.usuarioBean = usuarioBean;
    }

    public String doCrear() {
        setAvisoSeleccionado(new Aviso());
        error = "";
        prioridad = null;
        inicioReparacion = null;
        finReparacion = null;
        latitudGPS = null;
        longitudGPS = null;
        return "editarAviso";
    }

    public String doEditar(Aviso aviso) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        formatter.applyPattern("dd-MM-yyyy");

        setAvisoSeleccionado(aviso);
        error = "";

        if (avisoSeleccionado.getPrioridad() != null) {
            prioridad = "" + avisoSeleccionado.getPrioridad();
        } else {
            prioridad = null;
        }

        if (avisoSeleccionado.getInicioReparacion() != null) {
            inicioReparacion = formatter.format(avisoSeleccionado.getInicioReparacion());
        } else {
            inicioReparacion = null;
        }

        if (avisoSeleccionado.getFinReparacion() != null) {
            finReparacion = formatter.format(avisoSeleccionado.getFinReparacion());
        } else {
            finReparacion = null;
        }

        if (avisoSeleccionado.getPosGPS() != null && !avisoSeleccionado.getPosGPS().isEmpty()) {
            String[] posGPS = avisoSeleccionado.getPosGPS().split(";");
            latitudGPS = posGPS[0];
            longitudGPS = posGPS[1];
        } else {
            latitudGPS = null;
            longitudGPS = null;
        }

        return "editarAviso";
    }

    public String doGuardar() {
        if (avisoSeleccionado.getUbicacion() == null || avisoSeleccionado.getUbicacion().trim().isEmpty()) {
            error = "Ubicación inválida";
            return "editarAviso";
        }

        if (avisoSeleccionado.getEstado() == null || avisoSeleccionado.getEstado().trim().isEmpty()) {
            error = "El campo estado no puede estar vacío";
            return "editarAviso";
        }

        if (avisoSeleccionado.getObservaciones() == null || avisoSeleccionado.getObservaciones().trim().isEmpty()) {
            error = "El campo observaciones no puede estar vacío";
            return "editarAviso";
        }

        if (prioridad != null && !prioridad.trim().isEmpty()) {
            try {
                int num = Integer.parseInt(prioridad);
                if (num < 1 || num > 9) {
                    error = "La prioridad debe estar entre 1 y 9";
                    return "editarAviso";
                }
                avisoSeleccionado.setPrioridad(num);
            } catch (NumberFormatException e) {
                error = "La prioridad debe ser un número";
                return "editarAviso";
            }
        } else {
            avisoSeleccionado.setPrioridad(null);
        }

        if (inicioReparacion != null && !inicioReparacion.trim().isEmpty()) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
            formatter.applyPattern("dd-MM-yyyy");
            try {
                avisoSeleccionado.setInicioReparacion(formatter.parse(inicioReparacion));
            } catch (ParseException e) {
                error = "El formato de fecha debe ser dd-MM-yyyy";
                return "editarAviso";
            }
        } else {
            avisoSeleccionado.setInicioReparacion(null);
        }

        if (finReparacion != null && !finReparacion.trim().isEmpty()) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
            formatter.applyPattern("dd-MM-yyyy");
            try {
                avisoSeleccionado.setFinReparacion(formatter.parse(finReparacion));
            } catch (ParseException e) {
                error = "El formato de fecha debe ser dd-MM-yyyy";
                return "editarAviso";
            }
        } else {
            avisoSeleccionado.setFinReparacion(null);
        }

        if (latitudGPS != null && !latitudGPS.trim().isEmpty()) {
            if (longitudGPS == null || longitudGPS.trim().isEmpty()) {
                error = "La longitud no puede estar vacía";
                return "editarAviso";
            }
        } else if (longitudGPS != null && !longitudGPS.trim().isEmpty()) {
            error = "La latitud no puede estar vacía";
            return "editarAviso";
        }

        if ((latitudGPS != null && !latitudGPS.trim().isEmpty())
                && longitudGPS != null && !longitudGPS.trim().isEmpty()) {
            try {
                Double.parseDouble(longitudGPS);
                Double.parseDouble(latitudGPS);
                avisoSeleccionado.setPosGPS(latitudGPS + ";" + longitudGPS);
            } catch (NumberFormatException e) {
                error = "La latitud y longitud deben ser numéricos";
                return "editarAviso";
            }
        } else {
            avisoSeleccionado.setPosGPS(null);
        }

        if (avisoSeleccionado.getId() == null) {
            avisoSeleccionado.setFechacreacion(new Date());
            avisoSeleccionado.setUsuarioemail(usuarioBean.getUsuario());

            create(avisoSeleccionado);
        } else {
            edit(avisoSeleccionado);
        }

        return "listaAvisos?faces-redirect=true";
    }

    public String doBorrar(Aviso aviso) {
        remove(aviso);
        return "listaAvisos";
    }

    public String doVer(Aviso aviso) {
        avisoSeleccionado = aviso;
        return "verAviso";
    }

    public String verOperaciones(Aviso aviso) {
        avisoSeleccionado = aviso;
        return "listaOperaciones";
    }

    private java.util.List<Aviso> findAll() {
        List<Aviso> listaAvisos = null;

        Response r = avisoJersey.findAll(Response.class);
        if (r.getStatus() == 200) {
            GenericType<List<Aviso>> genericType = new GenericType<List<Aviso>>() {
            };
            listaAvisos = r.readEntity(genericType);
        }

        return listaAvisos;
    }

    private void create(Aviso entity) {
        avisoJersey.create_JSON(entity);
    }

    private void remove(Aviso entity) {
        avisoJersey.remove("" + entity.getId());
    }

    private void edit(Aviso entity) {
        avisoJersey.edit_JSON(entity);
    }

    private static double havershine(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return R * c;
    }

    public void enRadio() {
        avisosEnRadio = new ArrayList<>();
        List<Aviso> listaAvisos = this.findAll();
        for (Aviso aviso : listaAvisos) {

            if (aviso.getPosGPS() != null && !aviso.getPosGPS().isEmpty()) {
                String[] posGPS = aviso.getPosGPS().split(";");
                double latitudGPSaviso = Double.parseDouble(posGPS[0]);
                double longitudGPSaviso = Double.parseDouble(posGPS[1]);
                double res = havershine(Double.parseDouble(latitudGPSOperador), Double.parseDouble(longitudGPSOperador), latitudGPSaviso, longitudGPSaviso);
                if (res <= 10) {

                    avisosEnRadio.add(aviso);

                }

            }
        }

    }

    public String doMostrarAvisos() {
        return "listaAvisos";
    }

}
