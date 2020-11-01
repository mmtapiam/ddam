package com.mariotm.ddamq.tablas;

import java.io.Serializable;

public class Usuarios implements Serializable {

    private Integer id;
    private String nombre;
    private String perfil;
    private String usuario;
    private String contraseña;

    public Usuarios(Integer id, String nombre, String perfil, String usuario, String contraseña) {
        this.id = id;
        this.nombre = nombre;
        this.perfil = perfil;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public Usuarios() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

}
