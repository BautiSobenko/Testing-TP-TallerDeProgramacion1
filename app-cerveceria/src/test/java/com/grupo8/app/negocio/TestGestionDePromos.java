package com.grupo8.app.negocio;

import com.grupo8.app.dto.*;
import com.grupo8.app.excepciones.EntidadNoEncontradaException;
import com.grupo8.app.excepciones.MalaSolicitudException;
import org.junit.Assert;
import org.junit.Test;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class TestGestionDePromos {
    private GestionDePromos gestionDePromos;

    public TestGestionDePromos() {
        this.gestionDePromos = new GestionDePromos();
    }

    @Test
    public void testPromoTempExito() throws MalaSolicitudException {
        PromoTemporalRequest req = new PromoTemporalRequest();
        req.setDiasPromo(List.of(DayOfWeek.MONDAY,DayOfWeek.FRIDAY));
        req.setPorcentajeDescuento(50);
        req.setAcumulable(true);
        req.setFormaPago("Efectivo");
        req.setNombre("Test promo");
        req.setActiva(true);

        Assert.assertNotNull(gestionDePromos.agregarPromoTemporal(req));
    }

    @Test(expected = MalaSolicitudException.class)
    public void testPromoTempFalla() throws MalaSolicitudException {
        PromoTemporalRequest req = new PromoTemporalRequest();
        req.setDiasPromo(new ArrayList<>()); //Deberia fallar por no tener dias
        req.setPorcentajeDescuento(50);
        req.setAcumulable(true);
        req.setFormaPago("Efectivo");
        req.setNombre("Test promo");
        req.setActiva(true);

        gestionDePromos.agregarPromoTemporal(req);
    }

    @Test(expected = MalaSolicitudException.class)
    public void testPromoTempFalla2() throws MalaSolicitudException {
        PromoTemporalRequest req = new PromoTemporalRequest();
        req.setDiasPromo(List.of(DayOfWeek.MONDAY,DayOfWeek.FRIDAY));
        req.setPorcentajeDescuento(0); //Deberia fallar por tener porcentaje 0
        req.setAcumulable(true);
        req.setFormaPago("Efectivo");
        req.setNombre("Test promo");
        req.setActiva(true);

        gestionDePromos.agregarPromoTemporal(req);
    }

    @Test
    public void testEditarPromoTemporal() throws MalaSolicitudException, EntidadNoEncontradaException {
        PromoTemporalRequest req = new PromoTemporalRequest();
        req.setDiasPromo(List.of(DayOfWeek.MONDAY,DayOfWeek.FRIDAY));
        req.setPorcentajeDescuento(50);
        req.setAcumulable(true);
        req.setFormaPago("Efectivo");
        req.setNombre("Test promo");
        req.setActiva(true);
        PromoTemporalDTO promo = gestionDePromos.agregarPromoTemporal(req);
        Assert.assertNotNull(promo);

        promo.setPorcentajeDescuento(25);
        promo.setNombre("Test promo editada");

        req = PromoTemporalRequest.of(promo);
        Assert.assertNotNull(gestionDePromos.editarPromoTemporal(req, promo.getIdPromocion()));
    }

    @Test(expected = EntidadNoEncontradaException.class)
    public void testEditarPromoTemporalFalla() throws MalaSolicitudException, EntidadNoEncontradaException {
        PromoTemporalRequest req = new PromoTemporalRequest();
        req.setDiasPromo(List.of(DayOfWeek.MONDAY,DayOfWeek.FRIDAY));
        req.setPorcentajeDescuento(50);
        req.setAcumulable(true);
        req.setFormaPago("Efectivo");
        req.setNombre("Test promo");
        req.setActiva(true);
        PromoTemporalDTO promo = gestionDePromos.agregarPromoTemporal(req);
        Assert.assertNotNull(promo);

        promo.setPorcentajeDescuento(25);
        promo.setNombre("Test promo editada");

        req = PromoTemporalRequest.of(promo);
        gestionDePromos.editarPromoTemporal(req, "idFailTest"); //Deberia fallar por no encontrar la promo
    }

    @Test(expected = MalaSolicitudException.class)
    public void testEditarPromoTemporalFalla2() throws MalaSolicitudException, EntidadNoEncontradaException {
        PromoTemporalRequest req = new PromoTemporalRequest();
        req.setDiasPromo(List.of(DayOfWeek.MONDAY,DayOfWeek.FRIDAY));
        req.setPorcentajeDescuento(50);
        req.setAcumulable(true);
        req.setFormaPago("Efectivo");
        req.setNombre("Test promo");
        req.setActiva(true);
        PromoTemporalDTO promo = gestionDePromos.agregarPromoTemporal(req);
        Assert.assertNotNull(promo);

        promo.setPorcentajeDescuento(0); //Deberia fallar por tener porcentaje 0
        promo.setNombre("Test promo editada");

        req = PromoTemporalRequest.of(promo);
        gestionDePromos.editarPromoTemporal(req, promo.getIdPromocion());
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

        PromoFijaRequest req = new PromoFijaRequest();
        req.setDiasPromo(List.of(DayOfWeek.SATURDAY,DayOfWeek.SUNDAY));
        req.setDosPorUno(true);
        req.setDtoPorCantidad(false);
        req.setNombre("Test promo");
        req.setActiva(true);

        ProductoDTO producto = crearProducto();
        req.setIdProducto(producto.getId());

        Assert.assertNotNull(gestionDePromos.agregarPromoFija(req));
    }

    @Test(expected = MalaSolicitudException.class)
    public void agregarPromoFijaFalla() throws MalaSolicitudException {
        PromoFijaRequest request = new PromoFijaRequest();
        request.setDiasPromo(new ArrayList<>()); //Deberia fallar por no tener dias
        request.setDosPorUno(true);
        request.setDtoPorCantidad(false);
        request.setNombre("Test promo");
        request.setActiva(true);

        ProductoDTO producto = crearProducto();
        request.setIdProducto(producto.getId());

        gestionDePromos.agregarPromoFija(request);
    }

    @Test(expected = MalaSolicitudException.class)
    public void agregarPromoFijaFalla2() throws MalaSolicitudException {
        PromoFijaRequest request = new PromoFijaRequest();
        request.setDiasPromo(List.of(DayOfWeek.SATURDAY,DayOfWeek.SUNDAY));
        request.setDosPorUno(false); //Deberia fallar por no tener ningun tipo de promo
        request.setDtoPorCantidad(false);
        request.setNombre("Test promo");
        request.setActiva(true);

        ProductoDTO producto = crearProducto();
        request.setIdProducto(producto.getId());

        gestionDePromos.agregarPromoFija(request);
    }

    @Test(expected = MalaSolicitudException.class)
    public void agregarPromoFijaFalla3() throws MalaSolicitudException {
        PromoFijaRequest request = new PromoFijaRequest();
        request.setDiasPromo(List.of(DayOfWeek.SATURDAY,DayOfWeek.SUNDAY));
        request.setDosPorUno(true);
        request.setDtoPorCantidad(true); //Deberia fallar por tener dos tipos de promo
        request.setNombre("Test promo");
        request.setActiva(true);

        ProductoDTO producto = crearProducto();
        request.setIdProducto(producto.getId());

        gestionDePromos.agregarPromoFija(request);
    }


    @Test
    public void testEditarPromoFija() throws MalaSolicitudException, EntidadNoEncontradaException {
        PromoFijaRequest req = new PromoFijaRequest();
        req.setDiasPromo(List.of(DayOfWeek.SATURDAY,DayOfWeek.SUNDAY));
        req.setDosPorUno(true);
        req.setDtoPorCantidad(false);
        req.setNombre("Test promo");
        req.setActiva(true);

        ProductoDTO producto = crearProducto();
        req.setIdProducto(producto.getId());

        PromoFijaDTO promo = gestionDePromos.agregarPromoFija(req);
        Assert.assertNotNull(promo);

        promo.setDosPorUno(false);
        promo.setDtoPorCant(true);
        promo.setNombre("Test promo editada");

        req = PromoFijaRequest.of(promo);
        Assert.assertNotNull(gestionDePromos.editarPromoFija(req, promo.getIdPromocion()));
    }


    @Test(expected = MalaSolicitudException.class)
    public void testEditarPromoFijaFalla() throws EntidadNoEncontradaException, MalaSolicitudException {
        PromoFijaRequest req = new PromoFijaRequest();
        req.setDiasPromo(List.of(DayOfWeek.SATURDAY,DayOfWeek.SUNDAY));
        req.setDosPorUno(true);
        req.setDtoPorCantidad(false);
        req.setNombre("Test promo");
        req.setActiva(true);

        ProductoDTO producto = crearProducto();
        req.setIdProducto(producto.getId());

        PromoFijaDTO promo = gestionDePromos.agregarPromoFija(req);
        Assert.assertNotNull(promo);

        promo.setDosPorUno(true);
        promo.setDtoPorCant(true); //Deberia fallar por tener dos tipos de promo
        promo.setNombre("Test promo editada");

        req = PromoFijaRequest.of(promo);
        Assert.assertNotNull(gestionDePromos.editarPromoFija(req, promo.getIdPromocion()));
    }

    @Test(expected = MalaSolicitudException.class)
    public void testEditarPromoFijaFalla2() throws EntidadNoEncontradaException, MalaSolicitudException {
        PromoFijaRequest req = new PromoFijaRequest();
        req.setDiasPromo(List.of(DayOfWeek.SATURDAY,DayOfWeek.SUNDAY));
        req.setDosPorUno(true);
        req.setDtoPorCantidad(false);
        req.setNombre("Test promo");
        req.setActiva(true);

        ProductoDTO producto = crearProducto();
        req.setIdProducto("fallar"); //Deberia fallar por no tener un id de producto valido

        PromoFijaDTO promo = gestionDePromos.agregarPromoFija(req);
        Assert.assertNotNull(promo);

        promo.setDosPorUno(false);
        promo.setDtoPorCant(true);
        promo.setNombre("Test promo editada");

        req = PromoFijaRequest.of(promo);
        Assert.assertNotNull(gestionDePromos.editarPromoFija(req, promo.getIdPromocion()));
    }

    @Test
    public void testEliminarPromoFija() throws MalaSolicitudException, EntidadNoEncontradaException {
        PromoFijaRequest req = new PromoFijaRequest();
        req.setDiasPromo(List.of(DayOfWeek.SATURDAY,DayOfWeek.SUNDAY));
        req.setDosPorUno(true);
        req.setDtoPorCantidad(false);
        req.setNombre("Test promo");
        req.setActiva(true);

        ProductoDTO producto = crearProducto();
        req.setIdProducto(producto.getId());

        PromoFijaDTO promo = gestionDePromos.agregarPromoFija(req);
        Assert.assertNotNull(promo);

        Assert.assertTrue(gestionDePromos.eliminarPromoFija(promo.getIdPromocion()));
    }

    @Test(expected = EntidadNoEncontradaException.class)
    public void testEliminarPromoFijaFalla() throws EntidadNoEncontradaException {
        Assert.assertTrue(gestionDePromos.eliminarPromoFija("fallar"));
    }

}
