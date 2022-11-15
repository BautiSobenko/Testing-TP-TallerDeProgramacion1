package com.grupo8.app.modelo;

import com.grupo8.app.excepciones.CredencialesInvalidasException;
import com.grupo8.app.persistencia.Ipersistencia;
import com.grupo8.app.persistencia.PersistenciaXML;
import com.grupo8.app.wrappers.*;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

@Getter
@Setter
public class Empresa {

    private static Empresa empresa;
    private String nombre;
    private MozoWrapper mozos;
    private MesasWrapper mesas;
    private ProductoWrapper productos;
    private OperariosWrapper operarios;
    private ComandasWrapper comandas;
    private CierreComandaWrapper cierreComandas;
    private PromocionesFijasWrapper promocionesFijas;
    private PromocionesTemporalesWrapper promocionesTemporales;
    private Operario usuarioLogueado;
    private Float sueldoBase;


    public static Empresa getEmpresa() {
        if (empresa == null) {
            empresa = new Empresa();
        }
        return empresa;
    }

    private Empresa() {
        comandas = new ComandasWrapper();
        comandas.setComandas(new ArrayList<>());
        cargarMesas();
        cargarMozos();
        cargarProductos();
        cargarOperarios();
        cargarComandasCerradas();
        cargarComandas();
        cargarPromocionesFijas();
        cargarPromocionesTemporales();
        this.sueldoBase = 10000F;
    }

    private void cargarOperarios() {
        Ipersistencia<OperariosWrapper> persistencia = new PersistenciaXML();

        try { // cargar los datos de la agencia desde el archivo XML
            persistencia.abrirInput("./operarios.xml");
            this.operarios = persistencia.lee();
            if (operarios == null) {
                operarios = new OperariosWrapper();
                operarios.setOperarios(new HashSet<>());
                operarios.getOperarios().add(Operario.administrador()); //cargar admin por default
                persistencia.escribir(operarios);
            }
            persistencia.escribir(operarios);
            persistencia.cerrarInput();
        } catch (Exception err) {
            operarios = new OperariosWrapper();
            operarios.setOperarios(new HashSet<>());
            operarios.getOperarios().add(Operario.administrador()); //cargar admin por default
            try {
                persistencia.abrirOutput("operarios.xml");
                persistencia.escribir(operarios);
            } catch (IOException e) {}
        }
    }

    private void cargarComandasCerradas() {
        Ipersistencia<CierreComandaWrapper> persistencia = new PersistenciaXML();

        try { // cargar los datos de la agencia desde el archivo XML
            persistencia.abrirInput("./cierres.xml");
            this.cierreComandas = persistencia.lee();
            if (cierreComandas == null) {
                cierreComandas = new CierreComandaWrapper();
                cierreComandas.setCierreComandas(new ArrayList<>());
            }
            persistencia.cerrarInput();
        } catch (Exception err) {
            cierreComandas = new CierreComandaWrapper();
            cierreComandas.setCierreComandas(new ArrayList<>());
            try {
                persistencia.abrirOutput("./cierres.xml");
                persistencia.escribir(cierreComandas);
            } catch (IOException e) {}
        }
    }

    private void cargarPromocionesFijas() {
        Ipersistencia<PromocionesFijasWrapper> persistencia = new PersistenciaXML();

        try { // cargar los datos de la agencia desde el archivo XML
            persistencia.abrirInput("./promocionesFijas.xml");
            this.promocionesFijas = persistencia.lee();
            if (promocionesFijas == null) {
                promocionesFijas = new PromocionesFijasWrapper();
                promocionesFijas.setPromocionesFijas(new HashSet<>());
            }

            persistencia.cerrarInput();
        } catch (Exception err) {
            promocionesFijas = new PromocionesFijasWrapper();
            promocionesFijas.setPromocionesFijas(new HashSet<>());
            try {
                persistencia.abrirOutput("./promocionesFijas.xml");
                persistencia.escribir(promocionesFijas);
            } catch (IOException e) {}
        }
    }

