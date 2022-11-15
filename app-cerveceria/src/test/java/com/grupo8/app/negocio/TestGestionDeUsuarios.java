package com.grupo8.app.negocio;

import com.grupo8.app.dto.AddMozoRequest;
import com.grupo8.app.dto.AddOperarioRequest;
import com.grupo8.app.dto.MozoDTO;
import com.grupo8.app.excepciones.CredencialesInvalidasException;
import com.grupo8.app.excepciones.PermisoDenegadoException;
import com.grupo8.app.modelo.Empresa;
import com.grupo8.app.modelo.Mozo;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;

public class TestGestionDeUsuarios {
    private GestionDeUsuarios gestionDeUsuarios;

    public TestGestionDeUsuarios() {
        this.gestionDeUsuarios = new GestionDeUsuarios();
    }


    @Test
    public void testLoginExito() throws CredencialesInvalidasException {
        this.gestionDeUsuarios.login("admin", "admin1234");
        Assert.assertEquals("admin", Empresa.getEmpresa().getUsuarioLogueado().getUsername());
    }

    @Test
    public void testLoginFallo() {
        try {
            this.gestionDeUsuarios.login("admin", "admin12345");
            Assert.fail(); // Si llega aca es porque no tiro la excepcion
        } catch (CredencialesInvalidasException e) {
            Assert.assertEquals("Credenciales invalidas", e.getMessage());
        }
    }

    @Test
    public void testLogout() throws CredencialesInvalidasException {
        this.gestionDeUsuarios.login("admin", "admin1234");
        this.gestionDeUsuarios.logout();
        Assert.assertNull(Empresa.getEmpresa().getUsuarioLogueado());
    }

    @Test
    public void addOperarioExito() throws PermisoDenegadoException, CredencialesInvalidasException {
        this.gestionDeUsuarios.login("admin", "admin1234");

        this.gestionDeUsuarios.addOperario(new AddOperarioRequest("[TEST] Juan Perez", "jperez", "jperez1234"));
    }

    @Test
    public void addOperarioFallo() throws PermisoDenegadoException {
        //No hay usuario logueado
        this.gestionDeUsuarios.addOperario(new AddOperarioRequest("[TEST] Juan Perez", "jperez", "jperez1234"));
    }

    @Test
    public void addMozoExito() {
        MozoDTO resultado = this.gestionDeUsuarios.addMozo(new AddMozoRequest("[TEST] Juan Perez", Date.valueOf(LocalDate.of(1989,10,17)), 2));
        Assert.assertNotNull(resultado);
        this.gestionDeUsuarios.deleteMozoPorNombre("[TEST]");
    }

    @Test
    public void addMozoFallo() {
        this.gestionDeUsuarios.addMozo(new AddMozoRequest("[TEST] Juan Perez", null, 2));
    }

    @Test
    public void testMozos() {
        Assert.assertNotNull(
                gestionDeUsuarios.obtenerMozos()
        );
    }

    @Test
    public void testOperarios() {
        Assert.assertNotNull(
                gestionDeUsuarios.obtenerOperarios()
        );
    }
}
