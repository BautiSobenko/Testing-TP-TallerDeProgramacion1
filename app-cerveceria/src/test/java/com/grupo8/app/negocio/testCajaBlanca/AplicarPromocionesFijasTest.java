package com.grupo8.app.negocio.testCajaBlanca;

import com.grupo8.app.modelo.*;
import com.grupo8.app.negocio.GestionDeMesas;
import com.grupo8.app.negocio.testCajaBlanca.escenarios.Escenario1;
import com.grupo8.app.negocio.testCajaBlanca.escenarios.Escenario2;
import com.grupo8.app.negocio.testCajaBlanca.escenarios.Escenario3;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AplicarPromocionesFijasTest {

    Escenario1 escenarioSinPromo;
    Escenario2 escenarioPromo2x1;
    Escenario3 escenarioPromoCant;
    GestionDeMesas gestionDeMesas;


    @Before
    public void setUp() throws Exception {
        escenarioSinPromo = new Escenario1();
        escenarioPromo2x1 = new Escenario2();
        escenarioPromoCant = new Escenario3();
        gestionDeMesas = new GestionDeMesas();
    }

    @After
    public void tearDown() throws Exception {

    }

    /*
    public class Comanda implements Serializable {
      private String id;
      private List<Pedido> pedidos;
      private EstadoComanda estadoPedido;
      private Date apertura;
      private Date cierre;
      private Mesa mesa;
     */

    @Test
    public void C1() {
        this.escenarioPromoCant.aplicaEscenario3();

        Mesa mesa = new Mesa(1 , 2);
        Comanda comanda = new Comanda(mesa);
        Pedido pedido1 = new Pedido(this.escenarioPromoCant.getProducto(), 4);
        Pedido pedido2 = new Pedido(new Producto("Fideos", 20, 10, 20), 1);
        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(pedido1);
        pedidos.add(pedido2);
        comanda.setPedidos(pedidos);

        CierreComanda cierreComanda = new CierreComanda(comanda);
        assertTrue("Se deberia haber aplicado promocion", gestionDeMesas.aplicarPromocionesFijas(cierreComanda));

        this.escenarioPromoCant.borraEscenario();

    }

    @Test
    public void C2() {

        this.escenarioPromoCant.aplicaEscenario3();

        Mesa mesa = new Mesa(1 , 2);
        Comanda comanda = new Comanda(mesa);
        Pedido pedido1 = new Pedido(this.escenarioPromoCant.getProducto(), 2);
        Pedido pedido2 = new Pedido(new Producto("Fideos", 20, 10, 20), 1);
        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(pedido1);
        pedidos.add(pedido2);
        comanda.setPedidos(pedidos);

        System.out.println(Empresa.getEmpresa().getPromocionesFijas().getPromocionesFijas());

        CierreComanda cierreComanda = new CierreComanda(comanda);
        assertFalse("No se deberia haber aplicado promocion", gestionDeMesas.aplicarPromocionesFijas(cierreComanda));

        this.escenarioPromoCant.borraEscenario();

    }

    @Test
    public void C3() {

        this.escenarioPromo2x1.aplicaEscenario2();

        Mesa mesa = new Mesa(1 , 2);
        Comanda comanda = new Comanda(mesa);
        Pedido pedido1 = new Pedido(this.escenarioPromo2x1.getProducto(), 3);
        Pedido pedido2 = new Pedido(new Producto("Fideos", 20, 10, 20), 1);
        comanda.getPedidos().add(pedido1);
        comanda.getPedidos().add(pedido2);

        CierreComanda cierreComanda = new CierreComanda(comanda);
        assertTrue("Deberia haber aplicado promocion", gestionDeMesas.aplicarPromocionesFijas(cierreComanda));

        this.escenarioPromo2x1.borraEscenario();

    }

    @Test
    public void C4() {

        this.escenarioPromoCant.aplicaEscenario3();

        Mesa mesa = new Mesa(1 , 2);
        Comanda comanda = new Comanda(mesa);
        Producto prodDiferente = new Producto( "Sopa", 20, 10, 5 );
        Pedido pedido1 = new Pedido( prodDiferente, 3);
        Pedido pedido2 = new Pedido(new Producto("Fideos", 20, 10, 20), 1);
        comanda.getPedidos().add(pedido1);
        comanda.getPedidos().add(pedido2);

        CierreComanda cierreComanda = new CierreComanda(comanda);
        assertFalse("No deberia haber aplicado promocion", gestionDeMesas.aplicarPromocionesFijas(cierreComanda));

        this.escenarioPromoCant.borraEscenario();

    }

    @Test
    public void C5() {

        this.escenarioSinPromo.aplicaEscenario1();

        Mesa mesa = new Mesa(1 , 2);
        Comanda comanda = new Comanda(mesa);
        Producto prodDiferente = new Producto( "Pizza", 20, 10, 5 );
        Pedido pedido1 = new Pedido( prodDiferente, 3);
        Pedido pedido2 = new Pedido(new Producto("Fideos", 20, 10, 20), 1);
        comanda.getPedidos().add(pedido1);
        comanda.getPedidos().add(pedido2);

        CierreComanda cierreComanda = new CierreComanda(comanda);
        assertFalse("No deberia haber aplicado promocion", gestionDeMesas.aplicarPromocionesFijas(cierreComanda));

        this.escenarioSinPromo.borraEscenario();

    }
}