    private void cargarPromocionesTemporales() {
        Ipersistencia<PromocionesTemporalesWrapper> persistencia = new PersistenciaXML();

        try { //cargar los datos de la agencia desde el archivo XML
            persistencia.abrirInput("./promocionesTemporales.xml");
            this.promocionesTemporales = persistencia.lee();
            if (promocionesTemporales == null) {
                promocionesTemporales = new PromocionesTemporalesWrapper();
                promocionesTemporales.setPromocionesTemporales(new HashSet<>());
            }

            persistencia.cerrarInput();
        } catch (Exception err) {
            promocionesTemporales = new PromocionesTemporalesWrapper();
            promocionesTemporales.setPromocionesTemporales(new HashSet<>());
            try {
                persistencia.abrirOutput("./promocionesTemporales.xml");
                persistencia.escribir(promocionesTemporales);
            } catch (IOException e) {}
        }
    }

    private void cargarProductos() {
        Ipersistencia<ProductoWrapper> persistencia = new PersistenciaXML();

        try { // cargar los datos de la agencia desde el archivo XML
            persistencia.abrirInput("./productos.xml");
            this.productos = persistencia.lee();
            if (productos == null) {
                productos = new ProductoWrapper();
                productos.setProductos(new HashSet<>());
            }
            persistencia.cerrarInput();
        } catch (Exception err) {
            productos = new ProductoWrapper();
            productos.setProductos(new HashSet<>());
        }
    }

    private void cargarMesas() {
        Ipersistencia<MesasWrapper> persistencia = new PersistenciaXML();

        try { // cargar los datos de la agencia desde el archivo XML
            persistencia.abrirInput("./mesas.xml");
            this.mesas = persistencia.lee();
            if (mesas == null) {
                mesas = new MesasWrapper();
                mesas.setMesas(new HashSet<>());
            }
            persistencia.cerrarInput();
        } catch (Exception err) {
            mesas = new MesasWrapper();
            mesas.setMesas(new HashSet<>());
        }
    }

    private void cargarMozos() {
        Ipersistencia<MozoWrapper> persistencia = new PersistenciaXML();

        try { // cargar los datos de la agencia desde el archivo XML
            persistencia.abrirInput("./mozos.xml");
            this.mozos = persistencia.lee();
            if (mozos == null) {
                mozos = new MozoWrapper();
                mozos.setMozos(new HashSet<>());
            }
            persistencia.cerrarInput();
        } catch (Exception err) {
            mozos = new MozoWrapper();
            mozos.setMozos(new HashSet<>());
        }
    }
    public void cargarComandas() {
        Ipersistencia<ComandasWrapper> persistencia = new PersistenciaXML();

        try { // cargar las comandas desde el archivo XML
            persistencia.abrirInput("./comandas.xml");
            this.comandas = persistencia.lee();
            if (comandas == null) {
                comandas = new ComandasWrapper();
                comandas.setComandas(new ArrayList<>());
            }
            persistencia.cerrarInput();
        } catch (Exception err) {
            comandas = new ComandasWrapper();
            comandas.setComandas(new ArrayList<>());
        }
    }

    public void cargarSueldo() {
        Ipersistencia<Float> persistencia = new PersistenciaXML();

        try {
            persistencia.abrirInput("./sueldo.xml");
            this.sueldoBase = persistencia.lee();
            if (sueldoBase == null) {
                sueldoBase = 10000f;
                persistirSueldo();
            }
            persistencia.cerrarInput();

        } catch (Exception err) {
            sueldoBase = 10000f;
            try {
                persistencia.abrirOutput("./sueldo.xml");
                persistencia.escribir(sueldoBase);
            } catch (IOException e) {}
        }
    }

    public void persistirSueldo() {
        Ipersistencia<Float> persistencia = new PersistenciaXML();

        try {
            persistencia.abrirOutput("./sueldo.xml");
            persistencia.escribir(sueldoBase);
        } catch (IOException e) {}
    }


    public Operario login(String username, String password) throws CredencialesInvalidasException {
        Optional<Operario> logueado = operarios.getOperarios().stream()
                .filter(operario -> operario.getPassword().equals(password) && operario.getUsername().equals(username)).findFirst();

        if (logueado.isPresent()) {
            usuarioLogueado = logueado.get();
        } else {
            throw new CredencialesInvalidasException("Credenciales invalidas");
        }

        return usuarioLogueado;
    }

    public void logout() {
        usuarioLogueado = null;
    }
}
