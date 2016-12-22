/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import jersey.UsuarioJersey;
import rest.ejb.Usuario;

/**
 *
 * @author Serg
 */
@ManagedBean
@SessionScoped
public class UsuarioBean {
    private UsuarioJersey usuarioJersey;
    private Usuario usuario;
    
    /**
     * Creates a new instance of UsuarioBean
     */
    public UsuarioBean() {
    }
    
    @PostConstruct
    public void init() {
        usuarioJersey = new UsuarioJersey();
        usuario = find("Ejemplo@correo.com");
    }

    public Usuario getUsuario() {
        return usuario;
    }

    private Usuario find(String id) {
        Usuario auxUser = null;
        
        Response r = usuarioJersey.find_JSON(Response.class, id);
        if(r.getStatus() == 200) {
            GenericType<Usuario> genericType = new GenericType<Usuario>(){};
            auxUser = r.readEntity(genericType);
        }
        
        return auxUser;
    }
    
}
