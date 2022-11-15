package com.grupo8.app.negocio;

import com.grupo8.app.dto.*;
import com.grupo8.app.excepciones.EntidadNoEncontradaException;
import com.grupo8.app.excepciones.EstadoInvalidoException;
import com.grupo8.app.excepciones.MalaSolicitudException;
import com.grupo8.app.excepciones.NumeroMesaInvalidoException;
import com.grupo8.app.modelo.Empresa;
import com.grupo8.app.modelo.Producto;
import org.junit.Assert;
import org.junit.Test;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TestGestionDeMesas {
    private GestionDeMesas gestionDeMesas;

    public TestGestionDeMesas() {
        gestionDeMesas = new GestionDeMesas();
    }

    @Test
    public void testAddMesa() throws NumeroMesaInvalidoException, EntidadNoEncontradaException, MalaSolicitudException {
        Assert.assertNotNull(crearMesa());
    }

    @Test(expected = NumeroMesaInvalidoException.class)
    public void testAddMesaNumeroInvalido() throws NumeroMesaInvalidoException, MalaSolicitudException {
        AddMesaRequest addMesaRequest = new AddMesaRequest();
        addMesaRequest.setNroMesa(1);
        addMesaRequest.setCantSillas(4);

        gestionDeMesas.addMesa(addMesaRequest);

        gestionDeMesas.addMesa(addMesaRequest); //deberia fallar porque ya existe una mesa con ese numero
    }

    @Test
    public void testDeleteMesa() throws NumeroMesaInvalidoException, EntidadNoEncontradaException, MalaSolicitudException {
        crearMesa();
        Assert.assertTrue(gestionDeMesas.deleteMesa(1000));
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
    private void crearPromociones() throws MalaSolicitudException {
        PromoFijaRequest req = new PromoFijaRequest();
        req.setDiasPromo(Arrays.stream(DayOfWeek.values()).collect(Collectors.toList()));
        req.setDosPorUno(true);
        req.setDtoPorCantidad(false);
        req.setNombre("Test promo");
        req.setActiva(true);

        ProductoDTO producto = crearProducto();
        req.setIdProducto(producto.getId());
        GestionDePromos gestionDePromos = new GestionDePromos();
        gestionDePromos.agregarPromoFija(req);

        req.setDtoPorCantidad(true);
        req.setDtoPorCantMin(3);
        req.setDtoPorCantPrecioU(50.0);
        req.setDosPorUno(false);
        req.setNombre("Test promo 2");
        gestionDePromos.agregarPromoFija(req);
    }
    @Test
    public void iniciarTurno() throws EstadoInvalidoException, MalaSolicitudException {
        crearPromociones(); //Necesito al menos dos promos para poder iniciar un turno
        gestionDeMesas.iniciarTurno();
    }

    private void borrarPromociones() throws MalaSolicitudException {
        GestionDePromos gestionDePromos = new GestionDePromos();
        List<PromocionDTO> promos = gestionDePromos.obtenerPromos();
        for (PromocionDTO promo : promos) {
            gestionDePromos.eliminarPromo(promo.getIdPromocion());
        }
    }

    @Test(expected = EstadoInvalidoException.class)
    public void iniciarTurnoFalla() throws EstadoInvalidoException, MalaSolicitudException {
        borrarPromociones();
        gestionDeMesas.iniciarTurno(); //no creo las promociones antes
    }

    private MozoDTO crearMozo() {
        GestionDeUsuarios gestionDeUsuarios = new GestionDeUsuarios();
        return gestionDeUsuarios.addMozo(new AddMozoRequest("TEST", new Date(), 0));
    }

    private void borrarMozo() {
        GestionDeUsuarios gestionDeUsuarios = new GestionDeUsuarios();
        List<MozoDTO> mozos = gestionDeUsuarios.obtenerMozos();
        for (MozoDTO mozo : mozos) {
            if (mozo.getNombreCompleto().equals("TEST")) {
                gestionDeUsuarios.deleteMozo(mozo);
            }
        }
    }
    private MesaDTO crearMesa() throws NumeroMesaInvalidoException, EntidadNoEncontradaException, MalaSolicitudException {
        borrarMesa(); //borro la mesa por si ya existe
        AddMesaRequest addMesaRequest = new AddMesaRequest();
        addMesaRequest.setNroMesa(1000);
        addMesaRequest.setCantSillas(4);
        addMesaRequest.setMozoAsignado(crearMozo());
        MesaDTO mesa = gestionDeMesas.addMesa(addMesaRequest);
        borrarMozo();

        return mesa;
    }

    private void borrarMesa() throws NumeroMesaInvalidoException {
        gestionDeMesas.deleteMesa(1000);
    }

    @Test
    public void testCrearComanda() throws NumeroMesaInvalidoException, EntidadNoEncontradaException, EstadoInvalidoException, MalaSolicitudException {
        crearMesa();
        ComandaDTO comanda = gestionDeMesas.crearComanda(1000);
        Assert.assertNotNull(comanda);
        borrarMesa();
    }

    @Test(expected = EstadoInvalidoException.class)
    public void testCrearComandaFalla() throws NumeroMesaInvalidoException, EntidadNoEncontradaException, EstadoInvalidoException, MalaSolicitudException {
        crearMesa();
        gestionDeMesas.crearComanda(1000);
        gestionDeMesas.crearComanda(1000); //deberia fallar porque la mesa ya se ocupo
    }

    @Test
    public void testAgregarPedidoAComanda() throws NumeroMesaInvalidoException, EntidadNoEncontradaException, EstadoInvalidoException, MalaSolicitudException {
        crearMesa();

        ComandaDTO comanda = gestionDeMesas.crearComanda(1000);
        Producto producto = new Producto("Test", 100.0F, 50.0F, 200);
        Empresa.getEmpresa().getProductos().getProductos().add(producto);
        PedidoRequest addPedidoRequest = new PedidoRequest();
        addPedidoRequest.setCantidad(2);
        addPedidoRequest.setIdProducto(producto.getId());
        addPedidoRequest.setIdComanda(comanda.getId());
        gestionDeMesas.agregarPedidoAComanda(addPedidoRequest);

        borrarMesa();
    }

    private ComandaDTO crearYAsignarComanda() throws EntidadNoEncontradaException, NumeroMesaInvalidoException, EstadoInvalidoException, MalaSolicitudException {
        crearMesa();

        ComandaDTO comanda = gestionDeMesas.crearComanda(1000);
        Producto producto = new Producto("Test", 100.0F, 50.0F, 200);
        Empresa.getEmpresa().getProductos().getProductos().add(producto);
        PedidoRequest addPedidoRequest = new PedidoRequest();
        addPedidoRequest.setCantidad(2);
        addPedidoRequest.setIdProducto(producto.getId());
        addPedidoRequest.setIdComanda(comanda.getId());
        gestionDeMesas.agregarPedidoAComanda(addPedidoRequest);

        return comanda;
    }

    @Test
    public void testCerrarComanda() throws NumeroMesaInvalidoException, EntidadNoEncontradaException, EstadoInvalidoException, MalaSolicitudException {
        ComandaDTO comanda = crearYAsignarComanda();
        gestionDeMesas.cerrarComanda(comanda.getId(), "Efectivo");
        borrarMesa();
    }

    @Test
    public void testCerrarTurno() throws NumeroMesaInvalidoException, EntidadNoEncontradaException, EstadoInvalidoException {
        gestionDeMesas.obtenerComandas().forEach(c -> {
            try {
                gestionDeMesas.cerrarComanda(c.getId(), "Efectivo");
            } catch (EntidadNoEncontradaException ex) {}
        });
        gestionDeMesas.cerrarTurno();
        borrarMesa();
    }

    @Test(expected = EstadoInvalidoException.class)
    public void testCerrarTurnoFallo() throws EntidadNoEncontradaException, NumeroMesaInvalidoException, EstadoInvalidoException, MalaSolicitudException {
        ComandaDTO comanda = crearYAsignarComanda();
        gestionDeMesas.cerrarTurno();
    }
}
