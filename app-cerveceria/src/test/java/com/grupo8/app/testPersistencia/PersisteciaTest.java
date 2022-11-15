package com.grupo8.app.testPersistencia;

import com.grupo8.app.modelo.Empresa;
import com.grupo8.app.negocio.GestionDeProductos;
import com.grupo8.app.wrappers.ProductoWrapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.HashSet;

import static org.junit.Assert.*;

public class PersisteciaTest {

    ProductoWrapper setProductosAnterior;
    GestionDeProductos gestionDeProductos;
    ProductoWrapper productosExpected;

    @Before
    public void setUp() {

        ProductoWrapper productoWrapper = new ProductoWrapper();
        productoWrapper.setProductos( new HashSet<>() );

        this.productosExpected = productoWrapper;

        this.setProductosAnterior = Empresa.getEmpresa().getProductos();

        Empresa.getEmpresa().setProductos(productoWrapper);

        gestionDeProductos = new GestionDeProductos();

        File archivo = new File("productos.xml");
        if( archivo.exists() )
            archivo.delete();

    }

    @After
    public void tearDown() {
        Empresa.getEmpresa().setProductos(this.setProductosAnterior);
    }

    @Test
    public void crearArchivoTest() {
        gestionDeProductos.persistir();
        File archivo = new File("productos.xml");

        assertTrue( "El archivo deberia existir" , archivo.exists() );
    }

    @Test
    public void despersistenciaTest() {

        gestionDeProductos.persistir();

        Empresa.getEmpresa().cargarProductos();

        ProductoWrapper productosDespersistidos = Empresa.getEmpresa().getProductos();

        assertEquals("Error en la despersistencia", productosDespersistidos.getProductos().size() , this.productosExpected.getProductos().size());

    }
}
