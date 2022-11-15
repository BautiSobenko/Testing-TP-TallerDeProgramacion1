package com.grupo8.app.testIntegracion;

import com.grupo8.app.dto.*;
import com.grupo8.app.excepciones.EntidadNoEncontradaException;
import com.grupo8.app.excepciones.EstadoInvalidoException;
import com.grupo8.app.excepciones.MalaSolicitudException;
import com.grupo8.app.excepciones.NumeroMesaInvalidoException;
import com.grupo8.app.modelo.CierreComanda;
import com.grupo8.app.modelo.Empresa;
import com.grupo8.app.modelo.Pedido;
import com.grupo8.app.modelo.Producto;
import com.grupo8.app.negocio.GestionDeMesas;
import com.grupo8.app.negocio.GestionDeUsuarios;
import com.grupo8.app.tipos.EstadoComanda;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.Assert.*;

public class testIntegracion {

    private GestionDeMesas gestionDeMesas;

    private GestionDeUsuarios gestionDeUsuarios;

    private Empresa empresa;

    public testIntegracion() {

        gestionDeUsuarios = new GestionDeUsuarios();
        gestionDeMesas = new GestionDeMesas();
        empresa = Empresa.getEmpresa();
    }

    @After
    public void tearDown(){
        List<MozoDTO> mozos = gestionDeUsuarios.obtenerMozos();//Se elimina el mozo
        for (MozoDTO mozo : mozos) {
            if (mozo.getNombreCompleto().equals("TEST")) {
                gestionDeUsuarios.deleteMozo(mozo);
            }
        }

        gestionDeMesas.deleteMesa(1002); //Se elimina la mesa
    }
   @Test
    public void testCrearyAsignar(){

        AddMozoRequest mozoReq = new AddMozoRequest("TEST", new Date(), 0);
        MozoDTO mozoDTO = gestionDeUsuarios.addMozo(mozoReq);

       AddMesaRequest addMesaRequest = new AddMesaRequest();
       addMesaRequest.setNroMesa(1002);
       addMesaRequest.setCantSillas(4);
       addMesaRequest.setMozoAsignado(mozoDTO);
       MesaDTO mesa = null;
       try {
           mesa = gestionDeMesas.addMesa(addMesaRequest);
       } catch (NumeroMesaInvalidoException e) {
           fail("No deberia arrojar excepcion");
       } catch (MalaSolicitudException e) {
           fail("No deberia arrojar excepcion");
       }

       assertEquals("El mozo asignado deberia ser el creado",mesa.getMozoAsignado().getId(),mozoDTO.getId());

    }

    @Test
    public void testFlujoComanda(){

        AddMozoRequest mozoReq = new AddMozoRequest("TEST", new Date(), 0);
        MozoDTO mozoDTO = gestionDeUsuarios.addMozo(mozoReq);

        AddMesaRequest addMesaRequest = new AddMesaRequest();
        addMesaRequest.setNroMesa(1002);
        addMesaRequest.setCantSillas(4);
        addMesaRequest.setMozoAsignado(mozoDTO);

        try {
            MesaDTO mesa = gestionDeMesas.addMesa(addMesaRequest);
            try {

                ComandaDTO comandaDTO = gestionDeMesas.crearComanda(1002);
                Producto producto = new Producto("Banana",30,10,15);
                Empresa.getEmpresa().getProductos().getProductos().add(producto);
                PedidoRequest addPedidoRequest = new PedidoRequest();
                addPedidoRequest.setCantidad(2);
                addPedidoRequest.setIdProducto(producto.getId());
                addPedidoRequest.setIdComanda(comandaDTO.getId());
                gestionDeMesas.agregarPedidoAComanda(addPedidoRequest);

                gestionDeMesas.cerrarComanda(comandaDTO.getId(), "Efectivo");

                Optional<CierreComanda> cierre = empresa.getCierreComandas().getCierreComandas().stream()
                        .filter(x -> Objects.equals(x.getId(), comandaDTO.getId())).findFirst();
                System.out.println(comandaDTO.getId());
                for(CierreComanda x : empresa.getCierreComandas().getCierreComandas()){
                    System.out.println(x.getId());
                }
                Assert.assertEquals("La comanda deberia estar cerrada",cierre.get().getEstadoPedido(), EstadoComanda.CERRADA);
                Assert.assertEquals("El precio deberia ser 30*2=60",comandaDTO.getSubtotal(),60,0.5);

            } catch (EntidadNoEncontradaException e) {
                Assert.fail("No deberia arrojar excepcion EntidadNoEncontrada");
            } catch (EstadoInvalidoException e) {
                Assert.fail("No deberia arrojar excepcion EstadoInvalido");
            }
        } catch (NumeroMesaInvalidoException e) {
            Assert.fail("No deberia arrojar excepcion NumeroMesaInvalido");
        } catch (MalaSolicitudException e) {
            Assert.fail("No deberia arrojar excepcion MalaSolicitud");
        }


    }
}
