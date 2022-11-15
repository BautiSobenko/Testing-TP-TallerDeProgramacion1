package com.grupo8.app.testCajaNegra;

import com.grupo8.app.dto.AddProductoRequest;
import com.grupo8.app.dto.ProductoDTO;
import com.grupo8.app.dto.PromoFijaRequest;
import com.grupo8.app.excepciones.MalaSolicitudException;
import com.grupo8.app.negocio.GestionDeProductos;
import com.grupo8.app.negocio.GestionDePromos;
import com.grupo8.app.testCajaNegra.escenarios.Escenario1;
import com.grupo8.app.testCajaNegra.escenarios.Escenario2;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class agregarPromocionFijaTest {

    private Escenario1 escenarioListaVacia;
    private Escenario2 escenarioListaNoVacia;
    private GestionDePromos gestionDePromos;

    @Before
    public void setUp() throws Exception {
        escenarioListaVacia = new Escenario1();
        escenarioListaNoVacia = new Escenario2();
        gestionDePromos = new GestionDePromos();
    }

    public void tearDown1() {
        escenarioListaVacia.borraEscenario();
    }

    public void tearDown2() {
        escenarioListaNoVacia.borraEscenario();
    }

    private ProductoDTO crearProducto() {
        AddProductoRequest producto = new AddProductoRequest();
        producto.setNombre("test");
        producto.setPrecio(100.0F);
        producto.setCosto(50.0F);
        producto.setStock(200);
        GestionDeProductos gestionDeProductos = new GestionDeProductos();
        return gestionDeProductos.addProducto(producto);
    }

    @Test
    public void agregarPromoFija() throws MalaSolicitudException {

        this.escenarioListaVacia.aplicaEscenario1();

        PromoFijaRequest req = new PromoFijaRequest();
        req.setDiasPromo(List.of(DayOfWeek.SATURDAY,DayOfWeek.SUNDAY));
        req.setDosPorUno(true);
        req.setDtoPorCantidad(false);
        req.setNombre("Test promo");
        req.setActiva(true);

        ProductoDTO producto = crearProducto();
        req.setIdProducto(producto.getId());

        Assert.assertNotNull(gestionDePromos.agregarPromoFija(req));
        this.escenarioListaVacia.borraEscenario();

    }

    @Test(expected = MalaSolicitudException.class)
    public void agregarPromoFijaFail() throws MalaSolicitudException {

        this.escenarioListaNoVacia.aplicaEscenario2();

        PromoFijaRequest req = new PromoFijaRequest();
        req.setDiasPromo(List.of(DayOfWeek.SATURDAY,DayOfWeek.SUNDAY));
        req.setDosPorUno(true);
        req.setDtoPorCantidad(false);
        req.setNombre("Promo1");
        req.setActiva(true);

        ProductoDTO producto = crearProducto();
        req.setIdProducto(producto.getId());

        Assert.assertThrows("Deberia lanzar excepcion, promocion presente en la coleccion", MalaSolicitudException.class, () -> gestionDePromos.agregarPromoFija(req));

        this.tearDown2();
    }

    @Test(expected = MalaSolicitudException.class)
    public void agregarPromoFijaFalla() throws MalaSolicitudException {

        this.escenarioListaVacia.aplicaEscenario1();
        PromoFijaRequest request = new PromoFijaRequest();
        request.setDiasPromo(new ArrayList<>()); //Deberia fallar por no tener dias
        request.setDosPorUno(true);
        request.setDtoPorCantidad(false);
        request.setNombre("Test promo");
        request.setActiva(true);

        ProductoDTO producto = crearProducto();
        request.setIdProducto(producto.getId());

        gestionDePromos.agregarPromoFija(request);
        this.escenarioListaVacia.borraEscenario();
    }

    @Test(expected = MalaSolicitudException.class)
    public void agregarPromoFijaFalla2() throws MalaSolicitudException {

        this.escenarioListaVacia.aplicaEscenario1();
        PromoFijaRequest request = new PromoFijaRequest();
        request.setDiasPromo(List.of(DayOfWeek.SATURDAY,DayOfWeek.SUNDAY));
        request.setDosPorUno(false); //Deberia fallar por no tener ningun tipo de promo
        request.setDtoPorCantidad(false);
        request.setNombre("Test promo");
        request.setActiva(true);

        ProductoDTO producto = crearProducto();
        request.setIdProducto(producto.getId());

        gestionDePromos.agregarPromoFija(request);
        this.escenarioListaVacia.borraEscenario();
    }

    @Test(expected = MalaSolicitudException.class)
    public void agregarPromoFijaFalla3() throws MalaSolicitudException {

        this.escenarioListaVacia.aplicaEscenario1();
        PromoFijaRequest request = new PromoFijaRequest();
        request.setDiasPromo(List.of(DayOfWeek.SATURDAY,DayOfWeek.SUNDAY));
        request.setDosPorUno(true);
        request.setDtoPorCantidad(true); //Deberia fallar por tener dos tipos de promo
        request.setNombre("Test promo");
        request.setActiva(true);

        ProductoDTO producto = crearProducto();
        request.setIdProducto(producto.getId());

        gestionDePromos.agregarPromoFija(request);
        this.escenarioListaVacia.borraEscenario();
    }

}
