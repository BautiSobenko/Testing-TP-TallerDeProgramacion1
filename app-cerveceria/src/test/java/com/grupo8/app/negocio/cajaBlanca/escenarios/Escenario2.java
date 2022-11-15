package com.grupo8.app.negocio.cajaBlanca.escenarios;

import com.grupo8.app.dto.AddProductoRequest;
import com.grupo8.app.dto.ProductoDTO;
import com.grupo8.app.dto.PromoFijaRequest;
import com.grupo8.app.excepciones.MalaSolicitudException;
import com.grupo8.app.modelo.Empresa;
import com.grupo8.app.modelo.Producto;
import com.grupo8.app.modelo.Promociones.PromocionFija;
import com.grupo8.app.negocio.GestionDeProductos;
import com.grupo8.app.negocio.GestionDePromos;
import com.grupo8.app.wrappers.PromocionesFijasWrapper;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Escenario2 {

    Empresa empresa;
    PromocionesFijasWrapper promocionesFijas;
    Producto producto;

    public Escenario2() {
        empresa = Empresa.getEmpresa();
    }

    public void aplicaEscenario2(){

        this.promocionesFijas = empresa.getPromocionesFijas();

        Set<PromocionFija> promocionesFijas = new HashSet<>();

        empresa.getPromocionesFijas().setPromocionesFijas(promocionesFijas);

        List<DayOfWeek> diasPromo = new ArrayList<>();
        diasPromo.add( DayOfWeek.MONDAY );
        diasPromo.add( DayOfWeek.SUNDAY );
        diasPromo.add( DayOfWeek.TUESDAY );

        this.producto = new Producto(
                "Pizza",
                20,
                10,
                5
        );

        PromocionFija promocionFija = new PromocionFija(
                "Promo1",
                diasPromo,
                producto,
                true,
                false,
                null,
                null
        );

        empresa.getPromocionesFijas().getPromocionesFijas().add(promocionFija);

    }


    public void borraEscenario(){
        empresa.setPromocionesFijas(promocionesFijas);
    }

    public Producto getProducto() {
        return producto;
    }
}
