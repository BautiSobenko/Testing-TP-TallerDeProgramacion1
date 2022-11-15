package com.grupo8.app.modelo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class Operario implements Serializable {
    private String nombreCompleto;
    private String username;
    private String password;
    private Boolean activo;

    public Operario(String nombreCompleto, String username, String password) {
        this.nombreCompleto = nombreCompleto;
        this.username = username;
        this.password = password;
        this.activo = true;
    }
    
    public static Operario administrador() {
        return new Operario("admin", "admin", "admin1234");
    }
}
